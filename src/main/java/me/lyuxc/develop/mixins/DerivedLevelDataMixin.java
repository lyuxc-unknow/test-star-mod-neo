package me.lyuxc.develop.mixins;

import net.minecraft.world.level.storage.DerivedLevelData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DerivedLevelData.class)
public class DerivedLevelDataMixin {
    @Inject(method = "getAllowCommands",at = @At("RETURN"), cancellable = true)
    public void getAllowCommands(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }
}
