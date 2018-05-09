package am2.blocks.tileentity;

import am2.handler.RegistryHandler;
import am2.utils.Reference;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;


public class AM2TileEntity extends TileEntity {

    private String name;

    public AM2TileEntity(String name) {
        this.name = name;
        RegistryHandler.AddTileEntityToRegistry(this);
    }

    public String getName() {
        return name;
    }

    public ResourceLocation GetRegistryName() {
        return new ResourceLocation(Reference.MODID, name);
    }

}
