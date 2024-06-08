package me.lyuxc.develop.utils;

import me.lyuxc.develop.Variables;
import me.lyuxc.develop.recipes.*;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Utils {
    public static int getTime(int time) {
        return time * 20;
    }

    @SuppressWarnings("unused")
    public static void executeCommand(ServerLevel level, Player player, String command) {
        MinecraftServer server = level.getServer();
        Vec3 vec3 = new Vec3(player.getX(),player.getY(),player.getZ());
        CommandSourceStack sourceStack = new CommandSourceStack(CommandSource.NULL, vec3, Vec2.ZERO, level, 4, "", Component.literal(""), server, null)
                .withSuppressedOutput();
        server.getCommands().performPrefixedCommand(sourceStack, command);
    }
    @SuppressWarnings("unused")
    public static void disableFly(Player player) {
        player.getAbilities().flying = false;
        player.getAbilities().setFlyingSpeed(0);
    }
    @SuppressWarnings("unused")
    public static void enableFly(Player player) {
        player.getAbilities().flying = true;
        player.getAbilities().setFlyingSpeed(0.015F);
    }
    public static Item getItem(String itemId) {
        return BuiltInRegistries.ITEM.get(new ResourceLocation(itemId));
    }
    public static ItemStack getItemStack(String itemId) {
        return getItem(itemId).getDefaultInstance();
    }
    public static Item getRandomItemStack() {
        List<Item> item = new ArrayList<>();
        for(ResourceLocation rl : BuiltInRegistries.ITEM.keySet()) {
            item.add(BuiltInRegistries.ITEM.get(rl));
        }
        return item.get(Variables.random.nextInt(item.size()));
    }
    public static void loadModResource(RegistryAccess access,RecipeManager manager) {
        try {
            //读取前清空
            DropCraftingRecipes.RECIPES.clear();
            ExplosionCraftingRecipes.RECIPES.clear();
            ExplosionMultiItemRecipes.RECIPES.clear();
            DeputyCraftingRecipes.RECIPES.clear();
            LightningCraftingRecipes.RECIPES.clear();

            //读取文件存入合成表列表
            Variables.IDs = FileUtils.readFromFile("banBlock.recipes", false).split(System.lineSeparator());
            addRecipesFromFile("dropCrafting.recipes", DropCraftingRecipes::addPlayerPickupRecipes);
            addRecipesFromFile("multiExplosion.recipes", ExplosionMultiItemRecipes::addExplosionMultiRecipes);
            addRecipesFromFile("explosion.recipes", ExplosionCraftingRecipes::addExplosionRecipes);
            for (String recipe : FileUtils.readFromFile("deputy.recipes", false).split(System.lineSeparator())) {
                if (!recipe.isEmpty()) {
                    DeputyCraftingRecipes.addDeputyCraftingRecipes(recipe,access,manager);
                }
            }
            addRecipesFromFile("lightning.recipes", LightningCraftingRecipes::addLightningCraftingRecipes);
            DropCraftingRecipes.addPlayerPickupRecipes(Items.DIRT,Items.AIR,0,Items.DIAMOND,1);
            for(int i=0;i<20;i++) {
                if(i<19) {
                    RandomDropCraftingRecipes.addRandomDropCraftingRecipe(Utils.getItemStack("test_star:package_" + i),Utils.getItemStack("test_star:package_" + (i + 1)));
                } else {
                    RandomDropCraftingRecipes.addRandomDropCraftingRecipe(Utils.getItemStack("test_star:package_" + i), Items.DIAMOND_BLOCK.getDefaultInstance());
                }
            }
        } catch (FileNotFoundException e) {
            // 如果没找到就创建
            createRecipeFiles();
        }
    }
    //添加到合成表
    private static void addRecipesFromFile(String fileName, Consumer<String> recipeConsumer) throws FileNotFoundException {
        String[] recipes = FileUtils.readFromFile(fileName, false).split(System.lineSeparator());
        for (String recipe : recipes) {
            if (!recipe.isEmpty()) {
                recipeConsumer.accept(recipe);
            }
        }
    }

    //如果没找到文件就创建
    private static void createRecipeFiles() {
        FileUtils.createFiles();
        FileUtils.writeToNewFile("banBlock.recipes", "", false);
        FileUtils.writeToNewFile("dropCrafting.recipes", "", false);
        FileUtils.writeToNewFile("multiExplosion.recipes", "", false);
        FileUtils.writeToNewFile("explosion.recipes", "", false);
        FileUtils.writeToNewFile("deputy.recipes", "", false);
        FileUtils.writeToNewFile("lightning.recipes", "", false);
    }

    public static List<NonNullList<Ingredient>> getRecipe(@NotNull ItemStack itemStack, RegistryAccess access, RecipeManager recipeManager) {
        List<NonNullList<Ingredient>> recipes = new ArrayList<>();
        for(RecipeHolder<?> recipeHolder : recipeManager.getRecipes()) {
            if(recipeHolder.value().getType() == RecipeType.CRAFTING) {
                if (itemStack.is(recipeHolder.value().getResultItem(access).getItem())) {
                    recipes.add(recipeHolder.value().getIngredients());
                }
            }
        }
        return recipes;
    }
}
