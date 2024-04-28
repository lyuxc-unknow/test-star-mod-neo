package me.lyuxc.develop.utils;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;

public class CommandExecutes {
    public static int gc() {
        System.gc();
        return Command.SINGLE_SUCCESS;
    }

    public static int getHandItem(CommandContext<CommandSourceStack> context) {
        ServerPlayer player = context.getSource().getPlayer();
        if (player != null) {
            Item item = player.getItemInHand(InteractionHand.MAIN_HAND).getItem();
            Component component = Component.literal(item.toString())
                    .withStyle(Style.EMPTY.withClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD,item.toString())))
                    .withStyle(Style.EMPTY.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ITEM, new HoverEvent.ItemStackInfo(item.getDefaultInstance()))))
                    .withStyle(ChatFormatting.YELLOW)
                    ;
            context.getSource().sendSystemMessage(component);
        }
        return Command.SINGLE_SUCCESS;
    }
}
