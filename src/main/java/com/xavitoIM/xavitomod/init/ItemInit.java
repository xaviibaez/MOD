package com.xavitoIM.xavitomod.init;

import java.util.function.Supplier;

import com.xavitoIM.xavitomod.XavitoMod;
import com.xavitoIM.xavitomod.XavitoMod.TutorialItemGroup;
import com.xavitoIM.xavitomod.objects.items.SpecialItem;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Food;
import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.LazyValue;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = XavitoMod.MOD_ID, bus = Bus.MOD)
@ObjectHolder(XavitoMod.MOD_ID)
public class ItemInit {

	public static final Item example_item = null;
	public static final Item xavito_item = null;
	public static final Item special_item = null;
	
	//Tools
	public static final Item example_sword = null;
	public static final Item example_pickaxe = null;
	public static final Item example_shovel = null;
	public static final Item example_axe = null;
	public static final Item example_hoe = null;
	
	//Armor
	public static final Item example_helmet = null;
	public static final Item example_chestplate = null;
	public static final Item example_leggins = null;
	public static final Item example_boots = null;
	
	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event) {
		event.getRegistry().register(new Item(new Item.Properties().group(ItemGroup.MISC))
				.setRegistryName("example_item"));
		
		event.getRegistry().register(new Item(new Item.Properties().group(TutorialItemGroup.instance)
				.food(new Food.Builder().hunger(6).saturation(1.2f)
						.effect(new EffectInstance(Effects.ABSORPTION, 6000, 5), 0.7f).build()))
				.setRegistryName("xavito_item"));
		
		event.getRegistry().register(new SpecialItem(new Item.Properties().group(TutorialItemGroup.instance)).setRegistryName("special_item"));
		
		//Tools
		event.getRegistry().register(new SwordItem(ModItemTier.EXAMPLE, 7, 5.0f, new Item.Properties().group(TutorialItemGroup.instance))
				.setRegistryName("example_sword"));
		event.getRegistry().register(new PickaxeItem(ModItemTier.EXAMPLE, 4, 5.0f, new Item.Properties().group(TutorialItemGroup.instance))
				.setRegistryName("example_pickaxe"));
		event.getRegistry().register(new ShovelItem(ModItemTier.EXAMPLE, 2, 5.0f, new Item.Properties().group(TutorialItemGroup.instance))
				.setRegistryName("example_shovel"));
		event.getRegistry().register(new AxeItem(ModItemTier.EXAMPLE, 11, 3.0f, new Item.Properties().group(TutorialItemGroup.instance))
				.setRegistryName("example_axe"));
		event.getRegistry().register(new HoeItem(ModItemTier.EXAMPLE, 5.0f, new Item.Properties().group(TutorialItemGroup.instance))
				.setRegistryName("example_hoe"));
		
		//Armor
		//event.getRegistry().register(new ArmorItem(ModArmorMaterial.TEST, EquipmentSlotType.HEAD, new Item.Properties().group(TutorialItemGroup.instance)).setRegistryName("example_helmet"));
		//event.getRegistry().register(new ArmorItem(ModArmorMaterial.TEST, EquipmentSlotType.CHEST, new Item.Properties().group(TutorialItemGroup.instance)).setRegistryName("example_chestplate"));
		//event.getRegistry().register(new ArmorItem(ModArmorMaterial.TEST, EquipmentSlotType.LEGS, new Item.Properties().group(TutorialItemGroup.instance)).setRegistryName("example_leggins"));
		//event.getRegistry().register(new ArmorItem(ModArmorMaterial.TEST, EquipmentSlotType.FEET, new Item.Properties().group(TutorialItemGroup.instance)).setRegistryName("example_boots"));

	}
	
	public enum ModItemTier implements IItemTier{
		//harvestLevel, maxUses, efficiency, attackDamage, enchantability, repairMaterial
		EXAMPLE(4, 1500, 15.0F, 7.0F, 250, () -> {
			return Ingredient.fromItems(ItemInit.example_item);
		});
		
		private final int harvestLevel;
		private final int maxUses;
		private final float efficiency;
		private final float attackDamage;
		private final int enchantability;
		private final LazyValue<Ingredient> repairMaterial;

		private ModItemTier(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial) {
			this.harvestLevel = harvestLevel;
			this.maxUses = maxUses;
			this.efficiency = efficiency;
			this.attackDamage = attackDamage;
			this.enchantability = enchantability;
			this.repairMaterial = new LazyValue<>(repairMaterial);	
		}

		@Override
		public int getMaxUses() {
			return this.maxUses;
		}

		@Override
		public float getEfficiency() {
			return this.efficiency;
		}

		@Override
		public float getAttackDamage() {
			return this.attackDamage;
		}

		@Override
		public int getHarvestLevel() {
			return this.harvestLevel;
		}

		@Override
		public int getEnchantability() {
			return this.enchantability;
		}

		@Override
		public Ingredient getRepairMaterial() {
			return this.repairMaterial.getValue();
		}
	}
}
