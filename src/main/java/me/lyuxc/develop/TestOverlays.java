package me.lyuxc.develop;

import com.mojang.blaze3d.platform.GlUtil;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class TestOverlays implements LayeredDraw.Layer {
    private final Minecraft minecraft;

    public TestOverlays() {
        this.minecraft = Minecraft.getInstance();
    }


    @Override
    public void render(@NotNull GuiGraphics guiGraphics, @NotNull DeltaTracker deltaTracker) {
        if(minecraft.hitResult instanceof BlockHitResult result) {
            if (minecraft.level != null) {
                ItemStack itemStack = minecraft.level.getBlockState(result.getBlockPos()).getBlock().asItem().getDefaultInstance();
                String[] openGL_Version = GlUtil.getOpenGLVersion().split(" ");
                guiGraphics.renderItem(itemStack,0,0);
                guiGraphics.drawString(minecraft.font, itemStack.getHoverName().getString(),18,4,0xFFFFFF);
                guiGraphics.drawString(minecraft.font, "当前渲染显卡:"+GlUtil.getRenderer().split("/")[0],0,20,0xFFFFFF);
                guiGraphics.drawString(minecraft.font, "显卡驱动版本:" + openGL_Version[1] + " " + openGL_Version[2],0,30,0xFFFFFF);
                guiGraphics.drawString(minecraft.font, "驱动提供商:" + GlUtil.getVendor(),0,40,0xFFFFFF);
                guiGraphics.drawString(minecraft.font,"CPU信息:" + GlUtil.getCpuInfo(),0,50,0xFFFFFF);
            }
        }
    }
}
