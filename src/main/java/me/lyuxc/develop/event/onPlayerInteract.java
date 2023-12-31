package me.lyuxc.develop.event;

import me.lyuxc.develop.Star;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@Mod.EventBusSubscriber
public class onPlayerInteract {
    @SubscribeEvent
    public static void onPlayerInteractEvent(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        Level level = event.getLevel();
        BlockState blockState = level.getBlockState(event.getPos());
        for(String s : Star.IDs) {
            if(BuiltInRegistries.BLOCK.getKey(blockState.getBlock()).toString().equals(s)) {
                player.displayClientMessage(Component.literal("你无法使用"+s+"方块"),true);
                event.setCanceled(true);
            }
        }
    }
}
