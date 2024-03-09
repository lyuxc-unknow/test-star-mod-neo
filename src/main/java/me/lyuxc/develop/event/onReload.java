package me.lyuxc.develop.event;

import me.lyuxc.develop.utils.Utils;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.AddReloadListenerEvent;

@Mod.EventBusSubscriber
public class onReload {
    @SubscribeEvent
    public static void onReloadEvent(AddReloadListenerEvent event) {
        event.addListener((ResourceManagerReloadListener) resourceManager -> Utils.loadModResource(event.getRegistryAccess(),event.getServerResources().getRecipeManager()));
    }
}
