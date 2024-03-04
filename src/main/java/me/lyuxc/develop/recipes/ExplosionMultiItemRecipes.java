package me.lyuxc.develop.recipes;

import me.lyuxc.develop.utils.Utils;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public record ExplosionMultiItemRecipes(List<ItemStack> inputs,int inputCount,ItemStack output,double change) {
    public static Set<ExplosionMultiItemRecipes> recipes = new HashSet<>();
    public static void addExplosionMultiRecipes(String recipe) {
        String[] v = recipe.split("@");
        recipes.add(new ExplosionMultiItemRecipes(List.of(Utils.getItemStack(v[0]),Utils.getItemStack(v[1])),Integer.parseInt(v[2]),Utils.getItemStack(v[3]),Double.parseDouble(v[4])));
    }
    @SuppressWarnings("unused")
    public static void addExplosionMultiRecipes(@NotNull String[] input, int inputCount, @NotNull String output, double change) {
        addExplosionMultiRecipes(input[0] + "@" + input[1] + "@" + inputCount + "@" + output + "@" + change);
    }
    @SuppressWarnings("unused")
    public static void addExplosionMultiRecipes(List<ItemStack> input, int inputCount, Item output, double change) {
        recipes.add(new ExplosionMultiItemRecipes(input,inputCount,output.getDefaultInstance(),change));
    }
}
