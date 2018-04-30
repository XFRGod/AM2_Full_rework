package am2.capabilities;

import am2.utils.Reference;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AM2CapabilitiesProvider implements ICapabilityProvider, ICapabilitySerializable<NBTTagCompound> {

    @CapabilityInject(value = IAM2Capabilites.class)
    public static final Capability<IAM2Capabilites> AM2_CAPABILITY = null;

    private IAM2Capabilites instance = AM2_CAPABILITY.getDefaultInstance();

    public static final ResourceLocation AM2_CAPABILITY_RL = new ResourceLocation(Reference.MODID, "capabilites");


    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == AM2_CAPABILITY;
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == AM2_CAPABILITY ? AM2_CAPABILITY.<T> cast(this.instance) : null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return (NBTTagCompound) AM2_CAPABILITY.getStorage().writeNBT(AM2_CAPABILITY, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        AM2_CAPABILITY.getStorage().readNBT(AM2_CAPABILITY, this.instance, null, nbt);
    }


    public static AM2Capabilities For(EntityLivingBase player) {
        return (AM2Capabilities) player.getCapability(AM2_CAPABILITY, null);
    }
}
