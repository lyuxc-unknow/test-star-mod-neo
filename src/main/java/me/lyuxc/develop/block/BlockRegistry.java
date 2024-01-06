package me.lyuxc.develop.block;

import me.lyuxc.develop.Star;
import me.lyuxc.develop.block.blocks.FanBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;


public class BlockRegistry {
    //方块注册
    public static final DeferredRegister.Blocks BLOCK_DEFERRED_REGISTER = DeferredRegister.createBlocks(Star.MOD_ID);
    //物品注册
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Star.MOD_ID);
    //方块物品注册
    public static final DeferredRegister<BlockEntityType<?>> ITEM_DEFERRED_REGISTER = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Star.MOD_ID);
    //添加方块
    public static final DeferredBlock<Block> EXAMPLE_BLOCK = BLOCK_DEFERRED_REGISTER.register("example_block", () -> new Block(BlockBehaviour.Properties.of()
            .mapColor(MapColor.STONE)
            .strength(5)
    ));
    public static final DeferredBlock<Block> STAR_BLOCK = BLOCK_DEFERRED_REGISTER.register("star_block", () -> new Block(BlockBehaviour.Properties.of()
            .mapColor(MapColor.STONE)
            .requiresCorrectToolForDrops()
            .strength(100.0F, 1024.0F)
    ));
    public static final DeferredBlock<Block> FAN_BLOCK = BLOCK_DEFERRED_REGISTER.register("fan_block", () -> new FanBlock(BlockBehaviour.Properties.of()
            .mapColor(MapColor.STONE)
            .requiresCorrectToolForDrops()
            .strength(100.0F, 1024.0F)
    ));
    //方块物品
    public static final DeferredItem<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block", () -> new BlockItem(EXAMPLE_BLOCK.get(), new Item.Properties()));
    public static final DeferredItem<Item> STAR_BLOCK_ITEM = ITEMS.register("star_block", () -> new BlockItem(STAR_BLOCK.get(), new Item.Properties()));
    public static final DeferredItem<Item> FAN_BLOCK_ITEM = ITEMS.register("fan_block", () -> new BlockItem(FAN_BLOCK.get(), new Item.Properties()));

    //添加到创造物品栏
    public static void addCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == Star.STAR_TAB.value()) {
            ITEMS.getEntries().stream().map(DeferredHolder::get).forEach(event::accept);
        }
    }

    //初始化调用
    public static void init(IEventBus iEventBus) {
        BLOCK_DEFERRED_REGISTER.register(iEventBus);
        ITEMS.register(iEventBus);
        ITEM_DEFERRED_REGISTER.register(iEventBus);
    }
}
