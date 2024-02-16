package me.lyuxc.develop.item.tools;

import me.lyuxc.develop.Variables;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import org.jetbrains.annotations.NotNull;

public class TetanusBlade extends SwordItem {
    public TetanusBlade(Properties pProperties) {
        super(Tiers.IRON, 0, 65535, pProperties);
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        double i = pAttacker.getHealth() * 0.5;
        pAttacker.setHealth((float) i);
        pTarget.hurt(new DamageSource(pAttacker.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.PLAYER_ATTACK)), (float) Math.max(i,0.5));
        pAttacker.setHealth(pAttacker.getHealth() + Variables.random.nextInt((int) (pTarget.getMaxHealth() * 0.5)));
        return true;
    }
}
