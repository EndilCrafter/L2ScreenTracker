package dev.xkmc.l2screentracker.screen.track;

import dev.xkmc.l2library.base.NamedEntry;
import dev.xkmc.l2screentracker.screen.base.LayerPopType;
import dev.xkmc.l2screentracker.screen.base.ScreenTrackerRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import javax.annotation.Nullable;

public abstract class TrackedEntryType<T extends Record & TrackedEntryData<T>> extends NamedEntry<TrackedEntryType<?>> {

	public TrackedEntryType() {
		super(ScreenTrackerRegistry.TRACKED_ENTRY_TYPE);
	}

	public abstract LayerPopType restoreMenuNotifyClient(ServerPlayer player, T data, @Nullable Component comp);

}
