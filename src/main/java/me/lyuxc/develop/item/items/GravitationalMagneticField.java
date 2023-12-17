package me.lyuxc.develop.item.items;

import me.lyuxc.develop.item.ItemRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.NeoForgeMod;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class GravitationalMagneticField extends Item {
    public GravitationalMagneticField(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {
        //设置重力
        Objects.requireNonNull(pPlayer.getAttributes().getInstance(NeoForgeMod.ENTITY_GRAVITY.value())).setBaseValue(0.08);
        //播放动画
        if (pLevel.isClientSide())
            Minecraft.getInstance().gameRenderer.displayItemActivation(ItemRegistry.GravitationalMagneticField.get().getDefaultInstance());
        //设置玩家当前手上的物品
//        pPlayer.setItemInHand(pUsedHand, ItemStack.EMPTY);
        pPlayer.getItemInHand(pUsedHand).setCount(pPlayer.getItemInHand(pUsedHand).getCount() - 1);
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
