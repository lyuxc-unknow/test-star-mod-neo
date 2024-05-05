package me.lyuxc.develop.block.renderer;

import me.lyuxc.develop.block.blockEntity.CreativeGeneratorBlockEntity;
import me.lyuxc.develop.block.model.CreativeGeneratorBlockModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class CreativeGeneratorBlockRenderer extends GeoBlockRenderer<CreativeGeneratorBlockEntity> {
    public CreativeGeneratorBlockRenderer(BlockEntityRendererProvider.Context context) {
        super(new CreativeGeneratorBlockModel());
    }
}
