package me.lyuxc.develop.keys;

import com.mojang.blaze3d.platform.InputConstants;
import me.lyuxc.develop.Variables;
import net.minecraft.client.KeyMapping;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class Keys {
    public static KeyMapping jei_recipe;
    public static KeyMapping jei_using;

    @SubscribeEvent
    public static void init(RegisterKeyMappingsEvent event) {
        jei_recipe = new KeyMapping("key.jei.recipe", KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_R, "key.categories." + Variables.MOD_ID);
        jei_using = new KeyMapping("key.jei.using", KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_U, "key.categories." + Variables.MOD_ID);
        event.register(jei_recipe);
        event.register(jei_using);
    }
}
