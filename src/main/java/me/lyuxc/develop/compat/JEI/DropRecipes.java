package me.lyuxc.develop.compat.JEI;

import net.minecraft.world.item.ItemStack;

public class DropRecipes {
    private final ItemStack input;
    private final ItemStack offhandItems;
    private final ItemStack output;

    public DropRecipes(ItemStack input,ItemStack offhandItems,ItemStack output) {
        this.input = input;
        this.offhandItems = offhandItems;
        this.output = output;
    }
    public ItemStack getOffhandItems() {
        return offhandItems;
    }
    public ItemStack getInput() {
        return input;
    }
    public ItemStack getOutput() {
        return output;
    }
}
