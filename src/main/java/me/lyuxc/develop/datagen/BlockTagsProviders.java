package me.lyuxc.develop.datagen;

import me.lyuxc.develop.block.BlockRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class BlockTagsProviders extends BlockTagsProvider {
    public BlockTagsProviders(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, modId, existingFileHelper);
    }

    @Override
    @SuppressWarnings("all")
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(BlockRegistry.EXAMPLE_BLOCK.get())
                .add(BlockRegistry.STAR_BLOCK.get())
                .add(BlockRegistry.SUPER_GENERATOR.get())
                .add(BlockRegistry.CIRCLE_BLOCK.get())
                .add(BlockRegistry.FAN_BLOCK.get());
        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(BlockRegistry.CIRCLE_BLOCK.get())
                .add(BlockRegistry.STAR_BLOCK.get());
        tag(TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "mineable/aotools"))).addTags(
                BlockTags.MINEABLE_WITH_PICKAXE,
                BlockTags.MINEABLE_WITH_AXE,
                BlockTags.MINEABLE_WITH_HOE,
                BlockTags.MINEABLE_WITH_SHOVEL,
                BlockTags.NEEDS_DIAMOND_TOOL
        );
//        tag(Tags.Blocks.STORAGE_BLOCKS_NETHERITE).add(BlockRegistry.STAR_BLOCK.get());
    }
}
