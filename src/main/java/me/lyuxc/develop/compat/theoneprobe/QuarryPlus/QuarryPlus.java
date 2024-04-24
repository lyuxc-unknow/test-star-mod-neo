//package me.lyuxc.develop.compat.theoneprobe.QuarryPlus;
//
//import com.yogpc.qp.machines.PowerTile;
//import com.yogpc.qp.machines.workbench.TileWorkbench;
//import mcjty.theoneprobe.api.*;
//import mcjty.theoneprobe.apiimpl.elements.ElementProgress;
//import mcjty.theoneprobe.apiimpl.styles.ProgressStyle;
//import mcjty.theoneprobe.apiimpl.styles.TextStyle;
//import me.lyuxc.develop.Star;
//import me.lyuxc.develop.utils.I18N;
//import net.minecraft.network.chat.Component;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.block.entity.BlockEntity;
//import net.minecraft.world.level.block.state.BlockState;
//
//import java.awt.Color;
//import java.util.Locale;
//
//public class QuarryPlus implements IProbeInfoProvider {
//    @Override
//    public ResourceLocation getID() {
//        return Star.rl(this.getClass().getName().toLowerCase(Locale.ROOT));
//    }
//
//    @Override
//    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, Player player, Level level, BlockState blockState, IProbeHitData iProbeHitData) {
//        BlockEntity blockEntity = level.getBlockEntity(iProbeHitData.getPos());
//        if (blockEntity instanceof PowerTile powerTile) {
//            if (powerTile.getMaxEnergy() / PowerTile.ONE_FE > 0) {
//                iProbeInfo.progress(powerTile.getEnergy() / PowerTile.ONE_FE, powerTile.getMaxEnergy() / PowerTile.ONE_FE, new ProgressStyle()
//                        .alignment(ElementAlignment.ALIGN_CENTER)
//                        .numberFormat(NumberFormat.COMPACT)
//                        .prefix(I18N.getComponent("ts.tips.top.energy"))
//                        .suffix("/" + ElementProgress.format(powerTile.getMaxEnergy() / PowerTile.ONE_FE, NumberFormat.COMPACT, Component.literal("")).getString())
//                        .color(java.awt.Color.GRAY.getRGB(), java.awt.Color.GREEN.getRGB(), java.awt.Color.GREEN.getRGB(), Color.WHITE.getRGB())
//                );
//            }
//        }
//        if (blockEntity instanceof TileWorkbench workbench) {
//            if (workbench.getRecipe().hasContent()) {
//                iProbeInfo.horizontal()
//                        .text(I18N.getComponent("ts.tips.top.crafting"), new TextStyle().topPadding(5))
//                        .item(workbench.getRecipe().output)
//                        .text(workbench.getRecipe().output.getHoverName(),new TextStyle().topPadding(5));
//            }
//        }
//    }
//}
