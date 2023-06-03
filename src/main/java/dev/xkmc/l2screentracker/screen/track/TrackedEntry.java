package dev.xkmc.l2screentracker.screen.track;

import dev.xkmc.l2screentracker.screen.base.LayerPopType;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import javax.annotation.Nullable;

@SerialClass
public final class TrackedEntry<T extends Record & TrackedEntryData<T>> {

	@SerialClass.SerialField
	private TrackedEntryType<T> type;

	private T data;

	@SerialClass.SerialField
	private String title;

	@Deprecated
	public TrackedEntry() {

	}

	public TrackedEntry(TrackedEntryType<T> type, T data) {
		this.type = type;
		this.data = data;
	}

	public static <T extends Record & TrackedEntryData<T>> TrackedEntry<T> of(TrackedEntryType<T> type, T data) {
		return new TrackedEntry<>(type, data);
	}

	public LayerPopType restoreServerMenu(ServerPlayer player) {
		Component comp = title == null ? null : Component.Serializer.fromJson(title);
		return type.restoreMenuNotifyClient(player, data, comp);
	}

	public boolean shouldReturn(TrackedEntry<?> next) {
		if (type != next.type) {
			return false;
		}
		return data.equals(next.data());
	}

	public TrackedEntryType<T> type() {
		return type;
	}

	public T data() {
		return data;
	}

	public String title() {
		return title;
	}

	public void setTitle(@Nullable Component title) {
		this.title = title == null ? "" : Component.Serializer.toJson(title);
	}
}
