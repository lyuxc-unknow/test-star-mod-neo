package me.lyuxc.develop.Tiers;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public enum Tiers implements Tier {
    LEVEL1(-1, 0, 2 - 1, 0, 22),
    LEVEL2(-1, 0, 4 - 1, 0, 100),
    LEVEL3(-1, 0, 6 - 1, 0, 100),
    LEVEL4(-1, 0, 8 - 1, 0, 100),
    LEVEL5(-1, 0, 10 - 1, 0, 100),
    LEVEL6(-1, 0, 12 - 1, 0, 100),
    LEVEL7(-1, 0, 20 - 1, 0, 100),
    LEVEL8(-1, 0, 50 - 1, 0, 100),
    LEVEL_INF(-1, 0, Long.MAX_VALUE - 1, 100, 1024);

    int uses, level, EnchantmentValue;
    float speed, damageBonus;

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
    public Ingredient getRepairIngredient() {
        return null;
    }
}
