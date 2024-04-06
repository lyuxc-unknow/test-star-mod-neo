package me.lyuxc.develop.utils;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;

import static net.minecraft.ChatFormatting.*;

/**
 * @author cnlimiter,Nova Committee
 */
public class TextUtils {
    //Based Reforged-Avaritia Source
    //从无尽贪婪复制的代码，用于实现彩色字体
     private static final ChatFormatting[] value = {RED, GOLD, YELLOW, GREEN, AQUA, BLUE, LIGHT_PURPLE};
    private static final ChatFormatting[] value2 = {BLACK, DARK_BLUE, DARK_GREEN, DARK_AQUA, DARK_RED, DARK_PURPLE, GOLD, GOLD, GRAY, DARK_GRAY, BLUE, GREEN, GRAY, DARK_GRAY, BLUE, GREEN, AQUA, RED, LIGHT_PURPLE, YELLOW, WHITE};

    public static Component apply(Component component) {
        return Component.literal(RainbowMarquee(component.getString(), value, 100));
    }

    public static Component applyAllColor(Component component) {
        return Component.literal(RainbowMarquee(component.getString(), value2, 40));
    }

    private static String RainbowMarquee(String input, ChatFormatting[] colours, double delay) {
        StringBuilder sb = new StringBuilder(input.length() * 3);
        if (delay <= 0) {
            delay = 0.001;
        }

        int offset = (int) Math.floor(Util.getMillis() / delay) % colours.length;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            int col = (i + colours.length - offset) % colours.length;

            sb.append(colours[col]);
            sb.append(c);
        }
        sb.append(RESET);
        return sb.toString();
    }
}
