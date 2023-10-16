package kono.ceu.materialreplication.common.machines;

import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.util.GTUtility;
import kono.ceu.materialreplication.api.recipes.MRRecipeMaps;
import kono.ceu.materialreplication.api.util.MRValues;
import kono.ceu.materialreplication.client.MRTextures;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;
import static gregtech.common.metatileentities.MetaTileEntities.registerSimpleMetaTileEntity;

public class MRMetaTileEntities {
    public static SimpleMachineMetaTileEntity[] DECONSTRUCTOR = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static SimpleMachineMetaTileEntity[] REPLICATOR = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    //20030 - 20044
    public static MetaTileEntityScrapMaker[] SCRAPPER = new MetaTileEntityScrapMaker[GTValues.V.length - 1];

    public static void init() {
        //20000 - 20014 : Material Deconstructor
        registerSimpleMetaTileEntity(DECONSTRUCTOR, 20000, "deconstructor", MRRecipeMaps.DECONSTRUCTION_RECIPES,
                MRTextures.DECONSTRUCTOR_OVERLAY,true, MRMetaTileEntities::MR, GTUtility.hvCappedTankSizeFunction);

        //20015 - 20029 : Material Replicator
        registerSimpleMetaTileEntity(REPLICATOR, 20015, "replicator", MRRecipeMaps.REPLICATION_RECIPES,
                MRTextures.REPLICATOR_OVERLAY,true, MRMetaTileEntities::MR, GTUtility.hvCappedTankSizeFunction);

        for (int i = 0; i < SCRAPPER.length - 1 ; i++ ) {
            String voltageName = GTValues.VN[i + 1].toLowerCase();
            if ( i <= 7) {
                SCRAPPER[i] = registerMetaTileEntity(20030 + i,  new MetaTileEntityScrapMaker(new ResourceLocation(MRValues.MODID, (String.format("%s.%s", "scrapmaker", voltageName))),
                        MRRecipeMaps.SCRAPMAKER_RECIPES, MRTextures.SCRAPPER_OVERLAY, i + 1, GTUtility.hvCappedTankSizeFunction));
            }
            else {
                if (GregTechAPI.isHighTier()) {
                    SCRAPPER[i] = registerMetaTileEntity(20030 + i,  new MetaTileEntityScrapMaker(new ResourceLocation(MRValues.MODID, (String.format("%s.%s", "scrapmaker", voltageName))),
                            MRRecipeMaps.SCRAPMAKER_RECIPES, MRTextures.SCRAPPER_OVERLAY, i + 1, GTUtility.hvCappedTankSizeFunction));
                }
            }
        }

    }

    private static @Nonnull ResourceLocation MR(String name) {
        return new ResourceLocation("materialreplication", name);
    }
}
