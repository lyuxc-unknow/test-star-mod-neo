package me.lyuxc.develop.mixins.Minecraft;

import me.lyuxc.develop.Variables;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.components.LockIconButton;
import net.minecraft.client.gui.layouts.LayoutElement;
import net.minecraft.client.gui.screens.OptionsScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ServerboundChangeDifficultyPacket;
import net.minecraft.world.Difficulty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(OptionsScreen.class)
public class OptionScreenMixin {
    @Shadow
    private CycleButton<Difficulty> difficultyButton;
    @Shadow
    private LockIconButton lockButton;

    /**
     * @author lyuxc
     * @reason lock Difficulty
     */
    @Overwrite
    public static CycleButton<Difficulty> createDifficultyButton(int x, int y, String message, Minecraft minecraft) {
        Difficulty[] difficulty = Variables.DEVELOPER?Difficulty.values(): new Difficulty[]{Difficulty.HARD};
        return CycleButton.builder(Difficulty::getDisplayName)
                .withValues(difficulty)
                .withInitialValue(Difficulty.HARD)
                .create(
                        x,
                        y,
                        150,
                        20,
                        Component.translatable(message),
                        (cycleButton, difficulty1) -> Objects.requireNonNull(minecraft.getConnection()).send(new ServerboundChangeDifficultyPacket(difficulty1))
                );
    }
    @Inject(method = "createOnlineButton",at = @At("RETURN"))
    private void mixinCreateOnlineButton(CallbackInfoReturnable<LayoutElement> cir) {
        if(!Variables.DEVELOPER) {
            if(this.difficultyButton != null && this.lockButton != null) {
                this.difficultyButton.active = false;
                this.lockButton.setLocked(true);
                this.lockButton.active = false;
            }
        }
    }
}
