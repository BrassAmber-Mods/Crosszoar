package de.dertoaster.crosszoar.client.init;

import de.dertoaster.crossbowverhaul.item.ItemBoltExplosive;
import de.dertoaster.crosszoar.CrosszoarMod;
import de.dertoaster.crosszoar.init.ModItems;
import de.dertoaster.crosszoar.item.ItemTortoiseCrossbow;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tiers;

public class ModItemProperties {

	public static void register() {
		//Netherite crossbow
				ItemProperties.register(ModItems.ITEM_CROSSBOW_TORTOISE.get(), new ResourceLocation(CrosszoarMod.MODID, "bolt_tier"), (itemStack, clientWorld, itemHolder, intIn) -> {
					if(ItemTortoiseCrossbow.getFirstLoadedBolt(itemStack).getItem() instanceof ItemBoltExplosive) { 
						return Tiers.values().length;
					}
					return itemStack != null ? ItemTortoiseCrossbow.getBoltTier(itemStack) : 0;
				});
				
				//Copied from vanilla to mimic normal crossbow
				ItemProperties.register(ModItems.ITEM_CROSSBOW_TORTOISE.get(), new ResourceLocation(CrosszoarMod.MODID, "pull"), (p_239427_0_, p_239427_1_, p_239427_2_, intIn) -> {
			         if (p_239427_2_ == null) {
			            return 0.0F;
			         } else {
			            return ItemTortoiseCrossbow.isCharged(p_239427_0_) ? 0.0F : (float)(p_239427_0_.getUseDuration() - p_239427_2_.getUseItemRemainingTicks()) / (float)ItemTortoiseCrossbow.getChargeDuration(p_239427_0_);
			         }
			      });
				ItemProperties.register(ModItems.ITEM_CROSSBOW_TORTOISE.get(), new ResourceLocation(CrosszoarMod.MODID, "pulling"), (p_239426_0_, p_239426_1_, p_239426_2_, intIn) -> {
			         return p_239426_2_ != null && p_239426_2_.isUsingItem() && p_239426_2_.getUseItem() == p_239426_0_ && !ItemTortoiseCrossbow.isCharged(p_239426_0_) ? 1.0F : 0.0F;
			      });
				ItemProperties.register(ModItems.ITEM_CROSSBOW_TORTOISE.get(), new ResourceLocation(CrosszoarMod.MODID, "charged"), (p_239425_0_, p_239425_1_, p_239425_2_, intIn) -> {
			         return p_239425_2_ != null && ItemTortoiseCrossbow.isCharged(p_239425_0_) ? 1.0F : 0.0F;
			      });
				ItemProperties.register(ModItems.ITEM_CROSSBOW_TORTOISE.get(), new ResourceLocation(CrosszoarMod.MODID, "firework"), (p_239424_0_, p_239424_1_, p_239424_2_, intIn) -> {
			         return p_239424_2_ != null && ItemTortoiseCrossbow.isCharged(p_239424_0_) && ItemTortoiseCrossbow.containsChargedProjectile(p_239424_0_, Items.FIREWORK_ROCKET) ? 1.0F : 0.0F;
			      });
	}

}
