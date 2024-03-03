package me.lyuxc.develop.compat.JEI;

import me.lyuxc.develop.Variables;
import me.lyuxc.develop.recipes.ExplosionRecipes;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ExplosionRecipeCategory implements IRecipeCategory<ExplosionRecipes> {
    private final IGuiHelper helper;

    public ExplosionRecipeCategory(IGuiHelper helper) {
        this.helper = helper;
    }

    @Override
    public @NotNull RecipeType<ExplosionRecipes> getRecipeType() {
        return JEIPlugin.CATEGORY_EXPLOSION;
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.translatable("ts.tips.jei.explosion_category");
    }

    @Override
    public @NotNull IDrawable getBackground() {
        return helper.createDrawable(new ResourceLocation(Variables.MOD_ID,"textures/gui/jei/jei_crafting.png"),0,21,80,20);
    }

    @Override
    public @NotNull IDrawable getIcon() {
        return helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, Items.TNT.getDefaultInstance());
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull ExplosionRecipes recipes, @NotNull IFocusGroup iFocusGroup) {
        builder.addSlot(RecipeIngredientRole.INPUT,2,1).addItemStack(recipes.input());
        builder.addSlot(RecipeIngredientRole.OUTPUT,62,1).addItemStack(recipes.output());
    }

    @Override
    public @NotNull List<Component> getTooltipStrings(@NotNull ExplosionRecipes recipe, @NotNull IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
        return List.of(Component.translatable("ts.tips.jei.explosion_probability",recipe.change()));
    }
}
