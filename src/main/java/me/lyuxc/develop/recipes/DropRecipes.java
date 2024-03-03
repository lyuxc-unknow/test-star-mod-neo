package me.lyuxc.develop.recipes;

import me.lyuxc.develop.utils.Utils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public record DropRecipes(ItemStack input, ItemStack offhandItems, int quantityConsumed, ItemStack output,
                          int outputCount) {
    //丢东西合成
    public static Set<DropRecipes> recipes = new HashSet<>();

    public static void addPlayerPickupRecipes(String recipe) {
        String[] v = recipe.split("@");
        ItemStack inputItem = Utils.getItemStack(v[0]);
        ItemStack offhandItem = Utils.getItemStack(v[1]);
        ItemStack outputItem = Utils.getItemStack(v[3]);
        offhandItem.setHoverName(Component.translatable("ts.tips.jei.offhandTip"));
        recipes.add(new DropRecipes(inputItem, offhandItem, Integer.parseInt(v[2]), outputItem, Integer.parseInt(v[4])));
    }

    @SuppressWarnings("unused")
    public static void addPlayerPickupRecipes(@NotNull String input, @NotNull String offHandItem, int quantityConsumed, @NotNull String output, int outputCount) {
        addPlayerPickupRecipes(input + "@" + offHandItem + "@" + quantityConsumed + "@" + output + "@" + outputCount);
    }

    public static void addPlayerPickupRecipes(@NotNull Item input, @NotNull Item offHandItem, int quantityConsumed, @NotNull Item output, int outputCount) {
        ItemStack inputItem = input.getDefaultInstance();
        ItemStack offhandItem = offHandItem.getDefaultInstance();
        ItemStack outputItem = output.getDefaultInstance();
        recipes.add(new DropRecipes(inputItem, offhandItem, quantityConsumed, outputItem, outputCount));
    }
}
