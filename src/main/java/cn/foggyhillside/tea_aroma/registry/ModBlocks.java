package cn.foggyhillside.tea_aroma.registry;

import cn.foggyhillside.tea_aroma.TeaAroma;
import cn.foggyhillside.tea_aroma.blocks.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TeaAroma.MODID);
    public static final DeferredRegister<Block> SIMPLY_TEA_BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TeaAroma.MODID);

    public static final RegistryObject<Block> TEA_TREE = BLOCKS.register("tea_tree",
            () -> new TeaTreeBlock(BlockBehaviour.Properties.copy(Blocks.SWEET_BERRY_BUSH)));

    public static final RegistryObject<Block> KETTLE = BLOCKS.register("kettle",
            () -> new KettleBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(0.5F, 6.0F).sound(SoundType.LANTERN).pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> CUP = BLOCKS.register("cup",
            () -> new CupBlock(BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> BAMBOO_TRAY = BLOCKS.register("bamboo_tray",
            () -> new BambooTrayBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).instrument(NoteBlockInstrument.BASS).noOcclusion().strength(0.5F).pushReaction(PushReaction.DESTROY)));

    //Tea
    public static final RegistryObject<Block> BAMBOO_TEA = BLOCKS.register("bamboo_tea",
            () -> new TATeaBlock(BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> WHITE_TEA = BLOCKS.register("white_tea",
            () -> new TATeaBlock(BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> GREEN_TEA = BLOCKS.register("green_tea",
            () -> new TATeaBlock(BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> BLACK_TEA = BLOCKS.register("black_tea",
            () -> new TATeaBlock(BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> YELLOW_TEA = BLOCKS.register("yellow_tea",
            () -> new TATeaBlock(BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> OOLONG_TEA = BLOCKS.register("oolong_tea",
            () -> new TATeaBlock(BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> DARK_TEA = BLOCKS.register("dark_tea",
            () -> new TATeaBlock(BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> ROSE_TEA = BLOCKS.register("rose_tea",
            () -> new TATeaBlock(BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> DANDELION_TEA = BLOCKS.register("dandelion_tea",
            () -> new TATeaBlock(BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> LILAC_TEA = BLOCKS.register("lilac_tea",
            () -> new TATeaBlock(BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> BLUE_ORCHID_TEA = BLOCKS.register("blue_orchid_tea",
            () -> new TATeaBlock(BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
    //Tea Latte
    public static final RegistryObject<Block> BAMBOO_TEA_LATTE = BLOCKS.register("bamboo_tea_latte",
            () -> new TeaLatteBlock(BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> WHITE_TEA_LATTE = BLOCKS.register("white_tea_latte",
            () -> new TeaLatteBlock(BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> GREEN_TEA_LATTE = BLOCKS.register("green_tea_latte",
            () -> new TeaLatteBlock(BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> BLACK_TEA_LATTE = BLOCKS.register("black_tea_latte",
            () -> new TeaLatteBlock(BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> YELLOW_TEA_LATTE = BLOCKS.register("yellow_tea_latte",
            () -> new TeaLatteBlock(BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> OOLONG_TEA_LATTE = BLOCKS.register("oolong_tea_latte",
            () -> new TeaLatteBlock(BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> DARK_TEA_LATTE = BLOCKS.register("dark_tea_latte",
            () -> new TeaLatteBlock(BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> ROSE_TEA_LATTE = BLOCKS.register("rose_tea_latte",
            () -> new TeaLatteBlock(BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> DANDELION_TEA_LATTE = BLOCKS.register("dandelion_tea_latte",
            () -> new TeaLatteBlock(BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> LILAC_TEA_LATTE = BLOCKS.register("lilac_tea_latte",
            () -> new TeaLatteBlock(BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> BLUE_ORCHID_TEA_LATTE = BLOCKS.register("blue_orchid_tea_latte",
            () -> new TeaLatteBlock(BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));

    //SimplyTea
    public static final RegistryObject<Block> CUP_COCOA = SIMPLY_TEA_BLOCKS.register("cup_cocoa",
            () -> new STHotChocolateBlock(BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> CUP_TEA_ICED = SIMPLY_TEA_BLOCKS.register("cup_tea_iced",
            () -> new STTeaBlock(BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> CUP_TEA_CHAI = SIMPLY_TEA_BLOCKS.register("cup_tea_chai",
            () -> new STTeaBlock(BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> CUP_TEA_GREEN = SIMPLY_TEA_BLOCKS.register("cup_tea_green",
            () -> new STTeaBlock(BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> CUP_TEA_FLORAL = SIMPLY_TEA_BLOCKS.register("cup_tea_floral",
            () -> new STTeaBlock(BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> CUP_TEA_CHORUS = SIMPLY_TEA_BLOCKS.register("cup_tea_chorus",
            () -> new STTeaBlock(BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> CUP_TEA_BLACK = SIMPLY_TEA_BLOCKS.register("cup_tea_black",
            () -> new STTeaBlock(BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));

}
