package me.lyuxc.develop.item.tools;

import me.lyuxc.develop.Tiers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class Level1Sword extends SwordItem {
    public Level1Sword(Properties properties) {
        super(Tiers.LEVEL1, properties);
    }
    //是否可以攻击（破坏）方块
    @Override
    public boolean canAttackBlock(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, @NotNull Player player) {
        return false;
    }
}
