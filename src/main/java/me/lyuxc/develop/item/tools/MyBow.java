package me.lyuxc.develop.item.tools;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class MyBow extends BowItem {
    public MyBow(Properties properties) {
        super(properties);
    }

    @Override
    public int getUseDuration(@NotNull ItemStack p_40680_) {
        return -75000;
    }

    @Override
    public float getDestroySpeed(@NotNull ItemStack p_41425_, @NotNull BlockState p_41426_) {
        return -1;
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack p_41395_, @NotNull LivingEntity pAtt, @NotNull LivingEntity player) {
        pAtt.hurt(new DamageSource(player.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.PLAYER_ATTACK)), 9999F);
        return super.hurtEnemy(p_41395_, pAtt, player);
    }
}
