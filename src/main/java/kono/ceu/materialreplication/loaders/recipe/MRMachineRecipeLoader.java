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

import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustTiny;
import static kono.ceu.materialreplication.api.MRValues.*;

public class MRMachineRecipeLoader {

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

        for (Material materialFluid : materialFluids) { //Has Fluid property, not dust. dustはないがFluidはある
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
    }

}
