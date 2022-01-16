package de.dertoaster.crosszoar.item;

import de.dertoaster.crossbowverhaul.entity.projectile.ProjectileBolt;
import de.dertoaster.crossbowverhaul.item.AbstractItemBolt;
import de.dertoaster.crosszoar.entity.projectile.ProjectileTortoiseBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;

public class ItemTortoiseBolt extends AbstractItemBolt {

	public ItemTortoiseBolt(Properties properties, final Tier tier) {
		super(properties, tier);
	}

	@Override
	public AbstractArrow createArrow(Level arg0, ItemStack arg1, LivingEntity arg2) {
		ProjectileBolt bolt = new ProjectileTortoiseBolt(arg0, arg2);
		bolt.setTier(this.tier);
		return bolt;
	}

	@Override
	public int getIdForItemProperties() {
		return 999;
	}

}
