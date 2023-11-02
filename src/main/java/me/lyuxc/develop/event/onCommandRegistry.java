package me.lyuxc.develop.event;

import me.lyuxc.develop.Star;
import net.minecraft.commands.Commands;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.RegisterCommandsEvent;


import java.util.Calendar;
import java.util.Objects;

@Mod.EventBusSubscriber
public class onCommandRegistry {
    @SubscribeEvent
    public static void register(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("jrrp")
                .executes(context -> {
                    ServerPlayer player = context.getSource().getPlayerOrException();
                    CompoundTag compoundTag = player.getPersistentData();
                    if ((compoundTag.get("jrrpLaseTime") == null || compoundTag.get("jrrp") == null) || (!Objects.requireNonNull(compoundTag.get("jrrpLaseTime")).getAsString().equals(String.valueOf(Star.calendar.get(Calendar.DAY_OF_YEAR))))) {
                        player.getPersistentData().putString("jrrpLaseTime", String.valueOf(Star.calendar.get(Calendar.DAY_OF_YEAR)));
                        player.getPersistentData().putString("jrrp", String.valueOf(Star.Random_Day.nextInt(101)));
                        compoundTag.putString("jrrpLaseTime", String.valueOf(Star.calendar.get(Calendar.DAY_OF_YEAR)));
                        compoundTag.putString("jrrp", String.valueOf(Star.Random_Day.nextInt(101)));
                        player.save(compoundTag);
                    }
                    player.sendSystemMessage(Component.translatable("ts.command.jrrp", Objects.requireNonNull(compoundTag.get("jrrp")).getAsString()));
                    return 0;
                })
        );
        event.getDispatcher().register(Commands.literal("gc")
                .executes(context -> {
                    System.gc();
                    return 0;
                })
        );
    }
}
