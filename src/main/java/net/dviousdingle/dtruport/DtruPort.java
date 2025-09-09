package net.dviousdingle.dtruport;

//import com.ferreusveritas.dynamictrees.api.GatherDataHelper;
//import com.ferreusveritas.dynamictrees.api.registry.RegistryHandler;
//import com.ferreusveritas.dynamictrees.block.leaves.LeavesProperties;
//import com.ferreusveritas.dynamictrees.block.rooty.SoilProperties;
//import com.ferreusveritas.dynamictrees.tree.family.Family;
//import com.ferreusveritas.dynamictrees.tree.species.Species;
//import dtteam.dtru.init.DTRUClient;
//import dtteam.dtru.init.DTRUPlusRegistries;
//import dtteam.dtru.init.DTRURegistries;
import com.dtteam.dynamictrees.api.registry.RegistryHandler;
import com.dtteam.dynamictrees.block.leaves.LeavesProperties;
import com.dtteam.dynamictrees.block.soil.SoilProperties;
import com.dtteam.dynamictrees.data.GatherDataHelper;
import com.dtteam.dynamictrees.tree.family.Family;
import com.dtteam.dynamictrees.tree.species.Species;
import net.dviousdingle.dtruport.init.DTRUClient;
import net.dviousdingle.dtruport.init.DTRUPlusRegistries;
import net.dviousdingle.dtruport.init.DTRURegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.data.event.GatherDataEvent;
//import net.minecraftforge.common.MinecraftForge;
//import net.minecraftforge.data.event.GatherDataEvent;
//import net.minecraftforge.eventbus.api.IEventBus;
//import net.minecraftforge.fml.ModList;
//import net.minecraftforge.fml.common.Mod;
//import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
//import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
//import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(DtruPort.MOD_ID)
public class DtruPort {
    public static final String MOD_ID = "dtru";

    public DtruPort() {
//        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        final IEventBus modEventBus = ModLoadingContext.get().getActiveContainer().getEventBus();
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::gatherData);

        if (isDynamicTreesPlusLoaded()){
            modEventBus.register(new DTRUPlusRegistries());
        }

        NeoForge.EVENT_BUS.register(this);
//        MinecraftForge.EVENT_BUS.register(this);

        RegistryHandler.get(MOD_ID);
//        RegistryHandler.setup(MOD_ID);
        DTRURegistries.setup();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        if (isDynamicTreesPlusLoaded()){
            DTRUPlusRegistries.setup();
        }
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        DTRUClient.setup();
    }

    private void gatherData(final GatherDataEvent event) {
        if (isDynamicTreesPlusLoaded()){
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
        return ResourceLocation.tryBuild(MOD_ID, name);
    }

    public static boolean isDynamicTreesPlusLoaded(){
        return ModList.get().isLoaded("dynamictreesplus");
    }
}
