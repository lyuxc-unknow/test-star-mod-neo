package me.lyuxc.develop.recipes;

import me.lyuxc.develop.utils.Utils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;

public record RandomDropCraftingRecipes(ItemStack inputItem,ItemStack outputItem) {
    public static void addRandomDropCraftingRecipe(ItemStack inputItem,ItemStack outputItem) {
        DropCraftingRecipes.addPlayerPickupRecipes(inputItem.getItem(),Utils.getRandomItemStack(), RandomSource.create().nextInt(64),outputItem.getItem(),1);
    }
}
