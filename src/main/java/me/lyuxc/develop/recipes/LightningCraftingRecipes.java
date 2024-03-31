package me.lyuxc.develop.recipes;

import me.lyuxc.develop.utils.Utils;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public record LightningCraftingRecipes(ItemStack input,ItemStack output) {
    public static Set<LightningCraftingRecipes> RECIPES = new HashSet<>();

    public static void addLightningCraftingRecipes(String recipe) {
        String[] v = recipe.split("@");
        ItemStack inputItem = Utils.getItemStack(v[0]);
        ItemStack outputItem = Utils.getItemStack(v[1]);
        RECIPES.add(new LightningCraftingRecipes(inputItem, outputItem));
    }

    @SuppressWarnings("unused")
    public static void addLightningCraftingRecipes(@NotNull String input, @NotNull String output) {
        addLightningCraftingRecipes(input + "@" + output);
    }
    @SuppressWarnings("unused")
    public static void addLightningCraftingRecipes(@NotNull Item input, @NotNull Item output) {
        ItemStack inputItem = input.getDefaultInstance();
        ItemStack outputItem = output.getDefaultInstance();
        RECIPES.add(new LightningCraftingRecipes(inputItem, outputItem));
    }
}
