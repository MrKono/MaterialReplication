package kono.ceu.materialreplication.common.machines;

import gregtech.api.GTValues;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.util.GTUtility;
import gregtech.common.metatileentities.MetaTileEntities;
import kono.ceu.materialreplication.client.MRTextures;
import kono.ceu.materialreplication.api.recipes.MRRecipeMaps;
import net.minecraft.util.ResourceLocation;
public class MRMetaTileEntities {
    //20000 - 20014 : Material deconstructor
    public static SimpleMachineMetaTileEntity[] DECONSTRUCTOR = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    //20015 - 20029 : Material Replicator
    public static SimpleMachineMetaTileEntity[] REPLICATOR = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];

    public static void init() {
        MetaTileEntities.registerSimpleMetaTileEntity(DECONSTRUCTOR, 20000, "deconstructor", MRRecipeMaps.DECONSTRUCTION_RECIPES, MRTextures.DECONSTRUCTOR_OVERRAY,true, MRMetaTileEntities::MR,GTUtility.hvCappedTankSizeFunction);
        MetaTileEntities.registerSimpleMetaTileEntity(REPLICATOR, 20015, "replicator", MRRecipeMaps.REPLICATION_RECIPES, MRTextures.REPLICATOR_OVERRAY,true, MRMetaTileEntities::MR,GTUtility.hvCappedTankSizeFunction);
    }
    private static ResourceLocation MR(String name) {
        return new ResourceLocation("materialreplication", name);
    }
}
