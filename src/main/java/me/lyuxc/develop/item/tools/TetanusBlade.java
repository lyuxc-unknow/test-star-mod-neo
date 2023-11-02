package me.lyuxc.develop.item.tools;

import me.lyuxc.develop.Star;
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
        pTarget.setHealth((float) (pTarget.getHealth() - (pAttacker.getHealth() * 0.5)));
        pAttacker.setHealth(pAttacker.getHealth() + Star.random.nextInt((int) (pTarget.getMaxHealth() * 0.5)));
        return true;
    }
}
