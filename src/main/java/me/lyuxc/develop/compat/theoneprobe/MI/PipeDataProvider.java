package me.lyuxc.develop.compat.theoneprobe.MI;

import aztech.modern_industrialization.MIText;
import aztech.modern_industrialization.pipes.electricity.ElectricityNetworkNode;
import aztech.modern_industrialization.pipes.fluid.FluidNetworkNode;
import aztech.modern_industrialization.pipes.impl.PipeBlockEntity;
import aztech.modern_industrialization.pipes.item.ItemNetworkNode;
import mcjty.theoneprobe.api.*;
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

public class PipeDataProvider implements IProbeInfoProvider {
    @Override
    public ResourceLocation getID() {
        return Star.rl("pipe");
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, Player player, Level level, BlockState blockState, IProbeHitData iProbeHitData) {
        if(level.getBlockEntity(iProbeHitData.getPos()) instanceof PipeBlockEntity pipeBlock) {
            pipeBlock.getNodes().forEach(pipeNetworkNode -> {
                if(pipeNetworkNode instanceof FluidNetworkNode fluidNetworkNode) {
                    FluidNetworkNode.InGameInfo info = fluidNetworkNode.collectNetworkInfo();
                    Fluid fluid = info.fluid().getFluid();
                    String perfix = MIText.Empty.text().getString() + ":";
                    if(fluid != Fluids.EMPTY) {
                        perfix = Component.translatable("block." + BuiltInRegistries.FLUID.getKey(fluid).toLanguageKey()).getString() + ":";
                    }
                    iProbeInfo.horizontal().element(new ElementTank(
                                    new TankReference((int) info.capacity(),(int) info.stored(),new FluidStack(fluid,(int) info.stored())),
                                    new ProgressStyle()
                                            .alignment(ElementAlignment.ALIGN_CENTER)
                                            .width(80)
                                            .backgroundColor(Color.WHITE.getRGB())
                                            .prefix(perfix)
                                            .suffix("mB/" + info.capacity() + "mB")
                            )
                    ).element(new ElementTank(
                            new TankReference((int) info.transfer(),(int) info.maxTransfer(),new FluidStack(fluid,(int) info.transfer())),
                            new ProgressStyle()
                                    .alignment(ElementAlignment.ALIGN_CENTER)
                                    .width(115)
                                    .backgroundColor(Color.WHITE.getRGB())
                                    .prefix(MIText.NetworkTransfer.text().getString() + ":")
                                    .suffix("mB/" + info.maxTransfer() + "mB")
                    )).vertical();
                }
                if(pipeNetworkNode instanceof ElectricityNetworkNode electricityNetworkNode) {
                    ElectricityNetworkNode.InGameInfo info = electricityNetworkNode.collectNetworkInfo();
                    iProbeInfo.horizontal().progress(info.stored(), info.capacity(), new ProgressStyle()
                            .alignment(ElementAlignment.ALIGN_CENTER)
                            .width(80)
                            .suffix("EU/" + info.capacity() + "EU")
                            .backgroundColor(java.awt.Color.WHITE.getRGB())
                            .filledColor(java.awt.Color.RED.getRGB())
                            .alternateFilledColor(java.awt.Color.GRAY.getRGB())
                    ).progress(info.transfer(), info.maxTransfer(), new ProgressStyle()
                            .alignment(ElementAlignment.ALIGN_CENTER)
                            .width(115)
                            .prefix(MIText.NetworkTransfer.text().getString() + ":")
                            .suffix("EU/" + info.maxTransfer() + "EU")
                            .backgroundColor(java.awt.Color.WHITE.getRGB())
                            .filledColor(java.awt.Color.RED.getRGB())
                            .alternateFilledColor(java.awt.Color.GRAY.getRGB())
                    ).vertical();
                }
                if(pipeNetworkNode instanceof ItemNetworkNode itemNetworkNode) {
                    ItemNetworkNode.InGameInfo info = itemNetworkNode.collectNetworkInfo();
                    iProbeInfo.text(MIText.NetworkMovedItems.text().getString() + ":" + info.movedItems());
                    iProbeInfo.progress((60 - info.pulse()) / 20, 60, new ProgressStyle()
                            .prefix(MIText.NetworkDelay.text().getString() + ":")
                            .alternateFilledColor(java.awt.Color.ORANGE.getRGB())
                            .filledColor(java.awt.Color.YELLOW.getRGB())
                            .backgroundColor(Color.WHITE.getRGB())
                    );
                }
            });
        }
    }
}
