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

    public static final Capability<IAM2Capabilites> MANA_CAP = null;

    private IAM2Capabilites instance = MANA_CAP.getDefaultInstance();

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == MANA_CAP;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == MANA_CAP ? MANA_CAP.<T> cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return MANA_CAP.getStorage().writeNBT(MANA_CAP, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        MANA_CAP.getStorage().readNBT(MANA_CAP, this.instance, null, nbt);

    }
}
