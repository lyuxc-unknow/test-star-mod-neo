package me.lyuxc.develop.compat.JEI;

import me.lyuxc.develop.Variables;
import me.lyuxc.develop.recipes.DropRecipes;
import me.lyuxc.develop.recipes.ExplosionMultiItemRecipes;
import me.lyuxc.develop.recipes.ExplosionRecipes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

@JeiPlugin
public class JEIPlugin implements IModPlugin {
    public static final RecipeType<DropRecipes> CATEGORY_DROP = RecipeType.create(Variables.MOD_ID,"drop",DropRecipes.class);
    public static final RecipeType<ExplosionRecipes> CATEGORY_EXPLOSION = RecipeType.create(Variables.MOD_ID,"explosion",ExplosionRecipes.class);
    public static final RecipeType<ExplosionMultiItemRecipes> CATEGORY_MULTI_EXPLOSION = RecipeType.create(Variables.MOD_ID,"multi", ExplosionMultiItemRecipes.class);
    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return new ResourceLocation(Variables.MOD_ID,"test_star_jei");
    }

    @Override
    public void registerRecipeCatalysts(@NotNull IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(Items.TNT.getDefaultInstance(),CATEGORY_EXPLOSION);
        registration.addRecipeCatalyst(Items.TNT.getDefaultInstance(),CATEGORY_MULTI_EXPLOSION);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new DropRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new ExplosionRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new MultiExplosionRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
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
        registration.addRecipes(CATEGORY_EXPLOSION, ExplosionRecipes.recipes.stream().map(explosionRecipes -> {
            ItemStack input = explosionRecipes.input();
            ItemStack output = explosionRecipes.output();
            input.setCount(explosionRecipes.inputCount());
            return new ExplosionRecipes(input,explosionRecipes.inputCount(),output,explosionRecipes.change());
        }).toList());
        registration.addRecipes(CATEGORY_MULTI_EXPLOSION,ExplosionMultiItemRecipes.recipes.stream().toList());
    }
}
