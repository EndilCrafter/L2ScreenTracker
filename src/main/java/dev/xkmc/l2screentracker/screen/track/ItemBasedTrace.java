package dev.xkmc.l2screentracker.screen.track;

import dev.xkmc.l2screentracker.screen.base.LayerPopType;
import dev.xkmc.l2screentracker.screen.source.PlayerSlot;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public abstract class ItemBasedTrace extends TrackedEntryType<ItemBasedTraceData> {

	@Override
	public LayerPopType restoreMenuNotifyClient(ServerPlayer player, ItemBasedTraceData data, @Nullable Component comp) {
		ItemStack stack = data.parent().getItem(player);
		if (stack.getItem() != data.verifier()) {
			return LayerPopType.FAIL;
		}
		return restore(player, data.parent(), stack, comp);
	}

	public abstract LayerPopType restore(ServerPlayer player, PlayerSlot<?> slot, ItemStack stack, @Nullable Component comp);

}
