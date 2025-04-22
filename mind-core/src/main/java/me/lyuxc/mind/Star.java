package me.lyuxc.mind;

import me.lyuxc.mind.block.BlockRegistry;
import me.lyuxc.mind.block.renderer.CircleBlockRenderer;
import me.lyuxc.mind.block.renderer.CreativeGeneratorBlockRenderer;
import me.lyuxc.mind.client.SuperGeneratorScreen;
import me.lyuxc.mind.datagen.DataGeneration;
import me.lyuxc.mind.item.ItemRegistry;
import me.lyuxc.mind.keys.Keys;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static me.lyuxc.mind.utils.I18N.getComponent;

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
        //属性注册
        AttachmentTypes.ATTACHMENT_TYPES.register(modEventBus);
        //方块 - 添加到创造物品栏
        modEventBus.addListener(BlockRegistry::addCreativeTab);
        //物品 - 添加到创造物品栏
        modEventBus.addListener(ItemRegistry::addCreativeTab);
        //数据生成器监听
        modEventBus.addListener(DataGeneration::generate);
        //事件注册
        modEventBus.addListener(this::registerCapabilities);
        //通用启动
        modEventBus.addListener(this::commonSetupEvent);
        //客户端启动
        modEventBus.addListener(this::clientSetupEvent);
        //gui注册
        modEventBus.addListener(this::registerMenuScreen);
        //按键初始化注册
        modEventBus.addListener(Keys::init);
        System.out.println();
    }

    public static ResourceLocation rl(String id) {
        return ResourceLocation.fromNamespaceAndPath(Variables.MOD_ID, id);
    }

    private void commonSetupEvent(FMLCommonSetupEvent event) {
        //模组加载数量将检测
        if (ModList.get().getMods().size() >= Variables.MAX_MOD_COUNT) {
            Variables.LOGGER.error("Your Minecraft instance was exited due to too many mods being loaded.");
            System.exit(0);
        }
    }

    private void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, BlockRegistry.SUPER_GENERATOR_ENTITY.get(), (o, direction) -> o.getItemHandler());
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, BlockRegistry.SUPER_GENERATOR_ENTITY.get(), (o, direction) -> o.getEnergyHandler());
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK,BlockRegistry.CREATIVE_GENERATOR_ENTITY.get(),(o,direction) -> o.getEnergyHandler());
    }

    public void registerMenuScreen(RegisterMenuScreensEvent event) {
        event.register(BlockRegistry.SUPER_GENERATOR_CONTAINER.get(), SuperGeneratorScreen::new);
    }

    public void clientSetupEvent(FMLClientSetupEvent event) {
        BlockEntityRenderers.register(BlockRegistry.CIRCLE_BLOCK_ENTITY.get(), CircleBlockRenderer::new);
        BlockEntityRenderers.register(BlockRegistry.CREATIVE_GENERATOR_ENTITY.get(), CreativeGeneratorBlockRenderer::new);
    }
}
