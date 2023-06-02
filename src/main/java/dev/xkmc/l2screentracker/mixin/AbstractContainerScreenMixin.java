package dev.xkmc.l2screentracker.mixin;

import dev.xkmc.l2screentracker.screen.base.ScreenTrackerClient;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.inventory.AbstractContainerMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractContainerScreen.class)
public abstract class AbstractContainerScreenMixin<T extends AbstractContainerMenu> {

	@Shadow
	public abstract T getMenu();

	@Inject(at = @At("HEAD"), method = "onClose", cancellable = true)
	public void l2library$onClose$trackClose(CallbackInfo ci) {
		if (ScreenTrackerClient.onClientClose(getMenu().containerId)) {
			ci.cancel();
		}
	}

}
