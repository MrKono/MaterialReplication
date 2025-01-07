package kono.ceu.materialreplication.loaders.recipe;

import static gregtech.api.GTValues.*;

import gregtech.api.fluids.store.FluidStorageKeys;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;

import gregicality.multiblocks.common.block.GCYMMetaBlocks;
import gregicality.multiblocks.common.block.blocks.BlockLargeMultiblockCasing;

import kono.ceu.materialreplication.common.blocks.BlockAntimatterCasing;
import kono.ceu.materialreplication.common.blocks.MRBlocks;

public class MRCasingRecipeLoader {

    public static void antimatterReactor() {
        RecipeMaps.CANNER_RECIPES.recipeBuilder()
                .fluidInputs(Materials.Helium.getFluid(FluidStorageKeys.LIQUID, 1000))
                .inputs(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.ALUMINIUM_FROSTPROOF,
                        1))
                .outputs(MRBlocks.ANTIMATTER_CASING
                        .getItemVariant(BlockAntimatterCasing.AntimatterCasingType.HELIUM_COOLANT, 1))
                .EUt(VA[IV]).duration(1200).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .fluidInputs(Materials.Neutronium.getFluid(FluidStorageKeys.LIQUID, 36))
                .inputs(GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                        .getItemVariant(BlockLargeMultiblockCasing.CasingType.ATOMIC_CASING, 36))
                .outputs(MRBlocks.ANTIMATTER_CASING
                        .getItemVariant(BlockAntimatterCasing.AntimatterCasingType.ANTIMATTER_REACTOR_CASING, 36))
                .EUt(VA[UV]).duration(1200).buildAndRegister();
    }
}
