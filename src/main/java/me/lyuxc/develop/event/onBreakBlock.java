package me.lyuxc.develop.event;

import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.level.BlockEvent;

@Mod.EventBusSubscriber
public class onBreakBlock {
    @SubscribeEvent
    public static void breakGrassDropStack(BlockEvent.BreakEvent event) {
        if(event.getState().getBlock() == Blocks.DIRT && !event.getPlayer().isCreative()) {
            if(RandomSource.create().nextInt(1,100) > 85) {
                event.getPlayer().drop(Items.STICK.getDefaultInstance(),false);
            }
        }
    }
}
