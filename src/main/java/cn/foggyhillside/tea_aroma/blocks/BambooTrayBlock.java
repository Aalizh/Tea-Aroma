package cn.foggyhillside.tea_aroma.blocks;

import cn.foggyhillside.tea_aroma.blocks.entities.BambooTrayEntity;
import cn.foggyhillside.tea_aroma.registry.ModBlockEntities;
import cn.foggyhillside.tea_aroma.registry.ModBlockStateProperties;
import cn.foggyhillside.tea_aroma.registry.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class BambooTrayBlock extends BaseEntityBlock {
    public static final IntegerProperty PROCESS_TYPE = ModBlockStateProperties.PROCESS_TYPE;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    protected static final VoxelShape SHAPE = Block.box(0.0F, 0.0F, 0.0F, 16.0F, 4.0F, 16.0F);

    public BambooTrayBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(PROCESS_TYPE, 0));
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pHand.equals(InteractionHand.MAIN_HAND) && pLevel.getBlockEntity(pPos) instanceof BambooTrayEntity entity) {
            ItemStack heldStack = pPlayer.getItemInHand(pHand);
            if (!heldStack.isEmpty()) {
                if (!pLevel.isClientSide() && !pState.getValue(PROCESS_TYPE).equals(3) && !entity.isFull() && entity.addItem(heldStack, pPlayer)) {
                    return InteractionResult.SUCCESS;
                }
                return InteractionResult.CONSUME;
            } else if (pPlayer.isShiftKeyDown()) {
                if (!entity.isEmpty()) {
                    Containers.dropContents(pLevel, pPos, entity.getInventoryList());
                    entity.emptyInventory();
                    pLevel.updateNeighbourForOutputSignal(pPos, this);
                    return InteractionResult.SUCCESS;
                }
            } else {
                if (pState.getValue(PROCESS_TYPE).equals(2)) {
                    entity.playerProcess();
                    pLevel.playSound(null, pPos, ModSounds.TEA_PROCESSING_2.get(), SoundSource.BLOCKS, 1.0F, 0.8F + pLevel.random.nextFloat() * 0.4F);
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(PROCESS_TYPE);
        pBuilder.add(FACING);
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        if (pState.getBlock() != pNewState.getBlock()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof BambooTrayEntity entity) {
                Containers.dropContents(pLevel, pPos, entity.getInventoryList());
                pLevel.updateNeighbourForOutputSignal(pPos, this);
            }

            super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
        }
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState pState) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState pState, Level pLevel, BlockPos pPos) {
        BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
        if (blockEntity instanceof BambooTrayEntity entity) {
            if (!entity.getInventory().getStackInSlot(0).isEmpty() && !entity.getInventory().getStackInSlot(1).isEmpty()) {
                return 15;
            } else if (entity.isEmpty()) {
                return 0;
            } else {
                return 8;
            }
        } else {
            return 0;
        }
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 60;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 100;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new BambooTrayEntity(pPos, pState);
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return pLevel.isClientSide ? null : createTickerHelper(pBlockEntityType, ModBlockEntities.BAMBOO_TRAY.get(), BambooTrayEntity::tick);
    }

}
