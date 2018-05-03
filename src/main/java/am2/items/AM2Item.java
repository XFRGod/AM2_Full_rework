package am2.items;

import am2.definitions.CreativeTabDefinitions;
import am2.utils.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class AM2Item extends Item {

    public AM2Item(String name) {
        this.setUnlocalizedName(new ResourceLocation(Reference.MODID, name).toString());
        this.setRegistryName(new ResourceLocation(Reference.MODID, name));
        this.setCreativeTab(CreativeTabDefinitions.am2ct);
    }

    @SideOnly(Side.CLIENT)
    public void registerRender(Item item){
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}
