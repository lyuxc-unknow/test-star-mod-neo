package me.lyuxc.develop.compat.theoneprobe.QuarryPlus;

import com.yogpc.qp.machines.workbench.TileWorkbench;
import mcjty.theoneprobe.api.*;
import mcjty.theoneprobe.apiimpl.elements.ElementProgress;
import mcjty.theoneprobe.apiimpl.styles.ProgressStyle;
import mcjty.theoneprobe.apiimpl.styles.TextStyle;
import me.lyuxc.develop.Star;
import me.lyuxc.develop.utils.I18N;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.awt.Color;
import java.util.Locale;

public class QuarryPlus implements IProbeInfoProvider {
    @Override
    public ResourceLocation getID() {
        return Star.rl(this.getClass().getName().toLowerCase(Locale.ROOT));
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, Player player, Level level, BlockState blockState, IProbeHitData iProbeHitData) {
        if(level.getBlockEntity(iProbeHitData.getPos()) instanceof TileWorkbench workbench){
            if(workbench.getEnergyStored() > 0) {
                iProbeInfo.progress(workbench.getEnergyStored(), workbench.getRecipe().getRequiredEnergy()/1000000000,new ProgressStyle()
                        .alignment(ElementAlignment.ALIGN_CENTER)
                        .numberFormat(NumberFormat.COMPACT)
                        .prefix(I18N.getComponent("ts.tips.top.progress"))
                        .suffix("/" + ElementProgress.format(workbench.getRecipe().getRequiredEnergy() / 1000000000, NumberFormat.COMPACT, Component.literal("")).getString())
                        .color(Color.GRAY.getRGB(),Color.GREEN.getRGB(),Color.GREEN.getRGB(),Color.WHITE.getRGB())
                );
                iProbeInfo.horizontal()
                        .text(I18N.getComponent("ts.tips.top.crafting"), new TextStyle().topPadding(5))
                        .item(workbench.getRecipe().output)
                        .text(workbench.getRecipe().output.getHoverName(),new TextStyle().topPadding(5));
            }
        }
    }
}
