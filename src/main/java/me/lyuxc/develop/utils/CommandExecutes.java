package me.lyuxc.develop.utils;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.lyuxc.develop.Variables;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.Calendar;
import java.util.Objects;

public class CommandExecutes {
    public static int gc() {
        System.gc();
        return Command.SINGLE_SUCCESS;
    }
    public static int jrrp(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer player = context.getSource().getPlayerOrException();
        CompoundTag compoundTag = player.getPersistentData();
        if ((compoundTag.get("jrrpLaseTime") == null || compoundTag.get("jrrp") == null) || (!Objects.requireNonNull(compoundTag.get("jrrpLaseTime")).getAsString().equals(String.valueOf(Variables.calendar.get(Calendar.DAY_OF_YEAR))))) {
            player.getPersistentData().putString("jrrpLaseTime", String.valueOf(Variables.calendar.get(Calendar.DAY_OF_YEAR)));
            player.getPersistentData().putString("jrrp", String.valueOf(Variables.Random_Day.nextInt(101)));
            compoundTag.putString("jrrpLaseTime", String.valueOf(Variables.calendar.get(Calendar.DAY_OF_YEAR)));
            compoundTag.putString("jrrp", String.valueOf(Variables.Random_Day.nextInt(101)));
            player.save(compoundTag);
        }
        player.sendSystemMessage(Component.translatable("ts.command.jrrp", Objects.requireNonNull(compoundTag.get("jrrp")).getAsString()));
        return Command.SINGLE_SUCCESS;
    }

    public static int getHandItem(CommandContext<CommandSourceStack> context) {
        ServerPlayer player = context.getSource().getPlayer();
        Item item = Items.AIR;
        if (player != null) {
            item = player.getItemInHand(InteractionHand.MAIN_HAND).getItem();
        }
        Component component = Component.literal(item.toString())
                .withStyle(Style.EMPTY.withClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD,item.toString())))
                .withStyle(Style.EMPTY.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ITEM, new HoverEvent.ItemStackInfo(item.getDefaultInstance()))))
                .withStyle(ChatFormatting.YELLOW)
                ;
        context.getSource().sendSystemMessage(component);
        return Command.SINGLE_SUCCESS;
    }
}
