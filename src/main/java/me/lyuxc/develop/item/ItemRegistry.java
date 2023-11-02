package me.lyuxc.develop.item;

import me.lyuxc.develop.Star;
import me.lyuxc.develop.item.items.*;
import me.lyuxc.develop.item.tools.*;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.ForgeRegistries;
import net.neoforged.neoforge.registries.RegistryObject;


public class ItemRegistry {
    //物品
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Star.MOD_ID);
    //一级剑
    public static final RegistryObject<Item> LEVEL1SWORD = ITEMS.register("level_one_sword", () -> new Level1Sword(new Item.Properties()));
    //二级剑
    public static final RegistryObject<Item> LEVEL2SWORD = ITEMS.register("level_two_sword", () -> new Level2Sword(new Item.Properties()));
    //三级剑
    public static final RegistryObject<Item> LEVEL3SWORD = ITEMS.register("level_three_sword", () -> new Level3Sword(new Item.Properties()));
    //四级剑
    public static final RegistryObject<Item> LEVEL4SWORD = ITEMS.register("level_four_sword", () -> new Level4Sword(new Item.Properties()));
    //五级剑
    public static final RegistryObject<Item> LEVEL5SWORD = ITEMS.register("level_five_sword", () -> new Level5Sword(new Item.Properties()));
    //六级剑
    public static final RegistryObject<Item> LEVEL6SWORD = ITEMS.register("level_six_sword", () -> new Level6Sword(new Item.Properties()));
    //七级剑
    public static final RegistryObject<Item> LEVEL7SWORD = ITEMS.register("level_seven_sword", () -> new Level7Sword(new Item.Properties()));
    //八级剑
    public static final RegistryObject<Item> LEVEL8SWORD = ITEMS.register("level_eight_sword", () -> new Level8Sword(new Item.Properties()));
    //测试物品
    public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () -> new Item(new Item.Properties()));
    //无铭者の剑
    public static final RegistryObject<Item> MY_SWORD = ITEMS.register("my_sword", MySword::new);
    //一级物品
    public static final RegistryObject<Item> LEVEL1ITEM = ITEMS.register("level_one_item", () -> new Item(new Item.Properties()));
    //二级物品
    public static final RegistryObject<Item> LEVEL2ITEM = ITEMS.register("level_two_item", () -> new Item(new Item.Properties()));
    //三级物品
    public static final RegistryObject<Item> LEVEL3ITEM = ITEMS.register("level_three_item", () -> new Item(new Item.Properties()));
    //四级物品
    public static final RegistryObject<Item> LEVEL4ITEM = ITEMS.register("level_four_item", () -> new Item(new Item.Properties()));
    //五级物品
    public static final RegistryObject<Item> LEVEL5ITEM = ITEMS.register("level_five_item", () -> new Item(new Item.Properties()));
    //六级物品
    public static final RegistryObject<Item> LEVEL6ITEM = ITEMS.register("level_six_item", () -> new Item(new Item.Properties()));
    //七级物品
    public static final RegistryObject<Item> LEVEL7ITEM = ITEMS.register("level_seven_item", () -> new Item(new Item.Properties()));
    //八级物品
    public static final RegistryObject<Item> LEVEL8ITEM = ITEMS.register("level_eight_item", () -> new Item(new Item.Properties()));
    //水汽收集器
    public static final RegistryObject<Item> WATER_GETTER = ITEMS.register("water_getter", () -> new WaterGetter(new Item.Properties()
            .stacksTo(1)
    ));
    //相饰位面（完结物品）
    public static final RegistryObject<Item> END_ITEM = ITEMS.register("end_item", () -> new EndItem(new Item.Properties()
            .stacksTo(1)
    ));
    //一次性引力磁场
    public static final RegistryObject<Item> GravitationalMagneticField = ITEMS.register("gravitational_magnetic_field", () -> new GravitationalMagneticField(new Item.Properties()
            .stacksTo(1)
    ));
    //阿尔法·医疗箱
    public static final RegistryObject<Item> MedicalBox = ITEMS.register("alpha_medical_box", () -> new AlphaMedicalBox(new Item.Properties()
            .stacksTo(16)
            .food(new FoodProperties.Builder()
                    .fast()
                    .alwaysEat()
                    .nutrition(10)
                    .saturationMod(10)
                    .build()
            )
    ));
    //资本家的目光
    public static final RegistryObject<Item> GazeOfCapital = ITEMS.register("gaze_of_capital", () -> new GazeOfCapital(new Item.Properties()
            .stacksTo(1)
    ));
    //精神粮食
    public static final RegistryObject<Item> SPIRITUAL_FOOD = ITEMS.register("spiritual_food", () -> new SpiritualFood(new Item.Properties()
            .stacksTo(16)
            .food(new FoodProperties.Builder()
                    .alwaysEat()
                    .nutrition(21)
                    .build()
            )
    ));
    //数据模组块 -- 数据
    public static final RegistryObject<Item> MOD_BLOCK_DATA = ITEMS.register("mod_block_data", () -> new Item(new Item.Properties()));
    //数据模组块 -- 流体
    public static final RegistryObject<Item> MOD_BLOCK_LIQUID = ITEMS.register("mod_block_liquid", () -> new Item(new Item.Properties()));
    //数据模组块 -- 介质
    public static final RegistryObject<Item> MOD_BLOCK_MEDIUM = ITEMS.register("mod_block_medium", () -> new Item(new Item.Properties()));
    //数据模组块 -- 金属
    public static final RegistryObject<Item> MOD_BLOCK_METAL = ITEMS.register("mod_block_metal", () -> new Item(new Item.Properties()));
    //数据模组块 -- 梦境
    public static final RegistryObject<Item> MOD_BLOCK_DREAM = ITEMS.register("mod_block_dream", () -> new Item(new Item.Properties()));
    //破伤风之刃
    public static final RegistryObject<Item> TETANUS_BLADE = ITEMS.register("tetanus_blade", () -> new TetanusBlade(new Item.Properties()));
    //雷电权杖
    public static final RegistryObject<Item> LIGHT_AR = ITEMS.register("light_ar", () -> new Light(new Item.Properties()));
    //多人模式解锁工具
    public static final RegistryObject<Item> MultiPlayerTool = ITEMS.register("multiplayer_tool", () -> new MultiPlayerTool(new Item.Properties()));
    //木剑刀
    public static final RegistryObject<Item> WoodShears = ITEMS.register("wood_shears", () -> new WoodShears(new Item.Properties()
            .durability(1024)
    ));

    //    废案 -- 重力靴
//    public static final RegistryObject<Item> boot_gr = ITEMS.register("boot_gr", () -> new ArmorBoot.Boots(new Item.Properties()));
//    循环注册
//    static String[] items_id = new String[] {
//            "example_item"
//    };
//    static void addReg() {
//        for(String id : items_id) {
//            ITEMS.register(id,() -> new Item(new Item.Properties()));
//        }
//    }
    //添加到模组的创造物品栏
    public static void addCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == Star.STAR_TAB.get()) {
            event.accept(LEVEL1SWORD);
            event.accept(LEVEL2SWORD);
            event.accept(LEVEL3SWORD);
            event.accept(LEVEL4SWORD);
            event.accept(LEVEL5SWORD);
            event.accept(LEVEL6SWORD);
            event.accept(LEVEL7SWORD);
            event.accept(LEVEL8SWORD);
            event.accept(EXAMPLE_ITEM);
            event.accept(MY_SWORD);
            event.accept(WATER_GETTER);
            event.accept(END_ITEM);
            event.accept(LEVEL1ITEM);
            event.accept(LEVEL2ITEM);
            event.accept(LEVEL3ITEM);
            event.accept(LEVEL4ITEM);
            event.accept(LEVEL5ITEM);
            event.accept(LEVEL6ITEM);
            event.accept(LEVEL7ITEM);
            event.accept(LEVEL8ITEM);
            event.accept(MedicalBox);
            event.accept(GazeOfCapital);
            event.accept(SPIRITUAL_FOOD);
            event.accept(GravitationalMagneticField);
            event.accept(MOD_BLOCK_DATA);
            event.accept(MOD_BLOCK_DREAM);
            event.accept(MOD_BLOCK_LIQUID);
            event.accept(MOD_BLOCK_MEDIUM);
            event.accept(MOD_BLOCK_METAL);
            event.accept(TETANUS_BLADE);
            event.accept(LIGHT_AR);
            event.accept(MultiPlayerTool);
            event.accept(WoodShears);
//            event.accept(boot_gr);
        }
    }

    //初始化调用
    public static void init(IEventBus iEventBus) {
//        addReg();
        ITEMS.register(iEventBus);
    }
}
