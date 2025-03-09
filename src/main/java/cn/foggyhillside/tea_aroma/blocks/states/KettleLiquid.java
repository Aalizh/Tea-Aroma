package cn.foggyhillside.tea_aroma.blocks.states;

import net.minecraft.util.StringRepresentable;

public enum KettleLiquid implements StringRepresentable {
    NONE("none"),
    WATER("water"),
    MILK("milk"),
    BOILING_WATER("boiling_water"),
    BOILING_MILK("boiling_milk");

    private final String supportName;

    KettleLiquid(String name) {
        this.supportName = name;
    }

    public String toString() {
        return this.getSerializedName();
    }

    public String getSerializedName() {
        return this.supportName;
    }
}
