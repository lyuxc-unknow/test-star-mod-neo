package me.lyuxc.develop.event;

import me.lyuxc.develop.Variables;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.util.UUID;

@Mod.EventBusSubscriber
public class onMouseInput {
    private static long lastTime=0;
    private static boolean writing = false;
    @SubscribeEvent
    public static void onMouseEvent(InputEvent.MouseButton.Pre event) {
        if (Variables.mc.player == null)
            return;
        Player player = Variables.mc.player;
        UUID uuid = player.getUUID();
        int leftClicks = Variables.playerLeftClicksMap.getOrDefault(uuid, 0);
        int rightClicks = Variables.playerRightClicksMap.getOrDefault(uuid, 0);

        if (System.currentTimeMillis() - lastTime >= 1000) {
            double clicksPerSecond = (double) leftClicks + rightClicks; // 1 second
            if (clicksPerSecond > 15) {
//                player.displayClientMessage(Component.literal("你的点击速度太快了！"), true);
                writing = true;
            }
            lastTime = System.currentTimeMillis();
        }

        if (event.getAction() == 0) { // 0: Press, 1: Release
            if (event.getButton() == 0) { // 0: Left click
                leftClicks++;
                Variables.playerLeftClicksMap.put(uuid, leftClicks);
            }
        }
        if (event.getAction() == 0) { // 0: Press, 1: Release
            if (event.getButton() == 1) {
                rightClicks++;
                Variables.playerRightClicksMap.put(uuid, rightClicks);
            }
        }
    }
    @SubscribeEvent
    public static void onPlayerLeave(PlayerEvent.PlayerLoggedOutEvent event) {
        UUID playerId = event.getEntity().getUUID();
        Variables.playerLeftClicksMap.remove(playerId);
        Variables.playerRightClicksMap.remove(playerId);
    }
    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        UUID playerId = event.getEntity().getUUID();
        Variables.playerLeftClicksMap.remove(playerId);
        Variables.playerRightClicksMap.remove(playerId);
    }
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player playerId = event.player;
        if(writing&&playerId instanceof ServerPlayer _player) {
            _player.connection.disconnect(Component.literal("使用连点器"));
        }
    }
}
