package me.lyuxc.develop.block;

import com.mojang.datafixers.DSL;
import me.lyuxc.develop.Star;
import me.lyuxc.develop.Variables;
import me.lyuxc.develop.block.blockContainer.SuperGeneratorContainer;
import me.lyuxc.develop.block.blockEntity.CircleBlockEntity;
import me.lyuxc.develop.block.blockEntity.CreativeGeneratorBlockEntity;
import me.lyuxc.develop.block.blockEntity.SuperGeneratorEntity;
import me.lyuxc.develop.block.blockItem.CircleBlockItem;
import me.lyuxc.develop.block.blockItem.CreativeGeneratorBlockItem;
import me.lyuxc.develop.block.blocks.CircleBlock;
import me.lyuxc.develop.block.blocks.CreativeGeneratorBlock;
import me.lyuxc.develop.block.blocks.FanBlock;
import me.lyuxc.develop.block.blocks.SuperGenerator;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class BlockRegistry {
    //方块注册
    public static final DeferredRegister.Blocks BLOCK_DEFERRED_REGISTER = DeferredRegister.createBlocks(Variables.MOD_ID);
    //物品注册
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Variables.MOD_ID);
    //方块物品注册
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPE = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Variables.MOD_ID);
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(BuiltInRegistries.MENU, Variables.MOD_ID);
    //发电机
    public static final DeferredBlock<SuperGenerator> SUPER_GENERATOR = BLOCK_DEFERRED_REGISTER.register("super_generator", SuperGenerator::new);
    public static final DeferredItem<Item> SUPER_GENERATOR_ITEM = ITEMS.register("super_generator", () -> new BlockItem(SUPER_GENERATOR.get(), new Item.Properties()));
    public static final Supplier<BlockEntityType<SuperGeneratorEntity>> SUPER_GENERATOR_ENTITY = BLOCK_ENTITY_TYPE.register("super_generator",
            () -> BlockEntityType.Builder.of(SuperGeneratorEntity::new, SUPER_GENERATOR.get()).build(DSL.emptyPartType()));
    public static final Supplier<MenuType<SuperGeneratorContainer>> SUPER_GENERATOR_CONTAINER = MENU_TYPES.register("super_generator",
            () -> IMenuTypeExtension.create((menuId, inv, data) -> new SuperGeneratorContainer(menuId, inv.player, data.readBlockPos())));
    //法阵效果
    public static final DeferredBlock<CircleBlock> CIRCLE_BLOCK = BLOCK_DEFERRED_REGISTER.register("circle", CircleBlock::new);

    public static final Supplier<BlockEntityType<CircleBlockEntity>> CIRCLE_BLOCK_ENTITY = BLOCK_ENTITY_TYPE.register("circle",
            () -> BlockEntityType.Builder.of(CircleBlockEntity::new, CIRCLE_BLOCK.get()).build(DSL.emptyPartType()));

    public static final DeferredItem<Item> CIRCLE_BLOCK_ITEM = ITEMS.register("circle", () -> new CircleBlockItem(CIRCLE_BLOCK.get(), new Item.Properties()));
    //创造发电机
    public static final DeferredBlock<CreativeGeneratorBlock> CREATIVE_GENERATOR_BLOCK = BLOCK_DEFERRED_REGISTER.register("creative_generator", CreativeGeneratorBlock::new);

    public static final Supplier<BlockEntityType<CreativeGeneratorBlockEntity>> CREATIVE_GENERATOR_ENTITY = BLOCK_ENTITY_TYPE.register("creative_generator",
            () -> BlockEntityType.Builder.of(CreativeGeneratorBlockEntity::new, CREATIVE_GENERATOR_BLOCK.get()).build(DSL.emptyPartType()));

    public static final DeferredItem<Item> CREATIVE_GENERATOR_ITEM = ITEMS.register("creative_generator", () -> new CreativeGeneratorBlockItem(CREATIVE_GENERATOR_BLOCK.get(), new Item.Properties()));
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
    @SuppressWarnings("unused")
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
        BLOCK_ENTITY_TYPE.register(iEventBus);
        MENU_TYPES.register(iEventBus);
    }
}
