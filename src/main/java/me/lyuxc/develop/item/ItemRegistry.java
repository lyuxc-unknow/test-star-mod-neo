package me.lyuxc.develop.item;

import me.lyuxc.develop.Star;
import me.lyuxc.develop.Tiers;
import me.lyuxc.develop.Variables;
import me.lyuxc.develop.item.items.*;
import me.lyuxc.develop.item.tools.*;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemRegistry {
    //物品
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Variables.MOD_ID);
    //一级剑
    public static final DeferredItem<Item> LEVEL1SWORD = ITEMS.register("level_one_sword", () -> new Level1Sword(new Item.Properties()));
    //二级剑
    public static final DeferredItem<Item> LEVEL2SWORD = ITEMS.register("level_two_sword", () -> new Level2Sword(new Item.Properties()));
    //三级剑
    public static final DeferredItem<Item> LEVEL3SWORD = ITEMS.register("level_three_sword", () -> new Level3Sword(new Item.Properties()));
    //四级剑
    public static final DeferredItem<Item> LEVEL4SWORD = ITEMS.register("level_four_sword", () -> new Level4Sword(new Item.Properties()));
    //五级剑
    public static final DeferredItem<Item> LEVEL5SWORD = ITEMS.register("level_five_sword", () -> new Level5Sword(new Item.Properties()));
    //六级剑
    public static final DeferredItem<Item> LEVEL6SWORD = ITEMS.register("level_six_sword", () -> new Level6Sword(new Item.Properties()));
    //七级剑
    public static final DeferredItem<Item> LEVEL7SWORD = ITEMS.register("level_seven_sword", () -> new Level7Sword(new Item.Properties()));
    //八级剑
    public static final DeferredItem<Item> LEVEL8SWORD = ITEMS.register("level_eight_sword", () -> new Level8Sword(new Item.Properties()));
    //测试物品
    public static final DeferredItem<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () -> new Item(new Item.Properties()));
    //无铭者の剑
    public static final DeferredItem<Item> MY_SWORD = ITEMS.register("my_sword", MySword::new);
    //一级物品
    public static final DeferredItem<Item> LEVEL1ITEM = ITEMS.register("level_one_item", () -> new Item(new Item.Properties()));
    //二级物品
    public static final DeferredItem<Item> LEVEL2ITEM = ITEMS.register("level_two_item", () -> new Item(new Item.Properties()));
    //三级物品
    public static final DeferredItem<Item> LEVEL3ITEM = ITEMS.register("level_three_item", () -> new Item(new Item.Properties()));
    //四级物品
    public static final DeferredItem<Item> LEVEL4ITEM = ITEMS.register("level_four_item", () -> new Item(new Item.Properties()));
    //五级物品
    public static final DeferredItem<Item> LEVEL5ITEM = ITEMS.register("level_five_item", () -> new Item(new Item.Properties()));
    //六级物品
    public static final DeferredItem<Item> LEVEL6ITEM = ITEMS.register("level_six_item", () -> new Item(new Item.Properties()));
    //七级物品
    public static final DeferredItem<Item> LEVEL7ITEM = ITEMS.register("level_seven_item", () -> new Item(new Item.Properties()));
    //八级物品
    public static final DeferredItem<Item> LEVEL8ITEM = ITEMS.register("level_eight_item", () -> new Item(new Item.Properties()));
    //水汽收集器
    public static final DeferredItem<Item> WATER_GETTER = ITEMS.register("water_getter", () -> new WaterGetter(new Item.Properties()
            .stacksTo(1)
    ));
    //相饰位面（完结物品）
    public static final DeferredItem<Item> END_ITEM = ITEMS.register("end_item", () -> new EndItem(new Item.Properties()
            .stacksTo(1)
    ));
    //一次性引力磁场
    public static final DeferredItem<Item> GravitationalMagneticField = ITEMS.register("gravitational_magnetic_field", () -> new GravitationalMagneticField(new Item.Properties()
            .stacksTo(32)
    ));
    //阿尔法·医疗箱
    public static final DeferredItem<Item> MEDICALBOX = ITEMS.register("alpha_medical_box", () -> new AlphaMedicalBox(new Item.Properties()
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
    public static final DeferredItem<Item> GAZE_OF_CAPITAL = ITEMS.register("gaze_of_capital", () -> new GazeOfCapital(new Item.Properties()
            .stacksTo(1)
    ));
    //精神粮食
    public static final DeferredItem<Item> SPIRITUAL_FOOD = ITEMS.register("spiritual_food", () -> new SpiritualFood(new Item.Properties()
            .stacksTo(16)
            .food(new FoodProperties.Builder()
                    .alwaysEat()
                    .nutrition(21)
                    .build()
            )
    ));
    //数据模组块 -- 数据
    public static final DeferredItem<Item> MOD_BLOCK_DATA = ITEMS.register("mod_block_data", () -> new Item(new Item.Properties()));
    //数据模组块 -- 流体
    public static final DeferredItem<Item> MOD_BLOCK_LIQUID = ITEMS.register("mod_block_liquid", () -> new Item(new Item.Properties()));
    //数据模组块 -- 介质
    public static final DeferredItem<Item> MOD_BLOCK_MEDIUM = ITEMS.register("mod_block_medium", () -> new Item(new Item.Properties()));
    //数据模组块 -- 金属
    public static final DeferredItem<Item> MOD_BLOCK_METAL = ITEMS.register("mod_block_metal", () -> new Item(new Item.Properties()));
    //数据模组块 -- 梦境
    public static final DeferredItem<Item> MOD_BLOCK_DREAM = ITEMS.register("mod_block_dream", () -> new Item(new Item.Properties()));
    //破伤风之刃
    public static final DeferredItem<Item> TETANUS_BLADE = ITEMS.register("tetanus_blade", () -> new TetanusBlade(new Item.Properties()));
    //雷电权杖
    public static final DeferredItem<Item> LIGHT_AR = ITEMS.register("light_ar", () -> new Light(new Item.Properties()));
    //多人模式解锁工具
    public static final DeferredItem<Item> MultiPlayerTool = ITEMS.register("multiplayer_tool", () -> new MultiPlayerTool(new Item.Properties()));
    //木剪刀
    public static final DeferredItem<Item> WoodShears = ITEMS.register("wood_shears", () -> new WoodShears(new Item.Properties()
            .durability(1024)
    ));
    //简易的弓
    public static final DeferredItem<BowItem> BowItem = ITEMS.register("my_bow",() -> new BowItem(new Item.Properties()
            .stacksTo(1)
    ));
    public static final DeferredItem<AllOurposeTool> ALL_OURPOSE_TOOL_1 = ITEMS.register("allinone_tool_1",() -> new AllOurposeTool(Tiers.LEVEL1));
    public static final DeferredItem<AllOurposeTool> ALL_OURPOSE_TOOL_2 = ITEMS.register("allinone_tool_2",() -> new AllOurposeTool(Tiers.LEVEL2));
    public static final DeferredItem<AllOurposeTool> ALL_OURPOSE_TOOL_3 = ITEMS.register("allinone_tool_3",() -> new AllOurposeTool(Tiers.LEVEL3));
    public static final DeferredItem<AllOurposeTool> ALL_OURPOSE_TOOL_4 = ITEMS.register("allinone_tool_4",() -> new AllOurposeTool(Tiers.LEVEL4));
    public static final DeferredItem<AllOurposeTool> ALL_OURPOSE_TOOL_5 = ITEMS.register("allinone_tool_5",() -> new AllOurposeTool(Tiers.LEVEL5));
    public static final DeferredItem<AllOurposeTool> ALL_OURPOSE_TOOL_6 = ITEMS.register("allinone_tool_6",() -> new AllOurposeTool(Tiers.LEVEL6));
    public static final DeferredItem<AllOurposeTool> ALL_OURPOSE_TOOL_7 = ITEMS.register("allinone_tool_7",() -> new AllOurposeTool(Tiers.LEVEL7));
    public static final DeferredItem<AllOurposeTool> ALL_OURPOSE_TOOL_8 = ITEMS.register("allinone_tool_8",() -> new AllOurposeTool(Tiers.LEVEL8));
    public static final DeferredItem<AllOurposeTool> ALL_OURPOSE_TOOL_INF = ITEMS.register("allinone_tool_inf",() -> new AllOurposeTool(Tiers.LEVEL_INF));
    public static final DeferredItem<WoodSawBlade> WOOD_SAW_BLADE = ITEMS.register("wood_sawblade", WoodSawBlade::new);
    //添加到模组的创造物品栏
    public static void addCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == Star.STAR_TAB.value()) {
            ITEMS.getEntries().stream().map(DeferredHolder::get).forEach(event::accept);
        }
    }

    //初始化调用
    public static void init(IEventBus iEventBus) {
        for (int i=0;i<20;i++) {
            ITEMS.register("package_"+i,() -> new Item(new Item.Properties()));
        }
        ITEMS.register(iEventBus);
    }
}
