package kono.ceu.materialreplication.loaders.recipe;

import gregtech.api.metatileentity.multiblock.CleanroomType;
import gregtech.api.recipes.GTRecipeHandler;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import kono.ceu.materialreplication.api.unification.materials.MRMaterials;
import net.minecraft.item.ItemStack;

import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.Electrum;
import static gregtech.api.unification.material.Materials.Polyethylene;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;
import static kono.ceu.materialreplication.api.MRValues.BaseTime_R;
import static kono.ceu.materialreplication.api.MRValues.Voltage_R;
import static kono.ceu.materialreplication.common.items.MRMetaItems.USB_STICK;

public class MRMiscRecipeLoader {
    public static void init() {
        materialRecipe();
        miscRecipe();
    }

    public static void materialRecipe(){
        //PrimalMatter
        // Remove 削除
        // WorkBench Recipes 作業台レシピ
        ModHandler.removeRecipeByName("gregtech:small_dust_assembling_primal_matter"); //tiny x9 -> dust x1
        ModHandler.removeRecipeByName("gregtech:tiny_dust_assembling_primal_matter"); //small x9 -> dust x1

        // Machine Recipes 機械レシピ
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.PACKER_RECIPES,
                new ItemStack[]{
                        OreDictUnifier.get(dustSmall, MRMaterials.PrimalMatter, 4),
                        IntCircuitIngredient.getIntegratedCircuit(1)
                });

        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.PACKER_RECIPES,
                new ItemStack[]{
                        OreDictUnifier.get(dustTiny, MRMaterials.PrimalMatter, 9),
                        IntCircuitIngredient.getIntegratedCircuit(1)
                });

        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.EXTRACTOR_RECIPES,
                new ItemStack[]{
                        OreDictUnifier.get(dust, MRMaterials.PrimalMatter)
                });

        // Add 追加
        // Machine Recipes 機械レシピ
        EXTRACTOR_RECIPES.recipeBuilder()
                .input(dustTiny, MRMaterials.PrimalMatter)
                .fluidOutputs(MRMaterials.PrimalMatter.getFluid(1))
                .duration(1200).EUt(VA[LV]).buildAndRegister();

        EXTRACTOR_RECIPES.recipeBuilder()
                .input(dust, MRMaterials.PrimalMatter)
                .fluidOutputs(MRMaterials.PrimalMatter.getFluid(10))
                .duration(1200).EUt(VA[EV]).buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder()
                .input(dustTiny, MRMaterials.PrimalMatter, 9)
                .output(dust, MRMaterials.PrimalMatter)
                .duration(1200).EUt(VA[HV]).buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(MRMaterials.PrimalMatter.getFluid(1))
                .fluidOutputs(MRMaterials.ChargedMatter.getFluid(1))
                .fluidOutputs(MRMaterials.NeutralMatter.getFluid(1))
                .duration(12000).EUt(16).buildAndRegister();

        // MatterAmplifier
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(MRMaterials.MatterAmplifier.getFluid(500))
                .fluidInputs(MRMaterials.ChargedMatter.getFluid(500))
                .fluidOutputs(MRMaterials.ChargedMatter.getFluid(1000))
                .duration(BaseTime_R).EUt(Voltage_R).buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(MRMaterials.MatterAmplifier.getFluid(500))
                .fluidInputs(MRMaterials.NeutralMatter.getFluid(500))
                .fluidOutputs(MRMaterials.NeutralMatter.getFluid(1000))
                .duration(BaseTime_R).EUt(Voltage_R).buildAndRegister();
    }

    public static void miscRecipe() {
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(PLASTIC_CIRCUIT_BOARD)
                .input(CENTRAL_PROCESSING_UNIT, 2)
                .input(NAND_MEMORY_CHIP, 32)
                .input(RANDOM_ACCESS_MEMORY, 4)
                .input(wireFine, Electrum, 16)
                .input(plate, Polyethylene, 4)
                .output(USB_STICK)
                .solderMultiplier(2)
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(400).EUt(90).buildAndRegister();

        ModHandler.addShapelessNBTClearingRecipe("usb_nbt_clearing", USB_STICK.getStackForm(), USB_STICK.getStackForm());
    }
}
