package kono.ceu.materialreplication.loaders.recipe;

import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import kono.ceu.materialreplication.api.util.recipeHelper;
import kono.ceu.materialreplication.common.machines.MRMetaTileEntities;

import static gregtech.loaders.recipe.CraftingComponent.*;
import static gregtech.loaders.recipe.MetaTileEntityLoader.registerMachineRecipe;
import static kono.ceu.materialreplication.api.util.MRValues.tierLargeDeconstruct;

public class MRMTECraftingRecipeLoader {
    public static void register() {
        //Deconstructor
        registerMachineRecipe(MRMetaTileEntities.DECONSTRUCTOR,
                "CSC", "FHF", "CQC",
                'C', BETTER_CIRCUIT,
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

        //LargeDeconstructor
        ModHandler.addShapedRecipe(true, "large_deconstructor", MRMetaTileEntities.LARGE_DECONSTRUCTOR.getStackForm(),
                "FCF", "EDE", "FQF",
                'C', recipeHelper.oreDictCircuit(tierLargeDeconstruct + 1),
                'D', MRMetaTileEntities.DECONSTRUCTOR[tierLargeDeconstruct].getStackForm(),
                'F', recipeHelper.FIELD_GENERATOR(tierLargeDeconstruct),
                'E', recipeHelper.EMITTER(tierLargeDeconstruct),
                'Q', new UnificationEntry(OrePrefix.cableGtQuadruple,
                        switch (tierLargeDeconstruct) {
                    case 6 -> Materials.VanadiumGallium;
                    case 7 -> Materials.YttriumBariumCuprate;
                    case 8 -> Materials.Europium;
                            default -> throw new IllegalStateException("Unexpected value: " + tierLargeDeconstruct);
                        }
                ));
    }
}
