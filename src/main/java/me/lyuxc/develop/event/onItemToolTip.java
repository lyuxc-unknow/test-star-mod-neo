package me.lyuxc.develop.event;

import me.lyuxc.develop.item.tools.MySword;
import me.lyuxc.develop.item.tools.TetanusBlade;
import me.lyuxc.develop.utils.TextUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;


@Mod.EventBusSubscriber
public class onItemToolTip {
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onToolTip(ItemTooltipEvent event) {
        if (event.getItemStack().getItem() instanceof MySword) {
            for (int x = 0; x < event.getToolTip().size(); x++) {
                if (event.getToolTip().get(x).contains(Component.translatable("attribute.name.generic.attack_damage")) || event.getToolTip().get(x).contains(Component.literal("Attack Damage"))) {
                    event.getToolTip().set(x,
                            Component.literal(" ").withStyle(ChatFormatting.BLUE)
                                    .append(TextUtils.apply(Component.translatable("ts.attribute.damage")))
                                    .append(" ")
                                    .append(TextUtils.applyAllColor(Component.translatable("attribute.name.generic.attack_damage")))
                                    .withStyle(ChatFormatting.DARK_GREEN));
                    return;
                }
            }
        }
        if (event.getItemStack().getItem() instanceof TetanusBlade) {
            for (int x = 0; x < event.getToolTip().size(); x++) {
                if (event.getToolTip().get(x).contains(Component.translatable("attribute.name.generic.attack_damage")) || event.getToolTip().get(x).contains(Component.literal("Attack Damage"))) {
                    event.getToolTip().set(x,
                            Component.literal(" ").withStyle(ChatFormatting.BLUE)
                                .append(TextUtils.apply(Component.translatable("ts.attribute.damage_tetanus_blade")))
                                .append(" ")
                                .append(Component.translatable("attribute.name.generic.attack_damage"))
                                .withStyle(ChatFormatting.DARK_GREEN));
                }
            }
            event.getToolTip().add(event.getToolTip().size() - 5, Component.translatable("ts.sword.tip.two"));
        }
    }
}
