package com.tmage.decorblock

import net.minecraft.world.item.CreativeModeTabs
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent
import net.minecraftforge.fml.common.Mod
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import thedarkcolour.kotlinforforge.forge.MOD_CONTEXT

@Mod(DecorBlockMod.MOD_ID)
class DecorBlockMod {
    companion object {
        const val MOD_ID = "decorblock"
        val LOGGER: Logger = LogManager.getLogger()
    }

    init {
        val modEventBus = MOD_CONTEXT.getKEventBus()

        ModBlocks.register(modEventBus)
        modEventBus.addListener(ModDataGenerators::gatherData)

        // Добавляем блоки в творческий инвентарь (вкладка Строительные блоки)
        modEventBus.addListener(this::addCreativeContents)

        LOGGER.info("DecorBlockMod готов к запуску!")
    }

    private fun addCreativeContents(event: BuildCreativeModeTabContentsEvent) {
        if (event.tabKey == CreativeModeTabs.BUILDING_BLOCKS) {
            // Добавляем абсолютно все наши предметы блоков в строительную вкладку
            ModBlocks.ITEM_REGISTRY.entries.forEach { itemObj ->
                event.accept(itemObj.get())
            }
        }
    }
}