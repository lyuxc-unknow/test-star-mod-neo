package me.lyuxc.develop.item.items;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class GazeOfCapital extends Item {
    public GazeOfCapital(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {
        if (!pLevel.isClientSide()) {
            pPlayer.setHealth(pPlayer.getHealth() / 2);
            pPlayer.addEffect(new MobEffectInstance(MobEffects.UNLUCK, 30 * 20, 255));
            pPlayer.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 30 * 20, 5));
            pPlayer.addEffect(new MobEffectInstance(MobEffects.POISON, 10 * 20, 3));
            pPlayer.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 10 * 20, 255));
            pPlayer.addEffect(new MobEffectInstance(MobEffects.HEAL, 20, 255));
            pPlayer.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 1000 * 20, 5));
            pPlayer.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 99999 * 20, 255));
//            pPlayer.addEffect(new MobEffectInstance(MobEffects.SATURATION, 99999 * 20, 10));
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
