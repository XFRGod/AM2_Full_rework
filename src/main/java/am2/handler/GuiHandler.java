package am2.handler;

import am2.blocks.tileentity.TileEntityOracle;
import am2.client.gui.container.GuiOracle;
import am2.container.ContainerOracle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class GuiHandler implements IGuiHandler {

    public final int GUI_OCULUS = 0;

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
        switch (ID) {
            case GUI_OCULUS:
                if (te instanceof TileEntityOracle)
                    return new ContainerOracle((TileEntityOracle)te, player.inventory);
        }
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
        switch (ID) {
            case GUI_OCULUS:
                if (te instanceof TileEntityOracle)
                    return new GuiOracle((TileEntityOracle)te, player.inventory);
        }
        return null;
    }
}
