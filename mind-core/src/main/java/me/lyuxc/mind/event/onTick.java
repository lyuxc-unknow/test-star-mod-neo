package me.lyuxc.mind.event;

import me.lyuxc.mind.AttachmentTypes;
import me.lyuxc.mind.Variables;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.Objects;


@EventBusSubscriber
public class onTick {
    @SubscribeEvent
    public static void onTickEvent(PlayerTickEvent.Pre event) {
        Player player = event.getEntity();
        if(player.level().getServer() != null) {
            if (Objects.requireNonNull(player.level().getServer()).getPlayerList().isOp(player.getGameProfile()) && !Variables.DEVELOPER) {
                Objects.requireNonNull(player.level().getServer()).getPlayerList().deop(player.getGameProfile());
            }
        }
    }

    @SubscribeEvent
    public static void onAttackEvent(LivingIncomingDamageEvent event) {
        if(!event.getEntity().level().isClientSide()) {
            if (event.getEntity() instanceof Player player) {
                if(player.getData(AttachmentTypes.INF_ATTACHMENT.get()) > 0) {
                    player.setData(AttachmentTypes.INF_ATTACHMENT.get(), player.getData(AttachmentTypes.INF_ATTACHMENT.get()) - 1);
                    event.setCanceled(true);
                }
            }
        }
    }
}
