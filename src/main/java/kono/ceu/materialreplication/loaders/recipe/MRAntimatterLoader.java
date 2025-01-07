package kono.ceu.materialreplication.loaders.recipe;

import static gregtech.api.GTValues.*;

import gregtech.api.fluids.store.FluidStorageKeys;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;

import kono.ceu.materialreplication.api.recipes.MRRecipeMaps;
import kono.ceu.materialreplication.api.unification.material.materials.MRMaterials;

public class MRAntimatterLoader {

    public static void init() {
        antimatterRecipe();
        fuelRecipe();
        MRCasingRecipeLoader.antimatterReactor();
    }

    public static void antimatterRecipe() {
        // Antimatter cooling
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

        // Each matter -> each plasma
        MRRecipeMaps.ANTIMATTER_PROCESSOR.recipeBuilder()
                .fluidInputs(MRMaterials.ChargedMatter.getFluid(FluidStorageKeys.LIQUID, 1000))
                .circuitMeta(1)
                .fluidOutputs(MRMaterials.ChargedMatter.getFluid(FluidStorageKeys.PLASMA, 1000))
                .EUt(VA[ZPM])
                .duration(60).buildAndRegister();

        MRRecipeMaps.ANTIMATTER_PROCESSOR.recipeBuilder()
                .fluidInputs(MRMaterials.NeutralMatter.getFluid(FluidStorageKeys.LIQUID, 1000))
                .circuitMeta(1)
                .fluidOutputs(MRMaterials.NeutralMatter.getFluid(FluidStorageKeys.PLASMA, 1000))
                .EUt(VA[ZPM])
                .duration(60).buildAndRegister();

        // Each matter + each plasma matter -> Antimatter
        MRRecipeMaps.ANTIMATTER_PROCESSOR.recipeBuilder()
                .fluidInputs(MRMaterials.NeutralMatter.getFluid(FluidStorageKeys.LIQUID, 100))
                .fluidInputs(MRMaterials.NeutralMatter.getFluid(FluidStorageKeys.PLASMA, 100))
                .circuitMeta(2)
                .fluidOutputs(MRMaterials.AntiNeutralMatter.getFluid(FluidStorageKeys.PLASMA, 100))
                .EUt(VA[UV])
                .duration(60).buildAndRegister();

        MRRecipeMaps.ANTIMATTER_PROCESSOR.recipeBuilder()
                .fluidInputs(MRMaterials.ChargedMatter.getFluid(FluidStorageKeys.LIQUID, 100))
                .fluidInputs(MRMaterials.ChargedMatter.getFluid(FluidStorageKeys.PLASMA, 100))
                .circuitMeta(2)
                .fluidOutputs(MRMaterials.AntiChargedMatter.getFluid(FluidStorageKeys.PLASMA, 100))
                .EUt(VA[UV])
                .duration(60).buildAndRegister();

        // Shortcut recipe for each antimatter
        MRRecipeMaps.ANTIMATTER_PROCESSOR.recipeBuilder()
                .fluidInputs(MRMaterials.NeutralMatter.getFluid(FluidStorageKeys.LIQUID, 1000))
                .circuitMeta(3)
                .fluidOutputs(MRMaterials.AntiNeutralMatter.getFluid(FluidStorageKeys.LIQUID, 1000))
                .EUt(VA[UHV])
                .duration(60).buildAndRegister();

        MRRecipeMaps.ANTIMATTER_PROCESSOR.recipeBuilder()
                .fluidInputs(MRMaterials.ChargedMatter.getFluid(FluidStorageKeys.LIQUID, 1000))
                .circuitMeta(3)
                .fluidOutputs(MRMaterials.AntiChargedMatter.getFluid(FluidStorageKeys.LIQUID, 1000))
                .EUt(VA[UHV])
                .duration(60).buildAndRegister();

        // Antimatter
        MRRecipeMaps.ANTIMATTER_PROCESSOR.recipeBuilder()
                .fluidInputs(MRMaterials.AntiChargedMatter.getFluid(FluidStorageKeys.LIQUID, 100))
                .fluidInputs(MRMaterials.AntiNeutralMatter.getFluid(FluidStorageKeys.LIQUID, 100))
                .circuitMeta(1)
                .fluidOutputs(MRMaterials.AntiMatter.getFluid(100))
                .duration(20)
                .EUt(VA[UV])
                .buildAndRegister();
    }

    public static void fuelRecipe() {
        MRRecipeMaps.PAIR_ANNIHILATION_FUELS.recipeBuilder()
                .fluidInputs(MRMaterials.AntiChargedMatter.getFluid(FluidStorageKeys.LIQUID, 1))
                .fluidInputs(MRMaterials.ChargedMatter.getFluid(1))
                .EUt((int) V[UHV]).duration(128).buildAndRegister();

        MRRecipeMaps.PAIR_ANNIHILATION_FUELS.recipeBuilder()
                .fluidInputs(MRMaterials.AntiNeutralMatter.getFluid(FluidStorageKeys.LIQUID, 1))
                .fluidInputs(MRMaterials.NeutralMatter.getFluid(1))
                .EUt((int) V[UHV]).duration(128).buildAndRegister();

        MRRecipeMaps.PAIR_ANNIHILATION_FUELS.recipeBuilder()
                .fluidInputs(MRMaterials.AntiMatter.getFluid(1))
                .fluidInputs(Materials.UUMatter.getFluid(1))
                .EUt((int) V[UHV]).duration(512).buildAndRegister();
    }
}
