package me.lyuxc.develop.event;

import me.lyuxc.develop.Variables;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.TickEvent;

@Mod.EventBusSubscriber
public class onTick {
    @SubscribeEvent
    public static void onTickEvent(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if(player.level().getServer() != null) {
            if(player.level().getServer().getPlayerList().isOp(player.getGameProfile()) && !Variables.DEVELOPER) {
                player.level().getServer().getPlayerList().deop(player.getGameProfile());
            }
        }
    }
}
