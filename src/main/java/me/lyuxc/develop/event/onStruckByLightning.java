package me.lyuxc.develop.event;

import me.lyuxc.develop.recipes.LightningCraftingRecipes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityStruckByLightningEvent;

@Mod.EventBusSubscriber
public class onStruckByLightning {
    @SubscribeEvent
    public static void lightning(EntityStruckByLightningEvent event) {
        LightningCraftingRecipes.RECIPES.forEach(recipes -> {
            Entity entity = event.getEntity();
            if(entity instanceof ItemEntity item) {
                event.setCanceled(true);
                if(item.getItem().is(recipes.input().getItem())) {
                    ItemStack items = recipes.output();
                    ItemEntity itemEntity = new ItemEntity(entity.level(),entity.position().x,entity.position().y,entity.position().z,items);
                    items.setCount(item.getItem().getCount());
                    itemEntity.setPickUpDelay(10);
                    entity.level().addFreshEntity(itemEntity);
                    entity.setRemoved(Entity.RemovalReason.KILLED);
                }
            }
        });
    }
}
