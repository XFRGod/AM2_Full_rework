package am2.world;

import am2.definitions.BlockDefinitions;
import am2.utils.LogHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import sun.rmi.runtime.Log;

import java.util.Random;

public class OreGen implements IWorldGenerator {

    private WorldGenerator oreblock_salt;

    public OreGen(){
        oreblock_salt = new WorldGenMinable(BlockDefinitions.blockSaltOre.getDefaultState(),6);
        LogHelper.info("registered oregen");
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.getDimension()){
            case 0 : runGenerator(oreblock_salt, world, random, chunkX, chunkZ, 5, 3, 32);
                     break;
        }
    }

    private void runGenerator(WorldGenerator gen, World world, Random rand, int chunkX, int chunkZ, int chance, int minHeight, int maxHeight){
        if (minHeight > maxHeight || minHeight < 0 || maxHeight > 256) throw new IllegalArgumentException("Ore Generated out of bounds"); //sanity check
        int heightDiff = maxHeight - minHeight + 1;

        for(int i = 0; i < chance; i++){
            int x = chunkX * 16 + rand.nextInt(16);
            int y = minHeight + rand.nextInt(heightDiff);
            int z = chunkZ * 16 + rand.nextInt(16);

            gen.generate(world, rand, new BlockPos(x, y, z));
        }
        LogHelper.info("generated ore at" + " " + chunkX + " " + " " + chunkZ);
    }
}
