package me.lyuxc.develop.compat.theoneprobe.MI;

import aztech.modern_industrialization.MIText;
import aztech.modern_industrialization.pipes.api.PipeNetworkNode;
import aztech.modern_industrialization.pipes.electricity.ElectricityNetworkNode;
import aztech.modern_industrialization.pipes.fluid.FluidNetworkNode;
import aztech.modern_industrialization.pipes.impl.PipeBlockEntity;
import aztech.modern_industrialization.pipes.item.ItemNetworkNode;
import mcjty.theoneprobe.api.*;
import mcjty.theoneprobe.apiimpl.elements.ElementProgress;
import mcjty.theoneprobe.apiimpl.elements.ElementTank;
import mcjty.theoneprobe.apiimpl.styles.ProgressStyle;
import me.lyuxc.develop.Star;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.fluids.FluidStack;

import java.awt.Color;
import java.util.List;

public class PipeDataProvider implements IProbeInfoProvider {
    private int va = 0;
    @Override
    public ResourceLocation getID() {
        return Star.rl("pipe");
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, Player player, Level level, BlockState blockState, IProbeHitData iProbeHitData) {
        if(level.getBlockEntity(iProbeHitData.getPos()) instanceof PipeBlockEntity pipeBlock) {
            List<PipeNetworkNode> pipeNetworkNode = pipeBlock.getNodes().stream().toList();
            addInfo(pipeNetworkNode.get(va / 12 % pipeNetworkNode.size()), iProbeInfo);
            va++;
        }
    }

    private void addInfo(PipeNetworkNode pipeNetworkNode, IProbeInfo iProbeInfo) {
        if (pipeNetworkNode instanceof FluidNetworkNode fluidNetworkNode) {
            FluidNetworkNode.InGameInfo info = fluidNetworkNode.collectNetworkInfo();
            Fluid fluid = info.fluid().getFluid();
            String perfix = MIText.Empty.text().getString() + ":";
            if (fluid != Fluids.EMPTY) {
                perfix = Component.translatable("block." + BuiltInRegistries.FLUID.getKey(fluid).toLanguageKey()).getString() + ":";
            }
            iProbeInfo.element(new ElementTank(
                            new TankReference((int) info.capacity(), (int) info.stored(), new FluidStack(fluid, (int) info.stored())),
                            new ProgressStyle()
                                    .alignment(ElementAlignment.ALIGN_CENTER)
                                    .numberFormat(NumberFormat.COMPACT)
                                    .width(120)
                                    .backgroundColor(Color.WHITE.getRGB())
                                    .prefix(perfix)
                                    .suffix("mB/" + ElementProgress.format(info.capacity(), NumberFormat.COMPACT, Component.literal("mB")).getString())
                    )
            ).element(new ElementTank(
                    new TankReference((int) info.transfer(), (int) info.maxTransfer(), new FluidStack(fluid, (int) info.transfer())),
                    new ProgressStyle()
                            .alignment(ElementAlignment.ALIGN_CENTER)
                            .numberFormat(NumberFormat.COMPACT)
                            .width(120)
                            .backgroundColor(Color.WHITE.getRGB())
                            .prefix(MIText.NetworkTransfer.text().getString() + ":")
                            .suffix("mB/" + ElementProgress.format(info.maxTransfer(), NumberFormat.COMPACT, Component.literal("mB")).getString())
            ));
        }
        if (pipeNetworkNode instanceof ElectricityNetworkNode electricityNetworkNode) {
            ElectricityNetworkNode.InGameInfo info = electricityNetworkNode.collectNetworkInfo();
            iProbeInfo.element(new ElementProgress(info.stored(), info.capacity(), new ProgressStyle()
                    .alignment(ElementAlignment.ALIGN_CENTER)
                    .numberFormat(NumberFormat.COMPACT)
                    .width(120)
                    .suffix("EU/" + ElementProgress.format(info.capacity(), NumberFormat.COMPACT, Component.literal("EU")).getString())
                    .backgroundColor(Color.WHITE.getRGB())
                    .filledColor(Color.RED.getRGB())
                    .alternateFilledColor(Color.GRAY.getRGB())
            )).element(new ElementProgress(info.transfer(), info.maxTransfer(), new ProgressStyle()
                    .alignment(ElementAlignment.ALIGN_CENTER)
                    .numberFormat(NumberFormat.COMPACT)
                    .width(120)
                    .prefix(MIText.NetworkTransfer.text().getString() + ":")
                    .suffix("EU/" + ElementProgress.format(info.maxTransfer(), NumberFormat.COMPACT, Component.literal("EU")).getString())
                    .backgroundColor(Color.WHITE.getRGB())
                    .filledColor(Color.RED.getRGB())
                    .alternateFilledColor(Color.GRAY.getRGB())));
        }
        if (pipeNetworkNode instanceof ItemNetworkNode itemNetworkNode) {
            ItemNetworkNode.InGameInfo info = itemNetworkNode.collectNetworkInfo();
            iProbeInfo.text(MIText.NetworkMovedItems.text().getString() + ":" + info.movedItems());
            iProbeInfo.progress((60 - info.pulse()) / 20, 3, new ProgressStyle()
                    .prefix(MIText.NetworkDelay.text().getString() + ":")
                    .alternateFilledColor(Color.ORANGE.getRGB())
                    .filledColor(Color.YELLOW.getRGB())
                    .backgroundColor(Color.WHITE.getRGB())
            );
        }
    }
}
