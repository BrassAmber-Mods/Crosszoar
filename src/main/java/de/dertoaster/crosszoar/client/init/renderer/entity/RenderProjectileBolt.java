package de.dertoaster.crosszoar.client.init.renderer.entity;

import de.dertoaster.crosszoar.CrosszoarMod;
import de.dertoaster.crosszoar.entity.projectile.ProjectileTortoiseBolt;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderProjectileBolt<T extends ProjectileTortoiseBolt> extends ArrowRenderer<T> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(CrosszoarMod.MODID, "textures/entity/projectiles/bolt_tortoise.png");
	
	public RenderProjectileBolt(EntityRendererProvider.Context renderManager) {
		super(renderManager);
	}

	@Override
	public ResourceLocation getTextureLocation(T boltEntity) {
		return TEXTURE;
	}

}
