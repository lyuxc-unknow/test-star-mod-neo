package me.lyuxc.develop.item.items;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class Light extends Item {
    public Light(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {
        if (pLevel instanceof ServerLevel _level) {
            Entity light = new LightningBolt(EntityType.LIGHTNING_BOLT, _level);
            light.moveTo(pPlayer.position());
            pLevel.addFreshEntity(light);
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
