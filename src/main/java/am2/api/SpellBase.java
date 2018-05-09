package am2.api;

import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.EnumSet;

public abstract class SpellBase extends IForgeRegistryEntry.Impl<SpellBase> {

    public abstract Object[] getRecipe();

    public abstract EnumSet<?> getModifiers();
}
