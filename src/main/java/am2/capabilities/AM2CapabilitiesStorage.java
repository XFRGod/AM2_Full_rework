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
        NBTTagCompound compound = new NBTTagCompound();
        NBTTagCompound compound2 = NBTUtils.getAM2Tag(compound);
        compound2.setFloat("CurrentMana", instance.getCurrentMana());
        compound2.setInteger("CurrentLevel", instance.getCurrentLevel());
        compound2.setFloat("CurrentXP", instance.getCurrentXP());
        compound2.setFloat("CurrentBurnout", instance.getCurrentBurnout());
                
        compound2.setDouble("MarkX", instance.getMarkX());
        compound2.setDouble("MarkY", instance.getMarkY());
        compound2.setDouble("MarkZ", instance.getMarkZ());
        compound2.setInteger("MarkDimensionID", instance.getMarkDimensionID());

        compound2.setBoolean("DoUpdate", instance.getDoUpdate());
        return compound;
    }

    @Override
    public void readNBT(Capability<IAM2Capabilites> capability, IAM2Capabilites instance, EnumFacing side, NBTBase nbt) {
        NBTTagCompound am2tag = NBTUtils.getAM2Tag((NBTTagCompound)nbt);
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
