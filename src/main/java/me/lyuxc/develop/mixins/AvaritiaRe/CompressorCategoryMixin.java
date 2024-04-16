package me.lyuxc.develop.mixins.AvaritiaRe;

import committee.nova.mods.avaritia.api.common.crafting.ICompressorRecipe;
import committee.nova.mods.avaritia.init.compat.jei.category.CompressorCategory;
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

@Mixin(CompressorCategory.class)
public class CompressorCategoryMixin {
    /**
     * @author lyuxc
     * @reason The x and y coordinates are offset and need to be reduced by one.
     */
    @Overwrite
    public void setRecipe(IRecipeLayoutBuilder builder, ICompressorRecipe recipe, @NotNull IFocusGroup focuses) {
        ClientLevel level = Minecraft.getInstance().level;

        assert level != null;

        NonNullList<Ingredient> inputs = recipe.getIngredients();
        ItemStack output = recipe.getResultItem(level.registryAccess());
        builder.addSlot(RecipeIngredientRole.INPUT, 36 + 1, 20 + 1).addIngredients(inputs.get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 116 + 1, 20 + 1).addItemStack(output);
    }
}
