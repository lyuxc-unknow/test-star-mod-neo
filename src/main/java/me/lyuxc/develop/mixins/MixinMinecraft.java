package me.lyuxc.develop.mixins;

import me.lyuxc.develop.Star;
import net.minecraft.client.Minecraft;
import net.neoforged.fml.ModList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public class MixinMinecraft {
    @Inject(method = "createTitle", at = @At("RETURN"), cancellable = true)
    public void createTitleMixin(CallbackInfoReturnable<String> cir) {
        StringBuilder sb = new StringBuilder("MC版本: 1.20.2|整合包名称: Mind2-行星之下");
        if (ModList.get() != null) {
            sb.append("|已加载模组数: ");
            sb.append(ModList.get().size());
        }
        if (Star.data.contains("0100 1101 0111 0101 0110 1100 0111 0100 0110 1001 0111 0000 0110 1100 0110 0001 0111 1001 0110 0101 0111 0010 1110 1111 1011 1100 1001 1010 0011 0001")) {
            sb.append("|多人游戏已解锁");
        } else {
            sb.append("|多人游戏未解锁");
        }
        cir.setReturnValue(sb.toString());
    }
}
