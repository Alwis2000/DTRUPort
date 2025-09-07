package net.dviousdingle.dtruport.growthlogic;

//import com.ferreusveritas.dynamictrees.api.registry.Registry;
//import com.ferreusveritas.dynamictrees.growthlogic.GrowthLogicKit;
import com.dtteam.dynamictrees.api.registry.Registry;
import com.dtteam.dynamictrees.systems.growthlogic.GrowthLogicKit;
import net.dviousdingle.dtruport.DtruPort;
import net.minecraft.resources.ResourceLocation;
//import dtteam.dtru.DtruPort;

public class DTRUGrowthLogicKits {
    public static final GrowthLogicKit THIN_CONIFER = new PineLogic(ResourceLocation.tryBuild(DtruPort.MOD_ID, "thin_conifer"));
    public static final GrowthLogicKit MEGA_PINE = new MegaPineLogic(ResourceLocation.tryBuild(DtruPort.MOD_ID, "mega_pine"));
    public static final GrowthLogicKit REDWOOD = new RedwoodLogic(ResourceLocation.tryBuild(DtruPort.MOD_ID, "redwood"));
    public static final GrowthLogicKit SMALL_REDWOOD = new SmallRedwoodLogic(ResourceLocation.tryBuild(DtruPort.MOD_ID, "small_redwood"));
    public static final GrowthLogicKit BAOBAB = new BaobabLogic(ResourceLocation.tryBuild(DtruPort.MOD_ID, "baobab"));
    public static final GrowthLogicKit PALM = new DiagonalPalmLogic(ResourceLocation.tryBuild(DtruPort.MOD_ID, "diagonal_palm"));
    public static final GrowthLogicKit VARIATE_HEIGHT = new VariateHeightLogic(ResourceLocation.tryBuild(DtruPort.MOD_ID, "variate_height"));
    public static final GrowthLogicKit CYPRESS = new CypressLogic(ResourceLocation.tryBuild(DtruPort.MOD_ID, "cypress"));
    public static final GrowthLogicKit MEGA_EUCALYPTUS = new MegaEucalyptusLogic(ResourceLocation.tryBuild(DtruPort.MOD_ID, "mega_eucalyptus"));
    public static final GrowthLogicKit WILLOW = new WillowLogic(ResourceLocation.tryBuild(DtruPort.MOD_ID, "willow"));
    public static final GrowthLogicKit BAMBOO = new BambooLogic(ResourceLocation.tryBuild(DtruPort.MOD_ID, "bamboo"));
    public static final GrowthLogicKit TWISTING_TREE = new TwistingTreeLogic(ResourceLocation.tryBuild(DtruPort.MOD_ID, "twisting_tree"));
    public static final GrowthLogicKit CANOPY = new CanopyLogic(ResourceLocation.tryBuild(DtruPort.MOD_ID, "canopy"));
    public static final GrowthLogicKit POPLAR = new PoplarLogic(ResourceLocation.tryBuild(DtruPort.MOD_ID, "poplar"));

    public static void register(final Registry<GrowthLogicKit> registry) {
        registry.registerAll(THIN_CONIFER, MEGA_PINE, REDWOOD, SMALL_REDWOOD,
                BAOBAB, VARIATE_HEIGHT, CYPRESS, MEGA_EUCALYPTUS, WILLOW, PALM,
                TWISTING_TREE, BAMBOO, CANOPY, POPLAR);
    }

}
