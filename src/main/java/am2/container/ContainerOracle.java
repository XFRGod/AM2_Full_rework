package am2.container;


import am2.blocks.tileentity.TileEntityOracle;
import am2.capabilities.AM2CapabilitiesProvider;
import am2.container.slot.SlotOracle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerOracle extends Container {

    public ContainerOracle(TileEntityOracle oracle, InventoryPlayer player) {

        addSlotToContainer(new SlotOracle(oracle, 38, 116, 35));

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(player, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k)
        {
            this.addSlotToContainer(new Slot(player, k, 8 + k * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return playerIn.hasCapability(AM2CapabilitiesProvider.INSTANCE, null);
    }
}
