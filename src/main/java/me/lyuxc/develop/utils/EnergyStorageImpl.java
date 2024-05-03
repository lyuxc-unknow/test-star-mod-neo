package me.lyuxc.develop.utils;

import net.neoforged.neoforge.energy.IEnergyStorage;

public class EnergyStorageImpl implements IEnergyStorage {
    private final IEnergyStorage energyStorage;

    public EnergyStorageImpl(IEnergyStorage energyStorage) {
        this.energyStorage = energyStorage;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return energyStorage.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(int maxReceive, boolean simulate) {
        return energyStorage.extractEnergy(maxReceive, simulate);
    }

    @Override
    public int getEnergyStored() {
        return energyStorage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored() {
        return energyStorage.getMaxEnergyStored();
    }

    @Override
    public boolean canExtract() {
        return energyStorage.canExtract();
    }

    @Override
    public boolean canReceive() {
        return energyStorage.canReceive();
    }
}
