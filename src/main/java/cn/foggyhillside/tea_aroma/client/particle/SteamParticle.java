package cn.foggyhillside.tea_aroma.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import org.jetbrains.annotations.Nullable;

public class SteamParticle extends TextureSheetParticle {
    protected SteamParticle(ClientLevel pLevel, double pX, double pY, double pZ, double motionX, double motionY, double motionZ) {
        super(pLevel, pX, pY, pZ);
        this.scale(2.0F);
        this.setSize(0.25F, 0.25F);
        this.lifetime = this.random.nextInt(4) + 8;
        this.xd = motionX;
        this.yd = motionY + (double) (this.random.nextFloat() / 500.0F);
        this.zd = motionZ;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ < this.lifetime && !(this.alpha <= 0.0F)) {
            this.yd += this.random.nextFloat() / 500.0F;
            this.move(0, this.yd, 0);
            if (this.age >= this.lifetime - 60 && this.alpha > 0.01F) {
                this.alpha -= 0.02F;
            }
        } else {
            this.remove();
        }
    }

    public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Factory(SpriteSet sprite) {
            this.spriteSet = sprite;
        }

        @Override
        public @Nullable Particle createParticle(SimpleParticleType simpleParticleType, ClientLevel clientLevel, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            SteamParticle particle = new SteamParticle(clientLevel, x, y + 0.3, z, xSpeed, ySpeed, zSpeed);
            particle.setAlpha(0.6F);
            particle.pickSprite(this.spriteSet);
            return particle;
        }
    }
}
