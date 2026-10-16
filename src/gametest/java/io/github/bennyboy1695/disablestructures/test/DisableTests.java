package io.github.bennyboy1695.disablestructures.test;

import io.github.bennyboy1695.disablestructures.DisableStructures;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;

import java.util.List;

public final class DisableTests {
	private DisableTests() {
	}

	/** Plain ids, whole-mod wildcards and bad entries. */
	public static void configEntries(GameTestHelper helper) {
		DisableStructures.setDisabled(List.of("minecraft:pillager_outpost", "somemod:*", "not a valid id!"));
		helper.assertTrue(DisableStructures.isDisabled(ResourceLocation.withDefaultNamespace("pillager_outpost")), "listed id not disabled");
		helper.assertTrue(DisableStructures.isDisabled(ResourceLocation.fromNamespaceAndPath("somemod", "castle")), "wildcard did not cover the mod");
		helper.assertFalse(DisableStructures.isDisabled(ResourceLocation.withDefaultNamespace("village_plains")), "unlisted id disabled");
		DisableStructures.setDisabled(List.of());
		helper.succeed();
	}

	/** /locate finds a village normally and finds nothing once villages are turned off. */
	public static void locateSkipsDisabled(GameTestHelper helper) {
		ServerLevel level = helper.getLevel();
		var village = level.registryAccess().registryOrThrow(Registries.STRUCTURE).getHolderOrThrow(BuiltinStructures.VILLAGE_PLAINS);
		var generator = level.getChunkSource().getGenerator();
		var origin = helper.absolutePos(net.minecraft.core.BlockPos.ZERO);
		DisableStructures.setDisabled(List.of());
		var found = generator.findNearestMapStructure(level, HolderSet.direct(village), origin, 100, false);
		helper.assertTrue(found != null, "no plains village found even with nothing disabled (test world has no villages?)");
		DisableStructures.setDisabled(List.of("minecraft:village_plains"));
		var after = generator.findNearestMapStructure(level, HolderSet.direct(village), origin, 100, false);
		DisableStructures.setDisabled(List.of());
		helper.assertTrue(after == null, "disabled village still located at " + (after == null ? null : after.getFirst()));
		helper.succeed();
	}
}
