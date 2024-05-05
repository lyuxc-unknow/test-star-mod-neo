package me.lyuxc.develop.block.model;

import me.lyuxc.develop.Variables;
import me.lyuxc.develop.block.blockItem.CreativeGeneratorBlockItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CreativeGeneratorBlockItemModel extends GeoModel<CreativeGeneratorBlockItem> {
    @Override
    public ResourceLocation getModelResource(CreativeGeneratorBlockItem creativeGeneratorBlockItem) {
        return new ResourceLocation(Variables.MOD_ID, "geo/creative_generator.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CreativeGeneratorBlockItem creativeGeneratorBlockItem) {
        return new ResourceLocation(Variables.MOD_ID, "textures/block/creative_generator.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CreativeGeneratorBlockItem creativeGeneratorBlockItem) {
        return new ResourceLocation(Variables.MOD_ID, "animations/creative_generator.animation.json");
    }
}
