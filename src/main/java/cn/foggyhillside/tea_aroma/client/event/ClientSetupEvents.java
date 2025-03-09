package cn.foggyhillside.tea_aroma.client.event;

import cn.foggyhillside.tea_aroma.TeaAroma;
import cn.foggyhillside.tea_aroma.client.particle.SteamParticle;
import cn.foggyhillside.tea_aroma.client.renderer.BambooTrayRenderer;
import cn.foggyhillside.tea_aroma.client.renderer.CupRenderer;
import cn.foggyhillside.tea_aroma.registry.ModBlockEntities;
import cn.foggyhillside.tea_aroma.registry.ModParticleTypes;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = TeaAroma.MODID,
        bus = Mod.EventBusSubscriber.Bus.MOD,
        value = {Dist.CLIENT}
)
public class ClientSetupEvents {
    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.CUP.get(), CupRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.BAMBOO_TRAY.get(), BambooTrayRenderer::new);
    }

    @SubscribeEvent(
            priority = EventPriority.LOWEST
    )
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(ModParticleTypes.STEAM.get(), SteamParticle.Factory::new);
    }
}
