package kono.ceu.materialreplication.loaders.recipe;

import gregtech.api.GregTechAPI;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.ingredients.GTRecipeItemInput;
import gregtech.api.recipes.ingredients.nbtmatch.NBTCondition;
import gregtech.api.recipes.ingredients.nbtmatch.NBTMatcher;
import gregtech.api.recipes.ingredients.nbtmatch.NBTTagType;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.PropertyKey;
import kono.ceu.materialreplication.api.recipes.MRRecipeMaps;
import kono.ceu.materialreplication.api.recipes.builders.ReplicatorRecipeBuilder;
import kono.ceu.materialreplication.api.recipes.machines.IReplicatorRecipeMap;
import kono.ceu.materialreplication.api.unification.materials.MRMaterials;
import kono.ceu.materialreplication.api.unification.materials.flags.MRMaterialFlags;
import kono.ceu.materialreplication.common.items.MRMetaItems;

import java.util.ArrayList;
import java.util.List;

import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustTiny;
import static kono.ceu.materialreplication.api.MRValues.*;
import static kono.ceu.materialreplication.common.items.MRMetaItems.SCRAP;
import static kono.ceu.materialreplication.common.items.MRMetaItems.SCRAP_BOX;

public class MRMachineRecipeLoader {
    public static void register() {
        recipeMatter();
        recipeScrap();
    }

    public static void recipeMatter() {
        List<Material> materialDusts = new ArrayList<>();
        List<Material> materialFluids = new ArrayList<>();
       for (Material material : GregTechAPI.MATERIAL_REGISTRY) {
           if (material.hasProperty(PropertyKey.DUST)) { // Has Dust Property? Propertyにdustがあるか
               materialDusts.add(material);
           } else if (material.hasProperty(PropertyKey.FLUID)) { // Has Fluid Property? ないならPropertyにFluidがあるか
               materialFluids.add(material);
           }
        }

        for (Material materialDust : materialDusts) {
            // Has dust property material. dustがあるmaterial
            // Deconstruction
            if (!materialDust.hasFlag(MRMaterialFlags.DISABLE_DECONSTRUCTION)) {
                MRRecipeMaps.DECONSTRUCTION_RECIPES.recipeBuilder()
                        .input(dust, materialDust)
                        .fluidOutputs(MRMaterials.ChargedMatter.getFluid((int) materialDust.getProtons()))
                        .fluidOutputs(MRMaterials.NeutralMatter.getFluid((int) materialDust.getNeutrons()))
                        .chancedOutput(dustTiny, MRMaterials.PrimalMatter, 5,5)
                        .duration(BaseTime_D * (int) materialDust.getMass())
                        .EUt(Voltage_D)
                        .buildAndRegister();
            }

            // Replication
            if (!materialDust.hasFlag(MRMaterialFlags.DISABLE_REPLICATION)) {
                RecipeBuilder<ReplicatorRecipeBuilder> builder = MRRecipeMaps.REPLICATION_RECIPES.recipeBuilder();
                if (materialDust.getProtons() == 0) {
                    builder.fluidInputs(MRMaterials.NeutralMatter.getFluid((int) materialDust.getNeutrons()));
                } else if (materialDust.getNeutrons() == 0) {
                    builder.fluidInputs(MRMaterials.ChargedMatter.getFluid((int) materialDust.getProtons()));
                } else {
                    builder.fluidInputs(MRMaterials.ChargedMatter.getFluid((int) materialDust.getProtons()))
                            .fluidInputs(MRMaterials.NeutralMatter.getFluid((int) materialDust.getNeutrons()));
                    }
                builder.duration(BaseTime_R * (int) materialDust.getMass())
                        .input(GTRecipeItemInput.getOrCreate(MRMetaItems.USB_STICK.getStackForm())
                                .setNBTMatchingCondition(NBTMatcher.RECURSIVE_EQUAL_TO,
                                        NBTCondition.create(NBTTagType.COMPOUND, IReplicatorRecipeMap.REPLICATE_NBT_TAG,
                                                NBTCondition.create(NBTTagType.STRING, IReplicatorRecipeMap.REPLICATE_MATERIAL, materialDust.toString())))
                                .setNonConsumable())
                        .replicate(materialDust)
                        .EUt(Voltage_R)
                        .output(dust, materialDust)
                        .buildAndRegister();
                }
            }

        for (Material materialFluid : materialFluids) {

            //Has Fluid property, not dust. dustはないがFluidはある
            // Deconstruction
            if (!materialFluid.hasFlag(MRMaterialFlags.DISABLE_DECONSTRUCTION)) {
                MRRecipeMaps.DECONSTRUCTION_RECIPES.recipeBuilder()
                        .fluidInputs(materialFluid.getFluid(1000))
                        .fluidOutputs(MRMaterials.ChargedMatter.getFluid((int) materialFluid.getProtons()))
                        .fluidOutputs(MRMaterials.NeutralMatter.getFluid((int) materialFluid.getNeutrons()))
                        .chancedOutput(dustTiny, MRMaterials.PrimalMatter, 5,5)
                        .duration(BaseTime_D * (int) materialFluid.getMass())
                        .EUt(Voltage_D)
                        .buildAndRegister();
            }
            // Replication
            if (!materialFluid.hasFlag(MRMaterialFlags.DISABLE_REPLICATION)) {
                RecipeBuilder <ReplicatorRecipeBuilder> builder = MRRecipeMaps.REPLICATION_RECIPES.recipeBuilder();
                if (materialFluid.getProtons() == 0) {
                    builder.fluidInputs(MRMaterials.NeutralMatter.getFluid((int) materialFluid.getNeutrons()));
                } else if (materialFluid.getNeutrons() == 0) {
                    builder.fluidInputs(MRMaterials.ChargedMatter.getFluid((int) materialFluid.getProtons()));
                } else {
                    builder.fluidInputs(MRMaterials.ChargedMatter.getFluid((int) materialFluid.getProtons()))
                            .fluidInputs(MRMaterials.NeutralMatter.getFluid((int) materialFluid.getNeutrons()));
                }
                builder.input(GTRecipeItemInput.getOrCreate(MRMetaItems.USB_STICK.getStackForm())
                        .setNBTMatchingCondition(NBTMatcher.RECURSIVE_EQUAL_TO,
                                NBTCondition.create(NBTTagType.COMPOUND, IReplicatorRecipeMap.REPLICATE_NBT_TAG,
                                        NBTCondition.create(NBTTagType.STRING, IReplicatorRecipeMap.REPLICATE_MATERIAL, materialFluid.toString())))
                        .setNonConsumable())
                        .fluidOutputs(materialFluid.getFluid(1000))
                        .replicate(materialFluid)
                        .duration(BaseTime_R * (int) materialFluid.getMass())
                        .EUt(Voltage_R)
                        .buildAndRegister();
            }
        }

        // Primal -> Charged & Neutral
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

    public static void recipeScrap() {
        COMPRESSOR_RECIPES.recipeBuilder()
                .input(SCRAP, 9)
                .output(SCRAP_BOX)
                .duration(1200).EUt(VA[HV]).buildAndRegister();

        SIFTER_RECIPES.recipeBuilder()
                .input(SCRAP)
                .chancedOutput(dustTiny, MRMaterials.MatterAmplifier, AmplifierChance, AmplifierChanceBoost)
                .duration(256).EUt(VA[LV]).buildAndRegister();

        SIFTER_RECIPES.recipeBuilder()
                .input(SCRAP_BOX)
                .chancedOutput(dustTiny, MRMaterials.MatterAmplifier, 2, AmplifierChance, AmplifierChanceBoost)
                .chancedOutput(dustTiny, MRMaterials.MatterAmplifier, 2, AmplifierChance, AmplifierChanceBoost)
                .chancedOutput(dustTiny, MRMaterials.MatterAmplifier, 2, AmplifierChance, AmplifierChanceBoost)
                .chancedOutput(dustTiny, MRMaterials.MatterAmplifier, 1, AmplifierChance, AmplifierChanceBoost)
                .chancedOutput(dustTiny, MRMaterials.MatterAmplifier, 1, AmplifierChance, AmplifierChanceBoost)
                .chancedOutput(dustTiny, MRMaterials.MatterAmplifier, 1, AmplifierChance, AmplifierChanceBoost)
                .duration(256).EUt(VA[LV]).buildAndRegister();

        EXTRACTOR_RECIPES.recipeBuilder()
                .input(dust, MRMaterials.MatterAmplifier)
                .fluidOutputs(MRMaterials.MatterAmplifier.getFluid(10))
                .duration(BaseTime_D / 4).EUt(VA[EV]).buildAndRegister();
    }

}
