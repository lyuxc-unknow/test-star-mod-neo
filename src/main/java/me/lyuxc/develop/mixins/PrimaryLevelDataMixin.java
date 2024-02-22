package me.lyuxc.develop.mixins;

import net.minecraft.world.level.storage.PrimaryLevelData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PrimaryLevelData.class)
public class PrimaryLevelDataMixin {
    @Inject(method = "getAllowCommands",at = @At("RETURN"), cancellable = true)
    public void getAllowCommands$mixin(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }
}
