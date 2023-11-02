package me.lyuxc.develop.block.blocks;

import me.lyuxc.develop.Star;
import me.lyuxc.develop.utils.TimeToTickUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class FanBlock extends Block {
    public FanBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void stepOn(@NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull BlockState pState, @NotNull Entity pEntity) {
        if (pEntity instanceof Player player) {
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, TimeToTickUtil.getTime(3), 10, false, false));
        } else {
//            pEntity.moveTo(pEntity.getX(), pEntity.getY() + Star.random.nextInt(50), pEntity.getZ());
            pEntity.moveTo(pEntity.getX(), pEntity.getY() + Star.random.nextInt(50), pEntity.getZ());
        }
        super.stepOn(pLevel, pPos, pState, pEntity);
    }
}
