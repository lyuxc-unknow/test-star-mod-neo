package me.lyuxc.develop.item.tools;

import me.lyuxc.develop.Tiers.Tiers;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import org.jetbrains.annotations.NotNull;

public class Level8Sword extends SwordItem {
    public Level8Sword(Properties properties) {
        super(Tiers.LEVEL8, 0, 65535 - 4, properties);
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack pStack, @NotNull LivingEntity pTarget, @NotNull LivingEntity pAttacker) {
        pTarget.setHealth(pTarget.getHealth() - 50);
        if (pAttacker instanceof Player) {
            pAttacker.setHealth(pAttacker.getHealth() - 10);
        }
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }
}
