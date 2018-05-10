package am2.definitions;

import am2.affinity.Affinity;
import am2.utils.Reference;
import net.minecraft.util.ResourceLocation;

public class AffinityDefinitions {
    public void Load() {}

    public static final AffinityDefinitions INSTANCE = new AffinityDefinitions();

    public static final Affinity fire = new Affinity("fire", 0xff5a01, new ResourceLocation(Reference.MODID, "affinities/fire"));
    public static final Affinity water = new Affinity("water", 0x2997c2, new ResourceLocation(Reference.MODID, "affinities/water"));
    public static final Affinity frost = new Affinity("frost", 0xd8eff9, new ResourceLocation(Reference.MODID, "affinities/frost"));
    public static final Affinity nature = new Affinity("nature", 0x40c534, new ResourceLocation(Reference.MODID, "affinities/nature"));
    public static final Affinity light = new Affinity("light", 0xffffff, new ResourceLocation(Reference.MODID, "affinities/light"));
    public static final Affinity dark = new Affinity("dark", 0x000000, new ResourceLocation(Reference.MODID, "affinities/dark"));
    public static final Affinity ender = new Affinity("ender", 0xcc00fa, new ResourceLocation(Reference.MODID, "affinities/ender"));
    public static final Affinity arcane = new Affinity("arcane", 0x000bff, new ResourceLocation(Reference.MODID, "affinities/arcane"));
    public static final Affinity none = new Affinity("none", 0xbababa, new ResourceLocation(Reference.MODID, "affinities/none"));

}
