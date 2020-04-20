package com.xavitoIM.xavitomod.events;

import com.xavitoIM.xavitomod.XavitoMod;
import com.xavitoIM.xavitomod.init.BlockInit;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = XavitoMod.MOD_ID, bus = Bus.FORGE)
public class TestJumpEvent {

	private static boolean activated = false;
	
	@SubscribeEvent
	public static void testJumpEvent(LivingJumpEvent event) {
		if(activated) {
			XavitoMod.LOGGER.info("testJumpEvent fired");
			LivingEntity livingEntity= event.getEntityLiving();
			World world = livingEntity.getEntityWorld();
			world.setBlockState(livingEntity.getPosition().add(0, 5, 0), BlockInit.example_block.getDefaultState());
			livingEntity.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 600, 255));
			livingEntity.addPotionEffect(new EffectInstance(Effects.ABSORPTION, 600, 255));
			livingEntity.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 255));
			livingEntity.setGlowing(true);
		}
	}	
}
