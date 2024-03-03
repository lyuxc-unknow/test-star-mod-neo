package me.lyuxc.develop.recipes;

import me.lyuxc.develop.utils.Utils;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public record ExplosionRecipes(ItemStack input,int inputCount,ItemStack output,double change) {
    public static Set<ExplosionRecipes> recipes = new HashSet<>();
    public static void addExplosionRecipes(String recipe) {
        String[] v = recipe.split("@");
        recipes.add(new ExplosionRecipes(Utils.getItemStack(v[0]),Integer.parseInt(v[1]),Utils.getItemStack(v[2]),Double.parseDouble(v[3])));
    }
    @SuppressWarnings("unused")
    public static void addExplosionRecipes(@NotNull String input, int inputCount, @NotNull String output, double change) {
        addExplosionRecipes(input + "@" + inputCount + "@" + output + "@" + change);
    }
    public static void addExplosionRecipes(ItemStack input,int inputCount,ItemStack output,double change) {
        recipes.add(new ExplosionRecipes(input,inputCount,output,change));
    }
}
