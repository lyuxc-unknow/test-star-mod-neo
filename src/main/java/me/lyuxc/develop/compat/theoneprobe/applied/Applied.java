package me.lyuxc.develop.compat.theoneprobe.applied;

import appeng.api.storage.cells.CellState;
import appeng.blockentity.storage.DriveBlockEntity;
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
                iProbeInfo.horizontal().progress(getProgress(state1).getPercentage(),100,getProgressStyle(state1))
                        .progress(getProgress(state2).getPercentage(),100,getProgressStyle(state2));
            }
        }
    }
    enum CellStateProgress {
        ABSENT(0,Color.WHITE.getRGB()),

        EMPTY(100,Color.GREEN.getRGB()),

        NOT_EMPTY(100,Color.CYAN.getRGB()),

        TYPES_FULL(100,Color.ORANGE.getRGB()),

        FULL(100,Color.RED.getRGB());
        final int color;
        final int percentage;
        CellStateProgress(int percentage,int color) {
            this.percentage = percentage;
            this.color = color;
        }
        public int getColor() {
            return this.color;
        }
        public int getPercentage() {
            return this.percentage;
        }
    }
    private ProgressStyle getProgressStyle(CellState state) {
        return new ProgressStyle()
                .filledColor(getProgress(state).getColor())
                .alternateFilledColor(getProgress(state).getColor())
                .alignment(ElementAlignment.ALIGN_CENTER)
                .width(50)
                .showText(false)
                .backgroundColor(0xFFFFFF)
                .borderColor(Color.GRAY.getRGB()
                );
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
