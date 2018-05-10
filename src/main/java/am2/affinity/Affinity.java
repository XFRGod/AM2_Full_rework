package am2.affinity;

import am2.handler.RegistryHandler;
import am2.utils.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.LinkedHashMap;

public class Affinity extends IForgeRegistryEntry.Impl<Affinity> implements IForgeRegistryEntry<Affinity> {
    String name;
    int color;
    private String chatcolor;
    ResourceLocation image;

    public Affinity(String name, int color, ResourceLocation image)
    {
        this.name = name;
        this.setRegistryName(Reference.MODID, "affinities/" + this.name);
        this.color = color;
        this.image = image;
        RegistryHandler.AddAffinityToRegistry(this);
    }
}
