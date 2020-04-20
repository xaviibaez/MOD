package com.xavitoIM.xavitomod;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.xavitoIM.xavitomod.init.BlockInit;
import com.xavitoIM.xavitomod.world.gen.TutorialOreGen;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
// EJEMPLO -> Dentro del juego -> /give @s minecraft:dirt
// 			  Dentro del juego -> /give @s xavitomod:dirt

@Mod("xavitomod")
@Mod.EventBusSubscriber(modid = XavitoMod.MOD_ID, bus = Bus.MOD)
public class XavitoMod
{
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "xavitomod";
    public static XavitoMod instance;

    public XavitoMod() {
    	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register the setup method for modloading
    	modEventBus.addListener(this::setup);
        // Register the doClientStuff method for modloading
    	modEventBus.addListener(this::doClientStuff);

        instance = this;
        
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event){
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
    }
    
    @SubscribeEvent
    public void loadCompleteEvent(FMLLoadCompleteEvent event) {
    	TutorialOreGen.generateOre();
    }
    
    public static class TutorialItemGroup extends ItemGroup{
    	public static final TutorialItemGroup instance = new TutorialItemGroup(ItemGroup.GROUPS.length, "tutorialtab");
    	
    	private TutorialItemGroup(int index, String label) {
    		super (index, label);
    	}

		@Override
		public ItemStack createIcon() {
			
			return new ItemStack(BlockInit.example_block);
		}
    	
    	
    }
}
