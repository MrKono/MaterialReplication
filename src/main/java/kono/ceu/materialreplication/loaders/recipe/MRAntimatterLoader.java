package kono.ceu.materialreplication.loaders.recipe;


import gregtech.api.fluids.store.FluidStorageKeys;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;
import kono.ceu.materialreplication.api.recipes.MRRecipeMaps;
import kono.ceu.materialreplication.api.unification.material.materials.MRMaterials;

import static gregtech.api.GTValues.*;

public class MRAntimatterLoader {

    public static void init() {
        antimatterRecipe();
        fuelRecipe();
    }

    public static void antimatterRecipe() {
        RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                .fluidInputs(MRMaterials.AntiChargedMatter.getFluid(FluidStorageKeys.PLASMA, 1000))
                .fluidInputs(Materials.Helium.getFluid(FluidStorageKeys.LIQUID, 1000))
                .fluidOutputs(MRMaterials.AntiChargedMatter.getFluid(FluidStorageKeys.LIQUID, 100))
                .EUt(VA[LuV]).duration(1200).buildAndRegister();

        RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                .fluidInputs(MRMaterials.AntiNeutralMatter.getFluid(FluidStorageKeys.PLASMA, 1000))
                .fluidInputs(Materials.Helium.getFluid(FluidStorageKeys.LIQUID, 1000))
                .fluidOutputs(MRMaterials.AntiNeutralMatter.getFluid(FluidStorageKeys.LIQUID, 100))
                .EUt(VA[LuV]).duration(1200).buildAndRegister();

        MRRecipeMaps.ANTIMATTER_PROCESSOR.recipeBuilder()
                .fluidInputs(MRMaterials.AntiChargedMatter.getFluid(FluidStorageKeys.LIQUID, 100))
                .fluidInputs(MRMaterials.AntiNeutralMatter.getFluid(FluidStorageKeys.LIQUID, 100))
                .circuitMeta(1)
                .fluidOutputs(MRMaterials.AntiMatter.getFluid(100))
                .duration(20)
                .EUt(VA[ZPM])
                .buildAndRegister();
    }

    public static void fuelRecipe() {
        MRRecipeMaps.PAIR_ANNIHILATION_FUELS.recipeBuilder()
                .fluidInputs(MRMaterials.AntiChargedMatter.getFluid(FluidStorageKeys.LIQUID, 1))
                .fluidInputs(MRMaterials.ChargedMatter.getFluid(1))
                .EUt((int) V[UHV]).duration(20).buildAndRegister();

        MRRecipeMaps.PAIR_ANNIHILATION_FUELS.recipeBuilder()
                .fluidInputs(MRMaterials.AntiNeutralMatter.getFluid(FluidStorageKeys.LIQUID, 1))
                .fluidInputs(MRMaterials.NeutralMatter.getFluid(1))
                .EUt((int) V[UHV]).duration(20).buildAndRegister();

        MRRecipeMaps.PAIR_ANNIHILATION_FUELS.recipeBuilder()
                .fluidInputs(MRMaterials.AntiMatter.getFluid(1))
                .fluidInputs(Materials.UUMatter.getFluid(1))
                .EUt((int) V[UEV]).duration(20).buildAndRegister();
    }
}
