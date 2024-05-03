package me.lyuxc.develop.block.renderer;

import me.lyuxc.develop.block.blockEntity.CircleBlockEntity;
import me.lyuxc.develop.block.model.CircleBlockModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class CircleBlockRenderer extends GeoBlockRenderer<CircleBlockEntity> {
    public CircleBlockRenderer(BlockEntityRendererProvider.Context context) {
        super(new CircleBlockModel());
    }
}
