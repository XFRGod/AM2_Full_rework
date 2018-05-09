package am2.blocks;

import am2.definitions.ItemDefinitions;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class BlockSaltOre extends AM2Block{
    public BlockSaltOre() {
        super("saltore", Material.ROCK);
        setHardness(3.0F);
        setResistance(5.0F);
        setSoundType(SoundType.STONE);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) { return ItemDefinitions.itemSalt; }

    @Override
    public int quantityDroppedWithBonus(int fortune, Random random)
    {
        return this.quantityDropped(random) + random.nextInt(fortune + 3);
    }

    @Override
    public int quantityDropped(Random random)
    {
        return 4 + random.nextInt(2);
    }

    @Override
    public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune)
    {
        if (this.getItemDropped(state, RANDOM, fortune) != Item.getItemFromBlock(this))
        {
            return 1 + RANDOM.nextInt(5);
        }
        return 0;
    }
}
