package me.lyuxc.develop.mixins;

import me.lyuxc.develop.Variables;
import net.minecraft.client.Minecraft;
import net.neoforged.fml.ModList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft {

    /**
     * @author lyuxc_
     * @reason Change Title
     */
    @Overwrite
    private String createTitle() {
        StringBuilder sb = new StringBuilder();
        sb.append("整合包名称: ");
        sb.append(Variables.title);
        if (ModList.get() != null) {
            sb.append("|已加载模组数: ").append(ModList.get().size());
        }
        if (Variables.data.contains("0100 1101 0111 0101 0110 1100 0111 0100 0110 1001 0111 0000 0110 1100 0110 0001 0111 1001 0110 0101 0111 0010 1110 1111 1011 1100 1001 1010 0011 0001")) {
            sb.append("|多人游戏已解锁");
        } else {
            sb.append("|多人游戏未解锁");
        }
        return sb.toString();
    }
}
