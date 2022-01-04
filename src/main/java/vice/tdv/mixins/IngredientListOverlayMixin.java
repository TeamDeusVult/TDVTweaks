package vice.tdv.mixins;

import com.mojang.blaze3d.matrix.MatrixStack;
import mezz.jei.api.gui.handlers.IGuiProperties;
import mezz.jei.gui.elements.GuiIconToggleButton;
import mezz.jei.gui.overlay.IngredientListOverlay;
import mezz.jei.input.GuiTextFieldFilter;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(value = IngredientListOverlay.class, remap = false)
public class IngredientListOverlayMixin
{
    @Shadow @Final private GuiTextFieldFilter searchField;

    @Shadow @Nullable private IGuiProperties guiProperties;

    @Shadow @Final private GuiIconToggleButton configButton;

    @Inject(cancellable = true,
            at = @At(value = "INVOKE", target = "Lmezz/jei/gui/overlay/IngredientGridWithNavigation;draw(Lnet/minecraft/client/Minecraft;Lcom/mojang/blaze3d/matrix/MatrixStack;IIF)V"),
            method = "drawScreen")
    public void render(Minecraft minecraft, MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks, CallbackInfo ci)
    {
        String value = searchField.getValue();
        if (value.equals("")) {
            if (guiProperties != null) {
                configButton.draw(matrixStack, mouseX, mouseY, partialTicks);
            }
            ci.cancel();
        }
    }

    @Inject(cancellable = true,
            at = @At(value = "HEAD"),
            method = "drawTooltips")
    public void render(Minecraft minecraft, MatrixStack matrixStack, int mouseX, int mouseY, CallbackInfo ci)
    {
        String value = searchField.getValue();
        if (value.equals("")) {
            ci.cancel();
        }
    }
}