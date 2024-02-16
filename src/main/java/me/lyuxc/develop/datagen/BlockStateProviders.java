package me.lyuxc.develop.datagen;

import me.lyuxc.develop.Star;
import me.lyuxc.develop.block.BlockRegistry;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;


public class BlockStateProviders extends BlockStateProvider {
    public BlockStateProviders(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Star.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(BlockRegistry.EXAMPLE_BLOCK.get());
        simpleBlock(BlockRegistry.STAR_BLOCK.get());
        simpleBlock(BlockRegistry.FAN_BLOCK.get());
    }
}
