package me.lyuxc.develop.compat.JEI;

import me.lyuxc.develop.Variables;
import me.lyuxc.develop.recipes.DeputyCraftingRecipes;
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

public class DeputyCraftingRecipeCategory implements IRecipeCategory<DeputyCraftingRecipes> {
    private final IGuiHelper helper;
    public DeputyCraftingRecipeCategory(IGuiHelper helper) {
        this.helper = helper;
    }

    @Override
    public @NotNull RecipeType<DeputyCraftingRecipes> getRecipeType() {
        return JEIPlugin.CATEGORY_DEPUTY;
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.translatable("ts.tips.jei.deputy_category");
    }

    @Override
    public @NotNull IDrawable getBackground() {
        return helper.createDrawable(new ResourceLocation(Variables.MOD_ID,"textures/gui/jei/jei_crafting.png"),0,60,100,80);
    }

    @Override
    public @NotNull IDrawable getIcon() {
        return helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, Items.CRAFTING_TABLE.getDefaultInstance());
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull DeputyCraftingRecipes recipes, @NotNull IFocusGroup iFocusGroup) {
        builder.addSlot(RecipeIngredientRole.INPUT,2,2).addItemStack(recipes.inputItem());
        builder.addSlot(RecipeIngredientRole.OUTPUT,80,2).addItemStack(recipes.outputItem());
        builder.addSlot(RecipeIngredientRole.OUTPUT,41,2).addItemStack(recipes.craftingOutputItem());
        recipes.recipe().forEach(ingredients -> {
            int i1=0;
            for(var i=0;i<3;i++) {
                for(var j=0;j<3;j++) {
                    if((i + j) < ingredients.size()) {
                        builder.addSlot(RecipeIngredientRole.INPUT,23 + 18 * j,24 + 18 * i).addIngredients(ingredients.get(i1));
                        i1++;
                    }
                }
            }
        });
    }
}
