package me.lyuxc.develop.datagen;

import me.lyuxc.develop.Star;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.data.event.GatherDataEvent;


public class DataGeneration {
    public static void generate(GatherDataEvent event) {
        //数据生成器
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = event.getGenerator().getPackOutput();
        //方块状态生成器
        generator.addProvider(event.includeClient(), new blockStateGeneration(packOutput, event.getExistingFileHelper()));
        //物品模型生成器
        generator.addProvider(event.includeClient(), new ItemModelDataGeneration(packOutput, event.getExistingFileHelper()));
        //英文 - 语言生成器
        generator.addProvider(event.includeClient(), new langEnUs(packOutput, "en_us"));
        //中文 - 语言生成器
        generator.addProvider(event.includeClient(), new langZhCn(packOutput, "zh_cn"));
        //标签生成器
        generator.addProvider(event.includeClient(), new BlockTag(packOutput, event.getLookupProvider(), Star.MOD_ID, event.getExistingFileHelper()));
    }
}
