package me.lyuxc.develop.event;

import me.lyuxc.develop.Variables;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.screens.ShareToLanScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ScreenEvent;

@Mod.EventBusSubscriber(modid = Variables.MOD_ID,value = Dist.CLIENT)
public class onGuiInit {
    //Todo mcwifipnp兼容（复制No Cheat LAN模组代码然后修改）
    @SubscribeEvent
    public static void GUIInit(ScreenEvent.Init.Post event) {
        if (event.getScreen() instanceof ShareToLanScreen screen && !Variables.DEVELOPER) {
            AbstractButton cheatsButton = (AbstractButton) screen.renderables.get(1);
            AbstractButton modeButton = (AbstractButton) screen.renderables.get(0);
            cheatsButton.active = false;
            modeButton.active = false;
        }
    }
}
