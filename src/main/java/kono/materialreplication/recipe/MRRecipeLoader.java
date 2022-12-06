package kono.materialreplication.recipe;

import gregtech.api.GregTechAPI;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.PropertyKey;
import kono.materialreplication.MRConfig;
import kono.materialreplication.materials.flags.MRMaterialFlags;

import java.util.ArrayList;
import java.util.List;

import static gregtech.api.GTValues.LV;
import static gregtech.api.GTValues.VA;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static kono.materialreplication.materials.MRMaterials.NeutralMatter;
import static kono.materialreplication.materials.MRMaterials.ChargedMatter;

public class MRRecipeLoader {
    private final static int BaseTime_D = MRConfig.deconstruction.DeconstructionBaseTime;
    private final static int BaseTime_R = MRConfig.replication.ReplicationBaseTime;
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

        for (Material materialDust : materialDusts) {
            // Deconstruction
            if (!materialDust.hasFlag(MRMaterialFlags.DISABLE_DECONSTRUCTION)) {
                MRRecipeMaps.DECONSTRUCTION_RECIPES.recipeBuilder()
                        .input(dust, materialDust)
                        .fluidOutputs(ChargedMatter.getFluid((int) materialDust.getProtons()))
                        .fluidOutputs(NeutralMatter.getFluid((int) materialDust.getNeutrons()))
                        .duration(BaseTime_D * (int) materialDust.getMass())
                        .EUt(VA[LV])
                        .buildAndRegister();
            }

            // Replication
            if (!materialDust.hasFlag(MRMaterialFlags.DISABLE_REPLICATION)) {
                MRRecipeMaps.REPLICATION_RECIPES.recipeBuilder()
                        .input(dust, materialDust)
                        .fluidInputs(ChargedMatter.getFluid((int) materialDust.getProtons()))
                        .fluidInputs(NeutralMatter.getFluid((int) materialDust.getNeutrons()))
                        .output(dust, materialDust, 2)
                        .duration(BaseTime_R * (int) materialDust.getMass())
                        .EUt(VA[LV])
                        .buildAndRegister();
            }
        }

        for (Material materialFluid : materialFluids) {
            // Deconstruction
            if (!materialFluid.hasFlag(MRMaterialFlags.DISABLE_DECONSTRUCTION)) {
                MRRecipeMaps.DECONSTRUCTION_RECIPES.recipeBuilder()
                        .fluidInputs(materialFluid.getFluid(1000))
                        .fluidOutputs(ChargedMatter.getFluid((int) materialFluid.getProtons()))
                        .fluidOutputs(NeutralMatter.getFluid((int) materialFluid.getNeutrons()))
                        .duration(BaseTime_D * (int) materialFluid.getMass())
                        .EUt(VA[LV])
                        .buildAndRegister();
            }
            // Replication
            if (!materialFluid.hasFlag(MRMaterialFlags.DISABLE_REPLICATION)) {
                MRRecipeMaps.REPLICATION_RECIPES.recipeBuilder()
                        .fluidInputs(materialFluid.getFluid(1000))
                        .fluidInputs(ChargedMatter.getFluid((int) materialFluid.getProtons()))
                        .fluidInputs(NeutralMatter.getFluid((int) materialFluid.getNeutrons()))
                        .fluidOutputs(materialFluid.getFluid(2000))
                        .duration(BaseTime_R * (int) materialFluid.getMass())
                        .EUt(32)
                        .buildAndRegister();
            }
        }

    }
}
