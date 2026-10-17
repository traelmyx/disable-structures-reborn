package io.github.bennyboy1695.disablestructures.test;

import io.github.bennyboy1695.disablestructures.DisableStructures;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;

import java.util.List;

public final class DisableTests {
	private DisableTests() {
	}

	/** Plain ids, whole-mod wildcards and bad entries. */
	public static void configEntries(GameTestHelper helper) {
		DisableStructures.setDisabled(List.of("minecraft:pillager_outpost", "somemod:*", "not a valid id!"));
		helper.assertTrue(DisableStructures.isDisabled("minecraft:pillager_outpost"), "listed id not disabled");
		helper.assertTrue(DisableStructures.isDisabled("somemod:castle"), "wildcard did not cover the mod");
		helper.assertFalse(DisableStructures.isDisabled("minecraft:village_plains"), "unlisted id disabled");
		DisableStructures.setDisabled(List.of());
		helper.succeed();
	}

	/** /locate never points at a disabled structure, and searching a mixed group still works. */
	public static void locateSkipsDisabled(GameTestHelper helper) {
		ServerLevel level = helper.getLevel();
		//? if >=1.21.11 {
		/*var structures = level.registryAccess().lookupOrThrow(Registries.STRUCTURE);
		var village = structures.getOrThrow(BuiltinStructures.VILLAGE_PLAINS);
		var desert = structures.getOrThrow(BuiltinStructures.VILLAGE_DESERT);
		*///?} else {
		var structures = level.registryAccess().registryOrThrow(Registries.STRUCTURE);
		var village = structures.getHolderOrThrow(BuiltinStructures.VILLAGE_PLAINS);
		var desert = structures.getHolderOrThrow(BuiltinStructures.VILLAGE_DESERT);
		//?}
		var generator = level.getChunkSource().getGenerator();
		var origin = helper.absolutePos(net.minecraft.core.BlockPos.ZERO);
		DisableStructures.setDisabled(List.of("minecraft:village_plains"));
		try {
			var found = generator.findNearestMapStructure(level, HolderSet.direct(village), origin, 8, false);
			helper.assertTrue(found == null, "disabled village located at " + (found == null ? null : found.getFirst()));
			var mixed = generator.findNearestMapStructure(level, HolderSet.direct(village, desert), origin, 8, false);
			helper.assertTrue(mixed == null || !mixed.getSecond().equals(village), "mixed search returned the disabled village");
		} finally {
			DisableStructures.setDisabled(List.of());
		}
		helper.succeed();
	}
}
