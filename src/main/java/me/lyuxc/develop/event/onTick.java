package me.lyuxc.develop.event;

import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.TickEvent;

@Mod.EventBusSubscriber
public class onTick {
    @SubscribeEvent
    public static void onTickEvent(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if(player.getAbilities().flying && player.getAbilities().getFlyingSpeed() == 0) {
            player.getAbilities().setFlyingSpeed(0.05F);
        }
    }
}
