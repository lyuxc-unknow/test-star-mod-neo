package me.lyuxc.develop.compat.theoneprobe.MI;

import aztech.modern_industrialization.MIText;
import aztech.modern_industrialization.api.machine.holder.CrafterComponentHolder;
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

public class OverclockProvider implements IProbeInfoProvider  {
    @Override
    public ResourceLocation getID() {
        return Star.rl("overclock");
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, Player player, Level level, BlockState blockState, IProbeHitData iProbeHitData) {
        BlockEntity block = level.getBlockEntity(iProbeHitData.getPos());
        if(block instanceof CrafterComponentHolder holder) {
            var crafterComponent = holder.getCrafterComponent();
            if(crafterComponent.hasActiveRecipe()) {
                int efficiencyTicks = crafterComponent.getEfficiencyTicks();
                int maxEfficiencyTicks = crafterComponent.getMaxEfficiencyTicks();
                long baseRecipeEu = crafterComponent.getBaseRecipeEu();
                long currentEu = crafterComponent.getCurrentRecipeEu();
                double mult = (double) currentEu / baseRecipeEu;
                iProbeInfo.progress(efficiencyTicks,maxEfficiencyTicks,new ProgressStyle()
                        .backgroundColor(Color.WHITE.getRGB())
                        .filledColor(Color.green.getRGB())
                        .alternateFilledColor(Color.green.getRGB())
                        .prefix(MIText.Efficiency.text().getString())
                        .suffix("/" + maxEfficiencyTicks)
                );
                iProbeInfo.text((MIText.EuTOverclocked.text(
                        String.format("%.1f", mult), String.format("%d", currentEu))));
            }
        }
    }
}
