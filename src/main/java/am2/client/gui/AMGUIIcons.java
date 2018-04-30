package am2.client.gui;

import am2.utils.Reference;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;


public class AMGUIIcons {
	public static boolean initialized = false;
	public static TextureAtlasSprite manaBar;
	public static TextureAtlasSprite manaLevel;

	public static TextureAtlasSprite fatigueIcon;
	public static TextureAtlasSprite fatigueBar;
	public static TextureAtlasSprite fatigueLevel;

	public static TextureAtlasSprite padlock;
	public static TextureAtlasSprite gatewayPortal;
	public static TextureAtlasSprite evilBook;
	public static TextureAtlasSprite selectedRunes;

	public static TextureAtlasSprite warning;
	public static TextureAtlasSprite checkmark;
	public static TextureAtlasSprite newEntry;
	
	public static TextureAtlasSprite frame;

	public static AMGUIIcons instance = new AMGUIIcons();


	private AMGUIIcons(){ }

	public void init(TextureMap textureMap){
		manaBar 	  = textureMap.registerSprite(new ResourceLocation(Reference.MODID + "gui/icons/mana_bar"));
		manaLevel 	  = textureMap.registerSprite(new ResourceLocation(Reference.MODID + "gui/icons/mana_level"));

		fatigueIcon   = textureMap.registerSprite(new ResourceLocation(Reference.MODID + "gui/icons/fatigue_icon"));
		fatigueBar 	  = textureMap.registerSprite(new ResourceLocation(Reference.MODID + "gui/icons/fatigue_bar"));
		fatigueLevel  = textureMap.registerSprite(new ResourceLocation(Reference.MODID + "gui/icons/fatigue_level"));

		padlock 	  = textureMap.registerSprite(new ResourceLocation(Reference.MODID + ":gui/icons/padlock"));
		warning    	  = textureMap.registerSprite(new ResourceLocation(Reference.MODID + ":gui/icons/update_available"));
		checkmark 	  = textureMap.registerSprite(new ResourceLocation(Reference.MODID + ":gui/icons/up_to_date"));

		newEntry 	  = textureMap.registerSprite(new ResourceLocation(Reference.MODID + ":gui/icons/new"));

		evilBook	  = textureMap.registerSprite(new ResourceLocation(Reference.MODID + ":items/evilbook"));

		gatewayPortal = textureMap.registerSprite(new ResourceLocation(Reference.MODID + ":gui/icons/gateway"));

		selectedRunes = textureMap.registerSprite(new ResourceLocation(Reference.MODID + ":gui/icons/rune_selected_aura"));

		frame 		  = textureMap.registerSprite(new ResourceLocation(Reference.MODID + ":gui/icons/spellframe"));
	}
}
