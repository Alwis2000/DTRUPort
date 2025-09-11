package net.dviousdingle.dtruport.data;

import com.dtteam.dynamictrees.treepack.Resources;
import net.dviousdingle.dtruport.DtruPort;
import net.dviousdingle.dtruport.data.loot.DTNSLootTableProvider;
import net.dviousdingle.dtruport.data.tag.DTNSBlockTagsProvider;
import net.dviousdingle.dtruport.data.tag.DTNSItemTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

public class DataGen {
    public final static String MODID = DtruPort.MOD_ID;

    public static void dataGen(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        if (event.includeServer()) {
//            DtruPort.logger("Generate recipe");
            Resources.MANAGER.reload(Resources.MANAGER.prepareReload(null, null));
            String modId = DtruPort.MOD_ID;

            DTNSBlockTagsProvider blockTags = new DTNSBlockTagsProvider(packOutput, lookupProvider,modId,helper);
            generator.addProvider(event.includeServer(), blockTags);
            generator.addProvider(event.includeServer(), new DTNSItemTagsProvider(packOutput, MODID, lookupProvider, blockTags.contentsGetter(), helper));
            //
            generator.addProvider(event.includeServer(), new DTNSLootTableProvider(packOutput, MODID, helper, lookupProvider));
            // generator.addProvider(new GLMProvider(generator, MODID));


            // GatherDataHelper.gatherLootData(modId, event);
//            generator.addProvider(event.includeServer(), new DatapackRegistryGenerator(packOutput, lookupProvider));

        }
        if (event.includeClient()) {
            // generator.addProvider(event.includeServer(),new Lang_EN(packOutput, helper));
            // generator.addProvider(event.includeServer(),new Lang_ZH(packOutput, helper));
            // generator.addProvider(new BlockStatesDataProvider(generator, helper));
            // generator.addProvider(new ItemModelProvider(generator, helper));
        }


    }
}

