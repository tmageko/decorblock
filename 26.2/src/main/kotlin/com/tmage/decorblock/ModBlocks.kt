package com.tmage.decorblock

import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.SlabBlock
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier

object ModBlocks {
    val REGISTRY: DeferredRegister<Block> = DeferredRegister.create(BuiltInRegistries.BLOCK, DecorBlockMod.MOD_ID)
    val ITEM_REGISTRY: DeferredRegister<Item> = DeferredRegister.create(BuiltInRegistries.ITEM, DecorBlockMod.MOD_ID)

    val WOOD_TYPES = listOf("oak", "birch", "spruce", "dark_oak", "acacia", "cherry", "pale_pine", "mahogany", "ash", "walnut", "alder", "balsa", "bamboo", "beech", "cedar", "chestnut", "cottonwood", "cypress", "driftwood", "ebony", "elm", "fir", "hazel", "hickory", "hornbeam", "ironwood", "koa", "larch", "linden", "mangrove", "maple", "olive_wood", "padauk", "poplar", "purpleheart", "redwood", "rosewood", "sapele", "sequoia", "sycamore", "teak", "wenge", "willow", "yew", "zebrawood")
    val PLANK_VARIANTS = 1..8
    val PLANKS = WOOD_TYPES.flatMap { type ->
        PLANK_VARIANTS.map { v -> registerBlock("planks_${type}_$v", 2.0f, 3.0f, SoundType.WOOD) }
    }
    val WOOD_PARQUET = WOOD_TYPES.map { type -> registerBlock("parquet_$type", 2.0f, 3.0f, SoundType.WOOD) }

    val WOOL_COLORS_EXISTING = listOf("crimson", "azure", "teal", "charcoal", "emerald", "mint", "ivory", "sand", "plum", "amber", "lavender", "coral")
    val WOOL_COLORS_NEW = listOf("dark_red", "deep_red", "red", "bright_red", "light_red", "pale_red", "muted_red", "dark_scarlet", "deep_scarlet", "scarlet", "bright_scarlet", "light_scarlet", "pale_scarlet", "muted_scarlet", "dark_vermillion", "deep_vermillion", "vermillion", "bright_vermillion", "light_vermillion", "pale_vermillion", "muted_vermillion", "dark_rust", "deep_rust", "rust", "bright_rust", "light_rust", "pale_rust", "muted_rust", "dark_copper", "deep_copper", "copper", "bright_copper", "light_copper", "pale_copper", "muted_copper", "dark_amber", "deep_amber", "bright_amber", "light_amber", "pale_amber", "muted_amber", "dark_honey", "deep_honey", "honey", "bright_honey", "light_honey", "pale_honey", "muted_honey", "dark_gold", "deep_gold", "gold", "bright_gold", "light_gold", "pale_gold", "muted_gold", "dark_mustard", "deep_mustard", "mustard", "bright_mustard", "light_mustard", "pale_mustard", "muted_mustard", "dark_olive", "deep_olive", "olive", "bright_olive", "light_olive", "pale_olive", "muted_olive", "dark_chartreuse", "deep_chartreuse", "chartreuse", "bright_chartreuse", "light_chartreuse", "pale_chartreuse", "muted_chartreuse", "dark_lime", "deep_lime", "lime", "bright_lime", "light_lime", "pale_lime", "muted_lime", "dark_moss", "deep_moss", "moss", "bright_moss", "light_moss", "pale_moss", "muted_moss", "bronze", "indigo", "jade", "magenta", "maroon", "navy", "orchid", "peach", "periwinkle", "ruby", "salmon", "sapphire", "silver", "turquoise")
    val WOOL_COLORS = WOOL_COLORS_EXISTING + WOOL_COLORS_NEW
    val CARPET_COMBOS = listOf("plum_azure", "emerald_charcoal", "sand_emerald", "crimson_amber", "charcoal_coral", "ivory_mint", "coral_sand", "amber_ivory", "teal_plum", "lavender_teal", "azure_lavender", "mint_crimson", "bronze_jade", "bronze_turquoise", "chartreuse_orchid", "gold_silver", "indigo_scarlet", "magenta_indigo", "maroon_navy", "navy_peach", "olive_maroon", "orchid_ruby", "peach_salmon", "periwinkle_chartreuse", "ruby_gold", "ruby_sapphire", "salmon_periwinkle", "sapphire_silver", "scarlet_olive", "turquoise_magenta")
    val CARPET_COLORS_NEW = listOf("dark_red", "deep_red", "red", "bright_red", "light_red", "pale_red", "muted_red", "dark_scarlet", "deep_scarlet", "scarlet", "bright_scarlet", "light_scarlet", "pale_scarlet", "muted_scarlet", "dark_vermillion", "deep_vermillion", "vermillion", "bright_vermillion", "light_vermillion", "pale_vermillion", "muted_vermillion", "dark_rust", "deep_rust", "rust", "bright_rust", "light_rust", "pale_rust", "muted_rust", "dark_copper", "deep_copper", "copper", "bright_copper", "light_copper", "pale_copper", "muted_copper", "dark_amber", "deep_amber", "bright_amber", "light_amber", "pale_amber", "muted_amber", "dark_honey", "deep_honey", "honey", "bright_honey", "light_honey", "pale_honey", "muted_honey", "dark_gold", "deep_gold", "gold", "bright_gold", "light_gold", "pale_gold", "muted_gold", "dark_mustard", "deep_mustard", "mustard", "bright_mustard", "light_mustard", "pale_mustard", "muted_mustard", "dark_olive", "deep_olive", "olive", "bright_olive", "light_olive", "pale_olive", "muted_olive", "dark_chartreuse", "deep_chartreuse", "chartreuse", "bright_chartreuse", "light_chartreuse", "pale_chartreuse", "muted_chartreuse", "dark_lime", "deep_lime", "lime", "bright_lime", "light_lime", "pale_lime", "muted_lime", "dark_moss", "deep_moss", "moss", "bright_moss", "light_moss", "pale_moss", "muted_moss")
    val CARPET_SOLID = listOf("amber", "azure", "bronze", "charcoal", "chartreuse", "coral", "crimson", "emerald", "gold", "indigo", "ivory", "jade", "lavender", "magenta", "maroon", "mint", "navy", "olive", "orchid", "peach", "periwinkle", "plum", "ruby", "salmon", "sand", "sapphire", "scarlet", "silver", "teal", "turquoise")
    val WOOLS = WOOL_COLORS.map { color -> registerBlock("wool_$color", 0.8f, 0.8f, SoundType.WOOL) }
    val CARPETS = (CARPET_COMBOS.map { combo -> registerBlock("carpet_$combo", 0.1f, 0.1f, SoundType.WOOL) }) +
            (CARPET_COLORS_NEW.map { color -> registerBlock("carpet_$color", 0.1f, 0.1f, SoundType.WOOL) })
    val CARPETS_SOLID = CARPET_SOLID.map { color -> registerBlock("carpet_solid_$color", 0.1f, 0.1f, SoundType.WOOL) }

    val METAL_TYPES = listOf("iron", "copper", "gold", "dark_steel", "bronze", "silver", "gunmetal", "rust", "steel", "chrome", "titanium", "cobalt_metal", "brass", "pewter_metal", "obsidian_metal", "platinum_metal", "zinc", "nickel", "tin", "aluminum", "cobalt", "graphite", "mercury", "pewter", "platinum")
    val METAL_VARIANTS = 1..6
    val METAL_PANELS = METAL_TYPES.flatMap { type ->
        METAL_VARIANTS.map { v -> registerBlock("panel_${type}_$v", 5.0f, 6.0f, SoundType.METAL, requiresTool = true) }
    }

    val CONCRETE_TYPES = listOf("light_grey", "dark", "blue_grey", "warm_grey", "sand_beige", "blush", "dark_fern", "deep_fern", "fern", "bright_fern", "light_fern", "pale_fern", "muted_fern", "dark_green", "deep_green", "green", "bright_green", "light_green", "pale_green", "muted_green", "dark_emerald", "deep_emerald", "emerald", "bright_emerald", "light_emerald", "pale_emerald", "muted_emerald", "dark_jade", "deep_jade", "jade", "charcoal_black", "dusty_pink", "forest_grey", "ivory_white", "midnight_navy", "mint_grey", "olive_grey", "rose_beige", "sage_green", "sand_grey", "slate_blue", "steel_blue", "sunset_orange", "terracotta_grey")
    val CONCRETE_VARIANTS = 1..6
    val CONCRETE_BLOCKS = CONCRETE_TYPES.flatMap { type ->
        CONCRETE_VARIANTS.map { v -> registerBlock("concrete_${type}_$v", 1.5f, 6.0f, SoundType.STONE, requiresTool = true) }
    }

    val STONE_MATERIALS = listOf("limestone", "slate", "sandstone", "obsidian", "clay_brick", "terracotta", "granite", "basalt", "marble_white", "marble_black", "copper", "bright_copper", "light_copper", "pale_copper", "muted_copper", "dark_amber", "deep_amber", "amber", "bright_amber", "light_amber", "pale_amber", "muted_amber", "dark_honey", "deep_honey", "honey", "bright_honey", "light_honey", "pale_honey", "muted_honey", "dark_gold", "deep_gold", "gold", "bright_gold", "light_gold", "pale_gold", "muted_gold", "dark_mustard", "deep_mustard", "mustard", "bright_mustard", "light_mustard", "pale_mustard", "muted_mustard", "dark_olive", "deep_olive", "olive", "bright_olive", "light_olive", "pale_olive", "muted_olive", "agate", "andesite", "bluestone", "dolomite", "flint", "gabbro", "gneiss", "greenstone", "gypsum", "jasper", "onyx", "pumice", "quartzite", "rhyolite", "schist", "shale", "soapstone", "travertine")
    val COBBLE = STONE_MATERIALS.map { registerBlock("cobble_$it", 1.5f, 6.0f, SoundType.STONE, requiresTool = true) }
    val BRICKS = STONE_MATERIALS.map { registerBlock("bricks_$it", 1.5f, 6.0f, SoundType.STONE, requiresTool = true) }
    val POLISHED = STONE_MATERIALS.map { registerBlock("polished_$it", 1.5f, 6.0f, SoundType.STONE, requiresTool = true) }

    val BRICK_PAINTED_COLORS = listOf("black", "blue", "brown", "coral_paint", "cyan", "green", "grey", "mint_paint", "orange", "pink", "purple", "red", "teal_paint", "white", "yellow")
    val BRICK_PAINTED = BRICK_PAINTED_COLORS.map { registerBlock("brick_painted_$it", 1.5f, 6.0f, SoundType.STONE, requiresTool = true) }

    val ROOF_SHINGLES = WOOD_TYPES.map { type -> registerBlock("shingles_$type", 2.0f, 3.0f, SoundType.WOOD) }
    val SHINGLE_COLORS_NEW = listOf("bright_cyan", "light_cyan", "pale_cyan", "muted_cyan", "dark_sky", "deep_sky", "sky", "bright_sky", "light_sky", "pale_sky", "muted_sky", "dark_azure", "deep_azure", "azure", "bright_azure", "light_azure", "pale_azure", "muted_azure", "dark_cornflower", "deep_cornflower")
    val ROOF_SHINGLES_COLORED = SHINGLE_COLORS_NEW.map { registerBlock("shingles_$it", 2.0f, 3.0f, SoundType.WOOD) }

    val TILE_COLORS = listOf("bw", "rose", "slate", "green", "blue", "gold", "cornflower", "bright_cornflower", "light_cornflower", "pale_cornflower", "muted_cornflower", "dark_blue", "deep_blue", "bright_blue", "light_blue", "pale_blue", "muted_blue", "dark_cobalt", "deep_cobalt", "cobalt", "bright_cobalt", "light_cobalt", "pale_cobalt", "muted_cobalt", "dark_indigo", "deep_indigo", "indigo", "bright_indigo", "light_indigo", "pale_indigo", "muted_indigo", "dark_violet", "deep_violet", "violet", "bright_violet", "light_violet", "pale_violet", "muted_violet", "dark_purple", "deep_purple", "copper", "coral", "ivory", "jade", "mint", "onyx", "plum", "ruby", "sand", "sapphire", "teal")
    val TILE_SIZES = listOf("2px", "4px", "8px")
    val TILES = TILE_COLORS.flatMap { color ->
        TILE_SIZES.map { size -> registerBlock("tiles_checker_${color}_$size", 1.5f, 6.0f, SoundType.STONE, requiresTool = true) }
    }
    val MOSAIC = TILE_COLORS.map { color -> registerBlock("mosaic_checker_$color", 1.5f, 6.0f, SoundType.STONE, requiresTool = true) }

    val ORNAMENT_CROSS = (1..30).map { registerBlock("ornament_cross_$it", 1.5f, 6.0f, SoundType.STONE, requiresTool = true) }
    val ORNAMENT_DIAMOND = (1..30).map { registerBlock("ornament_diamond_$it", 1.5f, 6.0f, SoundType.STONE, requiresTool = true) }
    val ORNAMENT_STAR = (1..30).map { registerBlock("ornament_star_$it", 1.5f, 6.0f, SoundType.STONE, requiresTool = true) }
    val ORNAMENT_CIRCLE = (1..30).map { registerBlock("ornament_circle_$it", 1.5f, 6.0f, SoundType.STONE, requiresTool = true) }

    val GLASS_COLORS = listOf("clear", "red", "amber_glass", "blue", "purple", "green", "graphite", "slate", "ash", "smoke", "fog", "ivory", "cream", "vanilla", "beige", "tan", "sand", "taupe", "umber", "sienna", "chestnut", "mahogany", "walnut", "chocolate", "espresso", "ebony", "onyx", "pearl", "snow", "silver", "platinum", "pewter", "dark_red", "deep_red", "bright_red", "light_red", "pale_red", "muted_red", "dark_scarlet", "deep_scarlet", "coral", "crimson", "cyan", "emerald", "gold", "indigo", "lime", "magenta", "rose", "sky", "teal", "turquoise", "violet")
    val GLASS_BLOCKS = GLASS_COLORS.map { color -> registerBlock("glass_$color", 0.3f, 0.3f, SoundType.GLASS, noOcclusion = true) }

    val VEINED_COLORS = listOf("bright_scarlet", "light_scarlet", "pale_scarlet", "muted_scarlet", "dark_vermillion", "deep_vermillion", "vermillion", "bright_vermillion", "light_vermillion", "pale_vermillion", "muted_vermillion", "dark_rust", "deep_rust", "rust", "bright_rust", "light_rust", "pale_rust", "muted_rust", "dark_copper", "deep_copper", "copper", "bright_copper", "light_copper", "pale_copper", "muted_copper", "dark_amber", "deep_amber", "amber", "bright_amber", "light_amber", "pale_amber", "muted_amber", "dark_honey", "deep_honey", "honey", "bright_honey", "light_honey", "pale_honey", "muted_honey", "dark_gold")
    val VEINED_BLOCKS = VEINED_COLORS.map { color -> registerBlock("veined_$color", 1.5f, 6.0f, SoundType.STONE, requiresTool = true) }

    val GRANULAR_COLORS = listOf("deep_gold", "gold", "bright_gold", "light_gold", "pale_gold", "muted_gold", "dark_mustard", "deep_mustard", "mustard", "bright_mustard", "light_mustard", "pale_mustard", "muted_mustard", "dark_olive", "deep_olive", "olive", "bright_olive", "light_olive", "pale_olive", "muted_olive", "dark_chartreuse", "deep_chartreuse", "chartreuse", "bright_chartreuse", "light_chartreuse", "pale_chartreuse", "muted_chartreuse", "dark_lime", "deep_lime", "lime")
    val GRANULAR_BLOCKS = GRANULAR_COLORS.map { color -> registerBlock("granular_$color", 1.5f, 6.0f, SoundType.STONE, requiresTool = true) }

    val GRADIENT_COLORS = listOf("arctic", "aurora", "berry", "copper", "dawn", "desert", "dusk", "forest", "lava", "meadow", "ocean", "steel", "sunset", "twilight")
    val GRADIENT_BLOCKS = GRADIENT_COLORS.map { registerBlock("gradient_$it", 1.5f, 6.0f, SoundType.STONE, requiresTool = true) }

    val HEX_TILE_COLORS = listOf("black", "blue", "coral", "cyan", "green", "grey", "maroon", "navy", "olive", "purple", "red", "teal", "white", "yellow")
    val HEX_TILE_BLOCKS = HEX_TILE_COLORS.map { registerBlock("hex_$it", 1.5f, 6.0f, SoundType.STONE, requiresTool = true) }

    val MARBLE_VEINS_COMBOS = listOf("black_gold", "black_white", "blue_white", "cream_brown", "green_white", "grey_silver", "ivory_grey", "purple_white", "red_black", "rose_white", "teal_white", "white_gold")
    val MARBLE_VEINS_BLOCKS = MARBLE_VEINS_COMBOS.map { registerBlock("marble_$it", 1.5f, 6.0f, SoundType.STONE, requiresTool = true) }

    val NEON_COLORS = listOf("blue", "cyan", "green", "lime", "magenta", "orange", "pink", "purple", "red", "teal", "violet", "yellow")
    val NEON_BLOCKS = NEON_COLORS.map { registerBlock("neon_$it", 0.5f, 0.5f, SoundType.AMETHYST_CLUSTER, light = 15, noOcclusion = true) }

    val STRIPES_COMBOS = listOf("azure_ivory", "blue_grey", "coral_charcoal", "green_black", "indigo_silver", "lavender_plum", "mint_slate", "olive_cream", "purple_gold", "red_white", "rose_grey", "sand_rust", "teal_cream", "yellow_navy")
    val STRIPES_BLOCKS = STRIPES_COMBOS.map { registerBlock("stripes_$it", 2.0f, 3.0f, SoundType.WOOD) }

    val TERRAZZO_COLORS = listOf("beige", "black", "blue", "charcoal", "green", "grey", "ivory", "lavender", "pink", "rose", "sage", "sand", "white", "yellow")
    val TERRAZZO_BLOCKS = TERRAZZO_COLORS.map { registerBlock("terrazzo_$it", 1.5f, 6.0f, SoundType.STONE, requiresTool = true) }

    val PLANK_SLABS = WOOD_TYPES.map { type -> registerSlab("planks_${type}_slab", 2.0f, 3.0f, SoundType.WOOD) }
    val STONE_SLABS = STONE_MATERIALS.map { type -> registerSlab("cobble_${type}_slab", 1.5f, 6.0f, SoundType.STONE, requiresTool = true) }
    val CONCRETE_SLABS = CONCRETE_TYPES.map { type -> registerSlab("concrete_${type}_slab", 1.5f, 6.0f, SoundType.STONE, requiresTool = true) }
    val VEINED_SLABS = VEINED_COLORS.map { color -> registerSlab("veined_${color}_slab", 1.5f, 6.0f, SoundType.STONE, requiresTool = true) }
    val TERRAZZO_SLABS = TERRAZZO_COLORS.map { color -> registerSlab("terrazzo_${color}_slab", 1.5f, 6.0f, SoundType.STONE, requiresTool = true) }

    private fun registerBlock(
        name: String,
        strength: Float = 1.5f,
        resistance: Float = 6.0f,
        sound: SoundType = SoundType.STONE,
        requiresTool: Boolean = false,
        light: Int = 0,
        noOcclusion: Boolean = false
    ): DeferredHolder<Block, Block> {
        val block = REGISTRY.register(name, Supplier {
            val props = BlockBehaviour.Properties.of()
                .strength(strength, resistance)
                .sound(sound)
                .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(DecorBlockMod.MOD_ID, name)))

            if (requiresTool) props.requiresCorrectToolForDrops()
            if (light > 0) props.lightLevel { light }
            if (noOcclusion) props.noOcclusion()

            Block(props)
        })

        ITEM_REGISTRY.register(name, Supplier {
            BlockItem(
                block.get(),
                Item.Properties().setId(
                    ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(DecorBlockMod.MOD_ID, name))
                )
            )
        })

        return block
    }

    private fun registerSlab(
        name: String,
        strength: Float = 1.5f,
        resistance: Float = 6.0f,
        sound: SoundType = SoundType.STONE,
        requiresTool: Boolean = false
    ): DeferredHolder<Block, SlabBlock> {
        val block = REGISTRY.register(name, Supplier {
            val props = BlockBehaviour.Properties.of()
                .strength(strength, resistance)
                .sound(sound)
                .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(DecorBlockMod.MOD_ID, name)))

            if (requiresTool) props.requiresCorrectToolForDrops()

            SlabBlock(props)
        })

        ITEM_REGISTRY.register(name, Supplier {
            BlockItem(
                block.get(),
                Item.Properties().setId(
                    ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(DecorBlockMod.MOD_ID, name))
                )
            )
        })

        return block
    }

    fun register(eventBus: IEventBus) {
        REGISTRY.register(eventBus)
        ITEM_REGISTRY.register(eventBus)
    }
}
