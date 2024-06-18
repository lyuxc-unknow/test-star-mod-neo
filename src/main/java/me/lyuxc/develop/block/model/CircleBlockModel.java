package me.lyuxc.develop.block.model;

import me.lyuxc.develop.Star;
import me.lyuxc.develop.block.blockEntity.CircleBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CircleBlockModel extends GeoModel<CircleBlockEntity> {
    @Override
    public ResourceLocation getModelResource(CircleBlockEntity block) {
        return Star.rl( "geo/circle.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CircleBlockEntity block) {
        return Star.rl( "textures/block/circle.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CircleBlockEntity block) {
        return Star.rl( "animations/circle.animation.json");
    }
}
