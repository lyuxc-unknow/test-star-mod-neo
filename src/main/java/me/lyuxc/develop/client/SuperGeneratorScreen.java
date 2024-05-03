package me.lyuxc.develop.client;

import me.lyuxc.develop.Variables;
import me.lyuxc.develop.block.blockContainer.SuperGeneratorContainer;
import me.lyuxc.develop.block.blockEntity.SuperGeneratorEntity;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class SuperGeneratorScreen extends AbstractContainerScreen<SuperGeneratorContainer> {
    private static final int ENERGY_LEFT = 96;
    private static final int ENERGY_WIDTH = 72;
    private static final int ENERGY_TOP = 8;
    private static final int ENERGY_HEIGHT = 8;
    private final ResourceLocation GUI = new ResourceLocation(Variables.MOD_ID, "textures/gui/super_generator.png");

    public SuperGeneratorScreen(SuperGeneratorContainer pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTicks, int mouseX, int mouseY) {
        graphics.blit(GUI, leftPos, topPos, 0, 0, this.imageWidth + 10, this.imageHeight);
        int power = menu.getPower();
        int p = (int) ((power / (float) SuperGeneratorEntity.CAPACITY) * ENERGY_WIDTH);
        graphics.fillGradient(leftPos + ENERGY_LEFT, topPos + ENERGY_TOP + 48, leftPos + ENERGY_LEFT + p, topPos + ENERGY_TOP + ENERGY_HEIGHT, 0xffff0000, 0xff000000);
        graphics.fill(leftPos + ENERGY_LEFT + p, topPos + ENERGY_TOP + 48, leftPos + ENERGY_LEFT + ENERGY_WIDTH, topPos + ENERGY_TOP + ENERGY_HEIGHT + 48, 0xff330000);
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int x, int y, float partialTick) {
        super.render(graphics, x, y, partialTick);
        //如果鼠标位置在能量条范围内，则显示能量数值提示
        if (x >= leftPos + ENERGY_LEFT && x < leftPos + ENERGY_LEFT + ENERGY_WIDTH && y >= topPos + ENERGY_TOP + 48 && y < topPos + ENERGY_TOP + ENERGY_HEIGHT + 48) {
            int power = menu.getPower();
            graphics.renderTooltip(this.font, Component.literal(power + " RF"), x, y);
        }
    }

    @Override
    protected void renderLabels(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY) {
        pGuiGraphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 4210752, false);
        pGuiGraphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY - 12, 4210752, false);
    }
}
