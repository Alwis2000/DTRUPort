package net.dviousdingle.dtruport.init;

//import com.ferreusveritas.dynamictrees.api.GatherDataHelper;
//import com.ferreusveritas.dynamictrees.api.TreeHelper;
//import com.ferreusveritas.dynamictrees.api.registry.TypeRegistryEvent;
//import com.ferreusveritas.dynamictrees.block.branch.BranchBlock;
//import com.ferreusveritas.dynamictrees.block.leaves.LeavesProperties;
//import com.ferreusveritas.dynamictrees.block.rooty.SoilProperties;
//import com.ferreusveritas.dynamictrees.systems.BranchConnectables;
//import com.ferreusveritas.dynamictrees.tree.family.Family;
//import com.ferreusveritas.dynamictrees.tree.species.Species;
//import com.ferreusveritas.dynamictreesplus.block.mushroom.CapProperties;
//import dtteam.dtru.DtruPort;
//import dtteam.dtru.block.BioshroomCapProperties;
//import dtteam.dtru.block.GreenBioshroomCapProperties;
//import dtteam.dtru.block.PinkBioshroomCapProperties;
//import dtteam.dtru.block.YellowBioshroomCapProperties;
//import dtteam.dtru.tree.BioshroomSpecies;
//import dtteam.dtru.tree.SmallBioshroomSpecies;
import com.dtteam.dynamictrees.block.branch.BranchBlock;
import com.dtteam.dynamictrees.block.leaves.LeavesProperties;
import com.dtteam.dynamictrees.block.soil.SoilProperties;
import com.dtteam.dynamictrees.data.GatherDataHelper;
import com.dtteam.dynamictrees.event.TypeRegistryEvent;
import com.dtteam.dynamictrees.systems.BranchConnectables;
import com.dtteam.dynamictrees.tree.TreeHelper;
import com.dtteam.dynamictrees.tree.family.Family;
import com.dtteam.dynamictrees.tree.species.Species;
import com.dtteam.dynamictreesplus.block.mushroom.CapProperties;
import net.dviousdingle.dtruport.DtruPort;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.regions_unexplored.block.RuBlocks;
//import net.minecraftforge.data.event.GatherDataEvent;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.regions_unexplored.block.RuBlocks;

public class DTRUPlusRegistries {
    public static void gatherData(final GatherDataEvent event) {
        GatherDataHelper.gatherAllData(DtruPort.MOD_ID, event,
                SoilProperties.REGISTRY,
                Family.REGISTRY,
                Species.REGISTRY,
                LeavesProperties.REGISTRY,
                CapProperties.REGISTRY
        );
    }

    @SubscribeEvent
    public void registerCapPropertiesTypes(final TypeRegistryEvent<CapProperties> event) {
        event.registerType(DtruPort.location("bioshroom_cap"), BioshroomCapProperties.TYPE);
        event.registerType(DtruPort.location("pink_bioshroom_cap"), PinkBioshroomCapProperties.TYPE);
        event.registerType(DtruPort.location("yellow_bioshroom_cap"), YellowBioshroomCapProperties.TYPE);
        event.registerType(DtruPort.location("green_bioshroom_cap"), GreenBioshroomCapProperties.TYPE);
    }

    public static void registerSpeciesTypes(final TypeRegistryEvent<Species> event) {
        event.registerType(ResourceLocation.tryBuild(DtruPort.MOD_ID, "bioshroom"), BioshroomSpecies.TYPE);
        event.registerType(ResourceLocation.tryBuild(DtruPort.MOD_ID, "small_bioshroom"), SmallBioshroomSpecies.TYPE);
    }

    public static void setup(){
        setupBioshroomConnectable(RuBlocks.BLUE_BIOSHROOM_BLOCK.get());
        setupBioshroomConnectable(RuBlocks.GLOWING_BLUE_BIOSHROOM_BLOCK.get());
        setupBioshroomConnectable(RuBlocks.GLOWING_GREEN_BIOSHROOM_BLOCK.get());
        setupBioshroomConnectable(RuBlocks.GLOWING_PINK_BIOSHROOM_BLOCK.get());
        setupBioshroomConnectable(RuBlocks.GLOWING_YELLOW_BIOSHROOM_BLOCK.get());

        BranchConnectables.makeBlockConnectable(Blocks.SHROOMLIGHT, (state, level, pos, side) -> {
            if (side.getAxis() == Direction.Axis.Y) return 0;
            BlockState branchState = level.getBlockState(pos.relative(side.getOpposite()));
            BranchBlock branch = TreeHelper.getBranch(branchState);
            return branch != null ? Mth.clamp(branch.getRadius(branchState) - 1, 1, 3) : 1;
        });
    }

    private static void setupBioshroomConnectable(Block block) {
        BranchConnectables.makeBlockConnectable(block, (state, level, pos, side) -> {
            if (side.getAxis() == Direction.Axis.Y) return 0;
            BlockState branchState = level.getBlockState(pos.relative(side.getOpposite()));
            BranchBlock branch = TreeHelper.getBranch(branchState);
            return branch != null ? Mth.clamp(branch.getRadius(branchState) - 1, 1, 3) : 1;
        });
    }
}
