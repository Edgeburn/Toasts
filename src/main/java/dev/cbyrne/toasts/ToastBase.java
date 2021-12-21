package dev.cbyrne.toasts;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.cbyrne.toasts.mixin.accessor.MinecraftClientAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.ToastManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

/**
 * A base class for creating custom toasts.
 * This implements some handy functions like drawBackground() and show()
 *
 * @see BasicToast
 */
public abstract class ToastBase implements Toast {
    public void show() {
        getToastManager().add(this);
    }

    protected void drawBackground(MatrixStack matrices, ToastManager manager) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, TEXTURE);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        manager.drawTexture(matrices, 0, 0, 0, 0, this.getWidth(), this.getHeight());
    }

    protected void drawTexture(Identifier texture, MatrixStack matrices, ToastManager manager, int x, int y, int width, int height) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, texture);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        DrawableHelper.drawTexture(matrices, x, y, 0, 0, 0, width, height, width, height);
    }

    protected ToastManager getToastManager() {
        return ((MinecraftClientAccessor) MinecraftClient.getInstance()).getToastManager();
    }
}
