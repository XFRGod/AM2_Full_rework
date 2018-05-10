package am2.items;

import am2.capabilities.AM2Capabilities;
import am2.capabilities.AM2CapabilitiesProvider;
import am2.utils.NBTUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemMagicianOrb extends AM2Item {
    public ItemMagicianOrb() {
        super("magicianorb");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (playerIn.isSneaking()) {
            if (playerIn.getHeldItem(handIn).getTagCompound() == null) {
                playerIn.getHeldItem(handIn).setTagCompound(new NBTTagCompound());
            }
            NBTTagCompound compound = NBTUtils.getAM2Tag(playerIn.getHeldItem(handIn).getTagCompound());
            AM2Capabilities instance = AM2CapabilitiesProvider.For(playerIn);
            playerIn.sendMessage(new TextComponentString("" + instance.getCurrentMana()));
            compound.setFloat("CurrentMana", instance.getCurrentMana());
            compound.setInteger("CurrentLevel", instance.getCurrentLevel());
            compound.setFloat("CurrentXP", instance.getCurrentXP());
            compound.setFloat("CurrentBurnout", instance.getCurrentBurnout());

            compound.setDouble("MarkX", instance.getMarkX());
            compound.setDouble("MarkY", instance.getMarkY());
            compound.setDouble("MarkZ", instance.getMarkZ());
            compound.setInteger("MarkDimensionID", instance.getMarkDimensionID());
            playerIn.sendMessage(new TextComponentString(playerIn.getHeldItem(handIn).getTagCompound().toString()));
            return ActionResult.newResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
        } else {
            if (playerIn.getHeldItem(handIn).getTagCompound() == null){
                if (!worldIn.isRemote) playerIn.sendMessage(new TextComponentString("Use Shift+Right-Click first!"));
                return ActionResult.newResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
            }
            else {
                playerIn.sendMessage(new TextComponentString(playerIn.getHeldItem(handIn).getTagCompound().toString()));
                NBTTagCompound am2tag = NBTUtils.getAM2Tag(playerIn.getHeldItem(handIn).getTagCompound());
                AM2Capabilities instance = AM2CapabilitiesProvider.For(playerIn);
                instance.setCurrentMana(am2tag.getFloat("CurrentMana"));
                instance.setCurrentLevel(am2tag.getInteger("CurrentLevel"));
                instance.setCurrentXP(am2tag.getFloat("CurrentXP"));
                instance.setCurrentBurnout(am2tag.getFloat("CurrentBurnout"));

                instance.setMarkX(am2tag.getDouble("MarkX"));
                instance.setMarkY(am2tag.getDouble("MarkY"));
                instance.setMarkZ(am2tag.getDouble("MarkZ"));
                instance.setMarkDimensionID(am2tag.getInteger("MarkDimensionID"));
                return ActionResult.newResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (stack.hasTagCompound()) {
            NBTTagCompound compound = NBTUtils.getAM2Tag(stack.getTagCompound());
            tooltip.add("Mana: " + compound.getFloat("CurrentMana"));
            tooltip.add("Level: " + compound.getInteger("CurrentLevel"));
            tooltip.add("XP: " + compound.getFloat("CurrentXP"));
            tooltip.add("Burnout: " + compound.getFloat("CurrentBurnout"));
            tooltip.add("X: " + compound.getDouble("MarkX"));
            tooltip.add("Y: " + compound.getDouble("MarkY"));
            tooltip.add("Z: " + compound.getDouble("MarkZ"));
            tooltip.add("DimID: " + compound.getInteger("MarkDimensionID"));
        } else {
            tooltip.add("Unsaved. Shift+Right-Click to save.");
        }
    }

    @Override
    public boolean hasEffect(ItemStack par1ItemStack){
        return true;
    }
}
