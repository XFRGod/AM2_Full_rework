package am2.definitions;

import am2.items.ItemLevelStick;
import am2.items.ItemServerDebug;

public class ItemDefinitions {

    public void Load() {}

    public static ItemDefinitions INSTANCE = new ItemDefinitions();

    public static final ItemLevelStick itemLevelStick = new ItemLevelStick();
    public static final ItemServerDebug itemServerDebug = new ItemServerDebug();
}
