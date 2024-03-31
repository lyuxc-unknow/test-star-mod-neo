package me.lyuxc.develop.utils;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class I18N {
    @SuppressWarnings("unused")
    public static String getI18N(MutableComponent mutableComponent) {
        return mutableComponent.getString();
    }

    @SuppressWarnings("unused")
    public static String getI18N(String key) {
        return getI18N(Component.translatable(key));
    }

    @SuppressWarnings("unused")
    public static Component getComponent(String translateKey) {
        return Component.translatable(translateKey);
    }

    @SuppressWarnings("unused")
    public static String getItemName(ItemStack itemStack) {
        return Component.translatable(itemStack.getItem().getDescriptionId()).getString();
    }

    @SuppressWarnings("unused")
    public static String getItemName(Item itemStack) {
        return Component.translatable(itemStack.getDefaultInstance().getItem().getDescriptionId()).getString();
    }

    @SuppressWarnings("unused")
    public static Component getItemComponent(ItemStack itemStack) {
        return Component.translatable(itemStack.getItem().getDescriptionId());
    }

    @SuppressWarnings("unused")
    public static Component getItemComponent(Item itemStack) {
        return Component.translatable(itemStack.getDefaultInstance().getItem().getDescriptionId());
    }
}
