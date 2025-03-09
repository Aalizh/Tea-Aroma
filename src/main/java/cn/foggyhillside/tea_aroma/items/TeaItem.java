package cn.foggyhillside.tea_aroma.items;

import cn.foggyhillside.tea_aroma.TeaAroma;
import cn.foggyhillside.tea_aroma.blocks.TABlock;
import cn.foggyhillside.tea_aroma.blocks.TATeaBlock;
import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TeaItem extends BlockItem {
    boolean isLatte;

    public TeaItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
        this.isLatte = false;
    }

    public TeaItem(Block pBlock, Properties pProperties, boolean pIsLatte) {
        super(pBlock, pProperties);
        this.isLatte = pIsLatte;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 32;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    @Override
    public InteractionResult place(BlockPlaceContext pContext) {
        ItemStack heldStack = pContext.getItemInHand().copy();
        InteractionResult interactionResult = super.place(pContext);
        BlockPos pos = pContext.getClickedPos();
        Level level = pContext.getLevel();
        BlockState state = level.getBlockState(pos);
        if (level.getBlockState(pos).getBlock() instanceof TABlock) {
            if (!state.getValue(TATeaBlock.WITH_HONEY).equals(heldStack.getOrCreateTagElement("BlockStateTag").getBoolean("with_honey"))) {
                level.setBlockAndUpdate(pos, state.setValue(TATeaBlock.WITH_HONEY, heldStack.getOrCreateTagElement("BlockStateTag").getBoolean("with_honey")));
                state = level.getBlockState(pos);
            }
            if (!state.getValue(TATeaBlock.WITH_SUGAR).equals(heldStack.getOrCreateTagElement("BlockStateTag").getBoolean("with_sugar"))) {
                level.setBlockAndUpdate(pos, state.setValue(TATeaBlock.WITH_SUGAR, heldStack.getOrCreateTagElement("BlockStateTag").getBoolean("with_sugar")));
            }
        }
        return interactionResult;
    }

    @Override
    protected boolean canPlace(BlockPlaceContext pContext, BlockState pState) {
        if (pContext.getPlayer() != null && pContext.getPlayer().isShiftKeyDown()) {
            return super.canPlace(pContext, pState);
        }
        return false;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        if (isLatte) {
            Iterator<MobEffectInstance> itr = pLivingEntity.getActiveEffects().iterator();
            ArrayList<MobEffect> compatibleEffects = new ArrayList<>();

            while (itr.hasNext()) {
                MobEffectInstance effect = itr.next();
                if (effect.isCurativeItem(new ItemStack(Items.MILK_BUCKET))) {
                    compatibleEffects.add(effect.getEffect());
                }
            }

            if (!compatibleEffects.isEmpty()) {
                MobEffectInstance selectedEffect = pLivingEntity.getEffect(compatibleEffects.get(pLevel.random.nextInt(compatibleEffects.size())));
                if (selectedEffect != null && !MinecraftForge.EVENT_BUS.post(new MobEffectEvent.Remove(pLivingEntity, selectedEffect))) {
                    pLivingEntity.removeEffect(selectedEffect.getEffect());
                }
            }
        }

        if (pStack.getOrCreateTagElement("BlockStateTag").getBoolean(TABlock.WITH_HONEY.getName())) {
            if (!pLevel.isClientSide) {
                pLivingEntity.removeEffect(MobEffects.POISON);
            }
        }

        if (pStack.getOrCreateTagElement("BlockStateTag").getBoolean(TABlock.WITH_SUGAR.getName())) {
            if (!pLevel.isClientSide) {
                pLivingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 25 * 20, 0));
            }
        }

        ItemStack containerStack = pStack.getCraftingRemainingItem();
        super.finishUsingItem(pStack, pLevel, pLivingEntity);
        if (pStack.isEmpty()) {
            return containerStack;
        } else {
            if (pLivingEntity instanceof Player player) {
                if (!player.getAbilities().instabuild && !player.getInventory().add(containerStack)) {
                    player.drop(containerStack, false);
                }
            }

            return pStack;
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
        if (pStack.getOrCreateTagElement("BlockStateTag").getBoolean(TABlock.WITH_HONEY.getName())) {
            pTooltip.add(Component.translatable("%s.%s".formatted(TeaAroma.MODID, "tooltip.tea.honey")).withStyle(ChatFormatting.GRAY));
        }
        if (pStack.getOrCreateTagElement("BlockStateTag").getBoolean(TABlock.WITH_SUGAR.getName())) {
            pTooltip.add(Component.translatable("%s.%s".formatted(TeaAroma.MODID, "tooltip.tea.sugar")).withStyle(ChatFormatting.GRAY));
        }
        FoodProperties foodStats = pStack.getFoodProperties(null);
        if (foodStats == null) {
            return;
        }
        List<Pair<MobEffectInstance, Float>> effectList = foodStats.getEffects();
        List<Pair<Attribute, AttributeModifier>> attributeList = Lists.newArrayList();
        if (!effectList.isEmpty()) {
            for (Pair<MobEffectInstance, Float> effectPair : effectList) {
                MobEffectInstance instance = effectPair.getFirst();
                MutableComponent iformattabletextcomponent = Component.translatable(instance.getDescriptionId());
                MobEffect effect = instance.getEffect();
                Map<Attribute, AttributeModifier> attributeMap = effect.getAttributeModifiers();
                if (!attributeMap.isEmpty()) {
                    for (Map.Entry<Attribute, AttributeModifier> entry : attributeMap.entrySet()) {
                        AttributeModifier rawModifier = entry.getValue();
                        AttributeModifier modifier = new AttributeModifier(rawModifier.getName(), effect.getAttributeModifierValue(instance.getAmplifier(), rawModifier), rawModifier.getOperation());
                        attributeList.add(new Pair<>(entry.getKey(), modifier));
                    }
                }

                if (instance.getAmplifier() > 0) {
                    iformattabletextcomponent = Component.translatable("potion.withAmplifier", iformattabletextcomponent, Component.translatable("potion.potency." + instance.getAmplifier()));
                }

                if (instance.getDuration() > 20) {
                    iformattabletextcomponent = Component.translatable("potion.withDuration", iformattabletextcomponent, MobEffectUtil.formatDuration(instance, 1.0F));
                }

                pTooltip.add(iformattabletextcomponent.withStyle(effect.getCategory().getTooltipFormatting()));
            }
        }

        if (!attributeList.isEmpty()) {
            pTooltip.add(CommonComponents.EMPTY);
            pTooltip.add((Component.translatable("potion.whenDrank")).withStyle(ChatFormatting.DARK_PURPLE));

            for (Pair<Attribute, AttributeModifier> pair : attributeList) {
                AttributeModifier modifier = pair.getSecond();
                double amount = modifier.getAmount();
                double formattedAmount;
                if (modifier.getOperation() != AttributeModifier.Operation.MULTIPLY_BASE && modifier.getOperation() != AttributeModifier.Operation.MULTIPLY_TOTAL) {
                    formattedAmount = modifier.getAmount();
                } else {
                    formattedAmount = modifier.getAmount() * 100.0D;
                }

                if (amount > 0.0D) {
                    pTooltip.add((Component.translatable("attribute.modifier.plus." + modifier.getOperation().toValue(), ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(formattedAmount), Component.translatable(pair.getFirst().getDescriptionId()))).withStyle(ChatFormatting.BLUE));
                } else if (amount < 0.0D) {
                    formattedAmount = formattedAmount * -1.0D;
                    pTooltip.add((Component.translatable("attribute.modifier.take." + modifier.getOperation().toValue(), ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(formattedAmount), Component.translatable(pair.getFirst().getDescriptionId()))).withStyle(ChatFormatting.RED));
                }
            }
        }

    }
}
