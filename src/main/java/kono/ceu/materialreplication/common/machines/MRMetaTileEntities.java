package kono.ceu.materialreplication.common.machines;

import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;
import static kono.ceu.materialreplication.api.util.MRValues.*;

import net.minecraft.util.ResourceLocation;

import org.jetbrains.annotations.NotNull;

import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.util.GTUtility;

import kono.ceu.materialreplication.api.recipes.MRRecipeMaps;
import kono.ceu.materialreplication.client.MRTextures;
import kono.ceu.materialreplication.common.machines.multi.MetaTileEntityLargeDeconstructor;
import kono.ceu.materialreplication.common.machines.multi.MetaTileEntityLargeScrapper;
import kono.ceu.materialreplication.common.machines.single.MetaTileEntityScrapMaker;

public class MRMetaTileEntities {

    // Single Machine
    // Default: 20000 - 200044
    public static SimpleMachineMetaTileEntity[] DECONSTRUCTOR = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static SimpleMachineMetaTileEntity[] REPLICATOR = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static MetaTileEntityScrapMaker[] SCRAPPER = new MetaTileEntityScrapMaker[GTValues.V.length - 1];

    // Multi Machine
    // Default: 21000-
    public static MetaTileEntityLargeDeconstructor LARGE_DECONSTRUCTOR;
    public static MetaTileEntityLargeScrapper LARGE_SCRAPPER;

    public static void init() {
        registerSingleMachine();
        registerMultiMachine();
    }

    public static void registerSingleMachine() {
        // Material Deconstructor
        // Default: 20000 - 20014
        int id = baseID;
        for (int i = 1; i < DECONSTRUCTOR.length; i++) {
            String voltageName = GTValues.VN[i].toLowerCase();
            if (!GregTechAPI.isHighTier() && i > GTValues.UV)
                break;
            if (tierDeconstruct <= i)
                DECONSTRUCTOR[i] = registerMetaTileEntity(id + (i - 1),
                        new SimpleMachineMetaTileEntity(
                                new ResourceLocation(MODID, (String.format("%s.%s", "deconstructor", voltageName))),
                                MRRecipeMaps.DECONSTRUCTION_RECIPES, MRTextures.DECONSTRUCTOR_OVERLAY, i, true,
                                GTUtility.hvCappedTankSizeFunction));
        }

        // Material Replicator
        // Default: 20015 - 20029
        id = id + 15;
        for (int i = 1; i < REPLICATOR.length; i++) {
            String voltageName = GTValues.VN[i].toLowerCase();
            if (!GregTechAPI.isHighTier() && i > GTValues.UV)
                break;
            if (tierReplicate <= i)
                REPLICATOR[i] = registerMetaTileEntity(id + (i - 1),
                        new SimpleMachineMetaTileEntity(
                                new ResourceLocation(MODID, (String.format("%s.%s", "replicator", voltageName))),
                                MRRecipeMaps.REPLICATION_RECIPES, MRTextures.REPLICATOR_OVERLAY, i, true,
                                GTUtility.hvCappedTankSizeFunction));
        }

        // Scrapper
        // Default: 20030 - 20044
        id = id + 15;
        for (int i = 0; i < SCRAPPER.length - 1; i++) {
            String voltageName = GTValues.VN[i + 1].toLowerCase();
            if (!GregTechAPI.isHighTier() && i > GTValues.UV)
                break;
            SCRAPPER[i] = registerMetaTileEntity(id + i,
                    new MetaTileEntityScrapMaker(
                            new ResourceLocation(MODID, (String.format("%s.%s", "scrapmaker", voltageName))),
                            MRRecipeMaps.SCRAPMAKER_RECIPES, MRTextures.SCRAPPER_OVERLAY, i + 1,
                            GTUtility.hvCappedTankSizeFunction));

        }
    }

    public static void registerMultiMachine() {
        int id = baseID + 1000;
        // LargeDeconstructor
        // Default: 21000
        LARGE_DECONSTRUCTOR = registerMetaTileEntity(id,
                new MetaTileEntityLargeDeconstructor(mrId("large_deconstructor")));

        // LargeScrapper
        // Default: 21001
        LARGE_SCRAPPER = registerMetaTileEntity(id + 1,
                new MetaTileEntityLargeScrapper(mrId("large_scrapper")));
    }

    private static @NotNull ResourceLocation mrId(String name) {
        return new ResourceLocation(MODID, name);
    }
}
