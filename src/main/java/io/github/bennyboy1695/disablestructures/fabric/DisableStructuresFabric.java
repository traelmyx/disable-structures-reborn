package io.github.bennyboy1695.disablestructures.fabric;

//? if fabric {
/*import io.github.bennyboy1695.disablestructures.DisableStructures;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;

public class DisableStructuresFabric implements ModInitializer {
	@Override
	public void onInitialize() {
		DisableStructures.loadConfig(FabricLoader.getInstance().getConfigDir());
		// read the list again before each world loads, so edits apply without a restart
		ServerLifecycleEvents.SERVER_STARTING.register(server -> DisableStructures.loadConfig(FabricLoader.getInstance().getConfigDir()));
	}
}
*///?}
