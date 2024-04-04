package me.lyuxc.develop.compat.theoneprobe.MI;

import aztech.modern_industrialization.api.machine.component.EnergyAccess;
import aztech.modern_industrialization.api.machine.holder.EnergyComponentHolder;
import aztech.modern_industrialization.api.machine.holder.EnergyListComponentHolder;
import mcjty.theoneprobe.api.*;
import mcjty.theoneprobe.apiimpl.elements.ElementProgress;
import mcjty.theoneprobe.apiimpl.styles.ProgressStyle;
import me.lyuxc.develop.Star;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.awt.Color;
import java.util.List;

public class MachineComponentProvider implements IProbeInfoProvider {
    @Override
    public ResourceLocation getID() {
        return Star.rl("machine");
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, Player player, Level level, BlockState blockState, IProbeHitData iProbeHitData) {
        BlockEntity block = level.getBlockEntity(iProbeHitData.getPos());
        if (block instanceof EnergyComponentHolder holder) {
            EnergyAccess component = holder.getEnergyComponent();
            iProbeInfo.progress(component.getEu(),component.getCapacity(),new ProgressStyle()
                    .alignment(ElementAlignment.ALIGN_CENTER)
                    .numberFormat(NumberFormat.COMPACT)
                    .width(120)
                    .suffix("EU/" + ElementProgress.format(component.getCapacity(), NumberFormat.COMPACT, Component.literal("EU")).getString())
                    .backgroundColor(Color.WHITE.getRGB())
                    .filledColor(Color.RED.getRGB())
                    .alternateFilledColor(Color.GRAY.getRGB())
            );
        } else if (block instanceof EnergyListComponentHolder holder) {
            List<? extends EnergyAccess> components = holder.getEnergyComponents();
            if (!components.isEmpty()) {
                long stored = 0;
                long capacity = 0;
                for (var component : components) {
                    stored += component.getEu();
                    capacity += component.getCapacity();
                }
                iProbeInfo.progress(stored, capacity, new ProgressStyle()
                        .alignment(ElementAlignment.ALIGN_CENTER)
                        .numberFormat(NumberFormat.COMPACT)
                        .suffix("EU/" + ElementProgress.format(capacity, NumberFormat.COMPACT, Component.literal("EU")).getString())
                        .backgroundColor(Color.WHITE.getRGB())
                        .filledColor(Color.RED.getRGB())
                        .alternateFilledColor(Color.GRAY.getRGB())
                );
            }
        }
    }
}
