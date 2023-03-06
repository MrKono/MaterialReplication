package kono.ceu.materialreplication.loaders.recipe;

import kono.ceu.materialreplication.common.machines.MRMetaTileEntities;

import static gregtech.loaders.recipe.CraftingComponent.*;
import static gregtech.loaders.recipe.MetaTileEntityLoader.registerMachineRecipe;

public class MRRecipes {
    public static void init() {

        //New machine recipes. 新規機械のレシピを登録
        registerMachineRecipe(MRMetaTileEntities.DECONSTRUCTOR,
                "CSC", "FHF", "CQC",
                'C',BETTER_CIRCUIT,
                'S', SENSOR,
                'F', FIELD_GENERATOR,
                'H', HULL,
                'Q', CABLE_QUAD);
        registerMachineRecipe(MRMetaTileEntities.REPLICATOR,
                "EFE", "CHC", "SQS",
                'E', EMITTER,
                'F', FIELD_GENERATOR,
                'C', BETTER_CIRCUIT,
                'H', HULL,
                'S', SENSOR,
                'Q', CABLE_QUAD);
    }
}
