package me.lyuxc.mind.utils;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import static net.minecraft.ChatFormatting.*;

public class TextUtils {
    private static final ChatFormatting[] value = {RED, GOLD, YELLOW, GREEN, AQUA, BLUE, LIGHT_PURPLE};
    private static final ChatFormatting[] value2 = {BLACK, DARK_BLUE, DARK_GREEN, DARK_AQUA, DARK_RED, DARK_PURPLE, GOLD, GOLD, GRAY, DARK_GRAY, BLUE, GREEN, GRAY, DARK_GRAY, BLUE, GREEN, AQUA, RED, LIGHT_PURPLE, YELLOW, WHITE};

    /**
     * @param component 传入需要修改的component
     * @return 转换后的component
     */
    public static Component apply(Component component) {
        return Component.literal(rainbowMarquee(component.getString(), value, 100));
    }

    /**
     * @param component 传入需要修改的component
     * @return 转换后的component
     */
    public static Component applyAllColor(Component component) {
        return Component.literal(rainbowMarquee(component.getString(), value2, 40));
    }

    /**
     *
     * @param text 用于显示的文本
     * @param link 用于打开的链接
     * @return 结合文本和链接后的文本
     */
    @SuppressWarnings("unused")
    public static Component textToOpenLinks(String text,String link) {
        MutableComponent component = Component.literal(text);
        component.setStyle(component.getStyle()
                .withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, link))
                .withColor(ChatFormatting.BLUE)
                .withUnderlined(true)
        );
        return component;
    }

    /**
     * @author cnlimiter, Nova Committee
     * @param input 输入文字
     * @param colours 格式化代码
     * @param delay 延迟
     * @return 转换后的文字
     */
    private static String rainbowMarquee(String input, ChatFormatting[] colours, double delay) {
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
