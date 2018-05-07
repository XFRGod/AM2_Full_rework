package am2.blocks;

import am2.capabilities.AM2CapabilitiesProvider;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class BlockOracle extends AM2Block {

    public BlockOracle() {
        super("oracle", Material.ROCK, MapColor.BLACK);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        playerIn.sendMessage(new TextComponentString(AM2CapabilitiesProvider.For(playerIn).toString()));
        return true ;
    }

}
