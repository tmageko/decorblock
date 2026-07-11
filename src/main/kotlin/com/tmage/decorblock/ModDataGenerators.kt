package com.tmage.decorblock

import net.minecraft.data.DataGenerator
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.SlabBlock
import net.minecraftforge.client.model.generators.BlockStateProvider
import net.minecraftforge.common.data.ExistingFileHelper
import net.minecraftforge.data.event.GatherDataEvent
import net.minecraftforge.registries.RegistryObject
import java.util.function.Supplier

object ModDataGenerators {

    fun gatherData(event: GatherDataEvent) {
        val generator = event.generator

        generator.addProvider(
            event.includeClient(),
            ModBlockStateProvider(generator, DecorBlockMod.MOD_ID, event.existingFileHelper)
        )
    }

    class ModBlockStateProvider(gen: DataGenerator, modId: String, exFileHelper: ExistingFileHelper) :
        BlockStateProvider(gen.packOutput, modId, exFileHelper) {

        override fun registerStatesAndModels() {
            // Генерируем модели напрямую из списков блоков ModBlocks

            // 1. Wood Planks
            ModBlocks.PLANKS.forEach { generateSimpleCube(it, "wood_planks") }
            ModBlocks.WOOD_PARQUET.forEach { generateSimpleCube(it, "wood_parquet") }

            // 2. Fabric (Шерсть и ковры)
            ModBlocks.WOOLS.forEach { generateSimpleCube(it, "fabric") }
            ModBlocks.CARPETS.forEach { generateSimpleCube(it, "fabric") }
            ModBlocks.CARPETS_SOLID.forEach { generateSimpleCube(it, "fabric") }

            // 3. Metal Panels
            ModBlocks.METAL_PANELS.forEach { generateSimpleCube(it, "metal") }

            // 4. Concrete
            ModBlocks.CONCRETE_BLOCKS.forEach { generateSimpleCube(it, "concrete") }

            // 5. Stone & Roof
            ModBlocks.COBBLE.forEach { generateSimpleCube(it, "stone") }
            ModBlocks.BRICKS.forEach { generateSimpleCube(it, "stone") }
            ModBlocks.POLISHED.forEach { generateSimpleCube(it, "stone_polished") }
            ModBlocks.BRICK_PAINTED.forEach { generateSimpleCube(it, "stone") }
            ModBlocks.ROOF_SHINGLES.forEach { generateSimpleCube(it, "roof") }
            ModBlocks.ROOF_SHINGLES_COLORED.forEach { generateSimpleCube(it, "roof") }

            // 6. Tiles & Mosaic
            ModBlocks.TILES.forEach { generateSimpleCube(it, "tiles") }
            ModBlocks.MOSAIC.forEach { generateSimpleCube(it, "tiles") }

            // 7. Ornaments & Glass
            ModBlocks.ORNAMENT_CROSS.forEach { generateSimpleCube(it, "ornament") }
            ModBlocks.ORNAMENT_DIAMOND.forEach { generateSimpleCube(it, "ornament") }
            ModBlocks.ORNAMENT_STAR.forEach { generateSimpleCube(it, "ornament") }
            ModBlocks.ORNAMENT_CIRCLE.forEach { generateSimpleCube(it, "ornament") }
            ModBlocks.GLASS_BLOCKS.forEach { generateSimpleCube(it, "glass") }

            // 8. Veined (мрамор с прожилками) & Granular (сыпучие)
            ModBlocks.VEINED_BLOCKS.forEach { generateSimpleCube(it, "veined") }
            ModBlocks.GRANULAR_BLOCKS.forEach { generateSimpleCube(it, "granular") }

            // 9. Новые категории из второй пачки текстур
            ModBlocks.GRADIENT_BLOCKS.forEach { generateSimpleCube(it, "gradient") }
            ModBlocks.HEX_TILE_BLOCKS.forEach { generateSimpleCube(it, "hex_tiles") }
            ModBlocks.MARBLE_VEINS_BLOCKS.forEach { generateSimpleCube(it, "marble_veins") }
            ModBlocks.NEON_BLOCKS.forEach { generateSimpleCube(it, "neon") }
            ModBlocks.STRIPES_BLOCKS.forEach { generateSimpleCube(it, "stripes") }
            ModBlocks.TERRAZZO_BLOCKS.forEach { generateSimpleCube(it, "terrazzo") }

            // 10. Полублоки (слэбы) — переиспользуют текстуру родительского блока
            ModBlocks.PLANK_SLABS.forEach { generateSlab(it, "wood_planks") }
            ModBlocks.STONE_SLABS.forEach { generateSlab(it, "stone") }
            ModBlocks.CONCRETE_SLABS.forEach { generateSlab(it, "concrete") }
            ModBlocks.VEINED_SLABS.forEach { generateSlab(it, "veined") }
            ModBlocks.TERRAZZO_SLABS.forEach { generateSlab(it, "terrazzo") }
        }

        // Безопасный хелпер-метод без ручного поиска по строкам
        private fun generateSimpleCube(registryObject: Supplier<out Block>, folder: String) {
            val entry = registryObject as RegistryObject<out Block>
            val block = entry.get()
            val name = entry.id.path

            // Путь к текстуре внутри вашей папки ресурсов
            val textureLoc = ResourceLocation(DecorBlockMod.MOD_ID, "block/$folder/$name")

            // Создаем модель блока и стейт
            val blockModel = models().cubeAll(name, textureLoc)
            simpleBlock(block, blockModel)

            // Создаем модель предмета
            simpleBlockItem(block, blockModel)
        }

        // Хелпер для полублоков (Slab). Текстура берётся у "родительского" полноразмерного блока —
        // отдельная текстура для слэба не нужна. У planks_* и concrete_* родитель хранится с суффиксом
        // "_1" (первый вариант), у остальных категорий (cobble_*, veined_*) суффикса нет.
        private fun generateSlab(registryObject: Supplier<out SlabBlock>, folder: String) {
            val entry = registryObject as RegistryObject<out SlabBlock>
            val slab = entry.get()
            val slabName = entry.id.path // например "planks_oak_slab"
            val baseName = slabName.removeSuffix("_slab")
            val parentName = if (folder == "wood_planks" || folder == "concrete") "${baseName}_1" else baseName

            val texture = ResourceLocation(DecorBlockMod.MOD_ID, "block/$folder/$parentName")
            // Модель полного (двойного) блока уже была сгенерирована ранее как "$parentName"
            val doubleModel = ResourceLocation(DecorBlockMod.MOD_ID, parentName)

            slabBlock(slab, doubleModel, texture)
            simpleBlockItem(slab, models().slab(slabName, texture, texture, texture))
        }
    }
}