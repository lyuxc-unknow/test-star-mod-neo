package me.lyuxc.develop.compat.theoneprobe.MI;

import aztech.modern_industrialization.MIText;
import aztech.modern_industrialization.api.energy.CableTier;
import aztech.modern_industrialization.api.machine.holder.EnergyComponentHolder;
import aztech.modern_industrialization.api.machine.holder.EnergyListComponentHolder;
import aztech.modern_industrialization.machines.blockentities.StorageMachineBlockEntity;
import aztech.modern_industrialization.machines.blockentities.TransformerMachineBlockEntity;
import aztech.modern_industrialization.machines.blockentities.hatches.EnergyHatch;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ProbeMode;
import me.lyuxc.develop.Star;
import net.minecraft.ChatFormatting;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class MachineTierProvider implements IProbeInfoProvider {
    @Override
    public ResourceLocation getID() {
        return Star.rl("machine_tier");
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, Player player, Level level, BlockState blockState, IProbeHitData iProbeHitData) {
        BlockEntity block = level.getBlockEntity(iProbeHitData.getPos());
        if(block instanceof TransformerMachineBlockEntity blockEntity) {
            iProbeInfo.text(MIText.NetworkTier.text().getString() + ":" + getString(blockEntity,200));
        }
        else if(block instanceof StorageMachineBlockEntity blockEntity) {
            iProbeInfo.text(MIText.NetworkTier.text().getString() + ":" + getString(blockEntity,6000));
        }
        else if(block instanceof EnergyHatch blockEntity) {
            iProbeInfo.text(MIText.NetworkTier.text().getString() + ":" + getString(blockEntity, 600));
        }
        else if(block instanceof EnergyComponentHolder blockEntity) {
            if(blockEntity.getEnergyComponent().getCapacity()/100 >= 0) {
                iProbeInfo.text(MIText.NetworkTier.text().getString() + ":" + getString(blockEntity,100));
            }
        }
        else if (block instanceof EnergyListComponentHolder blockEntity) {
            var components = blockEntity.getEnergyComponents();
            if (!components.isEmpty()) {
                long capacity = 0;
                for (var component : components) {
                    capacity += component.getCapacity();
                }
                iProbeInfo.text(MIText.NetworkTier.text().getString() + ":" + getString(capacity,600));
            }
        }
    }

    private static String getString(EnergyComponentHolder block,int v) {
        return getString(block.getEnergyComponent().getCapacity(),v);
    }
    private static String getString(long energy,int v) {
        String tier="";
        energy = energy/v;
        if(energy == CableTier.LV.getEu()) {
            tier = CableTier.LV.longEnglishName().getString();
        } else if (energy == CableTier.MV.getEu()) {
            tier = CableTier.MV.longEnglishName().getString();
        } else if (energy == CableTier.HV.getEu()) {
            tier = CableTier.HV.longEnglishName().getString();
        } else if (energy == CableTier.EV.getEu()) {
            tier = CableTier.EV.longEnglishName().getString();
        } else if (energy == CableTier.SUPERCONDUCTOR.getEu()) {
            tier = CableTier.SUPERCONDUCTOR.longEnglishName().getString();
        }
        return ChatFormatting.GOLD + tier + ChatFormatting.RESET;
    }
}
