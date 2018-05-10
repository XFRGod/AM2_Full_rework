package am2.definitions;

import am2.items.*;
import am2.utils.Reference;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MODID)
public class ItemDefinitions {

    public void Load() { }

    public static ItemDefinitions INSTANCE = new ItemDefinitions();

    public static final ItemLevelStick itemLevelStick = new ItemLevelStick();
    public static final ItemServerDebug itemServerDebug = new ItemServerDebug();
    public static final ItemMagicianOrb itemMagicianOrb = new ItemMagicianOrb();
    public static final ItemNBTReader itemNBTReader = new ItemNBTReader();
    public static final ItemFireBall itemFireBall = new ItemFireBall();
    public static final AM2Item itemSalt = new AM2Item("salt");
    public static final AM2ItemFood saltedBakedPotato = new AM2ItemFood("salted_baked_potato",12, 0.85F, false);
    public static final ItemAffinityStone affinitystone = new ItemAffinityStone();
}
