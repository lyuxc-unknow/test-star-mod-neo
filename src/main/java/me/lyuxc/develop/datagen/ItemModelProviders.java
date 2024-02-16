package me.lyuxc.develop.datagen;

import me.lyuxc.develop.Star;
import me.lyuxc.develop.block.BlockRegistry;
import me.lyuxc.develop.item.ItemRegistry;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ItemModelProviders extends ItemModelProvider {

    public ItemModelProviders(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Star.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //方块模型继承方块状态
        withExistingParent(BlockRegistry.EXAMPLE_BLOCK.getId().getPath(), modLoc("block/example_block"));
        withExistingParent(BlockRegistry.STAR_BLOCK.getId().getPath(), modLoc("block/star_block"));
        withExistingParent(BlockRegistry.FAN_BLOCK.getId().getPath(), modLoc("block/fan_block"));
        //基础物品注册
        basicItem(ItemRegistry.LEVEL1SWORD.getId());
        basicItem(ItemRegistry.LEVEL2SWORD.getId());
        basicItem(ItemRegistry.LEVEL3SWORD.getId());
        basicItem(ItemRegistry.LEVEL4SWORD.getId());
        basicItem(ItemRegistry.LEVEL5SWORD.getId());
        basicItem(ItemRegistry.LEVEL6SWORD.getId());
        basicItem(ItemRegistry.LEVEL7SWORD.getId());
        basicItem(ItemRegistry.LEVEL8SWORD.getId());
        basicItem(ItemRegistry.LEVEL1ITEM.get());
        basicItem(ItemRegistry.LEVEL2ITEM.get());
        basicItem(ItemRegistry.LEVEL3ITEM.get());
        basicItem(ItemRegistry.LEVEL4ITEM.get());
        basicItem(ItemRegistry.LEVEL5ITEM.get());
        basicItem(ItemRegistry.LEVEL6ITEM.get());
        basicItem(ItemRegistry.LEVEL7ITEM.get());
        basicItem(ItemRegistry.LEVEL8ITEM.get());
        basicItem(ItemRegistry.MedicalBox.get());
        basicItem(ItemRegistry.EXAMPLE_ITEM.getId());
        basicItem(ItemRegistry.MY_SWORD.getId());
        basicItem(ItemRegistry.WATER_GETTER.getId());
        basicItem(ItemRegistry.END_ITEM.get());
        basicItem(ItemRegistry.GravitationalMagneticField.get());
        basicItem(ItemRegistry.GazeOfCapital.get());
        basicItem(ItemRegistry.SPIRITUAL_FOOD.get());
        basicItem(ItemRegistry.MOD_BLOCK_MEDIUM.get());
        basicItem(ItemRegistry.MOD_BLOCK_DATA.get());
        basicItem(ItemRegistry.MOD_BLOCK_DREAM.getId());
        basicItem(ItemRegistry.MOD_BLOCK_LIQUID.getId());
        basicItem(ItemRegistry.MOD_BLOCK_METAL.get());
        basicItem(ItemRegistry.TETANUS_BLADE.get());
        basicItem(ItemRegistry.LIGHT_AR.get());
        basicItem(ItemRegistry.MultiPlayerTool.get());
        basicItem(ItemRegistry.WoodShears.get());

    }
}
