package com.tmage.decorblock

import net.minecraft.world.item.CreativeModeTabs
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.common.Mod
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_BUS

@Mod(DecorBlockMod.MOD_ID)
class DecorBlockMod {
    companion object {
        const val MOD_ID = "decorblock"
        val LOGGER: Logger = LogManager.getLogger()
    }

    init {
        val modEventBus: IEventBus = MOD_BUS

        ModBlocks.register(modEventBus)
        modEventBus.addListener(ModDataGenerators::gatherData)

        modEventBus.addListener(this::addCreativeContents)

        LOGGER.info("DecorBlockMod готов к запуску на 26.2!")
    }

    private fun addCreativeContents(event: BuildCreativeModeTabContentsEvent) {
        if (event.tabKey == CreativeModeTabs.BUILDING_BLOCKS) {
            ModBlocks.ITEM_REGISTRY.entries.forEach { itemObj ->
                event.accept(itemObj.get())
            }
        }
    }
}
