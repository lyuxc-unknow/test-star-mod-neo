package me.lyuxc.develop.block.blockEntity;

import me.lyuxc.develop.block.BlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.util.RenderUtil;

public class CircleBlockEntity extends BlockEntity implements GeoBlockEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public CircleBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockRegistry.CIRCLE_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, this::predicate));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
        tAnimationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    /**
     * 动画相关Tick
    * */
    @Override
    public double getTick(Object blockEntity) {
        return RenderUtil.getCurrentTick();
    }

    /**
     * 伪多方快检测
    * */
    public void tickServer(Level level, BlockPos pos) {
        if (!(level.getBlockEntity(pos.atY(pos.getY() - 1)) instanceof SuperGeneratorEntity)) {
            level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
        }
    }
}
