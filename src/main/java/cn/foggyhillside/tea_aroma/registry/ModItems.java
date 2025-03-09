package cn.foggyhillside.tea_aroma.registry;

import cn.foggyhillside.tea_aroma.FoodList;
import cn.foggyhillside.tea_aroma.TeaAroma;
import cn.foggyhillside.tea_aroma.items.KettleItem;
import cn.foggyhillside.tea_aroma.items.TeaItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TeaAroma.MODID);

    private static Item.Properties tea() {
        return new Item.Properties().stacksTo(1).craftRemainder(ModItems.CUP.get());
    }

    public static final RegistryObject<Item> KETTLE = ITEMS.register("kettle",
            () -> new KettleItem(ModBlocks.KETTLE.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> CUP = ITEMS.register("cup",
            () -> new BlockItem(ModBlocks.CUP.get(), new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> BAMBOO_TRAY = ITEMS.register("bamboo_tray",
            () -> new BlockItem(ModBlocks.BAMBOO_TRAY.get(), new Item.Properties()));

    public static final RegistryObject<Item> TEA_SAPLING = ITEMS.register("tea_sapling",
            () -> new BlockItem(ModBlocks.TEA_TREE.get(), new Item.Properties()));
    public static final RegistryObject<Item> BAMBOO_LEAVES = ITEMS.register("bamboo_leaves",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FRESH_TEA_LEAVES = ITEMS.register("fresh_tea_leaves",
            () -> new Item(new Item.Properties()));
    //Tea Leaves
    public static final RegistryObject<Item> BAMBOO_TEA_LEAVES = ITEMS.register("bamboo_tea_leaves",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> WHITE_TEA_LEAVES = ITEMS.register("white_tea_leaves",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GREEN_TEA_LEAVES = ITEMS.register("green_tea_leaves",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BLACK_TEA_LEAVES = ITEMS.register("black_tea_leaves",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> YELLOW_TEA_LEAVES = ITEMS.register("yellow_tea_leaves",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> OOLONG_TEA_LEAVES = ITEMS.register("oolong_tea_leaves",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DARK_TEA_LEAVES = ITEMS.register("dark_tea_leaves",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROSE_TEA_LEAVES = ITEMS.register("rose_tea_leaves",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DANDELION_TEA_LEAVES = ITEMS.register("dandelion_tea_leaves",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LILAC_TEA_LEAVES = ITEMS.register("lilac_tea_leaves",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BLUE_ORCHID_TEA_LEAVES = ITEMS.register("blue_orchid_tea_leaves",
            () -> new Item(new Item.Properties()));
    //Tea In Processing
    public static final RegistryObject<Item> ROSE_TEA_IN_PROCESSING = ITEMS.register("rose_tea_in_processing",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DANDELION_TEA_IN_PROCESSING = ITEMS.register("dandelion_tea_in_processing",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LILAC_TEA_IN_PROCESSING = ITEMS.register("lilac_tea_in_processing",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BLUE_ORCHID_TEA_IN_PROCESSING = ITEMS.register("blue_orchid_tea_in_processing",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TEA_IN_PROCESSING_0 = ITEMS.register("tea_in_processing_0",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TEA_IN_PROCESSING_0_0 = ITEMS.register("tea_in_processing_0_0",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TEA_IN_PROCESSING_0_0_0 = ITEMS.register("tea_in_processing_0_0_0",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TEA_IN_PROCESSING_1 = ITEMS.register("tea_in_processing_1",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TEA_IN_PROCESSING_1_0 = ITEMS.register("tea_in_processing_1_0",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DARK_TEA_IN_PROCESSING = ITEMS.register("dark_tea_in_processing",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BLACK_TEA_IN_PROCESSING = ITEMS.register("black_tea_in_processing",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> OOLONG_TEA_IN_PROCESSING_0 = ITEMS.register("oolong_tea_in_processing_0",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> OOLONG_TEA_IN_PROCESSING_1 = ITEMS.register("oolong_tea_in_processing_1",
            () -> new Item(new Item.Properties()));
    //Tea
    public static final RegistryObject<Item> BAMBOO_TEA = ITEMS.register("bamboo_tea",
            () -> new TeaItem(ModBlocks.BAMBOO_TEA.get(), tea().food(FoodList.BAMBOO_TEA)));
    public static final RegistryObject<Item> WHITE_TEA = ITEMS.register("white_tea",
            () -> new TeaItem(ModBlocks.WHITE_TEA.get(), tea().food(FoodList.WHITE_TEA)));
    public static final RegistryObject<Item> GREEN_TEA = ITEMS.register("green_tea",
            () -> new TeaItem(ModBlocks.GREEN_TEA.get(), tea().food(FoodList.GREEN_TEA)));
    public static final RegistryObject<Item> BLACK_TEA = ITEMS.register("black_tea",
            () -> new TeaItem(ModBlocks.BLACK_TEA.get(), tea().food(FoodList.BLACK_TEA)));
    public static final RegistryObject<Item> YELLOW_TEA = ITEMS.register("yellow_tea",
            () -> new TeaItem(ModBlocks.YELLOW_TEA.get(), tea().food(FoodList.YELLOW_TEA)));
    public static final RegistryObject<Item> OOLONG_TEA = ITEMS.register("oolong_tea",
            () -> new TeaItem(ModBlocks.OOLONG_TEA.get(), tea().food(FoodList.OOLONG_TEA)));
    public static final RegistryObject<Item> DARK_TEA = ITEMS.register("dark_tea",
            () -> new TeaItem(ModBlocks.DARK_TEA.get(), tea().food(FoodList.DARK_TEA)));
    public static final RegistryObject<Item> ROSE_TEA = ITEMS.register("rose_tea",
            () -> new TeaItem(ModBlocks.ROSE_TEA.get(), tea().food(FoodList.ROSE_TEA)));
    public static final RegistryObject<Item> DANDELION_TEA = ITEMS.register("dandelion_tea",
            () -> new TeaItem(ModBlocks.DANDELION_TEA.get(), tea().food(FoodList.DANDELION_TEA)));
    public static final RegistryObject<Item> LILAC_TEA = ITEMS.register("lilac_tea",
            () -> new TeaItem(ModBlocks.LILAC_TEA.get(), tea().food(FoodList.LILAC_TEA)));
    public static final RegistryObject<Item> BLUE_ORCHID_TEA = ITEMS.register("blue_orchid_tea",
            () -> new TeaItem(ModBlocks.BLUE_ORCHID_TEA.get(), tea().food(FoodList.BLUE_ORCHID_TEA)));
    //Tea Latte
    public static final RegistryObject<Item> BAMBOO_TEA_LATTE = ITEMS.register("bamboo_tea_latte",
            () -> new TeaItem(ModBlocks.BAMBOO_TEA_LATTE.get(), tea().food(FoodList.BAMBOO_TEA_LATTE), true));
    public static final RegistryObject<Item> WHITE_TEA_LATTE = ITEMS.register("white_tea_latte",
            () -> new TeaItem(ModBlocks.WHITE_TEA_LATTE.get(), tea().food(FoodList.WHITE_TEA_LATTE)));
    public static final RegistryObject<Item> GREEN_TEA_LATTE = ITEMS.register("green_tea_latte",
            () -> new TeaItem(ModBlocks.GREEN_TEA_LATTE.get(), tea().food(FoodList.GREEN_TEA_LATTE)));
    public static final RegistryObject<Item> BLACK_TEA_LATTE = ITEMS.register("black_tea_latte",
            () -> new TeaItem(ModBlocks.BLACK_TEA_LATTE.get(), tea().food(FoodList.BLACK_TEA_LATTE)));
    public static final RegistryObject<Item> YELLOW_TEA_LATTE = ITEMS.register("yellow_tea_latte",
            () -> new TeaItem(ModBlocks.YELLOW_TEA_LATTE.get(), tea().food(FoodList.YELLOW_TEA_LATTE)));
    public static final RegistryObject<Item> OOLONG_TEA_LATTE = ITEMS.register("oolong_tea_latte",
            () -> new TeaItem(ModBlocks.OOLONG_TEA_LATTE.get(), tea().food(FoodList.OOLONG_TEA_LATTE)));
    public static final RegistryObject<Item> DARK_TEA_LATTE = ITEMS.register("dark_tea_latte",
            () -> new TeaItem(ModBlocks.DARK_TEA_LATTE.get(), tea().food(FoodList.DARK_TEA_LATTE)));
    public static final RegistryObject<Item> ROSE_TEA_LATTE = ITEMS.register("rose_tea_latte",
            () -> new TeaItem(ModBlocks.ROSE_TEA_LATTE.get(), tea().food(FoodList.ROSE_TEA_LATTE)));
    public static final RegistryObject<Item> DANDELION_TEA_LATTE = ITEMS.register("dandelion_tea_latte",
            () -> new TeaItem(ModBlocks.DANDELION_TEA_LATTE.get(), tea().food(FoodList.DANDELION_TEA_LATTE)));
    public static final RegistryObject<Item> LILAC_TEA_LATTE = ITEMS.register("lilac_tea_latte",
            () -> new TeaItem(ModBlocks.LILAC_TEA_LATTE.get(), tea().food(FoodList.LILAC_TEA_LATTE)));
    public static final RegistryObject<Item> BLUE_ORCHID_TEA_LATTE = ITEMS.register("blue_orchid_tea_latte",
            () -> new TeaItem(ModBlocks.BLUE_ORCHID_TEA_LATTE.get(), tea().food(FoodList.BLUE_ORCHID_TEA_LATTE)));
}
