package cn.foggyhillside.tea_aroma.registry;

import cn.foggyhillside.tea_aroma.TeaAroma;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, TeaAroma.MODID);

    public static final RegistryObject<SoundEvent> ITEM_TEA_LEAVES_PICK_FROM_TREE = SOUNDS.register("block.tea_tree.pick", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(TeaAroma.MODID, "block.tea_tree.pick")));
    public static final RegistryObject<SoundEvent> KETTLE_FILL = SOUNDS.register("block.kettle.fill", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(TeaAroma.MODID, "block.kettle.fill")));
    public static final RegistryObject<SoundEvent> TEA_BREW = SOUNDS.register("block.tea_brew", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(TeaAroma.MODID, "block.tea_brew")));
    public static final RegistryObject<SoundEvent> TEA_PROCESSING_2 = SOUNDS.register("block.tea_processing_2", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(TeaAroma.MODID, "block.tea_processing_2")));
}
