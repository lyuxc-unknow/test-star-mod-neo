package me.lyuxc.develop.mixins.Minecraft;

import net.minecraft.world.level.storage.PrimaryLevelData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PrimaryLevelData.class)
public abstract class PrimaryLevelDataMixin {

    @Inject(method = "isAllowCommands", at = @At("RETURN"), cancellable = true, remap = false)
    public void getAllowCommands$mixin(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(Boolean.FALSE);
    }
}
