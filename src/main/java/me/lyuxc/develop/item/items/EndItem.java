package me.lyuxc.develop.item.items;

import me.lyuxc.develop.item.ItemRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class EndItem extends Item {
    public EndItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand UsedHand) {
        if (player instanceof ServerPlayer serverPlayer) {
            //设置玩家模式
            serverPlayer.setGameMode(GameType.CREATIVE);
            //设置玩家手上的物品为空
            player.setItemInHand(UsedHand, ItemStack.EMPTY);
            if (player.level().isClientSide()) {
                //动画
                Minecraft.getInstance().gameRenderer.displayItemActivation(ItemRegistry.END_ITEM.get().getDefaultInstance());
            }
        }
        return super.use(level, player, UsedHand);
    }
}
