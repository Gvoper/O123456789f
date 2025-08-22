package Gvopus10;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import org.joml.Matrix4f;

@Mod(Gvopus.MOD_ID)
public final class Gvopus {
    public static final String MOD_ID = "o123456789f";
    public static final ResourceLocation FONT = new ResourceLocation(Gvopus.MOD_ID, "3x5");
    public static final ResourceLocation FONT_TINY = new ResourceLocation(Gvopus.MOD_ID, "3x5_tiny");

    public Gvopus() {
        // Инициализация мода
        init();
    }

    public static void init() {
        // Общая инициализация
    }

    public static void renderSizeLabel(Matrix4f matrix, Font fontRenderer, float xPos, float yPos, Component text, float scaleFactor) {
        RenderSystem.disableBlend();
        float x = (xPos + 16.0F) / scaleFactor - fontRenderer.width(text);
        float y = (yPos + 16.0F) / scaleFactor - 7;
        MultiBufferSource.BufferSource buffer = Minecraft.getInstance().renderBuffers().bufferSource();
        fontRenderer.drawInBatch(text, x, y, 16777215, true, matrix, buffer, Font.DisplayMode.NORMAL, 0, 15728880);
        buffer.endBatch();
        RenderSystem.enableBlend();
    }

    public static void renderSizeLabel(GuiGraphics guiGraphics, Font fontRenderer, float xPos, float yPos, Component text) {
        float scaleFactor = Math.min(1.0F, 15F / (float)fontRenderer.width(text));
        PoseStack stack = guiGraphics.pose();
        stack.pushPose();
        stack.translate(0.0F, 0.0F, 200.0F);
        stack.scale(scaleFactor, scaleFactor, 1f);
        renderSizeLabel(stack.last().pose(), fontRenderer, xPos, yPos, text, scaleFactor);
        stack.popPose();
    }
}