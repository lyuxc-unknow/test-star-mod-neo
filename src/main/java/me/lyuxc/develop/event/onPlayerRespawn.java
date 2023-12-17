package me.lyuxc.develop.event;

import me.lyuxc.develop.Star;
import me.lyuxc.develop.utils.TimeToTickUtil;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;


import java.util.Objects;

@Mod.EventBusSubscriber
public class onPlayerRespawn {
    @SubscribeEvent
    public static void onRespawn(PlayerEvent.PlayerRespawnEvent event) {
        Player player = event.getEntity();
        player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, TimeToTickUtil.getTime(5), 30));
        //设置最大血量
        Objects.requireNonNull(player.getAttributes().getInstance(Attributes.MAX_HEALTH)).setBaseValue(Star.MAX_HEALTH);
        Objects.requireNonNull(player.getAttributes().getInstance(NeoForgeMod.ENTITY_GRAVITY.value())).setBaseValue(0.015);
        //开发者标签添加
        if (player.getName().getString().equals(Star.DEVELOPER_NAME)) {
            player.addTag(Star.DEVELOPER_TAG);
        } else {
            player.getTags().remove(Star.DEVELOPER_TAG);
        }
    }
}
