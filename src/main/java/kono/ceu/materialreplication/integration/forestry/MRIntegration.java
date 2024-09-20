package kono.ceu.materialreplication.integration.forestry;

import gregtech.api.util.Mods;

public class MRIntegration {

    public static void registerRecipeLowest() {
        if (Mods.ForestryApiculture.isModLoaded()) {
            ForestryIntegration.registerRecipeLowest();
        }
    }
}
