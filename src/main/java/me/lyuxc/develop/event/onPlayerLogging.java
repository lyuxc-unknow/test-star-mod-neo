package me.lyuxc.develop.event;

import me.lyuxc.develop.Star;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.util.Objects;

@Mod.EventBusSubscriber
public class onPlayerLogging {
    @SubscribeEvent
    public static void onLogging(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        CompoundTag tags = player.getPersistentData();
        //如果第一次加入游戏
        if(tags.get("first_join") == null || !Objects.requireNonNull(tags.get("first_join")).getAsString().equals("1")) {
            player.getPersistentData().putString("first_join", "1");
            tags.putString("first_join", "1");
            Objects.requireNonNull(player.getAttributes().getInstance(Attributes.MAX_HEALTH)).setBaseValue(Star.MAX_HEALTH);
            Objects.requireNonNull(player.getAttributes().getInstance(NeoForgeMod.ENTITY_GRAVITY.value())).setBaseValue(0.015);
            player.save(tags);
        }
        //开发者标签添加
        if(player.getName().getString().equals(Star.DEVELOPER_NAME)) {
            Star.DEVELOPER = true;
        }
    }
}
