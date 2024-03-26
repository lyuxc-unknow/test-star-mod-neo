package me.lyuxc.develop.compat.theoneprobe.MI;

import aztech.modern_industrialization.MIText;
import aztech.modern_industrialization.pipes.MIPipes;
import aztech.modern_industrialization.pipes.electricity.ElectricityNetworkNode;
import aztech.modern_industrialization.pipes.impl.PipeBlockEntity;
import aztech.modern_industrialization.pipes.impl.PipeVoxelShape;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ProbeMode;
import me.lyuxc.develop.Star;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class NetworkTierProvider implements IProbeInfoProvider {
    @Override
    public ResourceLocation getID() {
        return Star.rl("pipe_tier");
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, Player player, Level level, BlockState blockState, IProbeHitData iProbeHitData) {
        if(level.getBlockEntity(iProbeHitData.getPos()) instanceof PipeBlockEntity pipeBlock) {
            PipeVoxelShape shape = getPipeVoxelShape(pipeBlock,iProbeHitData);
            if(pipeBlock.getNodes().first() instanceof ElectricityNetworkNode) {
                if (shape != null) {
                    if(!MIPipes.INSTANCE.getPipeItem(shape.type).isItemPipe() && !MIPipes.INSTANCE.getPipeItem(shape.type).isFluidPipe())
                        iProbeInfo.text(MIText.NetworkTier.text().getString() + ":" + ChatFormatting.GOLD + MIPipes.ELECTRICITY_PIPE_TIER.get(MIPipes.INSTANCE.getPipeItem(shape.type).type).longEnglishName().getString() + ChatFormatting.RESET);
                }
            }
        }
    }
    private PipeVoxelShape getPipeVoxelShape(PipeBlockEntity pipeBlock,IProbeHitData iProbeHitData) {
        Vec3 hitPos = iProbeHitData.getHitVec();
        BlockPos blockPos = iProbeHitData.getPos();
        if (pipeBlock != null) {
            for (PipeVoxelShape partShape : pipeBlock.getPartShapes()) {
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
