package me.lyuxc.develop.utils;

import aztech.modern_industrialization.pipes.impl.PipeBlockEntity;
import aztech.modern_industrialization.pipes.impl.PipeVoxelShape;
import mcjty.theoneprobe.api.IProbeHitData;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class TOPUtils {
    public static PipeVoxelShape getPipeVoxelShape(PipeBlockEntity pipeBlock, IProbeHitData iProbeHitData) {
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
