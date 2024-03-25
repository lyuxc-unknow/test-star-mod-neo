package me.lyuxc.develop.compat.theoneprobe.MI;

import aztech.modern_industrialization.machines.blockentities.AbstractCraftingMachineBlockEntity;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ProbeMode;
import me.lyuxc.develop.Star;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class MachineTypeProvider implements IProbeInfoProvider {
    @Override
    public ResourceLocation getID() {
        return Star.rl("machine_type");
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, Player player, Level level, BlockState blockState, IProbeHitData iProbeHitData) {
        if (level.getBlockEntity(iProbeHitData.getPos()) instanceof AbstractCraftingMachineBlockEntity block) {
            iProbeInfo.text(block.tier.name());
        }
    }
}
