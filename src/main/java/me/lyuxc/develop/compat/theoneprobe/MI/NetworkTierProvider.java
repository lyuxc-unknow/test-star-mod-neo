//package me.lyuxc.develop.compat.theoneprobe.MI;
//
//import aztech.modern_industrialization.MIText;
//import aztech.modern_industrialization.pipes.MIPipes;
//import aztech.modern_industrialization.pipes.electricity.ElectricityNetworkNode;
//import aztech.modern_industrialization.pipes.impl.PipeBlockEntity;
//import aztech.modern_industrialization.pipes.impl.PipeVoxelShape;
//import mcjty.theoneprobe.api.IProbeHitData;
//import mcjty.theoneprobe.api.IProbeInfo;
//import mcjty.theoneprobe.api.IProbeInfoProvider;
//import mcjty.theoneprobe.api.ProbeMode;
//import me.lyuxc.develop.Star;
//import me.lyuxc.develop.utils.TOPUtils;
//import net.minecraft.ChatFormatting;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.block.state.BlockState;
//
//public class NetworkTierProvider implements IProbeInfoProvider {
//    @Override
//    public ResourceLocation getID() {
//        return Star.rl("pipe_tier");
//    }
//
//    @Override
//    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, Player player, Level level, BlockState blockState, IProbeHitData iProbeHitData) {
//        if(level.getBlockEntity(iProbeHitData.getPos()) instanceof PipeBlockEntity pipeBlock) {
//            if(pipeBlock.getNodes().first() instanceof ElectricityNetworkNode) {
//                PipeVoxelShape shape = TOPUtils.getPipeVoxelShape(pipeBlock, iProbeHitData);
//                if (shape != null) {
//                    if(!MIPipes.INSTANCE.getPipeItem(shape.type).isItemPipe() && !MIPipes.INSTANCE.getPipeItem(shape.type).isFluidPipe())
//                        iProbeInfo.text(MIText.NetworkTier.text().getString() + ":" + ChatFormatting.GOLD + MIPipes.ELECTRICITY_PIPE_TIER.get(MIPipes.INSTANCE.getPipeItem(shape.type).type).longEnglishName().getString() + ChatFormatting.RESET);
//                }
//            }
//        }
//    }
//}
