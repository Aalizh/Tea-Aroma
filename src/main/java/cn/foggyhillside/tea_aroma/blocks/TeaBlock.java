package cn.foggyhillside.tea_aroma.blocks;

import cn.foggyhillside.tea_aroma.registry.ModItems;
import cn.foggyhillside.tea_aroma.registry.ModParticleTypes;
import cn.foggyhillside.tea_aroma.util.Utils;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TeaBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    protected static final VoxelShape SHAPE = Block.box(5.0F, 0.0F, 5.0F, 11.0F, 6.0F, 11.0F);

    public TeaBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public boolean isPossibleToRespawnInThis(BlockState pState) {
        return true;
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
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pPos, BlockPos pNeighborPos) {
        return pDirection == Direction.DOWN && !pState.canSurvive(pLevel, pPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(pState, pDirection, pNeighborState, pLevel, pPos, pNeighborPos);
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockPos floorPos = pPos.below();
        return canSupportRigidBlock(pLevel, floorPos) || canSupportCenter(pLevel, floorPos, Direction.UP);
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
    public List<ItemStack> getDrops(BlockState pState, LootParams.Builder pParams) {
        ObjectArrayList<ItemStack> objectarraylist = new ObjectArrayList<>();
        objectarraylist.add(new ItemStack(ModItems.CUP.get()));
        return objectarraylist;
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        RandomSource random = pLevel.random;
        Block block = pLevel.getBlockState(pPos).getBlock();
        if (block instanceof TeaBlock) {
            double x = pPos.getX() + 0.5;
            double y = pPos.getY();
            double z = pPos.getZ() + 0.5;

            double offset = random.nextDouble() * 0.2 - 0.1;
            double yOffset = random.nextDouble() * 4.0 / 16.0;
            pLevel.addParticle(ModParticleTypes.STEAM.get(), x + offset, y + 0.32 + yOffset, z + offset, 0, 0, 0);
        }
    }

    protected boolean flavour(Level level, BlockState state, BlockPos pos, Player player, InteractionHand
            hand, BooleanProperty property, @javax.annotation.Nullable Item flavouring) {
        if (!level.isClientSide()) {
            if (!state.getValue(property)) {
                ItemStack heldStack = player.getItemInHand(hand);
                if (heldStack.is(flavouring)) {
                    Utils.addItem(heldStack, player, heldStack.getCraftingRemainingItem());
                    level.setBlockAndUpdate(pos, state.setValue(property, true));
                    return true;
                }
            }
        }
        return false;
    }
}
