package me.lyuxc.develop.datagen;

import me.lyuxc.develop.Star;
import me.lyuxc.develop.block.BlockRegistry;
import me.lyuxc.develop.item.ItemRegistry;
import net.minecraft.data.PackOutput;

import java.util.Objects;


public class LanguageProvider extends net.neoforged.neoforge.common.data.LanguageProvider {
    private static String locale1 = "en_us";
    public LanguageProvider(PackOutput output, String locale) {
        super(output, Star.MOD_ID, locale);
        locale1 = locale;
    }

    @Override
    protected void addTranslations() {
        if(Objects.equals(locale1, "en_us")) {
            add(ItemRegistry.LEVEL1SWORD.get(), "Level 1 Sword");
            add(ItemRegistry.LEVEL2SWORD.get(), "Level 2 Sword");
            add(ItemRegistry.LEVEL3SWORD.get(), "Level 3 Sword");
            add(ItemRegistry.LEVEL4SWORD.get(), "Level 4 Sword");
            add(ItemRegistry.LEVEL5SWORD.get(), "Level 5 Sword");
            add(ItemRegistry.LEVEL6SWORD.get(), "Level 6 Sword");
            add(ItemRegistry.LEVEL7SWORD.get(), "Level 7 Sword");
            add(ItemRegistry.LEVEL8SWORD.get(), "Level 8 Sword");
            add(ItemRegistry.LEVEL1ITEM.get(), "Level 1 Item");
            add(ItemRegistry.LEVEL2ITEM.get(), "Level 2 Item");
            add(ItemRegistry.LEVEL3ITEM.get(), "Level 3 Item");
            add(ItemRegistry.LEVEL4ITEM.get(), "Level 4 Item");
            add(ItemRegistry.LEVEL5ITEM.get(), "Level 5 Item");
            add(ItemRegistry.LEVEL6ITEM.get(), "Level 6 Item");
            add(ItemRegistry.LEVEL7ITEM.get(), "Level 7 Item");
            add(ItemRegistry.LEVEL8ITEM.get(), "Level 8 Item");
            add(ItemRegistry.EXAMPLE_ITEM.get(), "Example Item");
            add(ItemRegistry.MedicalBox.get(), "Alpha Medical Box");
            add(ItemRegistry.MY_SWORD.get(), "My Sword");
            add(ItemRegistry.WATER_GETTER.get(), "Water Getter");
            add(ItemRegistry.END_ITEM.get(), "End Item");
            add(ItemRegistry.GravitationalMagneticField.get(), "Disposable gravity magnetic field");
            add(ItemRegistry.GazeOfCapital.get(), "Gaze of Capital");
            add(ItemRegistry.SPIRITUAL_FOOD.get(), "Spiritual Food");
            add(ItemRegistry.MOD_BLOCK_MEDIUM.get(), "Mod Block:Medium");
            add(ItemRegistry.MOD_BLOCK_DATA.get(), "Mod Block:Data");
            add(ItemRegistry.MOD_BLOCK_DREAM.get(), "Mod Block:Dream");
            add(ItemRegistry.MOD_BLOCK_METAL.get(), "Mod Block:Metal");
            add(ItemRegistry.MOD_BLOCK_LIQUID.get(), "Mod Block:Liquid");
            add(ItemRegistry.TETANUS_BLADE.get(), "Tetanus Blade");
            add(ItemRegistry.LIGHT_AR.get(), "Thunder Scepter");
            add(ItemRegistry.MultiPlayerTool.get(), "MultiPlayer Tool");
            add(ItemRegistry.WoodShears.get(), "Wood Shears");
            add(ItemRegistry.BowItem.get(), "Easy Bow");
            add(BlockRegistry.EXAMPLE_BLOCK_ITEM.get(), "Example Block");
            add(BlockRegistry.STAR_BLOCK_ITEM.get(), "Star Block");
            add(BlockRegistry.FAN_BLOCK.get(), "Fan Block");
            add("itemGroup.test_star", "[Mind] Creative Tab");
            add("star.chat.disable.command", "§4Command disable");
            add("chat.gift", "<%s>I need a starter pack");
            add("chat.welcome", "Welcome to the World");
            add("chat.creative", "Creative");
            add("chat.survival", "Survival");
            add("chat.spectator", "Spectator");
            add("chat.key", "Please provide the key");
            add("ts.attribute.damage", "Infinity");
            add("ts.top.block.hardness", "Block Hardness %s");
            add("ts.attribute.damage_tetanus_blade", "?");
            add("ts.command.jrrp.multiplayer.tip", "This function is not available to multiple people. Reason: The problem of interoperability between the data server and the client has not yet been solved.");
            add("ts.command.jrrp", "Today’s lucky value is: %s");
            add("ts.sword.tip.two", "When attacking, the attacked person will take half the damage as compared to the attacker, and at the same time, a certain amount of HP will be restored to the attacker randomly.");
            add("ts.multiplayer_tool.tip", "Multiplayer game has been unlocked");
            add("ts.multiplayer.disable", "Multiplayer Disable");
            add("tip.error.out_of_mod_number", "Number of Mods loaded exceeds limit");
        } else if(Objects.equals(locale1, "zh_cn")) {
            add(ItemRegistry.LEVEL1SWORD.get(), "侵蚀像素剑");
            add(ItemRegistry.LEVEL2SWORD.get(), "坍塌剑");
            add(ItemRegistry.LEVEL3SWORD.get(), "群星光点合金剑");
            add(ItemRegistry.LEVEL4SWORD.get(), "注入了曦绫量子的合金剑");
            add(ItemRegistry.LEVEL5SWORD.get(), "附加了超凌粒子的合金剑");
            add(ItemRegistry.LEVEL6SWORD.get(), "聚合了恒星离子的合金剑");
            add(ItemRegistry.LEVEL7SWORD.get(), "拥有了构建行星力量的合金剑");
            add(ItemRegistry.LEVEL8SWORD.get(), "聚散·聚合·物质剑");
            add(ItemRegistry.LEVEL1ITEM.get(), "侵蚀像素");
            add(ItemRegistry.LEVEL2ITEM.get(), "坍塌数据");
            add(ItemRegistry.LEVEL3ITEM.get(), "群星光点");
            add(ItemRegistry.LEVEL4ITEM.get(), "曦绫量子");
            add(ItemRegistry.LEVEL5ITEM.get(), "超凌粒子");
            add(ItemRegistry.LEVEL6ITEM.get(), "恒星离子");
            add(ItemRegistry.LEVEL7ITEM.get(), "拟·行星构建公式");
            add(ItemRegistry.LEVEL8ITEM.get(), "拟态·世界物质");
            add(ItemRegistry.MedicalBox.get(), "阿尔法·医疗箱");
            add(ItemRegistry.EXAMPLE_ITEM.get(), "测试物品");
            add(ItemRegistry.MY_SWORD.get(), "逆轉世間萬物輪回の劍");
            add(ItemRegistry.WATER_GETTER.get(), "水汽捕获装置");
            add(ItemRegistry.END_ITEM.get(), "相饰位面");
            add(ItemRegistry.GravitationalMagneticField.get(), "一次性重力磁场");
            add(ItemRegistry.GazeOfCapital.get(), "资本的目光");
            add(ItemRegistry.SPIRITUAL_FOOD.get(), "精神粮食");
            add(ItemRegistry.MOD_BLOCK_MEDIUM.get(), "模组数据块：介质");
            add(ItemRegistry.MOD_BLOCK_DATA.get(), "模组数据块：数据");
            add(ItemRegistry.MOD_BLOCK_DREAM.get(), "模组数据块：梦境");
            add(ItemRegistry.MOD_BLOCK_METAL.get(), "模组数据块：金属");
            add(ItemRegistry.MOD_BLOCK_LIQUID.get(), "模组数据块：流体");
            add(ItemRegistry.TETANUS_BLADE.get(), "破伤风之刃");
            add(ItemRegistry.LIGHT_AR.get(), "雷电权杖");
            add(ItemRegistry.MultiPlayerTool.get(), "多人模式解锁工具");
            add(ItemRegistry.WoodShears.get(), "纯木剪刀");
            add(ItemRegistry.BowItem.get(), "简易的弓");
            add(BlockRegistry.EXAMPLE_BLOCK_ITEM.get(), "测试方块");
            add(BlockRegistry.STAR_BLOCK_ITEM.get(), "行星方块");
            add(BlockRegistry.FAN_BLOCK.get(), "拟态·风扇");
            add("itemGroup.test_star", "[Mind]物品栏");
            add("star.chat.disable.command", "§4该命令已被管理员禁用");
            add("chat.gift", "<System>被侵蚀的剑已给与开发者");
            add("chat.welcome", "世界或许依旧，但变化无处不在，开发者，欢迎你！");
            add("chat.creative", "已入侵至管理员权限");
            add("chat.survival", "已回归正常模式");
            add("chat.spectator", "已入侵至旁观模式");
            add("chat.key", "请提供密钥");
            add("ts.attribute.damage", "世間萬物の最高傷害");
            add("ts.attribute.damage_tetanus_blade", "不可估量");
            add("ts.command.jrrp", "今天的幸运值为: %s");
            add("ts.sword.tip.two", "攻击时，被攻击者会受到相对于攻击者当前血量一半的伤害，同时随机回复攻击者一定的血量");
            add("ts.multiplayer_tool.tip", "多人游戏已解锁");
            add("ts.multiplayer.disable", "多人游戏目前被禁用！");
            add("tip.error.out_of_mod_number", "所加载到的模组数量超过上限");
        }
    }
}
