package Gvopus10.mixin;

import Gvopus10.Gvopus;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GuiGraphics.class)
public abstract class GuiGraphicsMixin {
    @Redirect(
            method = "renderItemDecorations(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;IILjava/lang/String;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Ljava/lang/String;IIIZ)I")
    )
    private int redirected(GuiGraphics instance, Font font, String text, int x, int y, int color, boolean dropShadow) {
        Component component = Component.literal(text).withStyle(Style.EMPTY.withFont(Gvopus.FONT));
        return instance.drawString(font, component, x, y, color);
    }
}