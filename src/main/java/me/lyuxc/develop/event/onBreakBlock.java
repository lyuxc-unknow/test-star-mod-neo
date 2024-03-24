package me.lyuxc.develop.event;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.level.BlockEvent;

@Mod.EventBusSubscriber
public class onBreakBlock {
    @SubscribeEvent
    public static void breakGrassDropStack(BlockEvent.BreakEvent event) {
        if(event.getState().getBlock() == Blocks.DIRT && !event.getPlayer().isCreative() && event.getPlayer().getItemInHand(InteractionHand.MAIN_HAND) == ItemStack.EMPTY) {
            if(Math.random() * 100 > 65) {
                event.getPlayer().drop(Items.STICK.getDefaultInstance(),false);
            }
        }
        if(event.getState().getBlock() == Blocks.STONE && !event.getPlayer().isCreative() && event.getPlayer().getItemInHand(InteractionHand.MAIN_HAND) == ItemStack.EMPTY) {
            event.getPlayer().drop(Items.FLINT.getDefaultInstance(),false);
        }
    }
}
