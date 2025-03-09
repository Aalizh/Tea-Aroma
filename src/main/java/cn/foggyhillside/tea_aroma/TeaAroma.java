package cn.foggyhillside.tea_aroma;

import cn.foggyhillside.tea_aroma.config.CommonConfigs;
import cn.foggyhillside.tea_aroma.items.KettleItem;
import cn.foggyhillside.tea_aroma.loot.CompostableAddition;
import cn.foggyhillside.tea_aroma.registry.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@Mod(TeaAroma.MODID)
public class TeaAroma {
    public static final String MODID = "tea_aroma";

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final RegistryObject<CreativeModeTab> TEA_AROMA_TAB = CREATIVE_MODE_TABS.register(MODID, () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup." + MODID))
            .icon(() -> ModItems.GREEN_TEA.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(KettleItem.getEmptyKettle());
                output.accept(KettleItem.getBoilingWaterKettle());
                output.accept(KettleItem.getBoilingMilkKettle());
                output.accept(ModItems.CUP.get());
                output.accept(ModItems.BAMBOO_TRAY.get());
                output.accept(ModItems.TEA_SAPLING.get());
                if (!ModCompat.isFestivalDelicaciesLoaded()) {
                    output.accept(ModItems.BAMBOO_LEAVES.get());
                }
                output.accept(ModItems.FRESH_TEA_LEAVES.get());
                output.accept(ModItems.BAMBOO_TEA_LEAVES.get());
                output.accept(ModItems.WHITE_TEA_LEAVES.get());
                output.accept(ModItems.GREEN_TEA_LEAVES.get());
                output.accept(ModItems.BLACK_TEA_LEAVES.get());
                output.accept(ModItems.YELLOW_TEA_LEAVES.get());
                output.accept(ModItems.OOLONG_TEA_LEAVES.get());
                output.accept(ModItems.DARK_TEA_LEAVES.get());
                output.accept(ModItems.LILAC_TEA_LEAVES.get());
                output.accept(ModItems.ROSE_TEA_LEAVES.get());
                output.accept(ModItems.BLUE_ORCHID_TEA_LEAVES.get());
                output.accept(ModItems.DANDELION_TEA_LEAVES.get());
                output.accept(ModItems.BAMBOO_TEA.get());
                output.accept(ModItems.WHITE_TEA.get());
                output.accept(ModItems.GREEN_TEA.get());
                output.accept(ModItems.BLACK_TEA.get());
                output.accept(ModItems.YELLOW_TEA.get());
                output.accept(ModItems.OOLONG_TEA.get());
                output.accept(ModItems.DARK_TEA.get());
                output.accept(ModItems.LILAC_TEA.get());
                output.accept(ModItems.ROSE_TEA.get());
                output.accept(ModItems.BLUE_ORCHID_TEA.get());
                output.accept(ModItems.DANDELION_TEA.get());
                output.accept(ModItems.BAMBOO_TEA_LATTE.get());
                output.accept(ModItems.WHITE_TEA_LATTE.get());
                output.accept(ModItems.GREEN_TEA_LATTE.get());
                output.accept(ModItems.BLACK_TEA_LATTE.get());
                output.accept(ModItems.YELLOW_TEA_LATTE.get());
                output.accept(ModItems.OOLONG_TEA_LATTE.get());
                output.accept(ModItems.DARK_TEA_LATTE.get());
                output.accept(ModItems.LILAC_TEA_LATTE.get());
                output.accept(ModItems.ROSE_TEA_LATTE.get());
                output.accept(ModItems.BLUE_ORCHID_TEA_LATTE.get());
                output.accept(ModItems.DANDELION_TEA_LATTE.get());
            }).build());

    public TeaAroma() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::setup);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CommonConfigs.SPEC);
        ModSounds.SOUNDS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        if (ModCompat.isSimplyTeaLoaded()) {
            ModBlocks.SIMPLY_TEA_BLOCKS.register(modEventBus);
        }
        ModParticleTypes.PARTICLE_TYPES.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ModRecipeTypes.RECIPE_TYPES.register(modEventBus);
        ModRecipeSerializers.RECIPE_SERIALIZERS.register(modEventBus);
        ModLootModifiers.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvent {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }

    private void setup(final FMLCommonSetupEvent event) {
        CompostableAddition.addCompostable();
    }
}
