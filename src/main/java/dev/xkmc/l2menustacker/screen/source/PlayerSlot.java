package dev.xkmc.l2menustacker.screen.source;

import dev.xkmc.l2menustacker.screen.base.L2MSReg;
import dev.xkmc.l2serial.serialization.codec.PacketCodec;
import dev.xkmc.l2serial.util.Wrappers;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

public record PlayerSlot<T extends Record & ItemSourceData<T>>(ItemSource<T> type, T data) {

	public static PlayerSlot<SimpleSlotData> ofInventory(int slot) {
		return new PlayerSlot<>(L2MSReg.IS_INVENTORY.get(), new SimpleSlotData(slot));
	}

	@Nullable
	public static PlayerSlot<?> ofOtherInventory(int slot, int index, int wid, AbstractContainerMenu menu) {
		var getter = MenuSourceRegistry.get(menu.getType());
		if (getter == null) return null;
		return getter.getSlot(Wrappers.cast(menu), slot, index, wid).orElse(null);
	}

	public static PlayerSlot<?> read(RegistryFriendlyByteBuf buf) {
		var ans = PacketCodec.from(buf, PlayerSlot.class, null);
		assert ans != null;
		return ans;
	}

	public void write(RegistryFriendlyByteBuf buf) {
		PacketCodec.to(buf, this);
	}

	public ItemStack getItem(Player player) {
		return type.getItem(player, data);
	}

}
