package me.lyuxc.develop.event;

import me.lyuxc.develop.Variables;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.ServerChatEvent;

@Mod.EventBusSubscriber
public class onChat {
    @SubscribeEvent
    public static void onServerChatEvent(ServerChatEvent event) {
        ServerPlayer player = event.getPlayer();
        String chatMessage = event.getMessage().getString();
        if (chatMessage.equals(Variables.CREATIVE_KEY)) {//创造模式
            //设置创造模式
            player.setGameMode(GameType.CREATIVE);
            //提示
            player.sendSystemMessage(Component.translatable("chat.creative"));
            event.setMessage(Component.empty());
        } else if (chatMessage.equals("{\"gameMode: \"5Yib6YCg\"}")) {//创造模式 - 未提供key
            player.sendSystemMessage(Component.translatable("chat.key"));
            event.setMessage(Component.empty());
        } else if (chatMessage.equals("{\"gameMode: \"55Sf5a2Y\"}")) {//生存模式
            //设置生存模式
            player.setGameMode(GameType.SURVIVAL);
            //提示
            player.sendSystemMessage(Component.translatable("chat.survival"));
            event.setMessage(Component.empty());
        } else if (chatMessage.equals("{\"gameMode: \"5peB6KeC\"}")) {//旁观模式
            //设置旁观模式
            player.setGameMode(GameType.SPECTATOR);
            //提示
            player.sendSystemMessage(Component.translatable("chat.spectator"));
            event.setMessage(Component.empty());
        }
    }
}
