package cn.foggyhillside.tea_aroma.registry;

import cn.foggyhillside.tea_aroma.TeaAroma;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static final TagKey<Block> HEAT_SOURCES = BlockTags.create((new ResourceLocation(TeaAroma.MODID, "heat_sources")));
    public static final TagKey<Block> TRAY_HEAT_SOURCES = BlockTags.create((new ResourceLocation(TeaAroma.MODID, "tray_heat_sources")));
    public static final TagKey<Item> WATER = ItemTags.create((new ResourceLocation(TeaAroma.MODID, "water")));
    public static final TagKey<Item> MILK = ItemTags.create((new ResourceLocation(TeaAroma.MODID, "milk")));
    public static final TagKey<Item> TEA_INGREDIENTS = ItemTags.create((new ResourceLocation(TeaAroma.MODID, "tea_ingredients")));
    public static final TagKey<Item> SIMPLYTEA_INGREDIENTS = ItemTags.create((new ResourceLocation(TeaAroma.MODID, "simplytea_ingredients")));
    public static final TagKey<Item> SIMPLYTEA_TEA = ItemTags.create((new ResourceLocation(TeaAroma.MODID, "simplytea_tea")));
    public static final TagKey<Item> BAMBOO_TRAY_TEA = ItemTags.create((new ResourceLocation(TeaAroma.MODID, "bamboo_tray_tea")));
    public static final TagKey<Item> BAMBOO_TRAY_FLOWER = ItemTags.create((new ResourceLocation(TeaAroma.MODID, "bamboo_tray_flower")));
    public static final TagKey<Item> BAMBOO_TRAY_FLOWER_SMALL = ItemTags.create((new ResourceLocation(TeaAroma.MODID, "bamboo_tray_flower_small")));
    public static final TagKey<Item> BAMBOO_TRAY_FLOWER_TALL = ItemTags.create((new ResourceLocation(TeaAroma.MODID, "bamboo_tray_flower_tall")));
}
