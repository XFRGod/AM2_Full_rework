package am2.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class ManaStorage implements Capability.IStorage<IMana> {
    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IMana> capability, IMana instance, EnumFacing side) {
        System.out.println("Written mana to NBT");
        return new NBTTagLong(instance.getCurrentMana());
    }

    @Override
    public void readNBT(Capability<IMana> capability, IMana instance, EnumFacing side, NBTBase nbt) {
        instance.setCurrentMana(((NBTPrimitive) nbt).getLong());

    }
}
