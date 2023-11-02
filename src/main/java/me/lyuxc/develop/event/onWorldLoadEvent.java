package me.lyuxc.develop.event;

import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;


import java.util.Objects;

@Deprecated
public class onWorldLoadEvent {
    @SubscribeEvent
    public static void load(EntityJoinLevelEvent event) {
        String[] blockIds = {"stone", "kelp_plant", "andesite", "seagrass", "sand", "granite", "diorite", "deepslate", "tall_seagrass", "water"};
        if (event.getEntity() instanceof ServerPlayer) {
            Level world = event.getLevel();
            double x = event.getEntity().getX();
            double y = event.getEntity().getY();
            double z = event.getEntity().getZ();
            Objects.requireNonNull(world.getServer()).getCommands().performPrefixedCommand(
                    new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, (ServerLevel) world, 4, "", Component.literal(""), world.getServer(), null).withSuppressedOutput(),
                    "gamerule commandModificationBlockLimit 100000000");
            for (String id : blockIds) {
                world.getServer().getCommands().performPrefixedCommand(
                        new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, (ServerLevel) world, 4, "", Component.literal(""), world.getServer(), null).withSuppressedOutput(),
                        "fill ~100 -63 ~100 ~-100 300 ~-100 air replace {id}".replace("{id}", id));
            }
        }
    }
}
