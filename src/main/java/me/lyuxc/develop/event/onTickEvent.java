package me.lyuxc.develop.event;

import me.lyuxc.develop.utils.Utils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerAbilitiesPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.TickEvent;

import java.util.Objects;

@Mod.EventBusSubscriber
public class onTickEvent {
    @SubscribeEvent
    public static void onTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        CompoundTag tags = player.getPersistentData();
        if(player.level().dimension() == Level.OVERWORLD) {
            if(!player.level().isClientSide()) {
                if(player instanceof ServerPlayer player1) {
                    player1.connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.WIN_GAME, 0));
                    player1.teleportTo(Objects.requireNonNull(((ServerPlayer) player).server.getLevel(Level.END)), 0, 80, 0, player.getYRot(), player.getXRot());
                    player1.connection.send(new ClientboundPlayerAbilitiesPacket(player.getAbilities()));
                    player1.addEffect(new MobEffectInstance(MobEffects.REGENERATION, Utils.getTime(10),255,false,false));
                }
            }
        }
        if(tags.get("first_kill") == null || !Objects.requireNonNull(tags.get("first_kill")).getAsString().equals("1")) {
            player.getPersistentData().putString("first_kill", "1");
            tags.putString("first_kill", "1");
            new Thread(() -> {
                try {
                    Thread.sleep(3000);
                    if (event.player.level() instanceof ServerLevel _level) Utils.executeCommand(_level,player,"kill @e[type=minecraft:ender_dragon]");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }).start();
            player.save(tags);
        }
    }
}
