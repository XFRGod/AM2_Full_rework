package am2.capabilities;

import am2.utils.Reference;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nullable;

public class AM2CapabilitiesProvider implements ICapabilityProvider, ICapabilitySerializable<NBTTagCompound> {

    @CapabilityInject(value = IAM2Capabilites.class)
    public static final Capability<IAM2Capabilites> INSTANCE = null;

    private IAM2Capabilites instance = INSTANCE.getDefaultInstance();

    public static final ResourceLocation AM2_CAPABILITY_RL = new ResourceLocation(Reference.MODID, "capabilites");


    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == INSTANCE;
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == INSTANCE ? INSTANCE.<T> cast(this.instance) : null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return (NBTTagCompound) INSTANCE.getStorage().writeNBT(INSTANCE, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        INSTANCE.getStorage().readNBT(INSTANCE, this.instance, null, nbt);
    }


    public static AM2Capabilities For(EntityLivingBase player) {
        return (AM2Capabilities) player.getCapability(INSTANCE, null);
    }
}
