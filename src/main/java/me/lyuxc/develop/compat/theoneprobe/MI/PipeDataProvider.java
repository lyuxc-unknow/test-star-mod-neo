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
                    var info = fluidNetworkNode.collectNetworkInfo();
                    Fluid fluid = info.fluid().getFluid();
                    String perfix = MIText.Empty.text().getString() + ":";
                    if(fluid != Fluids.EMPTY) {
                        perfix = Component.translatable("block." + BuiltInRegistries.FLUID.getKey(fluid).toLanguageKey()).getString() + ":";
                    }
                    iProbeInfo.element(new ElementTank(
                                    new TankReference((int) info.capacity(),(int) info.stored(),new FluidStack(fluid,(int) info.stored())),
                                    new ProgressStyle()
                                            .width(120)
                                            .backgroundColor(java.awt.Color.WHITE.getRGB())
                                            .prefix(perfix)
                                            .suffix("mB/" + info.capacity() + "mB")
                            )
                    );
                    iProbeInfo.element(new ElementTank(
                            new TankReference((int) info.transfer(),(int) info.maxTransfer(),new FluidStack(fluid,(int) info.transfer())),
                            new ProgressStyle()
                                    .width(120)
                                    .backgroundColor(java.awt.Color.WHITE.getRGB())
                                    .prefix(MIText.NetworkTransfer.text().getString() + ":")
                                    .suffix("mB/" + info.maxTransfer() + "mB")
                    ));
                }
                if(pipeNetworkNode instanceof ElectricityNetworkNode electricityNetworkNode) {
                    var info = electricityNetworkNode.collectNetworkInfo();
                    iProbeInfo.progress(info.stored(),info.capacity(),new ProgressStyle()
                            .suffix("EU/" + info.capacity() + "EU")
                            .backgroundColor(java.awt.Color.WHITE.getRGB())
                            .filledColor(java.awt.Color.RED.getRGB())
                            .alternateFilledColor(java.awt.Color.GRAY.getRGB())
                    );
                    iProbeInfo.progress(info.transfer(),info.maxTransfer(),new ProgressStyle()
                            .prefix(MIText.NetworkTransfer.text().getString() + ":")
                            .suffix("EU/" + info.maxTransfer() + "EU")
                            .backgroundColor(java.awt.Color.WHITE.getRGB())
                            .filledColor(java.awt.Color.RED.getRGB())
                            .alternateFilledColor(java.awt.Color.GRAY.getRGB())
                    );
                }
                if(pipeNetworkNode instanceof ItemNetworkNode itemNetworkNode) {
                    var info = itemNetworkNode.collectNetworkInfo();
                    iProbeInfo.text(MIText.NetworkMovedItems.text().getString() + ":" + info.movedItems());
                    iProbeInfo.progress(info.pulse(),3 * 20,new ProgressStyle()
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
