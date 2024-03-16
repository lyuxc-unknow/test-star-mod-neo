package me.lyuxc.develop;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

public enum Tiers implements Tier {
    LEVEL1(100, 5, 2 - 1, 0, 100),
    LEVEL2(200, 9, 4 - 1, 1, 100),
    LEVEL3(400, 13, 6 - 1, 2, 100),
    LEVEL4(800, 15, 8 - 1, 3, 100),
    LEVEL5(1600, 18, 10 - 1, 4, 100),
    LEVEL6(3200, 20, 12 - 1, 5, 100),
    LEVEL7(6400, 25, 20 - 1, 6, 100),
    LEVEL8(12800, 29, 50 - 1, 7, 100),
    LEVEL_INF(-1, 99999, Long.MAX_VALUE - 1, 100, 1024);

    final int uses,level,EnchantmentValue;
    final float speed,damageBonus;

    Tiers(int uses, float speed, float damageBonus, int level, int EnchantmentValue) {
        this.uses = uses;
        this.speed = speed;
        this.damageBonus = damageBonus;
        this.level = level;
        this.EnchantmentValue = EnchantmentValue;
    }

    @Override
    public int getUses() {
        return this.uses;
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.damageBonus;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public int getEnchantmentValue() {
        return this.EnchantmentValue;
    }

    @Override
    public @NotNull Ingredient getRepairIngredient() {
        return Ingredient.EMPTY;
    }
}