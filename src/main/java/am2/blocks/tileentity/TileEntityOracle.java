package am2.blocks.tileentity;

import am2.capabilities.AM2Capabilities;
import am2.items.ItemMagicianOrb;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileEntityOracle extends AM2TileEntity implements IItemHandlerModifiable {

    private ItemStack magicianOrbSlot;

    public TileEntityOracle() {
        super("oracle");
        magicianOrbSlot = ItemStack.EMPTY;
    }

    @Override
    public void setStackInSlot(int slot, @Nonnull ItemStack stack) {
        magicianOrbSlot = stack;
    }

    @Override
    public int getSlots() {
        return 1;
    }

    @Nullable
    public ITextComponent getDisplayName()
    {
        return new TextComponentString("Oracle");
    }

    @Nonnull
    @Override
    public ItemStack getStackInSlot(int slot) {
        return magicianOrbSlot;
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        ItemStack temp = magicianOrbSlot;
        if (stack.isEmpty())
            magicianOrbSlot = ItemStack.EMPTY;
        else
            magicianOrbSlot = stack;
        return temp;
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        ItemStack temp = magicianOrbSlot;
        magicianOrbSlot = ItemStack.EMPTY;
        return temp;

    }

    @Override
    public int getSlotLimit(int slot) {
        return 1;
    }

    @Nullable
    public AM2Capabilities GetCapabilityFromOrb() {
        if (magicianOrbSlot.hasTagCompound())
            return new AM2Capabilities().fromNBT(magicianOrbSlot.getTagCompound());
        else
            return null;
    }
}
