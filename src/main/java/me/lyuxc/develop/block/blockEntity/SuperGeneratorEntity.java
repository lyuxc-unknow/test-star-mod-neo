package me.lyuxc.develop.block.blockEntity;

import me.lyuxc.develop.block.BlockRegistry;
import me.lyuxc.develop.utils.EnergyStorageImpl;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.common.util.Lazy;
import net.neoforged.neoforge.energy.EnergyStorage;
import net.neoforged.neoforge.energy.IEnergyStorage;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class SuperGeneratorEntity extends BlockEntity {
    public static final int CAPACITY = Integer.MAX_VALUE;
    private static final String ITEMS_TAG = "Inventory";
    private static final String ENERGY_TAG = "Energy";
    private static final String TIME_TAG = "BurnTime";
    private static final int GENERATE = 10000;
    private static final int MAX_TRANSFER = 10240000;
    public static int SLOT_COUNT = 1;
    public static int SLOT = 0;
    private final ItemStackHandler items = createItemHandler();
    private final Lazy<IItemHandler> itemHandler = Lazy.of(() -> items);
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
    int i = 0;
    private int burnTime;
    public SuperGeneratorEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockRegistry.SUPER_GENERATOR_ENTITY.get(), pPos, pBlockState);
    }

    public void tickServer(Level level, BlockPos pos) {
        generateEnergy();
        distributeEnergy();
        if (burnTime != 0) {
            i++;
            if (i % 20 == 0) {
                LightningBolt lightningBolt = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
                lightningBolt.moveTo(pos.getX(), pos.getY() + 1, pos.getZ());
                level.addFreshEntity(lightningBolt);
            }
        }
        if (!(level.getBlockEntity(pos.atY(pos.getY() + 1)) instanceof CircleBlockEntity)) {
            level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
        }
    }

    private void generateEnergy() {
        if (energy.getEnergyStored() < energy.getMaxEnergyStored()) {
            if (burnTime <= 0) {
                ItemStack fuel = items.getStackInSlot(SLOT);
                if (fuel.isEmpty()) {
                    //槽内没有物品
                    return;
                }
                setBurnTime(fuel.getBurnTime(RecipeType.SMELTING));
                if (burnTime <= 0) {
                    //不是燃料
                    return;
                }
                items.extractItem(SLOT, 1, false);
            } else {
                setBurnTime(burnTime - 1);
                energy.receiveEnergy(GENERATE, false);
            }
            setChanged();
        }
    }

    /*
     * 向六个面发送能量。如果支持能量
     * */
    private void distributeEnergy() {
        for (Direction direction : Direction.values()) {
            if (energy.getEnergyStored() <= 0) {
                return;
            }
            IEnergyStorage energy = null;
            if (level != null) {
                energy = level.getCapability(Capabilities.EnergyStorage.BLOCK, getBlockPos().relative(direction), direction);
            }
            if (energy != null) {
                if (energy.canReceive()) {
                    int received = energy.receiveEnergy(Math.min(this.energy.getEnergyStored(), MAX_TRANSFER), false);
                    this.energy.extractEnergy(received, false);
                    setChanged();
                }
            }
        }
    }

    public ItemStackHandler getItems() {
        return items;
    }

    public int getStoredPower() {
        return energy.getEnergyStored();
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider provider) {
        super.saveAdditional(tag, provider);
        getPersistentData().put(ITEMS_TAG, items.serializeNBT(provider));
        getPersistentData().putInt(ENERGY_TAG, energy.getEnergyStored());
        getPersistentData().putInt(TIME_TAG, burnTime);
    }

    @Override
    protected void loadAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider provider) {
        super.loadAdditional(tag, provider);
        if (getPersistentData().contains(ITEMS_TAG)) {
            items.deserializeNBT(provider, getPersistentData().getCompound(ITEMS_TAG));
        }
        if (getPersistentData().contains(ENERGY_TAG)) {
            energy.receiveEnergy(getPersistentData().getInt(ENERGY_TAG), false);
        }
        if (getPersistentData().contains(TIME_TAG)) {
            burnTime = getPersistentData().getInt(TIME_TAG);
        }
    }

    public IItemHandler getItemHandler() {
        return itemHandler.get();
    }

    public IEnergyStorage getEnergyHandler() {
        return energyHandler.get();
    }

    @Nonnull
    private ItemStackHandler createItemHandler() {
        return new ItemStackHandler(SLOT_COUNT) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }
        };
    }

    @Nonnull
    private EnergyStorage createEnergyStorage() {
        return new EnergyStorage(CAPACITY, MAX_TRANSFER, MAX_TRANSFER);
    }

    public int getBurnTime() {
        return burnTime;
    }

    private void setBurnTime(int bt) {
        if (bt == burnTime) {
            return;
        }
        burnTime = bt;
        if (getBlockState().getValue(BlockStateProperties.POWERED) != (burnTime > 0)) {
            if (level != null) {
                level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(BlockStateProperties.POWERED, (burnTime > 0)));
            }
        }
        setChanged();
    }

}
