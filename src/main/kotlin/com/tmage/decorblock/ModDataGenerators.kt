package com.tmage.decorblock

import net.minecraft.client.data.models.BlockModelGenerators
import net.minecraft.client.data.models.ItemModelGenerators
import net.minecraft.client.data.models.ModelProvider
import net.minecraft.client.data.models.model.ModelTemplates
import net.minecraft.client.data.models.model.TextureMapping
import net.minecraft.client.data.models.model.TextureSlot
import net.minecraft.data.PackOutput
import net.minecraft.world.level.block.SlabBlock
import net.neoforged.neoforge.data.event.GatherDataEvent
import net.neoforged.neoforge.registries.DeferredHolder

object ModDataGenerators {

    fun gatherData(event: GatherDataEvent.Client) {
        event.createProvider(::ModModelProvider)
    }

    class ModModelProvider(output: PackOutput) : ModelProvider(output, DecorBlockMod.MOD_ID) {

        override fun registerModels(blockModels: BlockModelGenerators, itemModels: ItemModelGenerators) {

            fun cubes(list: List<DeferredHolder<net.minecraft.world.level.block.Block, out net.minecraft.world.level.block.Block>>) {
                list.forEach { blockModels.createTrivialCube(it.get()) }
            }

            cubes(ModBlocks.PLANKS)
            cubes(ModBlocks.WOOD_PARQUET)
            cubes(ModBlocks.WOOLS)
            cubes(ModBlocks.CARPETS)
            cubes(ModBlocks.CARPETS_SOLID)
            cubes(ModBlocks.METAL_PANELS)
            cubes(ModBlocks.CONCRETE_BLOCKS)
            cubes(ModBlocks.COBBLE)
            cubes(ModBlocks.BRICKS)
            cubes(ModBlocks.POLISHED)
            cubes(ModBlocks.BRICK_PAINTED)
            cubes(ModBlocks.ROOF_SHINGLES)
            cubes(ModBlocks.ROOF_SHINGLES_COLORED)
            cubes(ModBlocks.TILES)
            cubes(ModBlocks.MOSAIC)
            cubes(ModBlocks.ORNAMENT_CROSS)
            cubes(ModBlocks.ORNAMENT_DIAMOND)
            cubes(ModBlocks.ORNAMENT_STAR)
            cubes(ModBlocks.ORNAMENT_CIRCLE)
            cubes(ModBlocks.GLASS_BLOCKS)
            cubes(ModBlocks.VEINED_BLOCKS)
            cubes(ModBlocks.GRANULAR_BLOCKS)
            cubes(ModBlocks.GRADIENT_BLOCKS)
            cubes(ModBlocks.HEX_TILE_BLOCKS)
            cubes(ModBlocks.MARBLE_VEINS_BLOCKS)
            cubes(ModBlocks.NEON_BLOCKS)
            cubes(ModBlocks.STRIPES_BLOCKS)
            cubes(ModBlocks.TERRAZZO_BLOCKS)

            fun makeSlab(holder: DeferredHolder<net.minecraft.world.level.block.Block, out SlabBlock>) {
                val slab = holder.get()
                val textureMapping = TextureMapping()
                    .put(TextureSlot.ALL, TextureMapping.getBlockTexture(slab))

                val bottom = ModelTemplates.SLAB_BOTTOM.create(slab, textureMapping, blockModels.modelOutput)
                val top = ModelTemplates.SLAB_TOP.create(slab, textureMapping, blockModels.modelOutput)
                val full = ModelTemplates.CUBE_ALL.createWithSuffix(slab, "_double", textureMapping, blockModels.modelOutput)

                val bottomVar = BlockModelGenerators.plainVariant(bottom)
                val topVar = BlockModelGenerators.plainVariant(top)
                val fullVar = BlockModelGenerators.plainVariant(full)

                blockModels.blockStateOutput.accept(
                    BlockModelGenerators.createSlab(slab, bottomVar, topVar, fullVar)
                )

                blockModels.registerSimpleItemModel(slab, bottom)
            }

            ModBlocks.PLANK_SLABS.forEach { makeSlab(it) }
            ModBlocks.STONE_SLABS.forEach { makeSlab(it) }
            ModBlocks.CONCRETE_SLABS.forEach { makeSlab(it) }
            ModBlocks.VEINED_SLABS.forEach { makeSlab(it) }
            ModBlocks.TERRAZZO_SLABS.forEach { makeSlab(it) }
        }
    }
}
