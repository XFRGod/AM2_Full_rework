package am2.blocks;

import am2.AM2;
import am2.blocks.tileentity.TileEntityOracle;
import am2.definitions.BlockDefinitions;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockContainerOracle extends AM2BlockContainer {

    public BlockContainerOracle() {
        super("oracle");
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityOracle();
    }

    @Override
    public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand,
                                    EnumFacing side, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) return true;
        if (worldIn.getBlockState(pos.down()).getBlock() != BlockDefinitions.blockOracleBase) return true;
        playerIn.openGui(AM2.instance, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }
}
