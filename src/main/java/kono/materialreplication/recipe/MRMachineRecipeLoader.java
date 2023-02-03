package kono.materialreplication.recipe;

import gregtech.api.GregTechAPI;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.PropertyKey;
import kono.materialreplication.MRConfig;
import kono.materialreplication.materials.flags.MRMaterialFlags;

import java.util.ArrayList;
import java.util.List;

import static gregtech.api.unification.ore.OrePrefix.*;
import static kono.materialreplication.materials.MRMaterials.*;

public class MRMachineRecipeLoader {

    // Get Deconstruction Base-time from cfg. cfgから分解の基本時間を取得
    private final static int BaseTime_D = MRConfig.deconstruction.DeconstructionBaseTime;

    // Get Deconstruction Base Voltage from cfg. cfgから分解の基本電圧を取得
    private final static int Voltage_D = MRConfig.deconstruction.DeconstructionVoltage;

    // Get Replication Base-time from cfg. cfgから複製の基本時間を取得
    private final static int BaseTime_R = MRConfig.replication.ReplicationBaseTime;

    // Get Replication Base Voltage from cfg. cfgから複製の基本電圧を取得
    private final static int Voltage_R = MRConfig.replication.ReplicationVoltage;



    public static void init() {
        List<Material> materialDusts = new ArrayList<>();
        List<Material> materialFluids = new ArrayList<>();
        for (Material material : GregTechAPI.MATERIAL_REGISTRY) {
            if (!material.getChemicalFormula().isEmpty()) { // Has Chemical Formula? 化学式を持っているか
                if (material.hasProperty(PropertyKey.DUST)) { // Has Dust Property? Propertyにdustがあるか
                    materialDusts.add(material);
                } else if (material.hasProperty(PropertyKey.FLUID)) { // Has Fluid Property? ないならPropertyにFluidがあるか
                    materialFluids.add(material);
                }
            }
        }

        for (Material materialDust : materialDusts) { // Has dust property material. dustがあるmaterial
            // Deconstruction
            if (!materialDust.hasFlag(MRMaterialFlags.DISABLE_DECONSTRUCTION)) {
                MRRecipeMaps.DECONSTRUCTION_RECIPES.recipeBuilder()
                        .input(dust, materialDust)
                        .fluidOutputs(ChargedMatter.getFluid((int) materialDust.getProtons()))
                        .fluidOutputs(NeutralMatter.getFluid((int) materialDust.getNeutrons()))
                        .chancedOutput(dustTiny, PrimalMatter, 5,5)
                        .duration(BaseTime_D * (int) materialDust.getMass())
                        .EUt(Voltage_D)
                        .buildAndRegister();
            }

            // Replication
            if (!materialDust.hasFlag(MRMaterialFlags.DISABLE_REPLICATION)) {
                if (materialDust.getProtons() == 0) {
                    RecipeBuilder<SimpleRecipeBuilder> builder = MRRecipeMaps.REPLICATION_RECIPES.recipeBuilder()
                            .fluidInputs(NeutralMatter.getFluid((int) materialDust.getNeutrons()))
                            .output(dust, materialDust, 2)
                            .duration(BaseTime_R * (int) materialDust.getMass())
                            .EUt(Voltage_R);
                    if (MRConfig.replication.ConsumeSample) {
                        builder.input(dust, materialDust);
                    } else {
                        builder.notConsumable(dust, materialDust);
                    }
                    builder.buildAndRegister();
                } else if (materialDust.getNeutrons() == 0) {
                    RecipeBuilder<SimpleRecipeBuilder> builder = MRRecipeMaps.REPLICATION_RECIPES.recipeBuilder()
                            .fluidInputs(NeutralMatter.getFluid((int) materialDust.getProtons()))
                            .output(dust, materialDust, 2)
                            .duration(BaseTime_R * (int) materialDust.getMass())
                            .EUt(Voltage_R);
                    if (MRConfig.replication.ConsumeSample) {
                        builder.input(dust, materialDust);
                    } else {
                        builder.notConsumable(dust, materialDust);
                    }
                    builder.buildAndRegister();
                } else {
                    RecipeBuilder<SimpleRecipeBuilder> builder = MRRecipeMaps.REPLICATION_RECIPES.recipeBuilder()
                            .fluidInputs(NeutralMatter.getFluid((int) materialDust.getProtons()))
                            .fluidInputs(NeutralMatter.getFluid((int) materialDust.getNeutrons()))
                            .output(dust, materialDust, 2)
                            .duration(BaseTime_R * (int) materialDust.getMass())
                            .EUt(Voltage_R);
                    if (MRConfig.replication.ConsumeSample) {
                        builder.input(dust, materialDust);
                    } else {
                        builder.notConsumable(dust, materialDust);
                    }
                    builder.buildAndRegister();
                }
            }

        }

        for (Material materialFluid : materialFluids) { //Has Fluid property, not dust. dustはないがFluidはある
            // Deconstruction
            if (!materialFluid.hasFlag(MRMaterialFlags.DISABLE_DECONSTRUCTION)) {
                MRRecipeMaps.DECONSTRUCTION_RECIPES.recipeBuilder()
                        .fluidInputs(materialFluid.getFluid(1000))
                        .fluidOutputs(ChargedMatter.getFluid((int) materialFluid.getProtons()))
                        .fluidOutputs(NeutralMatter.getFluid((int) materialFluid.getNeutrons()))
                        .chancedOutput(dustTiny, PrimalMatter, 5,5)
                        .duration(BaseTime_D * (int) materialFluid.getMass())
                        .EUt(Voltage_D)
                        .buildAndRegister();
            }
            // Replication
            if (!materialFluid.hasFlag(MRMaterialFlags.DISABLE_REPLICATION)) {
                if (materialFluid.getProtons() == 0) {
                    RecipeBuilder <SimpleRecipeBuilder> builder = MRRecipeMaps.REPLICATION_RECIPES.recipeBuilder()
                            .fluidInputs(NeutralMatter.getFluid((int) materialFluid.getNeutrons()))
                            .fluidOutputs(materialFluid.getFluid(2000))
                            .duration(BaseTime_R * (int) materialFluid.getMass())
                            .EUt(Voltage_R);
                    if (MRConfig.replication.ConsumeSample) {
                        builder.fluidInputs(materialFluid.getFluid(1000));
                    } else {
                        builder.notConsumable(materialFluid.getFluid(), 1000);
                    }
                    builder.buildAndRegister();
                } else if (materialFluid.getNeutrons() == 0) {
                    RecipeBuilder <SimpleRecipeBuilder> builder = MRRecipeMaps.REPLICATION_RECIPES.recipeBuilder()
                            .fluidInputs(NeutralMatter.getFluid((int) materialFluid.getProtons()))
                            .fluidOutputs(materialFluid.getFluid(2000))
                            .duration(BaseTime_R * (int) materialFluid.getMass())
                            .EUt(Voltage_R);
                    if (MRConfig.replication.ConsumeSample) {
                        builder.fluidInputs(materialFluid.getFluid(1000));
                    } else {
                        builder.notConsumable(materialFluid.getFluid(), 1000);
                    }
                    builder.buildAndRegister();
                } else {
                    RecipeBuilder <SimpleRecipeBuilder> builder = MRRecipeMaps.REPLICATION_RECIPES.recipeBuilder()
                            .fluidInputs(NeutralMatter.getFluid((int) materialFluid.getProtons()))
                            .fluidInputs(NeutralMatter.getFluid((int) materialFluid.getNeutrons()))
                            .fluidOutputs(materialFluid.getFluid(2000))
                            .duration(BaseTime_R * (int) materialFluid.getMass())
                            .EUt(Voltage_R);
                    if (MRConfig.replication.ConsumeSample) {
                        builder.fluidInputs(materialFluid.getFluid(1000));
                    } else {
                        builder.notConsumable(materialFluid.getFluid(), 1000);
                    }
                    builder.buildAndRegister();
                }
            }
        }

    }
}
