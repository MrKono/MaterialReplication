package kono.ceu.materialreplication.loaders.recipe;

import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import kono.ceu.materialreplication.common.machines.MRMetaTileEntities;

import static gregtech.loaders.recipe.CraftingComponent.*;
import static gregtech.loaders.recipe.MetaTileEntityLoader.registerMachineRecipe;

public class MRMTECraftingRecipeLoader {
    public static void register() {
        //Deconstructor
        registerMachineRecipe(MRMetaTileEntities.DECONSTRUCTOR,
                "CSC", "FHF", "CQC",
                'C',BETTER_CIRCUIT,
                'S', SENSOR,
                'F', FIELD_GENERATOR,
                'H', HULL,
                'Q', CABLE_QUAD);

        //Replicator
        registerMachineRecipe(MRMetaTileEntities.REPLICATOR,
                "EFE", "CHC", "SQS",
                'E', EMITTER,
                'F', FIELD_GENERATOR,
                'C', BETTER_CIRCUIT,
                'H', HULL,
                'S', SENSOR,
                'Q', CABLE_QUAD);

        //ScrapMaker
        registerMachineRecipe(MRMetaTileEntities.SCRAPPER,
                    "GCG", "PHP", "WCW",
                    'G', new UnificationEntry(OrePrefix.dust, Materials.Glowstone),
                    'C', CIRCUIT,
                    'P', PISTON,
                    'W', CABLE,
                    'H', HULL);
    }
}
