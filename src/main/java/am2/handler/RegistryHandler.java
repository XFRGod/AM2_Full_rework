package am2.handler;

import am2.client.gui.AMGUIIcons;
import am2.definitions.ItemDefinitions;
import am2.items.AM2Item;
import am2.utils.LogHelper;
import am2.utils.Reference;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import am2.items.ItemLevelStick;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

@GameRegistry.ObjectHolder(Reference.MODID)
@Mod.EventBusSubscriber(modid = Reference.MODID)
public class RegistryHandler {
    private static List<Item> itemsToRegister;
    private static List<Block> blocksToRegister;
    private static List<SoundEvent> soundsToRegister;
    private static List<Potion> potionsToRegister;
    private static List<Biome> biomesToRegister;
    private static List<Enchantment> enchantmentsToRegister;
    private static List<EntityEntry> entitiesToRegister;

    public static List<Item> GetItemsToRegister() {
        if (itemsToRegister == null) itemsToRegister = new ArrayList<>();
        return itemsToRegister;
    }

    public static List<Block> GetBlocksToRegister() {
        if (blocksToRegister == null) blocksToRegister = new ArrayList<>();
        return blocksToRegister;
    }

    public static List<SoundEvent> GetSoundsToRegister() {
        if (soundsToRegister == null) soundsToRegister = new ArrayList<>();
        return soundsToRegister;
    }

    public static List<Potion> GetPotionsToRegister() {
        if (potionsToRegister == null) potionsToRegister = new ArrayList<>();
        return potionsToRegister;
    }

    public static List <Biome> GetBiomesToRegister ( ) {
        if ( biomesToRegister == null ) biomesToRegister = new ArrayList <> ( );
        return biomesToRegister;
    }

    public static List <Enchantment> GetEnchantmentsToRegister ( ) {
        if ( enchantmentsToRegister == null ) enchantmentsToRegister = new ArrayList <> ( );
        return enchantmentsToRegister;
    }

    public static List <EntityEntry> GetEntitiesToRegister() {
        if (entitiesToRegister == null) entitiesToRegister = new ArrayList<>();
        return entitiesToRegister;
    }

    public static void AddItemToRegistry(Item item) {
        if (itemsToRegister == null) itemsToRegister = new ArrayList<>();
        itemsToRegister.add(item);
    }

    public static void AddBlockToRegistry(Block block) {
        if (blocksToRegister == null) blocksToRegister = new ArrayList<>();
        blocksToRegister.add(block);
    }

    public static void AddEntityToRegistry(EntityEntry entity) {
        if (entitiesToRegister == null) entitiesToRegister = new ArrayList<>();
        entitiesToRegister.add(entity);
    }

    @SubscribeEvent
    public static void registerBlocks ( RegistryEvent.Register <Block> event ) {

        for ( Block block : RegistryHandler.GetBlocksToRegister ( ) ) {
            LogHelper.info("Registering Block:" + block.getRegistryName() + "/ UnLoc: " + block.getUnlocalizedName());
            event.getRegistry().register(block);
        }
    }

    @SubscribeEvent
    public static void registerEntities ( RegistryEvent.Register <EntityEntry> event ) {

        for ( EntityEntry entity : RegistryHandler.GetEntitiesToRegister ( ) ) {
            event.getRegistry().register(entity);
        }
    }

    @SubscribeEvent
    public static void registerItems ( RegistryEvent.Register <Item> event ) {
        LogHelper.info("Registered Items");
        for ( Item item : RegistryHandler.GetItemsToRegister ( ) ) {
            LogHelper.info("Registering Item:" + item.getRegistryName() + "/ UnLoc: " + item.getUnlocalizedName());
            event.getRegistry().register(item);
        }
    }

    @SubscribeEvent
    public static void registerPotions ( RegistryEvent.Register <Potion> event ) {
        for ( Potion potion : RegistryHandler.GetPotionsToRegister ( ) )
            event.getRegistry ( ).register ( potion );
    }

    @SubscribeEvent
    public static void registerBiomes ( RegistryEvent.Register <Biome> event ) {
        for ( Biome biome : RegistryHandler.GetBiomesToRegister ( ) )
            event.getRegistry ( ).register ( biome );
    }

    @SubscribeEvent
    public static void registerSoundEvents ( RegistryEvent.Register <SoundEvent> event ) {
        for ( SoundEvent sound : RegistryHandler.GetSoundsToRegister ( ) ) {
            event.getRegistry ( ).register ( sound );
        }
    }

    @SubscribeEvent
    public void registerEnchantments ( RegistryEvent.Register <Enchantment> event ) {
        for (Enchantment encha : RegistryHandler.GetEnchantmentsToRegister())
            event.getRegistry().register(encha);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void AM2GUIRegister(TextureStitchEvent.Pre e) {
        AMGUIIcons.instance.init(e.getMap());
    }
    @SideOnly(Side.CLIENT)
    public void registerItemRenderers(Item item) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}
