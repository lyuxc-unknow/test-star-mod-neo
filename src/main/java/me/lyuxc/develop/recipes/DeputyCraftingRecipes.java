package me.lyuxc.develop.recipes;

import me.lyuxc.develop.utils.Utils;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeManager;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public record DeputyCraftingRecipes(ItemStack inputItem, int inputCount, ItemStack outputItem, int outputCount, ItemStack craftingOutputItem,
                                    List<NonNullList<Ingredient>> recipe) {
    public static Set<DeputyCraftingRecipes> RECIPES = new HashSet<>();
    public static void addDeputyCraftingRecipes(String recipe, RegistryAccess access, RecipeManager recipeManager) {
        String[] v = recipe.split("@");
        ItemStack inputItem = Utils.getItemStack(v[0]);
        ItemStack offhandItem = Utils.getItemStack(v[2]);
        ItemStack craftingOutputItem = Utils.getItemStack(v[4]);
        RECIPES.add(new DeputyCraftingRecipes(inputItem, Integer.parseInt(v[1]), offhandItem, Integer.parseInt(v[3]), craftingOutputItem, Utils.getRecipe(craftingOutputItem, access, recipeManager)));
    }
    @SuppressWarnings("unused")
    public static void addDeputyCraftingRecipes(@NotNull String inputItem,@NotNull String inputCount,@NotNull String outputItem,@NotNull String outputCount,@NotNull String craftingOutputItem,RegistryAccess access,RecipeManager recipeManager) {
        addDeputyCraftingRecipes(inputItem + "@" + inputCount + "@" + outputItem + "@" + outputCount + "@" + craftingOutputItem,access,recipeManager);
    }
    @SuppressWarnings("unused")
    public static void addDeputyCraftingRecipes(@NotNull Item input,int inputCount, @NotNull Item output,int outputCount, @NotNull Item craftingOutputItem,RegistryAccess access,RecipeManager recipeManager) {
        ItemStack inputItem = input.getDefaultInstance();
        ItemStack offhandItem = output.getDefaultInstance();
        ItemStack craftingItems = craftingOutputItem.getDefaultInstance();
        RECIPES.add(new DeputyCraftingRecipes(inputItem, inputCount, offhandItem, outputCount, craftingItems, Utils.getRecipe(craftingOutputItem.getDefaultInstance(), access, recipeManager)));
    }
}
