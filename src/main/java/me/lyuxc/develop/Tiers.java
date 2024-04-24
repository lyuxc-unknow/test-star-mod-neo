package me.lyuxc.develop;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

public enum Tiers implements Tier {
    LEVEL1(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 100, 5, 2 - 1, 100),
    LEVEL2(BlockTags.INCORRECT_FOR_GOLD_TOOL, 200, 9, 4 - 1, 100),
    LEVEL3(BlockTags.INCORRECT_FOR_STONE_TOOL, 400, 13, 6 - 1, 100),
    LEVEL4(BlockTags.INCORRECT_FOR_IRON_TOOL, 800, 15, 8 - 1, 100),
    LEVEL5(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1600, 18, 10 - 1, 100),
    LEVEL6(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 3200, 20, 12 - 1, 100),
    LEVEL7(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 6400, 25, 20 - 1, 100),
    LEVEL8(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 12800, 29, 50 - 1, 100),
    LEVEL_INF(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, -1, 99999, Long.MAX_VALUE - 1, 1024);

    final int uses, EnchantmentValue;
    final float speed,damageBonus;
    private final TagKey<Block> correctBlocksForDrops;

    Tiers(TagKey<Block> correctBlocksForDrops, int uses, float speed, float damageBonus, int EnchantmentValue) {
        this.correctBlocksForDrops = correctBlocksForDrops;
        this.uses = uses;
        this.speed = speed;
        this.damageBonus = damageBonus;
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
    public @NotNull TagKey<Block> getIncorrectBlocksForDrops() {
        return this.correctBlocksForDrops;
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