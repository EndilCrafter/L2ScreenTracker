package dev.xkmc.l2screentracker.screen.track;

import dev.xkmc.l2screentracker.click.quickaccess.QuickAccessClickHandler;
import dev.xkmc.l2screentracker.screen.base.LayerPopType;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.Nullable;

public class QuickAccessTrace extends TrackedEntryType<QuickAccessTraceData> {

	@Override
	public LayerPopType restoreMenuNotifyClient(ServerPlayer player, QuickAccessTraceData data, @Nullable Component comp) {
		QuickAccessClickHandler.INS.handle(player, data.stack());
		return LayerPopType.REMAIN;
	}

}
