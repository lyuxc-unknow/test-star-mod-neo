package me.lyuxc.develop.datagen;

import me.lyuxc.develop.Variables;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.List;
import java.util.Set;


public class DataGeneration {
    public static void generate(GatherDataEvent event) {
        //数据生成器
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = event.getGenerator().getPackOutput();
        //方块状态生成器
        generator.addProvider(event.includeClient(), new BlockStateProviders(packOutput, event.getExistingFileHelper()));
        //物品模型生成器
        generator.addProvider(event.includeClient(), new ItemModelProviders(packOutput, event.getExistingFileHelper()));
        //英文
        generator.addProvider(event.includeClient(), new LanguageProviders(packOutput, "en_us"));
        //中文
        generator.addProvider(event.includeClient(), new LanguageProviders(packOutput, "zh_cn"));
        //标签生成器
        generator.addProvider(event.includeClient(), new BlockTagsProviders(packOutput, event.getLookupProvider(), Variables.MOD_ID, event.getExistingFileHelper()));
        //战利品表
        generator.addProvider(true, new LootTableProvider(packOutput, Set.of(),
                List.of(new LootTableProvider.SubProviderEntry(BlockLootTablesProviders::new, LootContextParamSets.BLOCK)), event.getLookupProvider()));
    }
}
