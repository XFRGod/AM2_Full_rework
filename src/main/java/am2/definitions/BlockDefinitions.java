package am2.definitions;

import am2.blocks.BlockContainerOracle;
import am2.blocks.BlockOracleBase;

public class BlockDefinitions {

    public void Load() {}

    public static BlockDefinitions INSTANCE = new BlockDefinitions();

    public static final BlockOracleBase blockOracleBase = new BlockOracleBase();
    public static final BlockContainerOracle blockOracle = new BlockContainerOracle();
}
