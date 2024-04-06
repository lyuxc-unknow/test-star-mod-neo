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
        double r = Math.random() * 100;
        item.setCount(item.getCount() - 1);
        if (r > 80) {
            player.drop(Utils.getItemStack("magic_clover:magic_clover"), false);
        } else if (r > 50) {
            item.setCount(item.getCount() + 1);
        }
        return super.onDroppedByPlayer(item, player);
    }
}
