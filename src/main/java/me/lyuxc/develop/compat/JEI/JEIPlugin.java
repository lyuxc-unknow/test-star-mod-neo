package me.lyuxc.develop.compat.JEI;

import me.lyuxc.develop.Variables;
import me.lyuxc.develop.recipes.*;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

@JeiPlugin
public class JEIPlugin implements IModPlugin {
    public static final RecipeType<DropCraftingRecipes> CATEGORY_DROP = RecipeType.create(Variables.MOD_ID,"drop_crafting", DropCraftingRecipes.class);
    public static final RecipeType<ExplosionCraftingRecipes> CATEGORY_EXPLOSION = RecipeType.create(Variables.MOD_ID,"explosion_crafting", ExplosionCraftingRecipes.class);
    public static final RecipeType<ExplosionMultiItemRecipes> CATEGORY_MULTI_EXPLOSION = RecipeType.create(Variables.MOD_ID,"multi_explosion_crafting", ExplosionMultiItemRecipes.class);
    public static final RecipeType<DeputyCraftingRecipes> CATEGORY_DEPUTY = RecipeType.create(Variables.MOD_ID,"deputy_crafting", DeputyCraftingRecipes.class);
    public static final RecipeType<LightningCraftingRecipes> CATEGORY_LIGHTNING = RecipeType.create(Variables.MOD_ID,"lightning_crafting", LightningCraftingRecipes.class);
    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return new ResourceLocation(Variables.MOD_ID,"test_star_jei");
    }

    @Override
    public void registerRecipeCatalysts(@NotNull IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(Items.TNT.getDefaultInstance(),CATEGORY_EXPLOSION);
        registration.addRecipeCatalyst(Items.TNT.getDefaultInstance(),CATEGORY_MULTI_EXPLOSION);
        registration.addRecipeCatalyst(Items.CRAFTING_TABLE.getDefaultInstance(),CATEGORY_DEPUTY);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new DropRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new ExplosionRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new MultiExplosionRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new DeputyCraftingRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new LightningCraftingCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(@NotNull IRecipeRegistration registration) {
        registration.addRecipes(CATEGORY_DROP, DropCraftingRecipes.recipes.stream().map(dropRecipes -> {
            ItemStack input = dropRecipes.input();
            ItemStack offhand = dropRecipes.offhandItems();
            ItemStack output = dropRecipes.output();
            offhand.setCount(dropRecipes.quantityConsumed()==0?1: dropRecipes.quantityConsumed());
            offhand.setHoverName(Component.translatable("ts.tips.jei.offhandTip",offhand.getHoverName()));
            output.setCount(dropRecipes.outputCount());
            return new DropCraftingRecipes(input,offhand, dropRecipes.quantityConsumed(), output, dropRecipes.outputCount());
        }).toList());
        registration.addRecipes(CATEGORY_EXPLOSION, ExplosionCraftingRecipes.recipes.stream().map(explosionRecipes -> {
            ItemStack input = explosionRecipes.input();
            ItemStack output = explosionRecipes.output();
            input.setCount(explosionRecipes.inputCount());
            return new ExplosionCraftingRecipes(input,explosionRecipes.inputCount(),output,explosionRecipes.change());
        }).toList());
        registration.addRecipes(CATEGORY_MULTI_EXPLOSION,ExplosionMultiItemRecipes.recipes.stream().toList());
        registration.addRecipes(CATEGORY_DEPUTY, DeputyCraftingRecipes.recipes.stream().map(recipes -> {
            ItemStack inputItem = recipes.inputItem();
            ItemStack outputItem = recipes.outputItem();
            ItemStack craftingItem = recipes.craftingOutputItem();
            inputItem.setCount(recipes.inputCount());
            inputItem.setHoverName(Component.translatable("ts.tips.jei.offhandTip",inputItem.getDisplayName()));
            outputItem.setCount(recipes.outputCount());
            return new DeputyCraftingRecipes(inputItem,recipes.inputCount(),outputItem, recipes.outputCount(), craftingItem,recipes.recipe());
        }).toList());
        registration.addRecipes(CATEGORY_LIGHTNING,LightningCraftingRecipes.recipes.stream().toList());
    }
}
