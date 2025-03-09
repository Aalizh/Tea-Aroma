package cn.foggyhillside.tea_aroma.loot;

import cn.foggyhillside.tea_aroma.registry.ModItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.ComposterBlock;

public class CompostableAddition {

    public static void addCompostable(){
        add(0.3F, ModItems.BAMBOO_LEAVES.get());
        add(0.3F, ModItems.FRESH_TEA_LEAVES.get());
        add(0.3F, ModItems.TEA_SAPLING.get());
        add(0.3F, ModItems.BAMBOO_TEA_LEAVES.get());
        add(0.3F, ModItems.WHITE_TEA_LEAVES.get());
        add(0.3F, ModItems.GREEN_TEA_LEAVES.get());
        add(0.3F, ModItems.BLACK_TEA_LEAVES.get());
        add(0.3F, ModItems.YELLOW_TEA_LEAVES.get());
        add(0.3F, ModItems.OOLONG_TEA_LEAVES.get());
        add(0.3F, ModItems.DARK_TEA_LEAVES.get());
        add(0.3F, ModItems.ROSE_TEA_LEAVES.get());
        add(0.3F, ModItems.DANDELION_TEA_LEAVES.get());
        add(0.3F, ModItems.LILAC_TEA_LEAVES.get());
        add(0.3F, ModItems.BLUE_ORCHID_TEA_LEAVES.get());
        add(0.3F, ModItems.ROSE_TEA_IN_PROCESSING.get());
        add(0.3F, ModItems.DANDELION_TEA_IN_PROCESSING.get());
        add(0.3F, ModItems.LILAC_TEA_IN_PROCESSING.get());
        add(0.3F, ModItems.BLUE_ORCHID_TEA_IN_PROCESSING.get());
        add(0.3F, ModItems.TEA_IN_PROCESSING_0.get());
        add(0.3F, ModItems.TEA_IN_PROCESSING_0_0.get());
        add(0.3F, ModItems.TEA_IN_PROCESSING_0_0_0.get());
        add(0.3F, ModItems.TEA_IN_PROCESSING_1.get());
        add(0.3F, ModItems.TEA_IN_PROCESSING_1_0.get());
        add(0.3F, ModItems.DARK_TEA_IN_PROCESSING.get());
        add(0.3F, ModItems.BLACK_TEA_IN_PROCESSING.get());
        add(0.3F, ModItems.OOLONG_TEA_IN_PROCESSING_0.get());
        add(0.3F, ModItems.OOLONG_TEA_IN_PROCESSING_1.get());
    }

    private static void add(float f, ItemLike itemLike) {
        ComposterBlock.COMPOSTABLES.put(itemLike.asItem(), f);
    }

}