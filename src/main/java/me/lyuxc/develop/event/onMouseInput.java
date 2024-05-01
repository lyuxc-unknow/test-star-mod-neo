package me.lyuxc.develop.event;

import me.lyuxc.develop.Variables;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;


@EventBusSubscriber(modid = Variables.MOD_ID)
public class onMouseInput {
    /*
    * FTB方案，自研看Github 历史记录，由于用到客户端专有的东西而导致服务器无法使用而替换
    * */
    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        player.getPersistentData().putString("digging","0");
        player.getPersistentData().putBoolean("throttled",false);
        player.getPersistentData().putInt("throttledTimer",0);
    }
    @SubscribeEvent
    public static void onPlayerRightBlock(PlayerInteractEvent.RightClickBlock event) {
        clickE(event);
    }
    @SubscribeEvent
    public static void onPlayerLeftBlock(PlayerInteractEvent.LeftClickBlock event) {
        if(event.getAction() == PlayerInteractEvent.LeftClickBlock.Action.ABORT) {
            clickE(event);
        }
    }
    private static void clickE(PlayerInteractEvent event) {
        Player player = event.getEntity();
        CompoundTag persistentData = player.getPersistentData();
        persistentData.putInt("digging",persistentData.getInt("digging") + 1);
        if(persistentData.getInt("digging") > 24) {
            if(player instanceof ServerPlayer _player) {
                _player.connection.disconnect(Component.translatable("ts.server.kick"));
            }
        } else if(persistentData.getInt("digging") > 18) {
            persistentData.putBoolean("throttled",true);
            persistentData.putInt("throttledTimer",0);
            player.displayClientMessage(Component.translatable("ts.click.much"),true);
        }
    }
    @SubscribeEvent
    public static void onTick(PlayerTickEvent.Pre event) {
        Player player = event.getEntity();
        CompoundTag compoundTag = player.getPersistentData();
        if(player.tickCount % 4 == 0 && compoundTag.getInt("digging") > 0) {
            compoundTag.putInt("digging",compoundTag.getInt("digging")-1);
        }
        if(compoundTag.getBoolean("throttled")) {
            compoundTag.putInt("throttledTimer", compoundTag.getInt("throttledTimer") + 1);
        }
        if(compoundTag.getInt("throttledTimer") > 40) {
            event.getEntity().displayClientMessage(Component.translatable("ts.click.enable"), true);
            compoundTag.putString("digging","0");
            compoundTag.putBoolean("throttled",false);
            compoundTag.putInt("throttledTimer", 0);
        }
    }
}
