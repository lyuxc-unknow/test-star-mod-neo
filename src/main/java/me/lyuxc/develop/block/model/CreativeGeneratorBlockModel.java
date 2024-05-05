package me.lyuxc.develop.block.model;

import me.lyuxc.develop.Variables;
import me.lyuxc.develop.block.blockEntity.CreativeGeneratorBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CreativeGeneratorBlockModel extends GeoModel<CreativeGeneratorBlockEntity> {
    @Override
    public ResourceLocation getModelResource(CreativeGeneratorBlockEntity creativeGeneratorBlockEntity) {
        return new ResourceLocation(Variables.MOD_ID, "geo/creative_generator.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CreativeGeneratorBlockEntity creativeGeneratorBlockEntity) {
        return new ResourceLocation(Variables.MOD_ID, "textures/block/creative_generator.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CreativeGeneratorBlockEntity creativeGeneratorBlockEntity) {
        return new ResourceLocation(Variables.MOD_ID, "animations/creative_generator.animation.json");
    }
}
