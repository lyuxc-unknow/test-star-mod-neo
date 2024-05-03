package me.lyuxc.develop.compat.theoneprobe.TestStar;

import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ProbeMode;
import me.lyuxc.develop.Star;
import me.lyuxc.develop.block.blockEntity.SuperGeneratorEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SuperGeneratorProvider implements IProbeInfoProvider {
    @Override
    public ResourceLocation getID() {
        return Star.rl("super_generator");
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, Player player, Level level, BlockState blockState, IProbeHitData iProbeHitData) {
        BlockEntity blockEntity = level.getBlockEntity(iProbeHitData.getPos());
        if (blockEntity instanceof SuperGeneratorEntity entity) {
            iProbeInfo.text("燃烧剩余时间:" + entity.getBurnTime() / 20.0f);
        }
    }
}
