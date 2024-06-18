package me.lyuxc.develop.event;

import me.lyuxc.develop.Variables;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.screens.ShareToLanScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ScreenEvent;

@EventBusSubscriber(modid = Variables.MOD_ID,value = Dist.CLIENT)
public class onGuiInit {
    @SubscribeEvent
    public static void GUIInit(ScreenEvent.Init.Post event) {
        if (event.getScreen() instanceof ShareToLanScreen screen && !Variables.DEVELOPER) {
            AbstractButton cheatsButton = (AbstractButton) screen.renderables.get(1);
            AbstractButton modeButton = (AbstractButton) screen.renderables.get(0);
            cheatsButton.active = false;
            modeButton.active = false;
//        } else if(event.getScreen() instanceof ShareToLanScreenNew screenNew && !Variables.DEVELOPER) {
//            AbstractButton gamemodeButton = (AbstractButton) screenNew.renderables.get(2);
//            AbstractButton allowCheatsButton = (AbstractButton) screenNew.renderables.get(3);
//            AbstractButton otherPlayersCheatButton = (AbstractButton) screenNew.renderables.get(7);
//            gamemodeButton.active = false;
//            allowCheatsButton.active = false;
//            otherPlayersCheatButton.active = false;
//        }
        }
    }
}
