package me.lyuxc.develop.compat.JEI;

import me.lyuxc.develop.Variables;
import me.lyuxc.develop.recipes.DropRecipes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

@JeiPlugin
public class JEIPlugin implements IModPlugin {
    public static final RecipeType<DropRecipes> CATEGORY_DROP = RecipeType.create(Variables.MOD_ID,"drop",DropRecipes.class);
    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return new ResourceLocation(Variables.MOD_ID,"test_star_jei");
    }

    @Override
    public void registerRecipeCatalysts(@NotNull IRecipeCatalystRegistration registration) {
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new DropRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(@NotNull IRecipeRegistration registration) {
        registration.addRecipes(CATEGORY_DROP,DropRecipes.recipes.stream().map(dropRecipes -> {
            ItemStack input = dropRecipes.input();
            ItemStack offhand = dropRecipes.offhandItems();
            ItemStack output = dropRecipes.output();
            offhand.setCount(dropRecipes.quantityConsumed()==0?1: dropRecipes.quantityConsumed());
            output.setCount(dropRecipes.outputCount());
            return new DropRecipes(input,offhand, dropRecipes.quantityConsumed(), output, dropRecipes.outputCount());
        }).toList());
    }
}
