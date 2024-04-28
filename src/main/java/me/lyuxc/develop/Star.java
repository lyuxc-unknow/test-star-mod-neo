package me.lyuxc.develop;

import me.lyuxc.develop.block.BlockRegistry;
import me.lyuxc.develop.datagen.DataGeneration;
import me.lyuxc.develop.item.ItemRegistry;
import me.lyuxc.develop.keys.Keys;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static me.lyuxc.develop.utils.I18N.getComponent;

@Mod(Variables.MOD_ID)
public class Star {
    //新建 - 创造物品栏
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Variables.MOD_ID);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> STAR_TAB = CREATIVE_MODE_TABS.register("mind_creative_tab",() -> CreativeModeTab.builder()
            .title(getComponent("itemGroup.test_star"))
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
        modEventBus.addListener(this::ClientSetupEvent);
        modEventBus.addListener(Keys::init);
    }

    @SuppressWarnings("unused")
    public static ResourceLocation rl(String id) {
        return new ResourceLocation(Variables.MOD_ID, id);
    }

    private void CommonSetupEvent(FMLCommonSetupEvent event) {
//        TOPRegister.register();
//        InterModComms.sendTo("theoneprobe", "getTheOneProbe",TOPRegister::new);
        //模组加载数量将检测
        if (ModList.get().getMods().size() >= Variables.MAX_MOD_COUNT) {
            Variables.LOGGER.error("Your Minecraft instance was exited due to too many mods being loaded.");
            System.exit(0);
        }
    }

    private void ClientSetupEvent(FMLClientSetupEvent event) {
        Minecraft.getInstance().options.gamma().set(1D);
    }
}
