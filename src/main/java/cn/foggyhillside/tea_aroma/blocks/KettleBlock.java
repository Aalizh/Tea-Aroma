package cn.foggyhillside.tea_aroma.blocks;

import cn.foggyhillside.tea_aroma.blocks.entities.KettleEntity;
import cn.foggyhillside.tea_aroma.blocks.states.KettleLiquid;
import cn.foggyhillside.tea_aroma.blocks.states.KettleSupport;
import cn.foggyhillside.tea_aroma.registry.*;
import cn.foggyhillside.tea_aroma.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class KettleBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final IntegerProperty AMOUNT = ModBlockStateProperties.AMOUNT;
    public static final EnumProperty<KettleSupport> SUPPORT = EnumProperty.create("support", KettleSupport.class);
    public static final EnumProperty<KettleLiquid> LIQUID = EnumProperty.create("liquid", KettleLiquid.class);
    protected static final VoxelShape SHAPE = Block.box(3.0F, 0.0F, 3.0F, 13.0F, 8.0F, 13.0F);
    protected static final VoxelShape SHAPE_WITH_TRAY = Shapes.or(SHAPE, Block.box(0.0F, -1.0F, 0.0F, 16.0F, 0.0F, 16.0F));

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockPos pos = pContext.getClickedPos();
        Level level = pContext.getLevel();
        BlockState state = (defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite())
                .setValue(AMOUNT, 0)
                .setValue(LIQUID, KettleLiquid.NONE));
        return pContext.getClickedFace().equals(Direction.DOWN) ? state.setValue(SUPPORT, KettleSupport.HANDLE) : state.setValue(SUPPORT, this.getTrayState(level, pos));
    }

    public KettleBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(AMOUNT, 0).setValue(SUPPORT, KettleSupport.NONE).setValue(LIQUID, KettleLiquid.NONE));
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack heldStack = pPlayer.getItemInHand(pHand);
        if (heldStack.isEmpty()) {
            if (pPlayer.isShiftKeyDown()) {
                pLevel.setBlockAndUpdate(pPos, pState.setValue(SUPPORT, pState.getValue(SUPPORT).equals(KettleSupport.HANDLE) ? this.getTrayState(pLevel, pPos) : KettleSupport.HANDLE));
                pLevel.playSound(null, pPos, SoundEvents.LANTERN_PLACE, SoundSource.BLOCKS, 0.7F, 1.0F);
                return InteractionResult.SUCCESS;
            } else if (pHand == InteractionHand.MAIN_HAND) {
                if (!pLevel.isClientSide()) {
                    ItemStack stack = getKettleStack(pState, pLevel, pPos);
                    pPlayer.setItemInHand(InteractionHand.MAIN_HAND, stack);
                    pLevel.removeBlock(pPos, false);
                }
                return InteractionResult.SUCCESS;
            }
        } else if (pState.getValue(LIQUID).equals(KettleLiquid.NONE)) {
            if (heldStack.is(ModTags.WATER) || (heldStack.getItem().equals(Items.POTION)
                    && heldStack.getTag() != null
                    && heldStack.getTag().getString("Potion").equals("minecraft:water"))) {
                if (!pLevel.isClientSide()) {
                    ItemStack remainingStack;
                    if (heldStack.getItem().equals(Items.POTION)) {
                        remainingStack = new ItemStack(Items.GLASS_BOTTLE);
                    } else {
                        remainingStack = heldStack.getCraftingRemainingItem();
                    }
                    Utils.addItem(heldStack, pPlayer, remainingStack);
                    pLevel.playSound(null, pPos.getX(), pPos.getY(), pPos.getZ(), ModSounds.KETTLE_FILL.get(), SoundSource.NEUTRAL, 1.0F, 1.0F);
                    pLevel.setBlockAndUpdate(pPos, pState.setValue(LIQUID, KettleLiquid.WATER).setValue(AMOUNT, 3));
                }
                return InteractionResult.SUCCESS;
            } else if (heldStack.is(ModTags.MILK)) {
                if (!pLevel.isClientSide()) {
                    Utils.addItem(heldStack, pPlayer, heldStack.getCraftingRemainingItem());
                    pLevel.setBlockAndUpdate(pPos, pState.setValue(LIQUID, KettleLiquid.MILK).setValue(AMOUNT, 3));
                    pLevel.playSound(null, pPos.getX(), pPos.getY(), pPos.getZ(), ModSounds.KETTLE_FILL.get(), SoundSource.NEUTRAL, 1.0F, 1.0F);
                }
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.PASS;
    }

    private ItemStack getKettleStack(BlockState pState, Level pLevel, BlockPos pPos) {
        ItemStack kettleStack = new ItemStack(ModItems.KETTLE.get(), 1);
        if (pLevel.getBlockEntity(pPos) instanceof KettleEntity) {
            kettleStack.addTagElement("BlockEntityTag", pLevel.getBlockEntity(pPos).saveWithoutMetadata());
            kettleStack.getOrCreateTagElement("BlockStateTag").putInt("amount", pState.getValue(AMOUNT));
            kettleStack.getOrCreateTagElement("BlockStateTag").putString("liquid", pState.getValue(LIQUID).toString());
        }
        return kettleStack;
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
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return (pState.getValue(SUPPORT)).equals(KettleSupport.TRAY) ? SHAPE_WITH_TRAY : SHAPE;
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pPos, BlockPos pNeighborPos) {
        return pDirection.getAxis().equals(Direction.Axis.Y) && !pState.getValue(SUPPORT).equals(KettleSupport.HANDLE) ? pState.setValue(SUPPORT, this.getTrayState(pLevel, pPos)) : pState;
    }

    private KettleSupport getTrayState(LevelAccessor pLevel, BlockPos pPos) {
        return pLevel.getBlockState(pPos.below()).is(ModTags.TRAY_HEAT_SOURCES) ? KettleSupport.TRAY : KettleSupport.NONE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(AMOUNT);
        builder.add(SUPPORT);
        builder.add(LIQUID);
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter pLevel, BlockPos pPos, BlockState pState) {
        ItemStack kettleStack = new ItemStack(ModItems.KETTLE.get());
        kettleStack.getOrCreateTagElement("BlockEntityTag").putInt("boil_progress", 0);
        kettleStack.getOrCreateTagElement("BlockStateTag").putInt("amount", 0);
        kettleStack.getOrCreateTagElement("BlockStateTag").putString("liquid", "none");
        return kettleStack;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new KettleEntity(blockPos, blockState);
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
        return pLevel.isClientSide ? createTickerHelper(pBlockEntityType, ModBlockEntities.KETTLE.get(), KettleEntity::animationTick) : createTickerHelper(pBlockEntityType, ModBlockEntities.KETTLE.get(), KettleEntity::tick);
    }
}
