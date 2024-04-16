package me.lyuxc.develop.mixins.AvaritiaRe;

import committee.nova.mods.avaritia.api.common.crafting.ISpecialRecipe;
import committee.nova.mods.avaritia.common.crafting.recipe.InfinityCatalystCraftRecipe;
import committee.nova.mods.avaritia.common.crafting.recipe.ShapedExtremeCraftingRecipe;
import committee.nova.mods.avaritia.common.crafting.recipe.ShapelessExtremeCraftingRecipe;
import committee.nova.mods.avaritia.init.compat.jei.category.ExtremeCraftingTableCategory;
import committee.nova.mods.avaritia.init.registry.ModItems;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ExtremeCraftingTableCategory.class)
public abstract class ExtremeCraftingTableCategoryMixin {
    @Shadow
    protected abstract void shapelessRecipe(@NotNull IRecipeLayoutBuilder builder, NonNullList<Ingredient> inputs);

    /**
     * @author lyuxc
     * @reason The x and y coordinates are offset and need to be reduced by one.
     */
    @Overwrite
    public void setRecipe(@NotNull IRecipeLayoutBuilder builder, ISpecialRecipe recipe, @NotNull IFocusGroup focuses) {
        ClientLevel level = Minecraft.getInstance().level;

        assert level != null;

        NonNullList<Ingredient> inputs = recipe.getIngredients();
        ItemStack output = recipe.getResultItem(level.registryAccess());
        if (recipe instanceof ShapedExtremeCraftingRecipe shaped) {
            int stackIndex = 0;
            int heightOffset = Math.floorDiv(9 - shaped.getHeight(), 2);
            int widthOffset = Math.floorDiv(9 - shaped.getWidth(), 2);

            for (int i = heightOffset; i < shaped.getHeight() + heightOffset; ++i) {
                for (int j = widthOffset; j < shaped.getWidth() + widthOffset; ++j) {
                    builder.addSlot(RecipeIngredientRole.INPUT, j * 18 + 2, i * 18 + 2).addIngredients(inputs.get(stackIndex));
                    ++stackIndex;
                }
            }

            builder.addSlot(RecipeIngredientRole.OUTPUT, 167 + 1, 73 + 1).addItemStack(output);
        } else if (recipe instanceof ShapelessExtremeCraftingRecipe) {
            this.shapelessRecipe(builder, inputs);
            builder.addSlot(RecipeIngredientRole.OUTPUT, 167 + 1, 73 + 1).addItemStack(output);
        } else if (recipe instanceof InfinityCatalystCraftRecipe) {
            this.shapelessRecipe(builder, inputs);
            builder.addSlot(RecipeIngredientRole.OUTPUT, 167 + 1, 73 + 1).addItemStack(new ItemStack(ModItems.infinity_catalyst.get()));
        }

        builder.moveRecipeTransferButton(170, 100);
    }
}
