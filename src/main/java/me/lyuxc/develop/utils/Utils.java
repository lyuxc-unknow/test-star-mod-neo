package me.lyuxc.develop.utils;

import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

import java.util.Objects;

public class Utils {
    public static int getTime(int time) {
        return time * 20;
    }

    public static void executeCommand(Level level, Player player, String command) {
        Objects.requireNonNull(level.getServer()).getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(player.getX(), player.getY(), player.getZ()), Vec2.ZERO, (ServerLevel) level, 4, "", Component.literal(""), level.getServer(), null).withSuppressedOutput(), command);
    }
}
