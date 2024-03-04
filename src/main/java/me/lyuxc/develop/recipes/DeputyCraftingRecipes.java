package me.lyuxc.develop.recipes;

import me.lyuxc.develop.utils.Utils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public record DeputyCraftingRecipes(ItemStack inputItem,int inputCount,ItemStack outputItem,int outputCount,ItemStack craftingOutputItem) {
    public static Set<DeputyCraftingRecipes> recipes = new HashSet<>();
    public static void addDeputyCraftingRecipes(String recipe) {
        String[] v = recipe.split("@");
        ItemStack inputItem = Utils.getItemStack(v[0]);
        ItemStack offhandItem = Utils.getItemStack(v[2]);
        ItemStack craftingOutputItem = Utils.getItemStack(v[4]);
        offhandItem.setHoverName(Component.translatable("ts.tips.jei.offhandTip"));
        recipes.add(new DeputyCraftingRecipes(inputItem,Integer.parseInt(v[1]),offhandItem,Integer.parseInt(v[3]),craftingOutputItem));
    }
    @SuppressWarnings("unused")
    public static void addDeputyCraftingRecipes(@NotNull String inputItem,@NotNull String inputCount,@NotNull String outputItem,@NotNull String outputCount,@NotNull String craftingOutputItem) {
        addDeputyCraftingRecipes(inputItem + "@" + inputCount + "@" + outputItem + "@" + outputCount + "@" + craftingOutputItem);
    }
    @SuppressWarnings("unused")
    public static void addDeputyCraftingRecipes(@NotNull Item input,int inputCount, @NotNull Item output,int outputCount, @NotNull Item craftingOutputItem) {
        ItemStack inputItem = input.getDefaultInstance();
        ItemStack offhandItem = output.getDefaultInstance();
        ItemStack craftingItems = craftingOutputItem.getDefaultInstance();
        recipes.add(new DeputyCraftingRecipes(inputItem,inputCount,offhandItem,outputCount,craftingItems));
    }
}
