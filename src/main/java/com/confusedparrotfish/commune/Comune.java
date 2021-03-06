package com.confusedparrotfish.commune;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.confusedparrotfish.commune.block.ModBlocks;
import com.confusedparrotfish.commune.item.ModItems;
import com.confusedparrotfish.commune.lib.effect.EffectRegistry;
import com.confusedparrotfish.commune.lib.effect.effect;
import com.confusedparrotfish.commune.tileentity.ModTileEntities;
import com.confusedparrotfish.commune.tileentity.renderer.mysticauldronrender;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Comune.modid)
public class Comune {
    public static final String modid = "commune";

    public static EffectRegistry effects;

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public Comune() {
        IEventBus eventbus= FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventbus);
        ModBlocks.register(eventbus);
        ModTileEntities.register(eventbus);

        // Register the setup method for modloading
        eventbus.addListener(this::setup);
        // Register the enqueueIMC method for modloading
        eventbus.addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        eventbus.addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        eventbus.addListener(this::doClientStuff);

        effects = new EffectRegistry()
            .register("id", new effect() {
                //
            });

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client

        event.enqueueWork(()->{
            // ClientRegistry.bindTileEntityRenderer(tileEntityType, rendererFactory);
            ClientRegistry.bindTileEntityRenderer(ModTileEntities.MYSTIC_CAULDRON_TILE.get(), mysticauldronrender::new);

            //cauldron lookup
            RenderTypeLookup.setRenderLayer(ModBlocks.MYSTIC_CAULDRON.get(), RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(ModBlocks.CRYSTALIZER.get(), RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(ModBlocks.SMOOTH_GLASS.get(), RenderType.getTranslucent());
        });
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> {
            LOGGER.info("Hello world from the MDK");
            return "Hello world";
        });
    }

    private void processIMC(final InterModProcessEvent event) {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}",
                event.getIMCStream().map(m -> m.getMessageSupplier().get()).collect(Collectors.toList()));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the
    // contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
}