package cn.foggyhillside.tea_aroma.registry;

import cn.foggyhillside.tea_aroma.TeaAroma;
import cn.foggyhillside.tea_aroma.loot.BambooLeavesAdditionModifier;
import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, TeaAroma.MODID);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> BAMBOO_LEAVES_SERIALIZERS = LOOT_MODIFIERS.register("bamboo_leaves", BambooLeavesAdditionModifier.CODEC);

    public static void register(IEventBus bus) {
        LOOT_MODIFIERS.register(bus);
    }
}
