package kono.ceu.materialreplication.loaders.recipe;

public class MRRecipes {
    public static void addRecipe(){
        MRMachineRecipeLoader.register();
        MRMiscRecipeLoader.addMaterialRecipe();
        MRMiscRecipeLoader.miscRecipe();
        MRMTECraftingRecipeLoader.register();
    }

    public static void removeRecipe() {
        MRMiscRecipeLoader.removeMaterialRecipe();
    }
}
