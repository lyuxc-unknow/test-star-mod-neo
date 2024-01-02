package me.lyuxc.develop.item.items;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class WaterGetter extends Item {
    public WaterGetter(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand UsedHand) {
        //设置当前手上的物品
        player.setItemInHand(UsedHand, Items.WATER_BUCKET.getDefaultInstance());
        return super.use(level, player, UsedHand);
    }
}
