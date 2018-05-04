package am2.capabilities;

import am2.utils.LogHelper;
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
        System.out.println("Written to NBT " + instance.getCurrentMana());
        NBTTagCompound compound = new NBTTagCompound();
        compound = NBTUtils.getAM2Tag(compound);
        compound.setFloat("CurrentMana", instance.getCurrentMana());
        compound.setInteger("CurrentLevel", instance.getCurrentLevel());
        compound.setFloat("CurrentXP", instance.getCurrentXP());
        compound.setFloat("CurrentBurnout", instance.getCurrentBurnout());

        compound.setDouble("MarkX", instance.getMarkX());
        compound.setDouble("MarkY", instance.getMarkY());
        compound.setDouble("MarkZ", instance.getMarkZ());
        compound.setInteger("MarkDimensionID", instance.getMarkDimensionID());
        return compound;
    }

    @Override
    public void readNBT(Capability<IAM2Capabilites> capability, IAM2Capabilites instance, EnumFacing side, NBTBase nbt) {
        NBTTagCompound am2tag = ((NBTTagCompound)nbt).getCompoundTag("AM2");
        LogHelper.info("Writing NBT " + am2tag.getInteger("CurrentLevel"));
        instance.setCurrentMana(am2tag.getFloat("CurrentMana"));
        instance.setCurrentLevel(am2tag.getInteger("CurrentLevel"));
        instance.setCurrentXP(am2tag.getFloat("CurrentXP"));
        instance.setCurrentBurnout(am2tag.getFloat("CurrentBurnout"));

        instance.setMarkX(am2tag.getDouble("MarkX"));
        instance.setMarkY(am2tag.getDouble("MarkY"));
        instance.setMarkZ(am2tag.getDouble("MarkZ"));
        instance.setMarkDimensionID(am2tag.getInteger("MarkDimensionID"));

    }
}
