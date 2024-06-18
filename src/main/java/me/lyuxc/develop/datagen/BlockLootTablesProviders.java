package me.lyuxc.develop.datagen;

import me.lyuxc.develop.block.BlockRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class BlockLootTablesProviders extends BlockLootSubProvider {
    public static final Set<Block> BLOCK = Set.of(
            BlockRegistry.EXAMPLE_BLOCK.get(),
            BlockRegistry.SUPER_GENERATOR.get(),
            BlockRegistry.FAN_BLOCK.get(),
            BlockRegistry.STAR_BLOCK.get(),
            BlockRegistry.CIRCLE_BLOCK.get()
    );

    protected BlockLootTablesProviders(HolderLookup.Provider pLookup) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(),pLookup);
    }

    @Override
    protected void generate() {
        this.dropSelf(BlockRegistry.EXAMPLE_BLOCK.get());
        this.dropSelf(BlockRegistry.FAN_BLOCK.get());
        this.dropSelf(BlockRegistry.STAR_BLOCK.get());
        this.dropSelf(BlockRegistry.SUPER_GENERATOR.get());
        this.dropOther(BlockRegistry.CIRCLE_BLOCK.get(), BlockRegistry.SUPER_GENERATOR);
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return BLOCK;
    }
}
