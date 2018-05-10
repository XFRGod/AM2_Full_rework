package am2.definitions;

import am2.items.*;
import am2.utils.Reference;
import net.minecraft.item.ItemFood;
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
    public static final ItemSalt itemSalt = new ItemSalt();
    public static final ItemSaltedBakedPotato saltedBakedPotato = new ItemSaltedBakedPotato();
    public static final ItemAffinityStone affinitystone = new ItemAffinityStone();
}
