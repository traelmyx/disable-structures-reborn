package io.github.bennyboy1695.disablestructures.mixin;

import io.github.bennyboy1695.disablestructures.DisableStructures;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.SectionPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ChunkGenerator.class)
public class MixinChunkGenerator {
	@Inject(method = "tryGenerateStructure", at = @At("HEAD"), cancellable = true)
	private void disablestructures$skipDisabled(StructureSet.StructureSelectionEntry entry, StructureManager structureManager, RegistryAccess registryAccess,
	                                            RandomState randomState, StructureTemplateManager templateManager, long seed, ChunkAccess chunk,
	                                            ChunkPos chunkPos, SectionPos sectionPos, CallbackInfoReturnable<Boolean> cir) {
		var id = entry.structure().unwrapKey().map(key -> key.location()).orElse(null);
		if (DisableStructures.isDisabled(id)) {
			if (DisableStructures.debug) DisableStructures.LOGGER.info("Disabled generation of {} at {}", id, chunkPos);
			cir.setReturnValue(false);
		}
	}

	// /locate and explorer maps only look for structures that can still generate
	@ModifyVariable(method = "findNearestMapStructure", at = @At("HEAD"), argsOnly = true)
	private HolderSet<Structure> disablestructures$filterSearch(HolderSet<Structure> structures) {
		List<Holder<Structure>> allowed = structures.stream()
			.filter(holder -> !DisableStructures.isDisabled(holder.unwrapKey().map(key -> key.location()).orElse(null)))
			.toList();
		return allowed.size() == structures.size() ? structures : HolderSet.direct(allowed);
	}
}
