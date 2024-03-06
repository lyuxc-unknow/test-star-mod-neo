package me.lyuxc.develop.compat.JEI;

import me.lyuxc.develop.Variables;
import me.lyuxc.develop.item.ItemRegistry;
import me.lyuxc.develop.recipes.LightningCraftingRecipes;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class LightningCraftingCategory implements IRecipeCategory<LightningCraftingRecipes> {
    private final IGuiHelper helper;
    public LightningCraftingCategory(IGuiHelper helper) {
        this.helper = helper;
    }
    @Override
    public @NotNull RecipeType<LightningCraftingRecipes> getRecipeType() {
        return JEIPlugin.CATEGORY_LIGHTNING;
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.translatable("ts.tips.jei.lightning_category");
    }

    @Override
    public @NotNull IDrawable getBackground() {
        return helper.createDrawable(new ResourceLocation(Variables.MOD_ID,"textures/gui/jei/jei_crafting.png"),0,80,80,20);
    }

    @Override
    public @NotNull IDrawable getIcon() {
        return helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, ItemRegistry.LIGHT_AR.toStack());
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull LightningCraftingRecipes recipes, @NotNull IFocusGroup iFocusGroup) {
        builder.addSlot(RecipeIngredientRole.INPUT,2,2).addItemStack(recipes.input());
        builder.addSlot(RecipeIngredientRole.OUTPUT,62,2).addItemStack(recipes.output());
    }
}
