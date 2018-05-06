package am2.definitions;

import am2.items.*;

public class ItemDefinitions {

    public void Load() {}

    public static ItemDefinitions INSTANCE = new ItemDefinitions();

    public static final ItemLevelStick itemLevelStick = new ItemLevelStick();
    public static final ItemServerDebug itemServerDebug = new ItemServerDebug();
    public static final ItemMagicianOrb itemMagicianOrg = new ItemMagicianOrb();
    public static final ItemNBTReader itemNBTReader = new ItemNBTReader();
    public static final ItemFireBall itemFireBall = new ItemFireBall();
}
