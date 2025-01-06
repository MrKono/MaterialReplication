package kono.ceu.materialreplication.loaders.recipe;

import static gregtech.api.GTValues.*;

import gregtech.api.fluids.store.FluidStorageKeys;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;

import kono.ceu.materialreplication.common.blocks.BlockAntimatterCasing;
import kono.ceu.materialreplication.common.blocks.MRBlocks;

public class MRCasingRecipeLoader {

    public static void init() {
        RecipeMaps.CANNER_RECIPES.recipeBuilder()
                .fluidInputs(Materials.Helium.getFluid(FluidStorageKeys.LIQUID, 1000))
                .inputs(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.ALUMINIUM_FROSTPROOF,
                        1))
                .outputs(MRBlocks.ANTIMATTER_CASING
                        .getItemVariant(BlockAntimatterCasing.AntimatterCasingType.HELIUM_COOLANT, 1))
                .EUt(VA[IV]).duration(1200).buildAndRegister();
    }
}
