package cn.foggyhillside.tea_aroma.registry;

import cn.foggyhillside.tea_aroma.TeaAroma;
import cn.foggyhillside.tea_aroma.blocks.entities.BambooTrayEntity;
import cn.foggyhillside.tea_aroma.blocks.entities.CupEntity;
import cn.foggyhillside.tea_aroma.blocks.entities.KettleEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TeaAroma.MODID);

    public static final RegistryObject<BlockEntityType<KettleEntity>> KETTLE = BLOCK_ENTITIES.register("kettle",
            ()-> BlockEntityType.Builder.of(KettleEntity::new, ModBlocks.KETTLE.get()).build(null));

    public static final RegistryObject<BlockEntityType<CupEntity>> CUP = BLOCK_ENTITIES.register("cup",
            ()-> BlockEntityType.Builder.of(CupEntity::new, ModBlocks.CUP.get()).build(null));

    public static final RegistryObject<BlockEntityType<BambooTrayEntity>> BAMBOO_TRAY = BLOCK_ENTITIES.register("bamboo_tray",
            ()-> BlockEntityType.Builder.of(BambooTrayEntity::new, ModBlocks.BAMBOO_TRAY.get()).build(null));

}
