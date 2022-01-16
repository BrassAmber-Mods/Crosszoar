package de.dertoaster.crosszoar.entity.projectile;

import de.dertoaster.crossbowverhaul.entity.projectile.ProjectileBolt;
import de.dertoaster.crosszoar.init.ModEntityTypes;
import de.dertoaster.crosszoar.init.ModItems;
import milamber.brass.bezoar.util.BezoarTiers;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;

public class ProjectileTortoiseBolt extends ProjectileBolt {

	public ProjectileTortoiseBolt(EntityType<? extends ProjectileBolt> entityType, Level world) {
		super(entityType, world);
		this.setBaseDamage(this.getBaseDamage());
	}

	public ProjectileTortoiseBolt(Level p_i46757_1_, double px, double py, double pz) {
		super(ModEntityTypes.BOLT_TORTOISE.get(), px, py, pz, p_i46757_1_);
		this.setBaseDamage(this.getBaseDamage());
	}

	public ProjectileTortoiseBolt(Level p_i46758_1_, LivingEntity shooter) {
		super(ModEntityTypes.BOLT_TORTOISE.get(), shooter, p_i46758_1_);
		this.setBaseDamage(this.getBaseDamage());
	}

	@Override
	public void setBaseDamage(double newValue) {
		//needed because of parent class behavior
		super.setBaseDamage(8.0 /* 2x netherite bolt damage */ - this.getTier().getAttackDamageBonus());
	}
	
	@Override
	public Tier getTier() {
		return BezoarTiers.TORTOISE;
	}
	
	@Override
	protected ItemStack getPickupItem() {
		return ModItems.ITEM_BOLT_TORTOSE.get().getDefaultInstance();
	}
	
	@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
	
}
