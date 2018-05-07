package am2.definitions;

import am2.blocks.BlockOracleBase;

public class BlockDefinitions {

    public void Load() {}

    public static BlockDefinitions INSTANCE = new BlockDefinitions();

    public static final BlockOracleBase blockOracle = new BlockOracleBase();
}
