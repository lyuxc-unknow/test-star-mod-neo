package me.lyuxc.develop.block.blockEntity;

import me.lyuxc.develop.block.BlockRegistry;
import me.lyuxc.develop.utils.EnergyStorageImpl;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.common.util.Lazy;
import net.neoforged.neoforge.energy.EnergyStorage;
import net.neoforged.neoforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.util.RenderUtil;

import javax.annotation.Nonnull;

public class CreativeGeneratorBlockEntity extends BlockEntity implements GeoBlockEntity {
    public static final int CAPACITY = Integer.MAX_VALUE;
    private static final String ENERGY_TAG = "Energy";
    private static final int MAX_TRANSFER = Integer.MAX_VALUE;
    private final EnergyStorage energy = createEnergyStorage();
    private final Lazy<IEnergyStorage> energyHandler = Lazy.of(() -> new EnergyStorageImpl(energy) {
        @Override
        public int receiveEnergy(int maxReceive, boolean simulate) {
            return 0;
        }

        @Override
        public int extractEnergy(int maxExtract, boolean simulate) {
            return MAX_TRANSFER;
        }

        @Override
        public boolean canExtract() {
            return true;
        }

        @Override
        public boolean canReceive() {
            return false;
        }
    });
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public CreativeGeneratorBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockRegistry.CREATIVE_GENERATOR_ENTITY.get(), pPos, pBlockState);
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

    @Override
    public double getTick(Object blockEntity) {
        return RenderUtil.getCurrentTick();
    }

    @Nonnull
    private EnergyStorage createEnergyStorage() {
        return new EnergyStorage(CAPACITY, MAX_TRANSFER, MAX_TRANSFER);
    }
    @Override
    protected void saveAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider provider) {
        super.saveAdditional(tag, provider);
        getPersistentData().putInt(ENERGY_TAG, energy.getEnergyStored());
    }

    @Override
    protected void loadAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider provider) {
        super.loadAdditional(tag, provider);
        if (getPersistentData().contains(ENERGY_TAG)) {
            energy.receiveEnergy(getPersistentData().getInt(ENERGY_TAG), false);
        }
    }
    public IEnergyStorage getEnergyHandler() {
        return energyHandler.get();
    }
    private void generateEnergy() {
        if(energy.getEnergyStored()<energy.getMaxEnergyStored()) {
            energy.receiveEnergy(Integer.MAX_VALUE, false);
        }
        setChanged();
    }
    private void distributeEnergy() {
        for (Direction direction : Direction.values()) {
            IEnergyStorage energy = null;
            if (level != null) {
                energy = level.getCapability(Capabilities.EnergyStorage.BLOCK, getBlockPos().relative(direction), direction);
            }
            if (energy != null) {
                if (energy.canReceive()) {
                    int received = energy.receiveEnergy(this.energy.getEnergyStored(), false);
                    this.energy.extractEnergy(received, false);
                    setChanged();
                }
            }
        }
    }
    public void tickServer() {
        generateEnergy();
        distributeEnergy();
        if (level != null) {
            level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(BlockStateProperties.POWERED, true));
        }
        setChanged();
    }
}
