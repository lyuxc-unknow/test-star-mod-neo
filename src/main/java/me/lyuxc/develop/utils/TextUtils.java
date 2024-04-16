package me.lyuxc.develop.utils;

import committee.nova.mods.avaritia.util.lang.TextUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import static net.minecraft.ChatFormatting.*;

public class TextUtils {
     private static final ChatFormatting[] value = {RED, GOLD, YELLOW, GREEN, AQUA, BLUE, LIGHT_PURPLE};
    private static final ChatFormatting[] value2 = {BLACK, DARK_BLUE, DARK_GREEN, DARK_AQUA, DARK_RED, DARK_PURPLE, GOLD, GOLD, GRAY, DARK_GRAY, BLUE, GREEN, GRAY, DARK_GRAY, BLUE, GREEN, AQUA, RED, LIGHT_PURPLE, YELLOW, WHITE};

    public static Component apply(Component component) {
        return Component.literal(TextUtil.ludicrousFormatting(component.getString(), value, 100, 2, 1));
    }

    public static Component applyAllColor(Component component) {
        return Component.literal(TextUtil.ludicrousFormatting(component.getString(), value2, 40, 2, 1));
    }
}
