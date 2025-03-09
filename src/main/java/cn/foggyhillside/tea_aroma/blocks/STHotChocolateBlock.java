package cn.foggyhillside.tea_aroma.blocks;

import cn.foggyhillside.tea_aroma.ModCompat;
import cn.foggyhillside.tea_aroma.registry.ModBlockStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class STHotChocolateBlock extends STBlock {
    public static final BooleanProperty WITH_CINNAMON = ModBlockStateProperties.WITH_CINNAMON;

    public STHotChocolateBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(WITH_CINNAMON, false));
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pHand.equals(InteractionHand.MAIN_HAND)) {
            if (!pPlayer.getItemInHand(pHand).isEmpty()) {
                if(pLevel.isClientSide() && !pState.getValue(WITH_CINNAMON)){
                    return InteractionResult.CONSUME;
                }
                if (flavour(pLevel, pState, pPos, pPlayer, pHand, WITH_CINNAMON, ModCompat.getTeaStick())) {
                    return InteractionResult.SUCCESS;
                }
            } else {
                pick(pLevel, pState, pPos, pPlayer, pHand, WITH_CINNAMON);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WITH_CINNAMON);
        super.createBlockStateDefinition(builder);
    }
}
