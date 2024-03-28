package me.lyuxc.develop.compat.theoneprobe.MI;

import aztech.modern_industrialization.MIText;
import aztech.modern_industrialization.machines.blockentities.multiblocks.ElectricBlastFurnaceBlockEntity;
import aztech.modern_industrialization.machines.blockentities.multiblocks.ElectricCraftingMultiblockBlockEntity;
import aztech.modern_industrialization.machines.blockentities.multiblocks.GeneratorMultiblockBlockEntity;
import aztech.modern_industrialization.machines.blockentities.multiblocks.LargeTankMultiblockBlockEntity;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ProbeMode;
import me.lyuxc.develop.Star;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class MultiblockProvider implements IProbeInfoProvider {
    @Override
    public ResourceLocation getID() {
        return Star.rl("test");
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, Player player, Level level, BlockState blockState, IProbeHitData iProbeHitData) {
        BlockEntity blockEntity = level.getBlockEntity(iProbeHitData.getPos());
        String statusText = MIText.MultiblockShapeInvalid.text().getString();
        if (blockEntity instanceof ElectricCraftingMultiblockBlockEntity && ((ElectricCraftingMultiblockBlockEntity) blockEntity).isEnabled()) {
            statusText = MIText.MultiblockShapeValid.text().getString();
        } else if (blockEntity instanceof ElectricBlastFurnaceBlockEntity && ((ElectricBlastFurnaceBlockEntity) blockEntity).isEnabled()) {
            statusText = MIText.MultiblockShapeValid.text().getString();
        } else if (blockEntity instanceof GeneratorMultiblockBlockEntity && ((GeneratorMultiblockBlockEntity) blockEntity).isShapeValid()) {
            statusText = MIText.MultiblockShapeValid.text().getString();
        } else if (blockEntity instanceof LargeTankMultiblockBlockEntity && ((LargeTankMultiblockBlockEntity) blockEntity).isShapeValid()) {
            statusText = MIText.MultiblockShapeValid.text().getString();
        }
        if (blockEntity instanceof ElectricCraftingMultiblockBlockEntity || blockEntity instanceof ElectricBlastFurnaceBlockEntity ||
                blockEntity instanceof GeneratorMultiblockBlockEntity || blockEntity instanceof  LargeTankMultiblockBlockEntity)
            iProbeInfo.text(statusText);
    }
}
