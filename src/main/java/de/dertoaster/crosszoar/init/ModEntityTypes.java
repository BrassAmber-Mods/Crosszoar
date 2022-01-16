package de.dertoaster.crosszoar.init;

import de.dertoaster.crosszoar.CrosszoarMod;
import de.dertoaster.crosszoar.entity.projectile.ProjectileTortoiseBolt;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
	
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, CrosszoarMod.MODID);
	
	public static final RegistryObject<EntityType<ProjectileTortoiseBolt>> BOLT_TORTOISE = ENTITY_TYPES.register("bolt_tortoise", () -> EntityType.Builder.<ProjectileTortoiseBolt>of(ProjectileTortoiseBolt::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4)
			.updateInterval(20).build(new ResourceLocation(CrosszoarMod.MODID, "bolt_tortoise").toString()));

}
