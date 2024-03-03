package me.lyuxc.develop.compat.JEI;

import me.lyuxc.develop.Variables;
import me.lyuxc.develop.recipes.DropRecipes;
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
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

public class DropRecipeCategory implements IRecipeCategory<DropRecipes> {
    private final IGuiHelper helper;
    public DropRecipeCategory(IGuiHelper helper) {
        this.helper = helper;
    }
    @Override
    public @NotNull RecipeType<DropRecipes> getRecipeType() {
        return JEIPlugin.CATEGORY_DROP;
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.translatable("ts.tips.jei.drop_category");
    }

    @Override
    public @NotNull IDrawable getBackground() {
        return helper.createDrawable(new ResourceLocation(Variables.MOD_ID,"textures/gui/jei/drop_crafting.png"),0,0,100,20);
    }

    @Override
    public @NotNull IDrawable getIcon() {
        return helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, Items.ANVIL.getDefaultInstance());
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder iRecipeLayoutBuilder, @NotNull DropRecipes dropRecipes, @NotNull IFocusGroup iFocusGroup) {
        iRecipeLayoutBuilder.addSlot(RecipeIngredientRole.INPUT,3,2).addItemStack(dropRecipes.input());
        iRecipeLayoutBuilder.addSlot(RecipeIngredientRole.INPUT,22,2).addItemStack(dropRecipes.offhandItems());
        iRecipeLayoutBuilder.addSlot(RecipeIngredientRole.OUTPUT,81,2).addItemStack(dropRecipes.output());
    }
}