package am2.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AM2CapabilitiesProvider implements ICapabilitySerializable<NBTBase> {
    @CapabilityInject(IAM2Capabilites.class)

    public static final Capability<IAM2Capabilites> AM2_CAPABILITY = null;

    private IAM2Capabilites instance = AM2_CAPABILITY.getDefaultInstance();

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == AM2_CAPABILITY;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == AM2_CAPABILITY ? AM2_CAPABILITY.<T> cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return AM2_CAPABILITY.getStorage().writeNBT(AM2_CAPABILITY, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        AM2_CAPABILITY.getStorage().readNBT(AM2_CAPABILITY, this.instance, null, nbt);

    }
}
