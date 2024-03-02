package me.lyuxc.develop.compat.JEI;

import me.lyuxc.develop.Variables;
import me.lyuxc.develop.utils.Utils;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.network.chat.Component;
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
        registration.addRecipes(CATEGORY_DROP,Variables.recipes.stream().map(s -> {
            String[] v = s.split("@");
            ItemStack inputItem = Utils.getItemStack(v[0]);
            ItemStack offhandItem = Utils.getItemStack(v[1]);
            offhandItem.setHoverName(Component.translatable("ts.tips.jei.offhandTip"));
            offhandItem.setCount(Integer.parseInt(v[2])==0?1:Integer.parseInt(v[2]));
            ItemStack outputItem = Utils.getItemStack(v[3]);
            outputItem.setCount(Integer.parseInt(v[4]));
            return new DropRecipes(inputItem,offhandItem,outputItem);
        }).toList());
    }
}
