package me.lyuxc.develop.mixins;

import me.lyuxc.develop.Variables;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.I18n;
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
        sb.append(I18n.get("ts.tips.modpack_name"));
        sb.append(Variables.title==null?I18n.get("ts.tips.user_title"):Variables.title);
        if (ModList.get() != null) {
            sb.append("|");
            sb.append(I18n.get("ts.tips.mods")).append(ModList.get().size());
        }
        sb.append("|");
        if (Variables.data.contains("0100 1101 0111 0101 0110 1100 0111 0100 0110 1001 0111 0000 0110 1100 0110 0001 0111 1001 0110 0101 0111 0010 1110 1111 1011 1100 1001 1010 0011 0001")) {
            sb.append(I18n.get("ts.multiplayer_tool.tip"));
        } else {
            sb.append(I18n.get("ts.multiplayer.disable_title"));
        }
        return sb.toString();
    }
}
