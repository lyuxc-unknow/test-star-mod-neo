package me.lyuxc.develop.block.model;

import me.lyuxc.develop.Variables;
import me.lyuxc.develop.block.blockItem.CircleBlockItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CircleBlockItemModel extends GeoModel<CircleBlockItem> {
    @Override
    public ResourceLocation getModelResource(CircleBlockItem circleBlockItem) {
        return new ResourceLocation(Variables.MOD_ID, "geo/circle.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CircleBlockItem circleBlockItem) {
        return new ResourceLocation(Variables.MOD_ID, "textures/block/circle.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CircleBlockItem circleBlockItem) {
        return new ResourceLocation(Variables.MOD_ID, "animations/circle.animation.json");
    }
}
