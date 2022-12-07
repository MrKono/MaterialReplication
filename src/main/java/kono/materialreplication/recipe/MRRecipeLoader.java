package kono.materialreplication.recipe;

import gregtech.api.GregTechAPI;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.PropertyKey;
import kono.materialreplication.MRConfig;
import kono.materialreplication.materials.flags.MRMaterialFlags;

import java.util.ArrayList;
import java.util.List;

import static gregtech.api.unification.ore.OrePrefix.dust;
import static kono.materialreplication.materials.MRMaterials.*;

public class MRRecipeLoader {

    // cfgから分解の基本時間を取得
    private final static int BaseTime_D = MRConfig.deconstruction.DeconstructionBaseTime;

    // cfgから分解の基本電圧を取得
    private final static int Voltage_D = MRConfig.deconstruction.DeconstructionVoltage;

    // cfgから複製の基本時間を取得
    private final static int BaseTime_R = MRConfig.replication.ReplicationBaseTime;

    // cfgから複製の基本電圧を取得
    private final static int Voltage_R = MRConfig.replication.ReplicationVoltage;



    public static void init() {
        List<Material> materialDusts = new ArrayList<>();
        List<Material> materialFluids = new ArrayList<>();
        for (Material material : GregTechAPI.MATERIAL_REGISTRY) {
            if (!material.getChemicalFormula().isEmpty()) { // 化学式を持っているか
                if (material.hasProperty(PropertyKey.DUST)) { // Propertyにdustがあるか
                    materialDusts.add(material);
                } else if (material.hasProperty(PropertyKey.FLUID)) { // ないならPropertyにFluidがあるか
                    materialFluids.add(material);
                }
            }
        }

        for (Material materialDust : materialDusts) { // dustがあるmaterial
            // Deconstruction
            if (!materialDust.hasFlag(MRMaterialFlags.DISABLE_DECONSTRUCTION)) {
                MRRecipeMaps.DECONSTRUCTION_RECIPES.recipeBuilder()
                        .input(dust, materialDust)
                        .fluidOutputs(ChargedMatter.getFluid((int) materialDust.getProtons()))
                        .fluidOutputs(NeutralMatter.getFluid((int) materialDust.getNeutrons()))
                        .duration(BaseTime_D * (int) materialDust.getMass())
                        .EUt(Voltage_D)
                        .buildAndRegister();
            }

            // Replication
            if (!materialDust.hasFlag(MRMaterialFlags.DISABLE_REPLICATION)) {
                if (MRConfig.replication.ConsumeSample) {
                    MRRecipeMaps.REPLICATION_RECIPES.recipeBuilder()
                            .input(dust, materialDust)
                            .fluidInputs(ChargedMatter.getFluid((int) materialDust.getProtons()))
                            .fluidInputs(NeutralMatter.getFluid((int) materialDust.getNeutrons()))
                            .output(dust, materialDust, 2)
                            .duration(BaseTime_R * (int) materialDust.getMass())
                            .EUt(Voltage_R)
                            .buildAndRegister();
                } else {
                    MRRecipeMaps.REPLICATION_RECIPES.recipeBuilder()
                            .notConsumable(dust, materialDust)
                            .fluidInputs(ChargedMatter.getFluid((int) materialDust.getProtons()))
                            .fluidInputs(NeutralMatter.getFluid((int) materialDust.getNeutrons()))
                            .output(dust, materialDust, 1)
                            .duration(BaseTime_R * (int) materialDust.getMass())
                            .EUt(Voltage_R)
                            .buildAndRegister();
                }
            }
        }

        for (Material materialFluid : materialFluids) { //Fluidしかないmaterial
            // Deconstruction
            if (!materialFluid.hasFlag(MRMaterialFlags.DISABLE_DECONSTRUCTION)) {
                MRRecipeMaps.DECONSTRUCTION_RECIPES.recipeBuilder()
                        .fluidInputs(materialFluid.getFluid(1000))
                        .fluidOutputs(ChargedMatter.getFluid((int) materialFluid.getProtons()))
                        .fluidOutputs(NeutralMatter.getFluid((int) materialFluid.getNeutrons()))
                        .duration(BaseTime_D * (int) materialFluid.getMass())
                        .EUt(Voltage_D)
                        .buildAndRegister();
            }
            // Replication
            if (!materialFluid.hasFlag(MRMaterialFlags.DISABLE_REPLICATION)) {
                if (MRConfig.replication.ConsumeSample) {
                    MRRecipeMaps.REPLICATION_RECIPES.recipeBuilder()
                            .fluidInputs(materialFluid.getFluid(1000))
                            .fluidInputs(ChargedMatter.getFluid((int) materialFluid.getProtons()))
                            .fluidInputs(NeutralMatter.getFluid((int) materialFluid.getNeutrons()))
                            .fluidOutputs(materialFluid.getFluid(2000))
                            .duration(BaseTime_R * (int) materialFluid.getMass())
                            .EUt(Voltage_R)
                            .buildAndRegister();
                } else {
                    MRRecipeMaps.REPLICATION_RECIPES.recipeBuilder()
                            .notConsumable(materialFluid.getFluid(), 1000)
                            .fluidInputs(ChargedMatter.getFluid((int) materialFluid.getProtons()))
                            .fluidInputs(NeutralMatter.getFluid((int) materialFluid.getNeutrons()))
                            .fluidOutputs(materialFluid.getFluid(1000))
                            .duration(BaseTime_R * (int) materialFluid.getMass())
                            .EUt(Voltage_R)
                            .buildAndRegister();
                }
            }
        }

    }
}
