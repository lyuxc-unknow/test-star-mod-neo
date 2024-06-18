package me.lyuxc.develop.event;

import me.lyuxc.develop.Variables;
import me.lyuxc.develop.recipes.DropCraftingRecipes;
import me.lyuxc.develop.utils.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemEntityPickupEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;


@EventBusSubscriber
public class onPlayerInteract {
    @SubscribeEvent
    public static void onPlayerRightBlock(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        BlockState blockState = level.getBlockState(pos);
        for(String s : Variables.IDs) {
            if(BuiltInRegistries.BLOCK.getKey(blockState.getBlock()).toString().equals(s)) {
                player.displayClientMessage(Component.translatable("ts.tips.right.disable",Utils.getItemStack(s).getHoverName()),true);
                event.setCanceled(true);
            }
        }
        if(blockState.getBlock() == Blocks.END_PORTAL_FRAME) {
            if(!blockState.getValue(BlockStateProperties.EYE) && player.getItemInHand(InteractionHand.MAIN_HAND).is(Items.ENDER_EYE)) {
                if(level instanceof ServerLevel level_) {
                    if(player.getItemBySlot(EquipmentSlot.OFFHAND).is(Utils.getItem("test_star:mod_block_dream"))) {
                        player.getItemBySlot(EquipmentSlot.OFFHAND).setCount(player.getItemBySlot(EquipmentSlot.OFFHAND).getCount() - 1);
                        Entity light = new LightningBolt(EntityType.LIGHTNING_BOLT, level_);
                        light.moveTo(player.position());
                        player.sendSystemMessage(Component.literal("§k welcome to the end"));
                        level_.addFreshEntity(light);
                    } else {
                        event.setCanceled(true);
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public static void onPlayerPickupItem(ItemEntityPickupEvent.Pre event) {
        Player player = event.getPlayer();
        ItemStack itemStack = event.getItemEntity().getItem();
        DropCraftingRecipes.RECIPES.forEach(s -> {
            if(itemStack.is(s.input().getItem()) && player.getItemBySlot(EquipmentSlot.OFFHAND).is(s.offhandItems().getItem())) {
                event.getItemEntity().setRemoved(Entity.RemovalReason.KILLED);
                player.getItemBySlot(EquipmentSlot.OFFHAND).setCount(player.getItemBySlot(EquipmentSlot.OFFHAND).getCount() - s.quantityConsumed());
                ItemStack outputItem = s.output().copy();
                outputItem.setCount(s.outputCount() * itemStack.getCount());
                player.drop(outputItem,false);
            }
        });
    }
}
