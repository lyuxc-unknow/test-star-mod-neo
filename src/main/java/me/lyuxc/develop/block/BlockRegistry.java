package me.lyuxc.develop.block;

import me.lyuxc.develop.Star;
import me.lyuxc.develop.block.blocks.FanBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.ForgeRegistries;
import net.neoforged.neoforge.registries.RegistryObject;


public class BlockRegistry {
    //方块注册
    public static final DeferredRegister<Block> BLOCK_DEFERRED_REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, Star.MOD_ID);
    //物品注册
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Star.MOD_ID);
    //方块物品注册
    public static final DeferredRegister<BlockEntityType<?>> ITEM_DEFERRED_REGISTER = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Star.MOD_ID);
    //添加方块
    public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCK_DEFERRED_REGISTER.register("example_block", () -> new Block(BlockBehaviour.Properties.of()
            .mapColor(MapColor.STONE)
            .strength(5)
    ));
    public static final RegistryObject<Block> STAR_BLOCK = BLOCK_DEFERRED_REGISTER.register("star_block", () -> new Block(BlockBehaviour.Properties.of()
            .mapColor(MapColor.STONE)
            .requiresCorrectToolForDrops()
            .strength(100.0F, 1024.0F)
    ));
    public static final RegistryObject<Block> FAN_BLOCK = BLOCK_DEFERRED_REGISTER.register("fan_block", () -> new FanBlock(BlockBehaviour.Properties.of()
            .mapColor(MapColor.STONE)
            .requiresCorrectToolForDrops()
            .strength(100.0F, 1024.0F)
    ));
    //方块物品
    public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block", () -> new BlockItem(EXAMPLE_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> STAR_BLOCK_ITEM = ITEMS.register("star_block", () -> new BlockItem(STAR_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> FAN_BLOCK_ITEM = ITEMS.register("fan_block", () -> new BlockItem(FAN_BLOCK.get(), new Item.Properties()));

    //        //注册方块类数组
//    static String[] blocks_id = new String[]{
//            "star_block"
//    };
//
//    //添加循环注册
//    static void addReg() {
//        for(String id : blocks_id) {
//            RegistryObject<Block> Block = BLOCK_DEFERRED_REGISTER.register(id,() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)));
//            ITEMS.register(id, () -> new BlockItem(Block.get(), new Item.Properties()));
//        }
//    }
    //添加到创造物品栏
    public static void addCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == Star.STAR_TAB.get()) {
            event.accept(EXAMPLE_BLOCK);
            event.accept(STAR_BLOCK);
            event.accept(FAN_BLOCK);
        }
    }

    //初始化调用
    public static void init(IEventBus iEventBus) {
//        addReg();
        BLOCK_DEFERRED_REGISTER.register(iEventBus);
        ITEMS.register(iEventBus);
        ITEM_DEFERRED_REGISTER.register(iEventBus);
    }
}
