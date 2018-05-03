package am2.blocks;

import am2.utils.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class AM2Block extends Block {
    public AM2Block(String name, Material blockMaterialIn, MapColor blockMapColorIn) {
        super(blockMaterialIn, blockMapColorIn);
        this.setRegistryName(new ResourceLocation(Reference.MODID, name));
        this.setUnlocalizedName(this.getRegistryName().toString());
    }

    public AM2Block(String name, Material blockMaterialIn) {
        super(blockMaterialIn, MapColor.CYAN);
        this.setRegistryName(new ResourceLocation(Reference.MODID, name));
        this.setUnlocalizedName(this.getRegistryName().toString());
    }

    public AM2Block(String name) {
        super(Material.ROCK, MapColor.CYAN);
        this.setRegistryName(new ResourceLocation(Reference.MODID, name));
        this.setUnlocalizedName(this.getRegistryName().toString());
    }

    @SideOnly(Side.CLIENT)
    public void registerRender(Block block) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getUnlocalizedName().substring(5), "inventory"));
    }
}
