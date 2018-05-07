package am2.utils;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class MiscUtils {
    public static Item BlockToItemBlock(Block in) {
        return new ItemBlock(in).setUnlocalizedName(in.getUnlocalizedName()).setRegistryName(in.getRegistryName());
    }
}
