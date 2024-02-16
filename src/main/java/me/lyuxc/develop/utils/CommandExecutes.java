package me.lyuxc.develop.utils;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.lyuxc.develop.Variables;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.Calendar;
import java.util.Objects;

public class CommandExecutes {
    public static int gc() {
        System.gc();
        return 1;
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
        return 0;
    }
}
