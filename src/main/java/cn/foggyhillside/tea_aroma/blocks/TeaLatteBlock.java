package cn.foggyhillside.tea_aroma.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class TeaLatteBlock extends TABlock {
    public TeaLatteBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pHand.equals(InteractionHand.MAIN_HAND)) {
            ItemStack heldStack = pPlayer.getItemInHand(pHand);
            if (heldStack.isEmpty()) {
                pick(pLevel, pState, pPos, pPlayer, pHand);
                return InteractionResult.SUCCESS;
            } else {
                if (pLevel.isClientSide()
                        && ((heldStack.is(Items.HONEY_BOTTLE) && !pState.getValue(WITH_HONEY))
                        || (heldStack.is(Items.SUGAR) && !pState.getValue(WITH_SUGAR)))) {
                    return InteractionResult.CONSUME;
                }
                if (flavour(pLevel, pState, pPos, pPlayer, pHand, WITH_SUGAR, Items.SUGAR)) {
                    return InteractionResult.SUCCESS;
                } else if (flavour(pLevel, pState, pPos, pPlayer, pHand, WITH_HONEY, Items.HONEY_BOTTLE)) {
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return InteractionResult.PASS;
    }
}
