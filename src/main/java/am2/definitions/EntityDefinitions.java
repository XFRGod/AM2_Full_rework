package am2.definitions;

import am2.handler.RegistryHandler;
import am2.utils.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

public class EntityDefinitions {

    public void Load() {
        entityBuilder = EntityEntryBuilder.create();
    }

    private EntityEntryBuilder<Entity> entityBuilder;

    public static final EntityDefinitions INSTANCE = new EntityDefinitions();

    private void AddEntityToList(Class entity, String name, int id) {
        RegistryHandler.AddEntityToRegistry(entityBuilder.entity(entity).id(new ResourceLocation(Reference.MODID, name), id).name(name).build());
    }

}
