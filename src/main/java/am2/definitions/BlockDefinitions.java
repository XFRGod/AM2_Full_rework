package am2.definitions;

import am2.blocks.BlockContainerOracle;
import am2.blocks.BlockOracleBase;
import am2.blocks.BlockSaltOre;

public class BlockDefinitions {

    public void Load() {}

    public static BlockDefinitions INSTANCE = new BlockDefinitions();

    public static final BlockSaltOre blockSaltOre = new BlockSaltOre();
    public static final BlockOracleBase blockOracleBase = new BlockOracleBase();
    public static final BlockContainerOracle blockOracle = new BlockContainerOracle();

}
