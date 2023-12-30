package me.lyuxc.develop.mixins;

import me.lyuxc.develop.Star;
import me.lyuxc.develop.utils.ReadOrWriteFile;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.File;

@Mixin(TitleScreen.class)
public class MixinTitleScreen {
    @Inject(at = @At("HEAD"), method = "getMultiplayerDisabledReason", cancellable = true)
    private void getMultiplayerDisabledReasonMixin(CallbackInfoReturnable<Component> cir) {
        try {
            File file = new File(ReadOrWriteFile.configFolder + Star.configDir);
            if (file.createNewFile()) {
                ReadOrWriteFile.writeToNewFile(file,"0100 1101 0111 0101 0110 1100 0111 0100 0110 1001 0111 0000 0110 1100 0110 0001 0111 1001 0110 0101 0111 0010 1110 1111 1011 1100 1001 1010 0011 0000",false);
            }
            Star.data = ReadOrWriteFile.readFromFile(file,true).split(System.lineSeparator())[0];
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!Star.data.contains("0100 1101 0111 0101 0110 1100 0111 0100 0110 1001 0111 0000 0110 1100 0110 0001 0111 1001 0110 0101 0111 0010 1110 1111 1011 1100 1001 1010 0011 0001")) {
            cir.setReturnValue(Component.translatable("ts.multiplayer.disable"));
        }
    }
}
