package me.lyuxc.develop.block.model;

import me.lyuxc.develop.Variables;
import me.lyuxc.develop.block.blockEntity.CircleBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CircleBlockModel extends GeoModel<CircleBlockEntity> {
    @Override
    public ResourceLocation getModelResource(CircleBlockEntity block) {
        return new ResourceLocation(Variables.MOD_ID, "geo/circle.json");
    }

    @Override
    public ResourceLocation getTextureResource(CircleBlockEntity block) {
        return new ResourceLocation(Variables.MOD_ID, "textures/block/circle.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CircleBlockEntity block) {
        return new ResourceLocation(Variables.MOD_ID, "animations/circle.animation.json");
    }
}
