package me.lyuxc.develop.block.renderer;

import me.lyuxc.develop.block.blockItem.CreativeGeneratorBlockItem;
import me.lyuxc.develop.block.model.CreativeGeneratorBlockItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class CreativeGeneratorBlockItemRenderer extends GeoItemRenderer<CreativeGeneratorBlockItem> {
    public CreativeGeneratorBlockItemRenderer() {
        super(new CreativeGeneratorBlockItemModel());
    }
}
