package me.lyuxc.develop.utils;

import me.lyuxc.develop.Variables;
import me.lyuxc.develop.recipes.DropRecipes;
import me.lyuxc.develop.recipes.ExplosionMultiItemRecipes;
import me.lyuxc.develop.recipes.ExplosionRecipes;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Objects;

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
    public static void loadModResource() {
        try {
            DropRecipes.recipes.clear();
            ExplosionRecipes.recipes.clear();
            ExplosionMultiItemRecipes.recipes.clear();
            Variables.IDs = FileUtils.readFromFile("banBlock.recipes", false).split(System.lineSeparator());
            for(String recipe : FileUtils.readFromFile("dropCrafting.recipes", false).split(System.lineSeparator())) {
                if(!recipe.isEmpty())
                    if(!recipe.startsWith("//"))
                        DropRecipes.addPlayerPickupRecipes(recipe);
            }
            for(String recipe : FileUtils.readFromFile("multiExplosion.recipes", false).split(System.lineSeparator())) {
                if(!recipe.isEmpty())
                    if(!recipe.startsWith("//"))
                        ExplosionMultiItemRecipes.addExplosionMultiRecipes(recipe);
            }
            for(String recipe : FileUtils.readFromFile("Explosion.recipes", false).split(System.lineSeparator())) {
                if(!recipe.isEmpty())
                    if(!recipe.startsWith("//"))
                        ExplosionRecipes.addExplosionRecipes(recipe);
            }
            ExplosionRecipes.addExplosionRecipes(Items.DIRT.getDefaultInstance(),4,Items.DIAMOND.getDefaultInstance(),100);
            ExplosionMultiItemRecipes.addExplosionMultiRecipes(List.of(Items.APPLE.getDefaultInstance(),Items.DIAMOND.getDefaultInstance()),1,Items.STONE.getDefaultInstance(),100);
            ExplosionMultiItemRecipes.addExplosionMultiRecipes(List.of(Items.DIRT.getDefaultInstance(),Items.DIAMOND.getDefaultInstance()),1,Items.IRON_INGOT.getDefaultInstance(),100);
            DropRecipes.addPlayerPickupRecipes(Items.OAK_LOG,Items.AIR,0 ,Items.OAK_PLANKS,3);
        } catch (FileNotFoundException e) {
            FileUtils.writeToNewFile("banBlock.recipes", "", false);
            FileUtils.writeToNewFile("dropCrafting.recipes","",false);
            FileUtils.writeToNewFile("multiExplosion.recipes","",false);
            FileUtils.writeToNewFile("Explosion.recipes","",false);
        }
    }
}
