package am2.capabilities;

import am2.utils.NBTUtils;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class AM2CapabilitiesStorage implements Capability.IStorage<IAM2Capabilites> {
    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IAM2Capabilites> capability, IAM2Capabilites instance, EnumFacing side) {
        System.out.println("Written to NBT");
        NBTTagCompound compound = new NBTTagCompound();
        compound = NBTUtils.getAM2Tag(compound);
        compound.setFloat("CurrentMana", instance.getCurrentMana());
        compound.setInteger("CurrentLevel", instance.getCurrentLevel());
        compound.setFloat("CurrentXP", instance.getCurrentXP());
        compound.setFloat("CurrentBurnout", instance.getCurrentBurnout());

        compound.setDouble("MarkX", instance.getMarkX());
        compound.setDouble("MarkY", instance.getMarkY());
        compound.setDouble("MarkZ", instance.getMarkZ());
        compound.setInteger("MarkDimensionId", instance.getMarkDimensionID());
        return compound;
    }

    @Override
    public void readNBT(Capability<IAM2Capabilites> capability, IAM2Capabilites instance, EnumFacing side, NBTBase nbt) {
        instance.setCurrentMana(((NBTPrimitive) nbt).getLong());

    }
}
