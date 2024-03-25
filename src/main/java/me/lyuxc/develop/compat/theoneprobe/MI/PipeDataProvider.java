package me.lyuxc.develop.compat.theoneprobe.MI;

import aztech.modern_industrialization.MIText;
import aztech.modern_industrialization.pipes.MIPipes;
import aztech.modern_industrialization.pipes.api.PipeNetworkNode;
import aztech.modern_industrialization.pipes.electricity.ElectricityNetworkNode;
import aztech.modern_industrialization.pipes.fluid.FluidNetworkNode;
import aztech.modern_industrialization.pipes.impl.PipeBlockEntity;
import aztech.modern_industrialization.pipes.impl.PipeVoxelShape;
import aztech.modern_industrialization.pipes.item.ItemNetworkNode;
import mcjty.theoneprobe.api.*;
import mcjty.theoneprobe.apiimpl.elements.ElementTank;
import mcjty.theoneprobe.apiimpl.styles.ProgressStyle;
import me.lyuxc.develop.Star;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
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
            for(PipeNetworkNode node : pipeBlock.getNodes()) {
                if(node instanceof FluidNetworkNode fluidNetworkNode) {
                    var info = fluidNetworkNode.collectNetworkInfo();
                    Fluid fluid = info.fluid().getFluid();
                    String perfix = Component.translatable("text.modern_industrialization.Empty").getString() + ":";
                    if(fluid != Fluids.EMPTY) {
                        perfix = Component.translatable("block." + BuiltInRegistries.FLUID.getKey(fluid).toLanguageKey()).getString() + ":";
                    }
                    iProbeInfo.element(new ElementTank(
                            new TankReference((int) info.capacity(),(int) info.stored(),new FluidStack(fluid,(int) info.stored())),
                            new ProgressStyle()
                                    .width(120)
                                    .backgroundColor(Color.WHITE.getRGB())
                                    .prefix(perfix)
                                    .suffix("mB/" + info.capacity() + "mB")
                        )
                    );
                    iProbeInfo.element(new ElementTank(
                            new TankReference((int) info.transfer(),(int) info.maxTransfer(),new FluidStack(fluid,(int) info.transfer())),
                            new ProgressStyle()
                                    .width(120)
                                    .backgroundColor(Color.WHITE.getRGB())
                                    .prefix(Component.translatable("text.modern_industrialization.NetworkTransfer").getString() + ":")
                                    .suffix("mB/" + info.maxTransfer() + "mB")
                    ));
                }
                if(node instanceof ElectricityNetworkNode electricityNetworkNode) {
                    var info = electricityNetworkNode.collectNetworkInfo();
                    iProbeInfo.progress(info.stored(),info.capacity(),new ProgressStyle()
                            .suffix("EU/" + info.capacity() + "EU")
                            .backgroundColor(Color.WHITE.getRGB())
                            .filledColor(Color.RED.getRGB())
                            .alternateFilledColor(Color.GRAY.getRGB())
                    );
                    PipeVoxelShape shape = getPipeVoxelShape(level,iProbeHitData);
                    if (shape != null) {
                        iProbeInfo.text(MIText.NetworkTier.text().getString() + ":" + ChatFormatting.GOLD + MIPipes.ELECTRICITY_PIPE_TIER.get(MIPipes.INSTANCE.getPipeItem(shape.type).type).longEnglishName().getString() + ChatFormatting.RESET);
                    }
                    iProbeInfo.progress(info.transfer(),info.maxTransfer(),new ProgressStyle()
                            .prefix(Component.translatable("text.modern_industrialization.NetworkTransfer").getString() + ":")
                            .suffix("EU/" + info.maxTransfer() + "EU")
                            .backgroundColor(Color.WHITE.getRGB())
                            .filledColor(Color.RED.getRGB())
                            .alternateFilledColor(Color.GRAY.getRGB())
                    );
                }
                if(node instanceof ItemNetworkNode itemNetworkNode) {
                    var info = itemNetworkNode.collectNetworkInfo();
                    iProbeInfo.text(Component.translatable("text.modern_industrialization.NetworkMovedItems").getString() + ":" + info.movedItems());
                    iProbeInfo.progress(info.pulse(),3 * 20,new ProgressStyle()
                            .prefix(Component.translatable("text.modern_industrialization.NetworkDelay").getString() + ":")
                            .alternateFilledColor(Color.ORANGE.getRGB())
                            .filledColor(Color.YELLOW.getRGB())
                            .backgroundColor(Color.WHITE.getRGB())
                    );
                }
            }
        }
    }
    private PipeVoxelShape getPipeVoxelShape(Level level,IProbeHitData iProbeHitData) {
        PipeBlockEntity pipe = (PipeBlockEntity) level.getBlockEntity(iProbeHitData.getPos());
        Vec3 hitPos = iProbeHitData.getHitVec();
        BlockPos blockPos = iProbeHitData.getPos();
        if (pipe != null) {
            for (PipeVoxelShape partShape : pipe.getPartShapes()) {
                Vec3 posInBlock = hitPos.subtract(blockPos.getX(), blockPos.getY(), blockPos.getZ());
                for (AABB box : partShape.shape.toAabbs()) {
                    Vec3 dir = box.getCenter().subtract(posInBlock).normalize().scale(1e-4);
                    if (box.contains(posInBlock.add(dir))) {
                        return partShape;
                    }
                }
            }
        }
        return null;
    }
}
