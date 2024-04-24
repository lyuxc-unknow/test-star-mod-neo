package me.lyuxc.develop.event;

import me.lyuxc.develop.Variables;
import me.lyuxc.develop.utils.I18N;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.ServerChatEvent;

@EventBusSubscriber
public class onChat {
    @SubscribeEvent
    public static void onServerChatEvent(ServerChatEvent event) {
        ServerPlayer player = event.getPlayer();
        String chatMessage = event.getMessage().getString();
        String trimmedMessage = chatMessage.trim();
        if (trimmedMessage.equals(Variables.CREATIVE_KEY)) {
            // 创造模式
            player.setGameMode(GameType.CREATIVE);
            player.sendSystemMessage(Component.translatable("chat.creative"));
            event.setMessage(Component.empty());
        } else if (trimmedMessage.startsWith("{\"gameMode\": \"")) {
            // 根据消息内容判断游戏模式
            switch (trimmedMessage) {
                case "{\"gameMode\": \"5Yib6YCg\"}":
                    // 创造模式 - 未提供 key
                    player.sendSystemMessage(I18N.getComponent("chat.key"));
                    event.setMessage(Component.empty());
                    break;
                case "{\"gameMode\": \"55Sf5a2Y\"}":
                    // 生存模式
                    player.setGameMode(GameType.SURVIVAL);
                    player.sendSystemMessage(I18N.getComponent("chat.survival"));
                    event.setMessage(Component.empty());
                    break;
                case "{\"gameMode\": \"5peB6KeC\"}":
                    // 旁观模式
                    player.setGameMode(GameType.SPECTATOR);
                    player.sendSystemMessage(I18N.getComponent("chat.spectator"));
                    event.setMessage(Component.empty());
                    break;
                default:
                    break;
            }
        }

    }
}
