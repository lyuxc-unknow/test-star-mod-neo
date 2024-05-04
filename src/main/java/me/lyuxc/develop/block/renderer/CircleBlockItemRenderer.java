package me.lyuxc.develop.block.renderer;

import me.lyuxc.develop.block.blockItem.CircleBlockItem;
import me.lyuxc.develop.block.model.CircleBlockItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class CircleBlockItemRenderer extends GeoItemRenderer<CircleBlockItem> {
    public CircleBlockItemRenderer() {
        super(new CircleBlockItemModel());
    }
}
