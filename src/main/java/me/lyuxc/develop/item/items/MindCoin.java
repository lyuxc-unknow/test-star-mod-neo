package me.lyuxc.develop.item.items;

import me.lyuxc.develop.utils.Utils;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class MindCoin extends Item {
    public MindCoin(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean onDroppedByPlayer(@NotNull ItemStack item, @NotNull Player player) {
        if (Math.random() * 100 > 80) {
            item.setCount(item.getCount() - 1);
            player.drop(Utils.getItemStack("magic_clover:magic_clover"), false);
        }
        return super.onDroppedByPlayer(item, player);
    }
}
