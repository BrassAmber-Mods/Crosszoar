package de.dertoaster.crosszoar.item;

import java.util.function.Predicate;

import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;

import de.dertoaster.crossbowverhaul.item.AbstractItemBolt;
import de.dertoaster.crossbowverhaul.item.IModifiedCrossbowMethod;
import de.dertoaster.crossbowverhaul.item.ItemCrossbowNetherite;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.CrossbowAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.AbstractArrow.Pickup;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.MultiShotEnchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ItemTortoiseCrossbow extends ItemCrossbowNetherite implements IModifiedCrossbowMethod {

	public ItemTortoiseCrossbow(Properties properties) {
		super(properties);
	}

	protected static final Predicate<ItemStack> PREDICATE_ANY_BOLT = (itemStack) -> {
		return !itemStack.isEmpty() && (itemStack.getItem() instanceof AbstractItemBolt);
	};

	protected static final Predicate<ItemStack> PREDICATE_ANY_BOLT_OR_FIREWORK = PREDICATE_ANY_BOLT.or((itemStack) -> {
		return itemStack.getItem() == Items.FIREWORK_ROCKET;
	});

	@Override
	public Predicate<ItemStack> getSupportedHeldProjectiles() {
		return PREDICATE_ANY_BOLT_OR_FIREWORK;
	}

	@Override
	public Predicate<ItemStack> getAllSupportedProjectiles() {
		return PREDICATE_ANY_BOLT;
	}

	@Override
	public int getDefaultProjectileRange() {
		return (int) (1.25 * super.getDefaultProjectileRange());
	}

	@Override
	public float getProjectileSpeedModifier() {
		return 1.25F * super.getProjectileSpeedModifier();
	}

	// Override charging duration
	@Override
	public int getMaxChargeTime() {
		return (int) (1.25 * super.getMaxChargeTime());
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return super.canApplyAtEnchantingTable(stack, enchantment) && !(enchantment instanceof MultiShotEnchantment);
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		if (super.isBookEnchantable(stack, book)) {
			for (Enchantment ench : EnchantmentHelper.getEnchantments(book).keySet()) {
				if (ench instanceof MultiShotEnchantment) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	protected Object actuallyShootProjectile(Level world, LivingEntity shooter, ItemStack crossbow, ItemStack projectileStack, boolean flagProjectileCantBePickedUp, float speed, float divergence, float simulated, boolean leftSide) {
		Projectile projectileentity;
		if (projectileStack.getItem() == Items.FIREWORK_ROCKET) {
			projectileentity = new FireworkRocketEntity(world, projectileStack, shooter, shooter.getX(),
					shooter.getEyeY() - 0.15000000596046448D, shooter.getZ(), true);
		} else {
			projectileentity = CrossbowItem.getArrow(world, shooter, crossbow, projectileStack);
			if (flagProjectileCantBePickedUp || simulated != 0.0F) {
				((AbstractArrow) projectileentity).pickup = Pickup.CREATIVE_ONLY;
			}
		}
		
		Vec3 offset = shooter.getLookAngle().normalize().scale(0.125);
		offset = offset.yRot(leftSide ? 270 : 90);
		projectileentity.setPos(projectileentity.position().add(offset));

		if (shooter instanceof CrossbowAttackMob) {
			CrossbowAttackMob icrossbowuser = (CrossbowAttackMob) shooter;
			icrossbowuser.shootCrossbowProjectile(icrossbowuser.getTarget(), crossbow,
					(Projectile) projectileentity, simulated);
		} else {
			Vec3 vector3d1 = shooter.getUpVector(1.0F);
			Quaternion quaternion = new Quaternion(new Vector3f(vector3d1), simulated, true);
			Vec3 vector3d = shooter.getViewVector(1.0F);
			Vector3f vector3f = new Vector3f(vector3d);
			vector3f.transform(quaternion);
			((Projectile) projectileentity).shoot((double) vector3f.x(), (double) vector3f.y(),
					(double) vector3f.z(), speed * this.getProjectileSpeedModifier(), divergence);
		}
		
		return projectileentity;
	}
	
	@Override
	public void modifiedShootProjectile(Level world, LivingEntity shooter, InteractionHand handUsed, ItemStack crossbow, ItemStack projectileStack, float shootSoundPitch, boolean flagProjectileCantBePickedUp, float speed, float divergence, float simulated) {
		if (!world.isClientSide) {
			boolean flag = projectileStack.getItem() == Items.FIREWORK_ROCKET;
			
			Object obj1 = this.actuallyShootProjectile(world, shooter, crossbow, projectileStack.copy(), flagProjectileCantBePickedUp, speed, divergence, simulated, false);
			Object obj2 = this.actuallyShootProjectile(world, shooter, crossbow, projectileStack, flagProjectileCantBePickedUp, speed, divergence, simulated, true);

			crossbow.hurtAndBreak(flag ? 3 : 1, shooter, (shooterTmp) -> {
	            shooterTmp.broadcastBreakEvent(handUsed);
	         });
			world.addFreshEntity((Entity) obj1);
			world.addFreshEntity((Entity) obj2);
			world.playSound((Player) null, shooter.getX(), shooter.getY(), shooter.getZ(), SoundEvents.CROSSBOW_SHOOT,
					SoundSource.PLAYERS, 1.0F, shootSoundPitch);
		}
	}

}
