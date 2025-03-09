package cn.foggyhillside.tea_aroma.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec SPEC;

    public static ForgeConfigSpec.IntValue BAMBOO_TRAY_MAX_PROGRESS;

    static {
        BUILDER.push("Configs for Tea Aroma");

        BAMBOO_TRAY_MAX_PROGRESS = BUILDER.comment("Default: 600").defineInRange("bamboo_tray_processing_time", 600, 10, 1000);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
