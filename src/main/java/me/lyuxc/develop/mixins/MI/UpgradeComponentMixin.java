package me.lyuxc.develop.mixins.MI;

import aztech.modern_industrialization.machines.components.UpgradeComponent;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(UpgradeComponent.class)
public class UpgradeComponentMixin {
    @Shadow
    private ItemStack itemStack;

    /**
     * @author lyuxc_
     * @reason fix:Cannot encode empty ItemStack
     */
    @Overwrite
    public void writeNbt(CompoundTag tag, HolderLookup.Provider registries) {
        if (!itemStack.isEmpty()) tag.put("upgradesItemStack", this.itemStack.save(registries));
    }
}
