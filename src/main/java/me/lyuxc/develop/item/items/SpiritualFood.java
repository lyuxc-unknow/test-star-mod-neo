package me.lyuxc.develop.item.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class SpiritualFood extends Item {
    public SpiritualFood(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack pStack, @NotNull Level pLevel, @NotNull LivingEntity pLivingEntity) {
        if (pLivingEntity instanceof Player player) {
            player.addEffect(new MobEffectInstance(MobEffects.SATURATION, 99999 * 20, 255));
        }
        return super.finishUsingItem(pStack, pLevel, pLivingEntity);
    }
}
