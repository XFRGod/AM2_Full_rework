package am2.api;

import am2.items.AM2Item;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public abstract class ItemSpellBase extends AM2Item {
    public ItemSpellBase(String name) {
        super(name);
    }
    /**
     * Specific version of getMovingObjectPosition that takes into account the targeting types that I need
     *
     * @param caster         The caster of the spell
     * @param world          The world the spell is being cast in
     * @param range          The range to raycast
     * @param targetEntities Stop at collision with entities
     * @param targetWater    Stop at collision with any non-solid block
     * @return A MovingObjectPosition instance if a collision was found, null otherwise
     */
    public abstract RayTraceResult getMovingObjectPosition(EntityLivingBase caster, World world, double range, boolean targetEntities, boolean targetWater);
}
