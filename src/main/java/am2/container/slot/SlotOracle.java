package am2.container.slot;

import am2.blocks.tileentity.TileEntityOracle;
import am2.definitions.ItemDefinitions;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class SlotOracle extends SlotItemHandler {

    public SlotOracle(TileEntityOracle itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(@Nonnull ItemStack stack) {
        if (stack.isEmpty()) return false;
        return stack.getItem() == ItemDefinitions.itemMagicianOrb;
    }

}
