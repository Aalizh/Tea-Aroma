package cn.foggyhillside.tea_aroma.blocks;

import cn.foggyhillside.tea_aroma.registry.ModBlockStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraftforge.registries.ForgeRegistries;

public class TABlock extends TeaBlock {
    public static final BooleanProperty WITH_HONEY = ModBlockStateProperties.WITH_HONEY;
    public static final BooleanProperty WITH_SUGAR = ModBlockStateProperties.WITH_SUGAR;

    public TABlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(WITH_HONEY, false).setValue(WITH_SUGAR, false));
    }

    protected void pick(Level level, BlockState state, BlockPos pos, Player player, InteractionHand hand) {
        if (!level.isClientSide()) {
            Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(ForgeRegistries.BLOCKS.getKey(level.getBlockState(pos).getBlock()).toString()));
            ItemStack stack = new ItemStack(item);
            stack.getOrCreateTagElement("BlockStateTag").putBoolean(WITH_HONEY.getName(), state.getValue(WITH_HONEY));
            stack.getOrCreateTagElement("BlockStateTag").putBoolean(WITH_SUGAR.getName(), state.getValue(WITH_SUGAR));
            player.setItemInHand(hand, stack);
            level.removeBlock(pos, false);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WITH_HONEY);
        builder.add(WITH_SUGAR);
        super.createBlockStateDefinition(builder);
    }
}
