package me.lyuxc.develop.compat.theoneprobe.applied;

import appeng.api.storage.cells.CellState;
import appeng.blockentity.storage.DriveBlockEntity;
import appeng.items.storage.BasicStorageCell;
import mcjty.theoneprobe.api.*;
import mcjty.theoneprobe.apiimpl.styles.ProgressStyle;
import me.lyuxc.develop.Star;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.awt.Color;

public class Applied implements IProbeInfoProvider {
    @Override
    public ResourceLocation getID() {
        return Star.rl("applied");
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, Player player, Level level, BlockState blockState, IProbeHitData iProbeHitData) {
        if(level.getBlockEntity(iProbeHitData.getPos()) instanceof DriveBlockEntity entity) {
            int j = entity.getCellCount();
            for(int i=0;i<j;i = i + 2) {
                CellState state1 = entity.getCellStatus(i);
                CellState state2 = entity.getCellStatus(i + 1);
                try {
                    int type1 = 0;
                    int type2 = 0;
                    int maxType1 = 0;
                    int maxType2 = 0;
                    if(entity.getCellItem(i) instanceof BasicStorageCell cell1) {
                        type1 = entity.getCellInventory(i).getAvailableStacks().size();
                        maxType1 = cell1.getTotalTypes(cell1.getDefaultInstance());
                    }
                    if(entity.getCellItem(i + 1) instanceof BasicStorageCell cell2) {
                        type2 = entity.getCellInventory(i + 1).getAvailableStacks().size();
                        maxType2 = cell2.getTotalTypes(cell2.getDefaultInstance());
                    }
                    iProbeInfo.horizontal().progress(Math.max(type1, 0),maxType1,getProgressStyle(state1,maxType1))
                            .progress(Math.max(type2, 0),maxType2,getProgressStyle(state2,maxType2));
                } catch (Exception e) {
                    e.fillInStackTrace();
                }
            }
            //最大类型 cell.getTotalTypes(entity.getCellItem(0).getDefaultInstance())
            //已用类型 entity.getCellInventory(0).getAvailableStacks().size()
        }
    }
    enum CellStateProgress {
        ABSENT(Color.GRAY.getRGB()),

        EMPTY(Color.GREEN.getRGB()),

        NOT_EMPTY(Color.CYAN.getRGB()),

        TYPES_FULL(Color.ORANGE.getRGB()),

        FULL(Color.RED.getRGB());
        final int color;
        CellStateProgress(int color) {
            this.color = color;
        }
        public int getColor() {
            return this.color;
        }
    }
    private ProgressStyle getProgressStyle(CellState state,int maxType) {
        return new ProgressStyle()
                .suffix("/" + maxType)
                .filledColor(getProgress(state).getColor())
                .alternateFilledColor(getProgress(state).getColor())
                .alignment(ElementAlignment.ALIGN_CENTER)
                .width(50)
                .backgroundColor(0xFFFFFF)
                .borderColor(Color.GRAY.getRGB())
                ;
    }
    private CellStateProgress getProgress(CellState slotState) {
        switch (slotState) {
            case CellState.EMPTY -> {
                return CellStateProgress.EMPTY;
            }
            case CellState.NOT_EMPTY -> {
                return CellStateProgress.NOT_EMPTY;
            }
            case CellState.TYPES_FULL -> {
                return CellStateProgress.TYPES_FULL;
            }
            case CellState.FULL -> {
                return CellStateProgress.FULL;
            }
            default -> {
                return CellStateProgress.ABSENT;
            }
        }
    }
}
