package de.dertoaster.crosszoar.client.init;

import de.dertoaster.crosszoar.CrosszoarMod;
import de.dertoaster.crosszoar.client.init.renderer.entity.RenderProjectileBolt;
import de.dertoaster.crosszoar.init.ModEntityTypes;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = CrosszoarMod.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

	@SubscribeEvent
	public static void onClientSetup(FMLClientSetupEvent event) {
		EntityRenderers.register(ModEntityTypes.BOLT_TORTOISE.get(), RenderProjectileBolt::new);
	}
}
