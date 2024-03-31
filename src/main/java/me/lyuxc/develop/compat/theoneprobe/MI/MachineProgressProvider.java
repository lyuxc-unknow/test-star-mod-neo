package me.lyuxc.develop.compat.theoneprobe.MI;

import aztech.modern_industrialization.api.machine.component.CrafterAccess;
import aztech.modern_industrialization.api.machine.holder.CrafterComponentHolder;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ProbeMode;
import mcjty.theoneprobe.apiimpl.styles.ProgressStyle;
import me.lyuxc.develop.Star;
import me.lyuxc.develop.utils.I18N;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.awt.*;

public class MachineProgressProvider implements IProbeInfoProvider {
    @Override
    public ResourceLocation getID() {
        return Star.rl("progress");
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, Player player, Level level, BlockState blockState, IProbeHitData iProbeHitData) {
        if(level.getBlockEntity(iProbeHitData.getPos()) instanceof CrafterComponentHolder holder) {
            CrafterAccess component = holder.getCrafterComponent();
            float progress = component.getProgress();
            if (progress > 0.0f) {
                iProbeInfo.progress((int) (progress*100), 100, new ProgressStyle()
                        .prefix(I18N.getComponent("ts.tips.top.progress"))
                        .suffix("%")
                        .backgroundColor(Color.GRAY.getRGB())
                        .filledColor(Color.LIGHT_GRAY.getRGB())
                        .alternateFilledColor(Color.LIGHT_GRAY.getRGB())
                );
            }
        }
    }
}
