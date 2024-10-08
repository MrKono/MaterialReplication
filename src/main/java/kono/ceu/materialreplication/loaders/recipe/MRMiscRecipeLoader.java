package kono.ceu.materialreplication.loaders.recipe;

import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;
import static kono.ceu.materialreplication.api.unification.materials.MRMaterials.MatterAmplifier;
import static kono.ceu.materialreplication.api.unification.materials.MRMaterials.PrimalMatter;
import static kono.ceu.materialreplication.api.util.MRValues.*;
import static kono.ceu.materialreplication.common.items.MRMetaItems.USB_STICK;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import gregtech.api.metatileentity.multiblock.CleanroomType;
import gregtech.api.recipes.GTRecipeHandler;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;

import kono.ceu.materialreplication.MRConfig;
import kono.ceu.materialreplication.api.unification.materials.MRMaterials;
import kono.ceu.materialreplication.api.util.Mods;

public class MRMiscRecipeLoader {

    public static void removeMaterialRecipe() {
        // PrimalMatter
        ModHandler.removeRecipeByName("gregtech:small_dust_assembling_primal_matter"); // tiny x9 -> dust x1
        ModHandler.removeRecipeByName("gregtech:tiny_dust_assembling_primal_matter"); // small x4 -> dust x1

        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.PACKER_RECIPES,
                OreDictUnifier.get(dustTiny, MatterAmplifier, 9),
                IntCircuitIngredient.getIntegratedCircuit(1));

        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.PACKER_RECIPES,
                OreDictUnifier.get(dustTiny, PrimalMatter, 9),
                IntCircuitIngredient.getIntegratedCircuit(1));

        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.EXTRACTOR_RECIPES,
                OreDictUnifier.get(dust, PrimalMatter));
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.EXTRACTOR_RECIPES,
                OreDictUnifier.get(dust, MatterAmplifier));
    }

    public static void addMaterialRecipe() {
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

        // UUMatter
        if (MRConfig.recipe.addUUMatterRecipe || Mods.Forestry.isModLoaded()) {
            RecipeBuilder<SimpleRecipeBuilder> builder = MIXER_RECIPES.recipeBuilder().duration(1200).EUt(VA[HV]);
            if (ChargedMatterAmount > 0) {
                builder.fluidInputs(MRMaterials.ChargedMatter.getFluid(ChargedMatterAmount));
            }
            if (NeutralMatterAmount > 0) {
                builder.fluidInputs(MRMaterials.NeutralMatter.getFluid(NeutralMatterAmount));
            }

            builder.fluidOutputs(UUMatter.getFluid(UUMatterAmount));

            if (MRConfig.recipe.CleanroomType.equals("CLEANROOM")) {
                builder.cleanroom(CleanroomType.CLEANROOM);
            } else if (MRConfig.recipe.CleanroomType.equals("STERILE")) {
                builder.cleanroom(CleanroomType.STERILE_CLEANROOM);
            }
            builder.buildAndRegister();

            // Will be removed if implemented by CEu.
            RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                    .input(OrePrefix.dust, Materials.NetherStar)
                    .fluidInputs(Materials.UUMatter.getFluid(576))
                    .chancedOutput(new ItemStack(Items.NETHER_STAR), 3333, 3333)
                    .duration(72000).EUt(VA[HV]).buildAndRegister();
        }
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

        ModHandler.addShapelessNBTClearingRecipe("usb_nbt_clearing", USB_STICK.getStackForm(),
                USB_STICK.getStackForm());
    }
}
