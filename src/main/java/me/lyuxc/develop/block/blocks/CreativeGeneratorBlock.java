package me.lyuxc.develop.block.blocks;

import me.lyuxc.develop.block.blockEntity.CreativeGeneratorBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CreativeGeneratorBlock extends Block implements EntityBlock {
    public CreativeGeneratorBlock() {
        super(Properties.of()
                .strength(10.0F)
                .noOcclusion()
        );
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new CreativeGeneratorBlockEntity(blockPos,blockState);
    }

    @SuppressWarnings("all")
    @Override
    protected @NotNull RenderShape getRenderShape(BlockState pState) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    protected @NotNull VoxelShape getShape(@NotNull BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull CollisionContext pContext) {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.25, 0.25, 0.25, 0.75, 0.75, 0.75), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.125, 0, 0.875, 1, 0.125, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.875, 0, 0, 1, 0.125, 0.875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0, 0, 0.875, 0.125, 0.125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0, 0.125, 0.125, 0.125, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.125, 0.875, 0.875, 1, 1, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.875, 0.875, 0, 1, 1, 0.875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.875, 0, 0.875, 1, 0.125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.875, 0.125, 0.125, 1, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.875, 0.125, 0.875, 1, 0.875, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.875, 0.125, 0, 1, 0.875, 0.125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.125, 0, 0.125, 0.875, 0.125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.125, 0.875, 0.125, 0.875, 1), BooleanOp.OR);

        return shape;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(BlockStateProperties.POWERED);
    }
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> type) {
        if (!level.isClientSide){
            return (level1, pos, state1, be) -> {
                if (be instanceof CreativeGeneratorBlockEntity block) {
                    block.tickServer();
                }
            };
        }
        return null;
    }
}
