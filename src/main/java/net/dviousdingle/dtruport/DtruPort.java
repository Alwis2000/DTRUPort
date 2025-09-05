package net.dviousdingle.dtruport;

import com.dtteam.dynamictrees.block.leaves.LeavesProperties;
import com.dtteam.dynamictrees.block.soil.SoilProperties;
import com.dtteam.dynamictrees.data.GatherDataHelper;
import com.dtteam.dynamictrees.tree.family.Family;
import com.dtteam.dynamictrees.tree.species.Species;
import net.dviousdingle.dtruport.init.DTRUPlusRegistries;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

//import com.ferreusveritas.dynamictrees.api.GatherDataHelper;
//import com.ferreusveritas.dynamictrees.api.registry.RegistryHandler;
//import com.ferreusveritas.dynamictrees.block.leaves.LeavesProperties;
//import com.ferreusveritas.dynamictrees.block.rooty.SoilProperties;
//import com.ferreusveritas.dynamictrees.tree.family.Family;
//import com.ferreusveritas.dynamictrees.tree.species.Species;
//import dtteam.dtru.init.DTRUClient;
//import dtteam.dtru.init.DTRUPlusRegistries;
//import dtteam.dtru.init.DTRURegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;


// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(DtruPort.MOD_ID)
public class DtruPort {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "dtru";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public DtruPort(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        if (isDynamicTreesPlusLoaded()){
            modEventBus.register(new DTRUPlusRegistries());
        }

        NeoForge.EVENT_BUS.register(this);

        RegistryHandler.setup(MOD_ID);
        DTRURegistries.setup();

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        if (isDynamicTreesPlusLoaded()){
            DTRUPlusRegistries.setup();
        }
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        DTRUClient.setup();
    }

    private void gatherData(final GatherDataEvent event) {
        if (isDynamicTreesPlusLoaded()) {
            DTRUPlusRegistries.gatherData(event);
        } else {
            GatherDataHelper.gatherAllData(MOD_ID, event,
                    SoilProperties.REGISTRY,
                    Family.REGISTRY,
                    Species.REGISTRY,
                    LeavesProperties.REGISTRY
            );
        }
    }

    public static ResourceLocation location (String name){
        return new ResourceLocation(MOD_ID, name);
    }

    public static boolean isDynamicTreesPlusLoaded(){
        return ModList.get().isLoaded("dynamictreesplus");
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
}
