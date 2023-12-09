package me.lyuxc.develop.event;

import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerAbilitiesPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.TickEvent;

import java.util.Objects;

@Mod.EventBusSubscriber
public class onTickEvent {
    @SubscribeEvent
    public static void onTick(TickEvent.PlayerTickEvent event) {
        LevelAccessor world = event.player.level();
        Player player = event.player;
        CompoundTag tags = player.getPersistentData();
        if(player.level().dimension() == Level.OVERWORLD) {
            if(!player.level().isClientSide()) {
                if(player instanceof ServerPlayer player1) {
                    player1.connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.WIN_GAME, 0));
                    player1.teleportTo(Objects.requireNonNull(((ServerPlayer) player).server.getLevel(Level.END)), 0, 150, 0, player.getYRot(), player.getXRot());
                    player1.connection.send(new ClientboundPlayerAbilitiesPacket(player.getAbilities()));
                }
            }
        }
        if(tags.get("first_kill") == null || !Objects.requireNonNull(tags.get("first_kill")).getAsString().equals("1")) {
            player.getPersistentData().putString("first_kill", "1");
            tags.putString("first_kill", "1");
            new Thread(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (world instanceof ServerLevel _level)
                    _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(player.getX(), player.getY(), player.getZ()), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
                            "kill @e[type=minecraft:ender_dragon]");
            }).start();
            player.save(tags);
        }
    }
}
