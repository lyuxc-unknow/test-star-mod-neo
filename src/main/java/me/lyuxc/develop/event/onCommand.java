package me.lyuxc.develop.event;

import me.lyuxc.develop.Star;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.CommandEvent;

@Mod.EventBusSubscriber
public class onCommand {
    @SubscribeEvent
    public static void onCommandEvent(CommandEvent event) {
        //获取输入的指令，并以空格进行切割
        String[] command = event.getParseResults().getReader().getRead().split(" ");
        //获取玩家实例
        Player player = event.getParseResults().getContext().getSource().getPlayer();
        //遍历禁用命令列表
        for (String disable_command : Star.DISABLE_COMMAND) {
            //如果输入的命令分割后第零下标为禁用列表中的命令
            if (command[0].equals(disable_command)) {
                //如果当前玩家为开发者，则不进行禁用指令，否则禁用，并提示
                if (player != null) {
                    if (!Star.DEVELOPER) {
                        event.setCanceled(true);
                        event.getParseResults().getContext().getSource().sendSystemMessage(Component.translatable("star.chat.disable.command"));
                    }
                }
            }
        }
    }
}
