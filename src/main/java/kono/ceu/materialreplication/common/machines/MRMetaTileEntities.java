package kono.ceu.materialreplication.common.machines;

import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.util.GTUtility;
import kono.ceu.materialreplication.api.recipes.MRRecipeMaps;
import kono.ceu.materialreplication.client.MRTextures;
import kono.ceu.materialreplication.common.machines.multi.MetaTileEntityLargeDeconstructor;
import kono.ceu.materialreplication.common.machines.multi.MetaTileEntityLargeScrapper;
import kono.ceu.materialreplication.common.machines.single.MetaTileEntityScrapMaker;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;
import static kono.ceu.materialreplication.api.util.MRValues.*;

public class MRMetaTileEntities {

    // Single : 0 -
    public static SimpleMachineMetaTileEntity[] DECONSTRUCTOR = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static SimpleMachineMetaTileEntity[] REPLICATOR = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static MetaTileEntityScrapMaker[] SCRAPPER = new MetaTileEntityScrapMaker[GTValues.V.length - 1];

    // Multi : 10000 -
    public static MetaTileEntityLargeDeconstructor LARGE_DECONSTRUCTOR;
    public static MetaTileEntityLargeScrapper LARGE_SCRAPPER;

    public static void init() {
        registerSingleMachine();
        registerMultiMachine();
    }

    public static void registerSingleMachine() {
        // 0 - 14 : Material Deconstructor
        for (int i = 1; i < DECONSTRUCTOR.length; i++) {
            String voltageName = GTValues.VN[i].toLowerCase();
            if (!GregTechAPI.isHighTier() && i > GTValues.UV)
                break;
            if (tierDeconstruct <= i)
                DECONSTRUCTOR[i] = registerMetaTileEntity((i - 1),
                        new SimpleMachineMetaTileEntity(
                                new ResourceLocation(MODID, (String.format("%s.%s", "deconstructor", voltageName))),
                                MRRecipeMaps.DECONSTRUCTION_RECIPES, MRTextures.DECONSTRUCTOR_OVERLAY, i, true,
                                GTUtility.hvCappedTankSizeFunction));
        }

        // 15 - 29 : Material Replicator
        for (int i = 1; i < REPLICATOR.length; i++) {
            String voltageName = GTValues.VN[i].toLowerCase();
            if (!GregTechAPI.isHighTier() && i > GTValues.UV)
                break;
            if (tierReplicate <= i)
                REPLICATOR[i] = registerMetaTileEntity(15 + (i - 1),
                        new SimpleMachineMetaTileEntity(
                                new ResourceLocation(MODID, (String.format("%s.%s", "replicator", voltageName))),
                                MRRecipeMaps.REPLICATION_RECIPES, MRTextures.REPLICATOR_OVERLAY, i, true,
                                GTUtility.hvCappedTankSizeFunction));
        }

        // 30 - 44 Scrapper
        for (int i = 1; i < SCRAPPER.length - 1; i++) {
            String voltageName = GTValues.VN[i].toLowerCase();
            if (!GregTechAPI.isHighTier() && i > GTValues.UV)
                break;
            SCRAPPER[i] = registerMetaTileEntity(30 + (i - 1),
                    new MetaTileEntityScrapMaker(
                            new ResourceLocation(MODID, (String.format("%s.%s", "scrapmaker", voltageName))),
                            MRRecipeMaps.SCRAPMAKER_RECIPES, MRTextures.SCRAPPER_OVERLAY, i,
                            GTUtility.hvCappedTankSizeFunction));

        }
    }

    public static void registerMultiMachine() {
        // Large Deconstructor 10000
        LARGE_DECONSTRUCTOR = registerMetaTileEntity(10000,
                new MetaTileEntityLargeDeconstructor(mrId("large_deconstructor")));

        // Large Scrapper 10001
        LARGE_SCRAPPER = registerMetaTileEntity(10001,
                new MetaTileEntityLargeScrapper(mrId("large_scrapper")));
    }

    private static @NotNull ResourceLocation mrId(String name) {
        return new ResourceLocation(MODID, name);
    }
}
