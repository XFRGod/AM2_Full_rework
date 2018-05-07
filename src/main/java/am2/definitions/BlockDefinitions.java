package am2.definitions;

import am2.blocks.BlockOracle;

public class BlockDefinitions {

    public void Load() {}

    public static BlockDefinitions INSTANCE = new BlockDefinitions();

    public static final BlockOracle blockOracle = new BlockOracle();
}
