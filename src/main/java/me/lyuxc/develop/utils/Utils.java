package me.lyuxc.develop.utils;

import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

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
}
