package me.lyuxc.develop.event;

import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.screens.ShareToLanScreen;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ScreenEvent;

@Mod.EventBusSubscriber
public class onGuiInit {
    @SubscribeEvent
    public static void GUIInit(ScreenEvent.Init.Post event) {
        if (event.getScreen() instanceof ShareToLanScreen screen) {
            AbstractButton commandsButton = (AbstractButton) screen.renderables.get(1);
            AbstractButton modeButton = (AbstractButton) screen.renderables.get(0);

            commandsButton.visible = false;
            modeButton.setX(screen.width / 2 - modeButton.getWidth() / 2);
            modeButton.active = false;
        }
    }

}
