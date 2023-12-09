package me.lyuxc.develop.mixins;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BaseFireBlock.class)
public class MixinBaseFireBlock {
    @Inject(method = "inPortalDimension", at = @At("RETURN"), cancellable = true)
    private static void inPortalDimension(Level p_49249_, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(p_49249_.dimension() == Level.END || p_49249_.dimension() == Level.NETHER);
    }
}
