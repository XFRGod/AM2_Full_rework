package am2.api;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import java.util.EnumSet;

public abstract class SpellShape extends SpellBase {
    public abstract boolean isTerminus();

    public abstract boolean isPrincipum();


    /**
     * Creates the target area/entity list and applies the effects to ground/mobs
     *
     * @param item     The spell being cast
     * @param stack    The itemstack representing the spell
     * @param caster   The caster of the spell
     * @param target   The specified target of the spell.  If this is not NULL, this is a forced target, and should be included with any other targets of the shape.  Otherwise the default spell shape logic should apply.
     * @param world    The world the spell is being cast in
     * @param x        The x-coordinate of the spell's effect
     * @param y        The y-coordinate of the spell's effect
     * @param z        The z-coordinate of the spell's effect
     * @param side     The side the spell is applied on
     * @param giveXP   This is passed along to be given back to the SpellHelper where needed.
     * @param useCount The number of ticks the spell item has been in use for
     * @return The result of the spell cast.
     */
    public abstract SPELLCASTRESULT beginStackStage(ItemSpellBase item, ItemStack stack, EntityLivingBase caster, EntityLivingBase target, World world, double x, double y, double z, EnumFacing side, boolean giveXP, int useCount);


    @Override
    public Object[] getRecipe() {
        return new Object[0];
    }

    @Override
    public EnumSet<?> getModifiers() {
        return null;
    }
}
