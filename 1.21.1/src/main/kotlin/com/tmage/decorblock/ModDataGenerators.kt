package com.tmage.decorblock

import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.SlabBlock
import net.neoforged.neoforge.client.model.generators.BlockStateProvider
import net.neoforged.neoforge.common.data.ExistingFileHelper
import net.neoforged.neoforge.data.event.GatherDataEvent
import net.neoforged.neoforge.registries.DeferredHolder
import java.util.concurrent.CompletableFuture
import java.util.function.Supplier

object ModDataGenerators {

    fun gatherData(event: GatherDataEvent) {
        val generator = event.generator
        val packOutput = generator.packOutput
        val existingFileHelper = event.existingFileHelper
        val lookupProvider = event.lookupProvider

        generator.addProvider(
            event.includeClient(),
            ModBlockStateProvider(packOutput, DecorBlockMod.MOD_ID, existingFileHelper)
        )
    }

    class ModBlockStateProvider(
        output: PackOutput,
        modId: String,
        existingFileHelper: ExistingFileHelper
    ) : BlockStateProvider(output, modId, existingFileHelper) {

        override fun registerStatesAndModels() {
            ModBlocks.PLANKS.forEach { generateSimpleCube(it, "wood_planks") }
            ModBlocks.WOOD_PARQUET.forEach { generateSimpleCube(it, "wood_parquet") }

            ModBlocks.WOOLS.forEach { generateSimpleCube(it, "fabric") }
            ModBlocks.CARPETS.forEach { generateSimpleCube(it, "fabric") }
            ModBlocks.CARPETS_SOLID.forEach { generateSimpleCube(it, "fabric") }

            ModBlocks.METAL_PANELS.forEach { generateSimpleCube(it, "metal") }

            ModBlocks.CONCRETE_BLOCKS.forEach { generateSimpleCube(it, "concrete") }

            ModBlocks.COBBLE.forEach { generateSimpleCube(it, "stone") }
            ModBlocks.BRICKS.forEach { generateSimpleCube(it, "stone") }
            ModBlocks.POLISHED.forEach { generateSimpleCube(it, "stone_polished") }
            ModBlocks.BRICK_PAINTED.forEach { generateSimpleCube(it, "stone") }
            ModBlocks.ROOF_SHINGLES.forEach { generateSimpleCube(it, "roof") }
            ModBlocks.ROOF_SHINGLES_COLORED.forEach { generateSimpleCube(it, "roof") }

            ModBlocks.TILES.forEach { generateSimpleCube(it, "tiles") }
            ModBlocks.MOSAIC.forEach { generateSimpleCube(it, "tiles") }

            ModBlocks.ORNAMENT_CROSS.forEach { generateSimpleCube(it, "ornament") }
            ModBlocks.ORNAMENT_DIAMOND.forEach { generateSimpleCube(it, "ornament") }
            ModBlocks.ORNAMENT_STAR.forEach { generateSimpleCube(it, "ornament") }
            ModBlocks.ORNAMENT_CIRCLE.forEach { generateSimpleCube(it, "ornament") }
            ModBlocks.GLASS_BLOCKS.forEach { generateSimpleCube(it, "glass") }

            ModBlocks.VEINED_BLOCKS.forEach { generateSimpleCube(it, "veined") }
            ModBlocks.GRANULAR_BLOCKS.forEach { generateSimpleCube(it, "granular") }

            ModBlocks.GRADIENT_BLOCKS.forEach { generateSimpleCube(it, "gradient") }
            ModBlocks.HEX_TILE_BLOCKS.forEach { generateSimpleCube(it, "hex_tiles") }
            ModBlocks.MARBLE_VEINS_BLOCKS.forEach { generateSimpleCube(it, "marble_veins") }
            ModBlocks.NEON_BLOCKS.forEach { generateSimpleCube(it, "neon") }
            ModBlocks.STRIPES_BLOCKS.forEach { generateSimpleCube(it, "stripes") }
            ModBlocks.TERRAZZO_BLOCKS.forEach { generateSimpleCube(it, "terrazzo") }

            ModBlocks.PLANK_SLABS.forEach { generateSlab(it, "wood_planks") }
            ModBlocks.STONE_SLABS.forEach { generateSlab(it, "stone") }
            ModBlocks.CONCRETE_SLABS.forEach { generateSlab(it, "concrete") }
            ModBlocks.VEINED_SLABS.forEach { generateSlab(it, "veined") }
            ModBlocks.TERRAZZO_SLABS.forEach { generateSlab(it, "terrazzo") }
        }

        private fun generateSimpleCube(holder: DeferredHolder<Block, out Block>, folder: String) {
            val block = holder.get()
            val name = holder.id.path

            val textureLoc = ResourceLocation.fromNamespaceAndPath(DecorBlockMod.MOD_ID, "block/$folder/$name")

            val blockModel = models().cubeAll(name, textureLoc)
            simpleBlock(block, blockModel)
            simpleBlockItem(block, blockModel)
        }

        private fun generateSlab(holder: DeferredHolder<Block, out SlabBlock>, folder: String) {
            val slab = holder.get()
            val slabName = holder.id.path
            val baseName = slabName.removeSuffix("_slab")
            val parentName = if (folder == "wood_planks" || folder == "concrete") "${baseName}_1" else baseName

            val texture = ResourceLocation.fromNamespaceAndPath(DecorBlockMod.MOD_ID, "block/$folder/$parentName")
            val doubleModel = ResourceLocation.fromNamespaceAndPath(DecorBlockMod.MOD_ID, parentName)

            slabBlock(slab, doubleModel, texture)
            simpleBlockItem(slab, models().slab(slabName, texture, texture, texture))
        }
    }
}
