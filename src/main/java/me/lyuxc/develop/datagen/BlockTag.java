package me.lyuxc.develop.datagen;

import me.lyuxc.develop.block.BlockRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;

import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class BlockTag extends BlockTagsProvider {
    public BlockTag(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, modId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(BlockRegistry.EXAMPLE_BLOCK.get())
                .add(BlockRegistry.STAR_BLOCK.get())
                .add(BlockRegistry.FAN_BLOCK.get());
        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(BlockRegistry.STAR_BLOCK.get());
//        tag(Tags.Blocks.STORAGE_BLOCKS_NETHERITE).add(BlockRegistry.STAR_BLOCK.get());
    }
}
