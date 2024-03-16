package me.lyuxc.develop.event;

import me.lyuxc.develop.utils.CommandExecutes;
import net.minecraft.commands.Commands;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@Mod.EventBusSubscriber
public class onCommandRegistry {
    @SubscribeEvent
    public static void register(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("jrrp")
                .executes(CommandExecutes::jrrp)
        );
        event.getDispatcher().register(Commands.literal("gc")
                .executes(context -> CommandExecutes.gc())
        );
        event.getDispatcher().register(Commands.literal("hand")
                .executes(CommandExecutes::getHandItem)
                .requires(r -> r.hasPermission(1))
        );
    }
}
