package me.lyuxc.mind.event;

import me.lyuxc.mind.Variables;
import me.lyuxc.mind.utils.I18N;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

import java.util.stream.Stream;


@EventBusSubscriber(modid = Variables.MOD_ID)
public class onPlayerLogging {
    @SubscribeEvent
    public static void onLogging(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        //开发者标签添加
        if(player.getName().getString().equals(Variables.DEVELOPER_NAME) && player.getStringUUID().equals(Variables.DEVELOPER_UUID)) {
            Variables.DEVELOPER = true;
            Variables.title = "Mind2-开发";
        }
        player.sendSystemMessage(I18N.getComponent("ts.tips.modpack"));
//        if (event.getEntity().level() instanceof ServerLevel serverLevel) {
//            int playerCount = serverLevel.getServer().getPlayerList().getPlayers().size();
//            if (playerCount == 1) {
//                Utils.executeCommand(serverLevel,"publish",player);
//                System.out.println("1");
//            }
//        }
    }
    @SubscribeEvent
    public static void placeEvent(BlockEvent.EntityPlaceEvent event) {
        Entity entity = event.getEntity();
        BlockPos pos = event.getPos();
        LevelAccessor level = event.getLevel();
        if(entity instanceof Player player) {
            player.sendSystemMessage(Component.literal(pos.toString()));
            Stream<BlockState> blockStateStream = level.getBlockStates(new AABB(pos.getX() - 4, pos.getY() - 1, pos.getZ() - 4, pos.getX() + 4, pos.getY() + 1, pos.getZ() + 4))
                    .filter(blockState -> blockState.is(Blocks.ENDER_CHEST));
            if (blockStateStream.count() > 1) {
                player.sendSystemMessage(Component.literal("11111"));
                event.setCanceled(true);
            }
        }
    }
}
