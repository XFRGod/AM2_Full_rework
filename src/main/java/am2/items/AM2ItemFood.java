package am2.items;

import am2.definitions.CreativeTabDefinitions;
import am2.handler.RegistryHandler;
import am2.utils.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemFood;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class AM2ItemFood extends ItemFood {
    public AM2ItemFood(String name, int amount, float saturation, boolean isWolfFood) {
        super(amount, saturation, isWolfFood);
        this.setUnlocalizedName(name);
        this.setRegistryName(new ResourceLocation(Reference.MODID, name));
        this.setCreativeTab(CreativeTabDefinitions.am2ct);
        RegistryHandler.AddItemToRegistry(this);
    }

    public AM2ItemFood(String name, int amount, boolean isWolfFood){
        super(amount, isWolfFood);
        this.setUnlocalizedName(name);
        this.setRegistryName(new ResourceLocation(Reference.MODID, name));
        this.setCreativeTab(CreativeTabDefinitions.am2ct);
        RegistryHandler.AddItemToRegistry(this);
    }

    @SideOnly(Side.CLIENT)
    public void registerRender(){
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(this.getRegistryName(), "inventory"));
    }
}
