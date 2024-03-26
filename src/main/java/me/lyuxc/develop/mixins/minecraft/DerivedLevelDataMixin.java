package me.lyuxc.develop.mixins.minecraft;

import net.minecraft.world.level.GameType;
import net.minecraft.world.level.storage.DerivedLevelData;
import net.minecraft.world.level.storage.WorldData;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DerivedLevelData.class)
public abstract class DerivedLevelDataMixin {
    @Shadow public abstract GameType getGameType();

    @Shadow @Final private WorldData worldData;

    @Inject(method = "getAllowCommands",at = @At("RETURN"), cancellable = true)
    public void getAllowCommands(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(this.getGameType().isCreative() && this.worldData.getAllowCommands());
    }
}
