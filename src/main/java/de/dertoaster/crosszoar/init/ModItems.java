package de.dertoaster.crosszoar.init;

import de.dertoaster.crosszoar.CrosszoarMod;
import de.dertoaster.crosszoar.item.ItemTortoiseBolt;
import de.dertoaster.crosszoar.item.ItemTortoiseCrossbow;
import milamber.brass.bezoar.util.BezoarTiers;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

	public static void registerToEventBus(IEventBus modbus) {
		ITEMS.register(modbus);
	}
	
	static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CrosszoarMod.MODID);

	
	public static final RegistryObject<Item>  ITEM_BOLT_TORTOSE = ITEMS.register("bolt_tortoise", () -> new ItemTortoiseBolt((new Properties().tab(CreativeModeTab.TAB_COMBAT).stacksTo(64)), BezoarTiers.TORTOISE));
	
	public static final RegistryObject<Item> ITEM_CROSSBOW_TORTOISE = ITEMS.register("crossbow_tortoise", () -> (new ItemTortoiseCrossbow(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_COMBAT).durability(2048).fireResistant())));
}
