package me.lyuxc.develop.recipes;

import me.lyuxc.develop.utils.Utils;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public record ExplosionCraftingRecipes(ItemStack input, int inputCount, ItemStack output, double change) {
    public static Set<ExplosionCraftingRecipes> recipes = new HashSet<>();
    public static void addExplosionRecipes(String recipe) {
        String[] v = recipe.split("@");
        recipes.add(new ExplosionCraftingRecipes(Utils.getItemStack(v[0]),Integer.parseInt(v[1]),Utils.getItemStack(v[2]),Double.parseDouble(v[3])));
    }
    @SuppressWarnings("unused")
    public static void addExplosionRecipes(@NotNull String input, int inputCount, @NotNull String output, double change) {
        addExplosionRecipes(input + "@" + inputCount + "@" + output + "@" + change);
    }
    @SuppressWarnings("unused")
    public static void addExplosionRecipes(Item input, int inputCount, Item output, double change) {
        recipes.add(new ExplosionCraftingRecipes(input.getDefaultInstance(),inputCount,output.getDefaultInstance(),change));
    }
}
