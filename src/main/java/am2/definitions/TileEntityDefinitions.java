package am2.definitions;

import am2.blocks.tileentity.TileEntityOracle;

public class TileEntityDefinitions {

    public void Load() {}

    public static TileEntityDefinitions INSTANCE = new TileEntityDefinitions();

    public static final TileEntityOracle tileEntityOracle = new TileEntityOracle();
}
