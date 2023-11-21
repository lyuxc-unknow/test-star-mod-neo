package me.lyuxc.develop.datagen;

import me.lyuxc.develop.Star;
import me.lyuxc.develop.block.BlockRegistry;
import me.lyuxc.develop.item.ItemRegistry;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;


public class langEnUs extends LanguageProvider {

    public langEnUs(PackOutput output, String locale) {
        super(output, Star.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        //物品
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
        //方块
        add(BlockRegistry.EXAMPLE_BLOCK_ITEM.get(), "Example Block");
        add(BlockRegistry.STAR_BLOCK_ITEM.get(), "Star Block");
        add(BlockRegistry.FAN_BLOCK.get(), "Fan Block");
        //自定义
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
        add("ts.multiplayer_tool.tip", "Multiplayer game has been unlocked, please restart the game");
        add("ts.multiplayer.disable", "Multiplayer Disable");
        add("tip.error.out_of_mod_number", "Number of Mods loaded exceeds limit");
    }
}
