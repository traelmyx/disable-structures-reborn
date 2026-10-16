package io.github.bennyboy1695.disablestructures.neoforge;

//? if neoforge {
import io.github.bennyboy1695.disablestructures.DisableStructures;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;

@Mod(DisableStructures.MOD_ID)
public class DisableStructuresNeoForge {
	public DisableStructuresNeoForge() {
		DisableStructures.loadConfig(FMLPaths.CONFIGDIR.get());
		// read the list again before each world loads, so edits apply without a restart
		NeoForge.EVENT_BUS.addListener((ServerAboutToStartEvent e) -> DisableStructures.loadConfig(FMLPaths.CONFIGDIR.get()));
	}
}
//?}
