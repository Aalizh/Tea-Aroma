package cn.foggyhillside.tea_aroma;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;

public class ModCompat {

    public static void teapotPour(ItemStack stack, Player player, InteractionHand hand){
        if (stack.getDamageValue() < stack.getMaxDamage() - 1) {
            stack.hurtAndBreak(1, player, (user) -> user.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        } else {
            if (stack.hurt(1, player.getRandom(), player instanceof ServerPlayer ? (ServerPlayer) player : null)) {
                stack.shrink(1);
                player.setItemInHand(hand, ModCompat.getTeapot() != null ? new ItemStack(ModCompat.getTeapot()) : ItemStack.EMPTY);
            }
        }
    }

    public static Item getTeaStick(){
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation("simplytea", "tea_stick"));
    }

    public static Item getIceCube(){
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation("simplytea", "ice_cube"));
    }

    public static Item getTeapot(){
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation("simplytea", "teapot"));
    }

    public static Item getTeapotHot(){
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation("simplytea", "teapot_hot"));
    }

    public static Item getTeapotFrothed(){
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation("simplytea", "teapot_frothed"));
    }
    
    public static Item blockToSTItem(Level level, BlockPos pos){
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation("simplytea", ForgeRegistries.BLOCKS.getKey(level.getBlockState(pos).getBlock()).getPath()));
    }

    public static boolean isFestivalDelicaciesLoaded() {
        return ModList.get().isLoaded("festival_delicacies");
    }

    public static boolean isSimplyTeaLoaded() {
        return ModList.get().isLoaded("simplytea");
    }

}
