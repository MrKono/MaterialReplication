package kono.ceu.materialreplication.common.machines;

import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.util.GTUtility;
import kono.ceu.materialreplication.api.recipes.MRRecipeMaps;
import kono.ceu.materialreplication.client.MRTextures;
import kono.ceu.materialreplication.common.machines.multi.MetaTileEntityLargeDeconstructor;
import kono.ceu.materialreplication.common.machines.single.MetaTileEntityScrapMaker;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;
import static gregtech.common.metatileentities.MetaTileEntities.registerSimpleMetaTileEntity;
import static kono.ceu.materialreplication.api.util.MRValues.MODID;

public class MRMetaTileEntities {

    // Single : 20000 - 200044
    public static SimpleMachineMetaTileEntity[] DECONSTRUCTOR = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static SimpleMachineMetaTileEntity[] REPLICATOR = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static MetaTileEntityScrapMaker[] SCRAPPER = new MetaTileEntityScrapMaker[GTValues.V.length - 1];

    //Multi : 21000
    public static MetaTileEntityLargeDeconstructor LARGE_DECONSTRUCTOR;

    public static void init() {
        registerSingleMachine();
        registerMultiMachine();
    }

    public static void registerSingleMachine() {
        //20000 - 20014 : Material Deconstructor
        registerSimpleMetaTileEntity(DECONSTRUCTOR, 20000, "deconstructor", MRRecipeMaps.DECONSTRUCTION_RECIPES,
                MRTextures.DECONSTRUCTOR_OVERLAY,true, MRMetaTileEntities::mrId, GTUtility.hvCappedTankSizeFunction);

        //20015 - 20029 : Material Replicator
        registerSimpleMetaTileEntity(REPLICATOR, 20015, "replicator", MRRecipeMaps.REPLICATION_RECIPES,
                MRTextures.REPLICATOR_OVERLAY,true, MRMetaTileEntities::mrId, GTUtility.hvCappedTankSizeFunction);

        //20030 - 20044 Scrapper
        for (int i = 0; i < SCRAPPER.length - 1 ; i++ ) {
            String voltageName = GTValues.VN[i + 1].toLowerCase();
            if ( i <= 7) {
                SCRAPPER[i] = registerMetaTileEntity(20030 + i,  new MetaTileEntityScrapMaker(new ResourceLocation(MODID, (String.format("%s.%s", "scrapmaker", voltageName))),
                        MRRecipeMaps.SCRAPMAKER_RECIPES, MRTextures.SCRAPPER_OVERLAY, i + 1, GTUtility.hvCappedTankSizeFunction));
            }
            else {
                if (GregTechAPI.isHighTier()) {
                    SCRAPPER[i] = registerMetaTileEntity(20030 + i,  new MetaTileEntityScrapMaker(new ResourceLocation(MODID, (String.format("%s.%s", "scrapmaker", voltageName))),
                            MRRecipeMaps.SCRAPMAKER_RECIPES, MRTextures.SCRAPPER_OVERLAY, i + 1, GTUtility.hvCappedTankSizeFunction));
                }
            }
        }

    }

    public static void registerMultiMachine() {
        //LargeDeconstructor 21000
        LARGE_DECONSTRUCTOR = registerMetaTileEntity(21000,
                new MetaTileEntityLargeDeconstructor(mrId("large_deconstructor")));
    }

    private static @NotNull ResourceLocation mrId(String name) {
        return new ResourceLocation(MODID, name);
    }
}
