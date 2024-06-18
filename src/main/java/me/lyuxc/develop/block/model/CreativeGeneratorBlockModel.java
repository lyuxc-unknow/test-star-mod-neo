package me.lyuxc.develop.block.model;

import me.lyuxc.develop.Star;
import me.lyuxc.develop.block.blockEntity.CreativeGeneratorBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CreativeGeneratorBlockModel extends GeoModel<CreativeGeneratorBlockEntity> {
    @Override
    public ResourceLocation getModelResource(CreativeGeneratorBlockEntity creativeGeneratorBlockEntity) {
        return Star.rl("geo/creative_generator.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CreativeGeneratorBlockEntity creativeGeneratorBlockEntity) {
        return Star.rl("textures/block/creative_generator.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CreativeGeneratorBlockEntity creativeGeneratorBlockEntity) {
        return Star.rl("animations/creative_generator.animation.json");
    }
}
