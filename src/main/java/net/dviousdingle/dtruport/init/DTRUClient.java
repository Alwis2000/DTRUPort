package net.dviousdingle.dtruport.init;

//import com.ferreusveritas.dynamictrees.api.client.ModelHelper;
//import com.ferreusveritas.dynamictrees.tree.family.Family;
//import dtteam.dtru.block.BambooBranchBlock;
//import dtteam.dtru.tree.EucalyptusFamily;
import com.dtteam.dynamictrees.tree.family.Family;
import net.dviousdingle.dtruport.DtruPort;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;

import java.util.stream.Collectors;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = DtruPort.MOD_ID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = DtruPort.MOD_ID, value = Dist.CLIENT)
public class DTRUClient {

    @SubscribeEvent
    public static void onRegisterRenderLayers(RegisterRenderLayerEvent event) {
        for (Block block : BuiltInRegistries.BLOCK) {
            if (block instanceof BambooBranchBlock) {
                event.setRenderLayer(block, RenderType.cutoutMipped());
            }
        }
    }

    private static void registerColorHandlers() {
        final int magenta = 0x00FF00FF;//for errors... because magenta sucks.

        // Register Eucalyptus branch Colorizers
        for (EucalyptusFamily family : Family.REGISTRY.getAll().stream()
                .filter(f -> f instanceof EucalyptusFamily)
                .map(f -> (EucalyptusFamily) f)
                .collect(Collectors.toSet())) {

            family.getBranch().ifPresent(branchBlock ->
                    ModelHelper.regColorHandler(branchBlock, (state, level, pos, tintIndex) ->
                            pos != null ? family.branchColorMultiplier(state, level, pos) : magenta
                    )
            );

            family.getSurfaceRoot().ifPresent(surfaceRoot ->
                    ModelHelper.regColorHandler(surfaceRoot, (state, level, pos, tintIndex) ->
                            pos != null ? family.branchColorMultiplier(state, level, pos) : magenta
                    )
            );
        }

    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        DtruPort.LOGGER.info("HELLO FROM CLIENT SETUP");
        DtruPort.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }
}
