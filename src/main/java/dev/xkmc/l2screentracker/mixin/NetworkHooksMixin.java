package dev.xkmc.l2screentracker.mixin;

import dev.xkmc.l2screentracker.screen.base.MenuTriggerType;
import dev.xkmc.l2screentracker.screen.base.ScreenTracker;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraftforge.network.NetworkHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(NetworkHooks.class)
public class NetworkHooksMixin {

	@Inject(at = @At("TAIL"), remap = false, method = "openScreen(Lnet/minecraft/server/level/ServerPlayer;Lnet/minecraft/world/MenuProvider;Ljava/util/function/Consumer;)V")
	private static void l2library_openMenu_recordTitle(ServerPlayer player, MenuProvider menu, Consumer<FriendlyByteBuf> cons, CallbackInfo ci) {
		if (menu != null) {
			ScreenTracker.onServerOpenMenu(player, menu, MenuTriggerType.NETWORK_HOOK_OTHER, cons);
		}
	}

}
