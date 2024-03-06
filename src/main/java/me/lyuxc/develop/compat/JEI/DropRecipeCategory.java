package me.lyuxc.develop.compat.JEI;

import me.lyuxc.develop.Variables;
import me.lyuxc.develop.recipes.DropCraftingRecipes;
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

public class DropRecipeCategory implements IRecipeCategory<DropCraftingRecipes> {
    private final IGuiHelper helper;
    public DropRecipeCategory(IGuiHelper helper) {
        this.helper = helper;
    }
    @Override
    public @NotNull RecipeType<DropCraftingRecipes> getRecipeType() {
        return JEIPlugin.CATEGORY_DROP;
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.translatable("ts.tips.jei.drop_category");
    }

    @Override
    public @NotNull IDrawable getBackground() {
        return helper.createDrawable(new ResourceLocation(Variables.MOD_ID,"textures/gui/jei/jei_crafting.png"),0,0,98,20);
    }

    @Override
    public @NotNull IDrawable getIcon() {
        return helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, Items.ANVIL.getDefaultInstance());
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder iRecipeLayoutBuilder, @NotNull DropCraftingRecipes dropCraftingRecipes, @NotNull IFocusGroup iFocusGroup) {
        iRecipeLayoutBuilder.addSlot(RecipeIngredientRole.INPUT,2,2).addItemStack(dropCraftingRecipes.input());
        iRecipeLayoutBuilder.addSlot(RecipeIngredientRole.INPUT,21,2).addItemStack(dropCraftingRecipes.offhandItems());
        iRecipeLayoutBuilder.addSlot(RecipeIngredientRole.OUTPUT,80,2).addItemStack(dropCraftingRecipes.output());
    }
}
