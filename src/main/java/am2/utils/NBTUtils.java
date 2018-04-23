package am2.utils;

import net.minecraft.nbt.NBTTagCompound;

public class NBTUtils {
    public static NBTTagCompound getAM2Tag (NBTTagCompound baseTag) {
        return addTag(baseTag, "AM2");
    }
    public static NBTTagCompound addTag (NBTTagCompound upper, String name) {
        if (upper == null) throw new IllegalStateException("Base Tag must exist");
        NBTTagCompound newTag = new NBTTagCompound();
        upper.getCompoundTag(name);
        newTag = upper.getCompoundTag(name);
        upper.setTag(name, newTag);
        return newTag;
    }
}
