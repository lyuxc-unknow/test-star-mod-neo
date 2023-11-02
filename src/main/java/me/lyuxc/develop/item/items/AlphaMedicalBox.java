package me.lyuxc.develop.item.items;

import me.lyuxc.develop.Star;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class AlphaMedicalBox extends Item {
    public AlphaMedicalBox(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack pStack, @NotNull Level pLevel, @NotNull LivingEntity pLivingEntity) {
        if (pLivingEntity instanceof Player) {
            pLivingEntity.setHealth((float) Star.MAX_HEALTH + pLivingEntity.getHealth());
        }
        return super.finishUsingItem(pStack, pLevel, pLivingEntity);
    }
}
