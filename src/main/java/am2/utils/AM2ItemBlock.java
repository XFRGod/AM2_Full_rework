package am2.utils;

import am2.definitions.CreativeTabDefinitions;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class AM2ItemBlock extends ItemBlock {

    public AM2ItemBlock(Block block) {
        super(block);
        this.setUnlocalizedName(block.getUnlocalizedName());
        this.setRegistryName(block.getRegistryName());
        this.setCreativeTab(CreativeTabDefinitions.am2ct);
    }

    @SideOnly(Side.CLIENT)
    public void registerRender(){
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(this.getRegistryName(), "inventory"));
    }
}
