package kono.materialreplication.recipe;

import gregtech.api.recipes.GTRecipeHandler;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import net.minecraft.item.ItemStack;

import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static kono.materialreplication.materials.MRMaterials.*;

public class MRMaterialRecipeLoader {
    public static void init() {
        // Remove 削除
        // WorkBench Recipes 作業台レシピ
        ModHandler.removeRecipeByName("gregtech:small_dust_assembling_primal_matter"); //tiny x9 -> dust x1
        ModHandler.removeRecipeByName("gregtech:tiny_dust_assembling_primal_matter"); //small x9 -> dust x1

        // Machine Recipes 機械レシピ
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.PACKER_RECIPES,
                new ItemStack[]{
                        OreDictUnifier.get(dustSmall,PrimalMatter, 4),
                        IntCircuitIngredient.getIntegratedCircuit(1)
                });

        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.PACKER_RECIPES,
                new ItemStack[]{
                        OreDictUnifier.get(dustTiny, PrimalMatter, 9),
                        IntCircuitIngredient.getIntegratedCircuit(1)
                });

        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.EXTRACTOR_RECIPES,
                new ItemStack[]{
                        OreDictUnifier.get(dust, PrimalMatter)
                });

        // Add 追加
        // Machine Recipes 機械レシピ
        EXTRACTOR_RECIPES.recipeBuilder()
                .input(dustTiny, PrimalMatter)
                .fluidOutputs(PrimalMatter.getFluid(1))
                .duration(1200)
                .EUt(VA[LV])
                .buildAndRegister();

        EXTRACTOR_RECIPES.recipeBuilder()
                .input(dust, PrimalMatter)
                .fluidOutputs(PrimalMatter.getFluid(10))
                .duration(1200)
                .EUt(VA[EV])
                .buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder()
                .input(dustTiny, PrimalMatter, 9)
                .output(dust, PrimalMatter)
                .duration(1200)
                .EUt(VA[HV])
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(PrimalMatter.getFluid(1))
                .fluidOutputs(ChargedMatter.getFluid(1))
                .fluidOutputs(NeutralMatter.getFluid(1))
                .duration(12000)
                .EUt(16)
                .buildAndRegister();
    }
}
