package io.github.bennyboy1695.disablestructures;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class DisableStructures {
	public static final String MOD_ID = "disablestructures";
	public static final Logger LOGGER = LogUtils.getLogger();
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

	private static volatile Set<String> disabled = Set.of();
	private static volatile Set<String> disabledNamespaces = Set.of();
	public static volatile boolean debug = false;

	private DisableStructures() {
	}

	/** True if this structure id is on the list, either by name or through a "namespace:*" entry. */
	public static boolean isDisabled(ResourceLocation id) {
		return id != null && (disabled.contains(id.toString()) || disabledNamespaces.contains(id.getNamespace()));
	}

	public static void setDisabled(List<String> entries) {
		Set<String> ids = new HashSet<>();
		Set<String> namespaces = new HashSet<>();
		for (String entry : entries) {
			String e = entry.trim();
			if (e.endsWith(":*")) namespaces.add(e.substring(0, e.length() - 2));
			else if (ResourceLocation.tryParse(e) != null) ids.add(e);
			else LOGGER.warn("Ignoring invalid structure id in disablestructures.json: {}", e);
		}
		disabled = Set.copyOf(ids);
		disabledNamespaces = Set.copyOf(namespaces);
	}

	public static void loadConfig(Path configDir) {
		Path file = configDir.resolve(MOD_ID + ".json");
		List<String> entries = new ArrayList<>();
		try {
			if (Files.exists(file)) {
				try (Reader reader = Files.newBufferedReader(file)) {
					JsonObject json = GSON.fromJson(reader, JsonObject.class);
					if (json.has("debug")) debug = json.get("debug").getAsBoolean();
					if (json.has("disabledStructures")) json.getAsJsonArray("disabledStructures").forEach(e -> entries.add(e.getAsString()));
				}
			} else {
				JsonObject json = new JsonObject();
				json.addProperty("_comment", "Structure ids that should not generate, e.g. \"minecraft:pillager_outpost\". Use \"modid:*\" to turn off every structure from a mod.");
				json.addProperty("debug", false);
				json.add("disabledStructures", GSON.toJsonTree(List.of()));
				Files.createDirectories(configDir);
				try (Writer writer = Files.newBufferedWriter(file)) {
					GSON.toJson(json, writer);
				}
			}
		} catch (IOException | RuntimeException e) {
			LOGGER.error("Could not read {}, nothing will be disabled", file, e);
		}
		setDisabled(entries);
		LOGGER.info("Disable Structures: {} structures and {} mods turned off", disabled.size(), disabledNamespaces.size());
	}
}
