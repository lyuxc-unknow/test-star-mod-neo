package me.lyuxc.develop.event;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.MobSplitEvent;

@Mod.EventBusSubscriber
public class onMobSplit {
    @SubscribeEvent
    public static void event(MobSplitEvent event) {
        event.setCanceled(true);
    }
}
