package cn.foggyhillside.tea_aroma.blocks;

import cn.foggyhillside.tea_aroma.ModCompat;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class STBlock extends TeaBlock {
    public STBlock(Properties pProperties) {
        super(pProperties);
    }

    protected void pick(Level level, BlockState state, BlockPos pos, Player player, InteractionHand hand, BooleanProperty property) {
        if (!level.isClientSide()) {
            ItemStack stack = new ItemStack(ModCompat.blockToSTItem(level, pos));
            if (!state.getValue(property)) {
                player.setItemInHand(hand, stack);
                level.removeBlock(pos, false);
            } else {
                stack.getOrCreateTag().putBoolean(property.getName(), true);
                player.setItemInHand(hand, stack);
                level.removeBlock(pos, false);
            }
        }
    }
}
