package kono.ceu.materialreplication.common.machines;

import gregtech.api.GTValues;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.ICubeRenderer;
import kono.ceu.materialreplication.api.MRValues;
import kono.ceu.materialreplication.api.recipes.MRRecipeMaps;
import kono.ceu.materialreplication.client.MRTextures;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.function.BiFunction;
import java.util.function.Function;

import static gregtech.common.metatileentities.MetaTileEntities.*;

public class MRMetaTileEntities {
    public static MRSimpleMachineMetaTileEntity[] DECONSTRUCTOR = new MRSimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static MRSimpleMachineMetaTileEntity[] REPLICATOR = new MRSimpleMachineMetaTileEntity[GTValues.V.length - 1];
    //20030 - 20044
    public static SimpleMachineMetaTileEntity[] SCRAPPER = new MetaTileEntityScrapMaker[GTValues.V.length - 1];

    public static void init() {
        //20000 - 20014 : Material Deconstructor
        registerMRSimpleMetaTileEntity(DECONSTRUCTOR, 20000, "deconstructor", MRRecipeMaps.DECONSTRUCTION_RECIPES, MRTextures.DECONSTRUCTOR_OVERLAY,true, MRMetaTileEntities::MR, GTUtility.hvCappedTankSizeFunction);

        //20015 - 20029 : Material Replicator
        registerMRSimpleMetaTileEntity(REPLICATOR, 20015, "replicator", MRRecipeMaps.REPLICATION_RECIPES, MRTextures.REPLICATOR_OVERLAY,true, MRMetaTileEntities::MR,GTUtility.hvCappedTankSizeFunction);

        for (int i = 0; i < SCRAPPER.length - 1 ; i++ ) {
            String voltageName = GTValues.VN[i + 1].toLowerCase();
            SCRAPPER[i] = registerMetaTileEntity(20030 + i,  new MetaTileEntityScrapMaker(new ResourceLocation(MRValues.MODID,(String.format("%s.%s", "scrapmaker", voltageName))), MRRecipeMaps.SCRAPMAKER_RECIPES, MRTextures.SCRAPPER_OVERLAY, i + 1, GTUtility.hvCappedTankSizeFunction));
        }

    }
    private static @Nonnull ResourceLocation MR(String name) {
        return new ResourceLocation("materialreplication", name);
    }

    private static <T extends MetaTileEntity> void registerMetaTileEntities(
            T[] machines,
            int startId,
            String name,
            BiFunction<Integer, String, T> mteCreator) {
        for (int i = 0; i < machines.length - 1; i++) {
            if (i > 4 && !getMidTier(name)) continue;
            if (i > 7 && !getHighTier(name)) break;

            String voltageName = GTValues.VN[i + 1].toLowerCase();
            machines[i + 1] = registerMetaTileEntity(startId + i,
                    mteCreator.apply(i + 1, voltageName));
        }
    }

    public static void registerMRSimpleMetaTileEntity(MRSimpleMachineMetaTileEntity[] machines, int startId,
                                                       String name, RecipeMap<?> map, ICubeRenderer texture,
                                                       boolean hasFrontFacing,
                                                       Function<String, ResourceLocation> resourceId,
                                                       Function<Integer, Integer> tankScalingFunction) {
        for (int i = 0; i < machines.length - 1; ++i) {
            if (i <= 4 || getMidTier(name)) {
                if (i > 7 && !getHighTier(name)) {
                    break;
                }

                String voltageName = GTValues.VN[i + 1].toLowerCase();
                machines[i + 1] = registerMetaTileEntity(startId + i,
                        new MRSimpleMachineMetaTileEntity(resourceId.apply(String.format("%s.%s", name, voltageName)),
                                map, texture, i + 1, hasFrontFacing, tankScalingFunction));
            }
        }
    }
}
