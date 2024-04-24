package me.lyuxc.develop.event;

import me.lyuxc.develop.recipes.ExplosionCraftingRecipes;
import me.lyuxc.develop.recipes.ExplosionMultiItemRecipes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.ExplosionEvent;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

@EventBusSubscriber
public class onExplosion {
    @SubscribeEvent
    public static void explosionCraft(ExplosionEvent.Detonate event) {
        if(!event.getLevel().isClientSide) {
            List<Entity> AffectedEntities = event.getAffectedEntities();
            List<ItemStack> items = new CopyOnWriteArrayList<>();
            for(Entity entity : AffectedEntities) {
                if(entity instanceof ItemEntity itemEntity) {
                    items.add(itemEntity.getItem());
                }
            }
            ExplosionMultiItemRecipes.RECIPES.forEach(recipes -> AffectedEntities.forEach(entity -> {
                if(entity instanceof ItemEntity item) {
                    for(ItemStack item1 : items) {
                        if(item.getItem().getItem() == recipes.inputs().get(0).getItem() && item1.getItem() == recipes.inputs().get(1).getItem()) {
                            int j = Math.min(item.getItem().getCount(),item1.getCount());
                            for(int i=0;i<j;i++) {
                                ExplosionMultiItemCraft(event,recipes, item);
                            }
                        }
                    }
                }
            })
            );
            ExplosionCraftingRecipes.RECIPES.forEach(recipes -> AffectedEntities.forEach(entity -> {
                if(entity instanceof ItemEntity itemEntity) {
                    ItemStack spawnItems = itemEntity.getItem();
                    if(recipes.input().is(spawnItems.getItem())) {
                        ExplosionItemCraft(event,recipes, itemEntity);
                    }
                }
            }));
        }
    }
    private static void ExplosionItemCraft(ExplosionEvent.Detonate event, ExplosionCraftingRecipes recipes, ItemEntity entity) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        if(entity.getItem().getCount() == 0) {
            return;
        }
        int recipeNum = entity.getItem().getCount() / recipes.inputCount();
        for (int i = 0; i < recipeNum; i++) {
            ItemEntity item = new ItemEntity(event.getLevel(), entity.position().x, entity.position().y, entity.position().z, recipes.output().copy());
            item.xo = 0;
            item.yo = 0;
            item.zo = 0;
            item.setPickUpDelay(10);

            if(recipes.change() >= random.nextInt(1, 100)) {
                event.getLevel().addFreshEntity(item);
                entity.getItem().shrink(recipes.inputCount());
            }
        }
    }
    private static void ExplosionMultiItemCraft(ExplosionEvent.Detonate event,ExplosionMultiItemRecipes recipes,ItemEntity entity) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        if(entity.getItem().getCount() == 0) {
            return;
        }
        int recipeNum = entity.getItem().getCount() / recipes.inputCount() / recipes.inputCount()==1?1:recipes.inputs().size();
        for (int i = 0; i < recipeNum; i++) {
            ItemEntity item = new ItemEntity(event.getLevel(), entity.position().x, entity.position().y, entity.position().z, recipes.output().copy());
            item.xo = 0;
            item.yo = 0;
            item.zo = 0;
            item.setPickUpDelay(10);
            if(recipes.change() >= random.nextInt(1, 200)) {
                event.getLevel().addFreshEntity(item);
                entity.getItem().shrink(recipes.inputCount());
            }
        }
    }
}
