package am2.blocks;

import am2.definitions.CreativeTabDefinitions;
import am2.handler.RegistryHandler;
import am2.utils.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class AM2BlockContainer extends BlockContainer {

    private boolean itemBlock;

    protected AM2BlockContainer(String name, Material materialIn) {
        super(materialIn);
        this.setUnlocalizedName(new ResourceLocation(Reference.MODID, name).toString());
        this.setRegistryName(new ResourceLocation(Reference.MODID, name).toString());
        this.setCreativeTab(CreativeTabDefinitions.am2ct);
        RegistryHandler.AddBlockToRegistry(this);
        itemBlock = true;
    }

    protected AM2BlockContainer(String name) {
        super(Material.ROCK);
        this.setUnlocalizedName(new ResourceLocation(Reference.MODID, name).toString());
        this.setRegistryName(new ResourceLocation(Reference.MODID, name).toString());
        this.setCreativeTab(CreativeTabDefinitions.am2ct);
        RegistryHandler.AddBlockToRegistry(this);
        itemBlock = true;
    }

    protected AM2BlockContainer(String name, boolean itemBlock) {
        super(Material.ROCK);
        this.setUnlocalizedName(new ResourceLocation(Reference.MODID, name).toString());
        this.setRegistryName(new ResourceLocation(Reference.MODID, name).toString());
        this.setCreativeTab(CreativeTabDefinitions.am2ct);
        RegistryHandler.AddBlockToRegistry(this);
        this.itemBlock = itemBlock;
    }

    public void notItemBlock() { this.itemBlock = false; }

    @SideOnly(Side.CLIENT)
    public void registerRender(Block block) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getUnlocalizedName().substring(5), "inventory"));
    }

    public boolean isItemBlock() {
        return itemBlock;
    }
}
