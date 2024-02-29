package me.lyuxc.develop;

import me.lyuxc.develop.block.BlockRegistry;
import me.lyuxc.develop.datagen.DataGeneration;
import me.lyuxc.develop.item.ItemRegistry;
import me.lyuxc.develop.utils.FileUtils;
import me.lyuxc.develop.utils.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.io.FileNotFoundException;
import java.util.List;

@Mod(Variables.MOD_ID)
public class Star {
    //新建 - 创造物品栏
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Variables.MOD_ID);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> STAR_TAB = CREATIVE_MODE_TABS.register("mind_creative_tab",() -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.test_star"))
            .icon(() -> ItemRegistry.END_ITEM.get().getDefaultInstance())
            .build());

    public Star(IEventBus modEventBus) {
        //方块注册
        BlockRegistry.init(modEventBus);
        //物品注册
        ItemRegistry.init(modEventBus);
        //物品栏注册
        CREATIVE_MODE_TABS.register(modEventBus);
        //方块 - 添加到创造物品栏
        modEventBus.addListener(BlockRegistry::addCreativeTab);
        //物品 - 添加到创造物品栏
        modEventBus.addListener(ItemRegistry::addCreativeTab);
        //数据生成器监听
        modEventBus.addListener(DataGeneration::generate);
        //事件注册
        modEventBus.addListener(this::CommonSetupEvent);
    }

    private void CommonSetupEvent(FMLCommonSetupEvent event) {
        //TOP注册 - TODO TOP Unable update to 1.20.4
        //TOPRegister.topRegister();
        //模组加载数量将检测
        try {
            Variables.IDs = FileUtils.readFromFile("banBlock.recipes", false).split(System.lineSeparator());
            Variables.recipes.addAll(List.of(FileUtils.readFromFile("dropCrafting.recipes", false).split(System.lineSeparator())));
        } catch (FileNotFoundException e) {
            FileUtils.writeToNewFile("banBlock.recipes", "", false);
            FileUtils.writeToNewFile("dropCrafting.recipes","",false);
//            e.fillInStackTrace();
        }
        Utils.addPlayerPickupRecipes(Items.OAK_LOG,Items.AIR,0 ,Items.OAK_PLANKS,3);
        if (ModList.get().getMods().size() >= Variables.MAX_MOD_COUNT) {
            Variables.LOGGER.error("Your Minecraft instance was exited due to too many mods being loaded.");
            System.exit(0);
        }
    }
}
