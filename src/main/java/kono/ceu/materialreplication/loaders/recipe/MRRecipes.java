package kono.ceu.materialreplication.loaders.recipe;

import static kono.ceu.materialreplication.api.util.MRValues.isAntimatter;

public class MRRecipes {

    public static void addRecipe() {
        MRMachineRecipeLoader.register();
        MRMiscRecipeLoader.addMaterialRecipe();
        MRMiscRecipeLoader.miscRecipe();
        MRMTECraftingRecipeLoader.register();
        if (isAntimatter) {
            MRAntimatterLoader.init();
        }
    }

    public static void removeRecipe() {
        MRMiscRecipeLoader.removeMaterialRecipe();
    }
}
