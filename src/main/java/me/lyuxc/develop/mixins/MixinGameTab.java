package me.lyuxc.develop.mixins;

import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.layouts.LayoutSettings;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.client.gui.screens.worldselection.WorldCreationUiState;
import net.minecraft.world.Difficulty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(targets = "net.minecraft.client.gui.screens.worldselection.CreateWorldScreen$GameTab")
public class MixinGameTab {
    @Inject(method = "<init>",at = @At("TAIL"),locals = LocalCapture.CAPTURE_FAILSOFT)
    private void removeCheatButton(CreateWorldScreen this$0, CallbackInfo ci, GridLayout.RowHelper gridlayout$rowhelper, LayoutSettings layoutsettings, CycleButton<WorldCreationUiState.SelectedGameMode> cyclebutton, CycleButton<Difficulty> cyclebutton1, CycleButton<Boolean> cyclebutton2) {
        this$0.getUiState().setAllowCheats(false);
        this$0.getUiState().setDifficulty(Difficulty.HARD);
        this$0.getUiState().setGameMode(WorldCreationUiState.SelectedGameMode.SURVIVAL);
        this$0.getUiState().addListener(worldCreationUiState -> {
            cyclebutton.active = false;
            cyclebutton1.active = false;
            cyclebutton2.active = false;
        });
    }
}
