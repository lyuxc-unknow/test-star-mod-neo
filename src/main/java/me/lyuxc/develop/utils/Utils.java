package me.lyuxc.develop.utils;

import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerAbilitiesPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

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
    public static void atEnt(Player player) {
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
    public static void disableFly(Player player) {
        player.getAbilities().flying = false;
        player.getAbilities().setFlyingSpeed(0);
    }
}
