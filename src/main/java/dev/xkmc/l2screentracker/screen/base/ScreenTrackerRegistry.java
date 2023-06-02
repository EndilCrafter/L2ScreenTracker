package dev.xkmc.l2screentracker.screen.base;

import com.tterrag.registrate.util.entry.RegistryEntry;
import dev.xkmc.l2library.base.L2Registrate;
import dev.xkmc.l2screentracker.init.L2ScreenTracker;
import dev.xkmc.l2screentracker.screen.source.*;
import dev.xkmc.l2screentracker.screen.track.*;
import dev.xkmc.l2screentracker.screen.triggers.ExitMenuTrigger;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.PlayerEnderChestContainer;

import java.util.Optional;

public class ScreenTrackerRegistry {

	public static final L2Registrate.RegistryInstance<ItemSource<?>> ITEM_SOURCE = L2ScreenTracker.REGISTRATE.newRegistry("item_source", ItemSource.class);
	public static final L2Registrate.RegistryInstance<TrackedEntryType<?>> TRACKED_ENTRY_TYPE = L2ScreenTracker.REGISTRATE.newRegistry("tracked_entry_type", TrackedEntryType.class);

	public static final RegistryEntry<InventorySource> IS_INVENTORY = L2ScreenTracker.REGISTRATE.simple("inventory", ITEM_SOURCE.key(), InventorySource::new);
	public static final RegistryEntry<EnderSource> IS_ENDER = L2ScreenTracker.REGISTRATE.simple("ender", ITEM_SOURCE.key(), EnderSource::new);

	public static final RegistryEntry<InventoryTrace> TE_INVENTORY = L2ScreenTracker.REGISTRATE.simple("inventory", TRACKED_ENTRY_TYPE.key(), InventoryTrace::new);
	public static final RegistryEntry<EnderTrace> TE_ENDER = L2ScreenTracker.REGISTRATE.simple("ender", TRACKED_ENTRY_TYPE.key(), EnderTrace::new);
	public static final RegistryEntry<QuickAccessTrace> TE_QUICK_ACCESS = L2ScreenTracker.REGISTRATE.simple("quick_access", TRACKED_ENTRY_TYPE.key(), QuickAccessTrace::new);
	public static final RegistryEntry<MenuProviderTrace> TE_MENU_PROVIDER = L2ScreenTracker.REGISTRATE.simple("menu_provider", TRACKED_ENTRY_TYPE.key(), MenuProviderTrace::new);

	public static void register() {
		ExitMenuTrigger.register();
	}

	public static void commonSetup() {

		MenuSourceRegistry.register(MenuType.GENERIC_9x3, (menu, slot, index, wid) ->
				menu.getContainer() instanceof PlayerEnderChestContainer ?
						Optional.of(new PlayerSlot<>(IS_ENDER.get(), new SimpleSlotData(index))) :
						Optional.empty());

		MenuTraceRegistry.register(MenuType.GENERIC_9x3, menu ->
				menu.getContainer() instanceof PlayerEnderChestContainer ?
						Optional.of(TrackedEntry.of(TE_ENDER.get(), NoData.DATA)) :
						Optional.empty());

	}

}
