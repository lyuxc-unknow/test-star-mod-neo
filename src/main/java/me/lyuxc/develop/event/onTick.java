package me.lyuxc.develop.event;

import me.lyuxc.develop.Variables;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.TickEvent;

import java.util.Objects;

@Mod.EventBusSubscriber
public class onTick {
    @SubscribeEvent
    public static void onTickEvent(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if(player.getAbilities().flying && player.getAbilities().getFlyingSpeed() == 0) {
            player.getAbilities().setFlyingSpeed(0.05F);
        }
        if(player.level().getServer() != null) {
            if(Objects.requireNonNull(player.level().getServer()).getPlayerList().isOp(player.getGameProfile()) && !Variables.DEVELOPER) {
                Objects.requireNonNull(player.level().getServer()).getPlayerList().deop(player.getGameProfile());
            }
        }
    }
}
