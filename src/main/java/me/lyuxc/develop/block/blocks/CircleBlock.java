package me.lyuxc.develop.block.blocks;

import me.lyuxc.develop.block.blockEntity.CircleBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CircleBlock extends Block implements EntityBlock {
    public CircleBlock() {
        super(Properties.of()
                .noOcclusion()
        );
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new CircleBlockEntity(blockPos, blockState);
    }

    @SuppressWarnings("all")
    @Override
    protected @NotNull RenderShape getRenderShape(BlockState pState) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> type) {
        if (!level.isClientSide) {
            return (level1, pos, state1, be) -> {
                if (be instanceof CircleBlockEntity block) {
                    block.tickServer(level1, pos);
                }
            };
        }
        return null;
    }
}
