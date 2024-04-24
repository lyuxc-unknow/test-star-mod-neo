package me.lyuxc.develop.item.tools;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Tiers;

public class WoodSawBlade extends AxeItem {
    public WoodSawBlade() {
        super(Tiers.WOOD, new Properties()
                .stacksTo(1)
                .durability(1)
        );
    }

}
