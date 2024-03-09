package me.lyuxc.develop.item.tools;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Tiers;

public class WoodSawBlade extends AxeItem {
    public WoodSawBlade() {
        super(Tiers.WOOD, 1.0f, 65535.0f, new Properties()
                .stacksTo(1)
                .durability(1)
        );
    }

}
