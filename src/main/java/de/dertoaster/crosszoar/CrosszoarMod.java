package de.dertoaster.crosszoar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.dertoaster.crosszoar.client.init.ModItemProperties;
import de.dertoaster.crosszoar.init.ModEntityTypes;
import de.dertoaster.crosszoar.init.ModItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CrosszoarMod.MODID)
@EventBusSubscriber(modid = CrosszoarMod.MODID, bus = Bus.MOD)
public class CrosszoarMod {
	public static final String MODID = "crosszoar";

	// Directly reference a log4j logger.
	public static final Logger LOGGER = LogManager.getLogger();

	public CrosszoarMod() {
IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();
    	
    	//Register items
    	ModItems.registerToEventBus(modbus);
    	//Register entities
    	ModEntityTypes.ENTITY_TYPES.register(modbus);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
    	event.enqueueWork(() -> ModItemProperties.register());
    }

}
