package de.dertoaster.crosszoar.item;

import java.util.function.Predicate;

import de.dertoaster.crossbowverhaul.item.AbstractItemBolt;
import de.dertoaster.crossbowverhaul.item.IModifiedCrossbowMethod;
import de.dertoaster.crossbowverhaul.item.ItemCrossbowNetherite;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.MultiShotEnchantment;

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

}
