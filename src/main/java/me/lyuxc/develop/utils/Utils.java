package me.lyuxc.develop.utils;

import me.lyuxc.develop.Variables;
import me.lyuxc.develop.recipes.*;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerAbilitiesPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class Utils {
    public static int getTime(int time) {
        return time * 20;
    }

    public static void executeCommand(ServerLevel level, Player player, String command) {
        MinecraftServer server = level.getServer();
        Vec3 vec3 = new Vec3(player.getX(),player.getY(),player.getZ());
        CommandSourceStack sourceStack = new CommandSourceStack(CommandSource.NULL, vec3, Vec2.ZERO, level, 4, "", Component.literal(""), server, null)
                .withSuppressedOutput();
        server.getCommands().performPrefixedCommand(sourceStack, command);
    }
    @Deprecated
    public static void toTheEnd(Player player) {
        if(player.level().dimension() == Level.OVERWORLD) {
            if(!player.level().isClientSide()) {
                if(player instanceof ServerPlayer player1) {
                    player1.connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.WIN_GAME, 0));
                    player1.teleportTo(Objects.requireNonNull(((ServerPlayer) player).server.getLevel(Level.END)), 0, 80, 0, player.getYRot(), player.getXRot());
                    player1.connection.send(new ClientboundPlayerAbilitiesPacket(player.getAbilities()));
                    player1.addEffect(new MobEffectInstance(MobEffects.REGENERATION, Utils.getTime(10),255,false,false));
                }
            }
        }
    }
    @Deprecated
    public static void firstJoinWorldKillEndDragon(CompoundTag compoundTag,Player player) {
        if(compoundTag.get("first_kill") == null || !Objects.requireNonNull(compoundTag.get("first_kill")).getAsString().equals("1")) {
            player.getPersistentData().putString("first_kill", "1");
            compoundTag.putString("first_kill", "1");
            new Thread(() -> {
                try {
                    Thread.sleep(3000);
                    if (player.level() instanceof ServerLevel _level) Utils.executeCommand(_level,player,"kill @e[type=minecraft:ender_dragon]");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }).start();
            player.save(compoundTag);
        }
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
            DropCraftingRecipes.recipes.clear();
            ExplosionCraftingRecipes.recipes.clear();
            ExplosionMultiItemRecipes.recipes.clear();
            DeputyCraftingRecipes.recipes.clear();
            LightningCraftingRecipes.recipes.clear();

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
