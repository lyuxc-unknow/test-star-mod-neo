package me.lyuxc.develop.compat.theoneprobe.MI;

import aztech.modern_industrialization.api.machine.holder.EnergyComponentHolder;
import aztech.modern_industrialization.api.machine.holder.EnergyListComponentHolder;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ProbeMode;
import mcjty.theoneprobe.apiimpl.styles.ProgressStyle;
import me.lyuxc.develop.Star;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.awt.*;

public class MachineComponentProvider implements IProbeInfoProvider {
    @Override
    public ResourceLocation getID() {
        return Star.rl("machine");
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, Player player, Level level, BlockState blockState, IProbeHitData iProbeHitData) {
        BlockEntity block = level.getBlockEntity(iProbeHitData.getPos());
        if (block instanceof EnergyComponentHolder holder) {
            var component = holder.getEnergyComponent();
            iProbeInfo.progress(component.getEu(),component.getCapacity(),new ProgressStyle()
                    .suffix("EU/" + component.getCapacity() + "EU")
                    .backgroundColor(Color.WHITE.getRGB())
                    .filledColor(Color.RED.getRGB())
                    .alternateFilledColor(Color.GRAY.getRGB())
            );
        } else if (block instanceof EnergyListComponentHolder holder) {
            var components = holder.getEnergyComponents();
            if (!components.isEmpty()) {
                long stored = 0;
                long capacity = 0;
                for (var component : components) {
                    stored += component.getEu();
                    capacity += component.getCapacity();
                }
                iProbeInfo.progress(stored, capacity, new ProgressStyle()
                        .suffix("EU/" + capacity + "EU")
                        .backgroundColor(Color.WHITE.getRGB())
                        .filledColor(Color.RED.getRGB())
                        .alternateFilledColor(Color.GRAY.getRGB())
                );
            }
        }
    }
}
