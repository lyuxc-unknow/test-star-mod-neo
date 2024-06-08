package me.lyuxc.develop.block.blocks;

import me.lyuxc.develop.block.BlockRegistry;
import me.lyuxc.develop.block.blockContainer.SuperGeneratorContainer;
import me.lyuxc.develop.block.blockEntity.SuperGeneratorEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SuperGenerator extends Block implements EntityBlock {
    public SuperGenerator() {
        super(Properties.of()
                .strength(10F)
        );
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new SuperGeneratorEntity(blockPos, blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> type) {
        if (!level.isClientSide()){
            return (level1, pos, state1, be) -> {
                if (be instanceof SuperGeneratorEntity block) {
                    block.tickServer(level1, pos);
                }
            };
        }
        return null;
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull BlockHitResult result) {
        if (!level.isClientSide) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof SuperGeneratorEntity) {
                MenuProvider containerProvider = new MenuProvider() {
                    @Override
                    public @NotNull Component getDisplayName() {
                        return BlockRegistry.SUPER_GENERATOR.get().getName();
                    }

                    @Override
                    public AbstractContainerMenu createMenu(int windowId, @NotNull Inventory playerInventory, @NotNull Player playerEntity) {
                        return new SuperGeneratorContainer(windowId, playerEntity, pos);
                    }
                };
                player.openMenu(containerProvider, buf -> buf.writeBlockPos(pos));
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(BlockStateProperties.POWERED);
    }

    @Override
    protected @NotNull VoxelShape getShape(@NotNull BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull CollisionContext pContext) {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.1875, 0.125, 0.1875, 0.8125, 0.75, 0.8125), BooleanOp.OR);

        return shape;
    }

    @Override
    protected void onPlace(@NotNull BlockState pState, @NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull BlockState pOldState, boolean pMovedByPiston) {
        BlockPos newBlockPos = new BlockPos(pPos.getX(), pPos.getY() + 1, pPos.getZ());
        pLevel.setBlockAndUpdate(newBlockPos, BlockRegistry.CIRCLE_BLOCK.get().defaultBlockState());
        super.onPlace(pState, pLevel, pPos, pOldState, pMovedByPiston);
    }

    @Override
    protected void onRemove(BlockState pState, @NotNull Level pLevel, @NotNull BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        if (!pState.is(pNewState.getBlock())) {
            SuperGeneratorEntity superGeneratorEntity = (SuperGeneratorEntity) pLevel.getBlockEntity(pPos);
            if (superGeneratorEntity != null) {
                ItemEntity item = new ItemEntity(pLevel,pPos.getX(),pPos.getY() + 1,pPos.getZ(),superGeneratorEntity.getItems().getStackInSlot(0));
                pLevel.addFreshEntity(item);
            }
            pLevel.updateNeighbourForOutputSignal(pPos, this);
            super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
        }
    }
}
