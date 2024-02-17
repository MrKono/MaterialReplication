package kono.ceu.materialreplication.loaders.recipe;

import gregicality.multiblocks.api.fluids.GCYMFluidStorageKeys;
import gregtech.api.GregTechAPI;
import gregtech.api.fluids.store.FluidStorageKeys;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
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
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustTiny;
import static kono.ceu.materialreplication.api.util.MRValues.*;
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
       for (Material material : GregTechAPI.materialManager.getRegisteredMaterials()) {
           if (material.hasProperty(PropertyKey.DUST)) { // Has Dust Property? Propertyにdustがあるか
               materialDusts.add(material);
           } else if (material.hasProperty(PropertyKey.FLUID) && material.getFluid() != null) { // Has Fluid Property? ないならPropertyにFluidがあるか
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

                // check the amount of Protons and Neutrons
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
            // Deconstruction
            if (!materialFluid.hasFlag(MRMaterialFlags.DISABLE_DECONSTRUCTION)) {
                RecipeBuilder<SimpleRecipeBuilder> builder = MRRecipeMaps.DECONSTRUCTION_RECIPES.recipeBuilder();
                // check Molten
                if (hasOnlyMolten(materialFluid)) {
                        builder.fluidInputs(new FluidStack(materialFluid.getFluid(GCYMFluidStorageKeys.MOLTEN), 1000));
                    } else {
                        builder.fluidInputs(materialFluid.getFluid(1000));
                    }
                builder.fluidOutputs(MRMaterials.ChargedMatter.getFluid((int) materialFluid.getProtons()))
                        .fluidOutputs(MRMaterials.NeutralMatter.getFluid((int) materialFluid.getNeutrons()))
                        .chancedOutput(dustTiny, MRMaterials.PrimalMatter, 5,5)
                        .duration(BaseTime_D * (int) materialFluid.getMass())
                        .EUt(Voltage_D)
                        .buildAndRegister();
            }

            // Replication
            if (!materialFluid.hasFlag(MRMaterialFlags.DISABLE_REPLICATION)) {
                RecipeBuilder <ReplicatorRecipeBuilder> builder = MRRecipeMaps.REPLICATION_RECIPES.recipeBuilder();

                // check the amount of Protons and Neutrons
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
                        .replicate(materialFluid)
                        .duration(BaseTime_R * (int) materialFluid.getMass())
                        .EUt(Voltage_R);

                // check Molten
                if (hasOnlyMolten(materialFluid)) {
                    builder.fluidOutputs(new FluidStack(materialFluid.getFluid(GCYMFluidStorageKeys.MOLTEN), 1000));
                } else {
                    builder.fluidOutputs(materialFluid.getFluid(1000));
                }
                builder.buildAndRegister();
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

        CENTRIFUGE_RECIPES.recipeBuilder()
                .input(SCRAP)
                .chancedFluidOutput(MRMaterials.MatterAmplifier.getFluid(1), AmplifierChance, AmplifierChanceBoost)
                .duration(256).EUt(VA[LV]).buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .input(SCRAP_BOX)
                .chancedFluidOutput(MRMaterials.MatterAmplifier.getFluid(2), AmplifierChance, AmplifierChanceBoost)
                .chancedFluidOutput(MRMaterials.MatterAmplifier.getFluid(2), AmplifierChance, AmplifierChanceBoost)
                .chancedFluidOutput(MRMaterials.MatterAmplifier.getFluid(2), AmplifierChance, AmplifierChanceBoost)
                .chancedFluidOutput(MRMaterials.MatterAmplifier.getFluid(1), AmplifierChance, AmplifierChanceBoost)
                .chancedFluidOutput(MRMaterials.MatterAmplifier.getFluid(1), AmplifierChance, AmplifierChanceBoost)
                .chancedFluidOutput(MRMaterials.MatterAmplifier.getFluid(1), AmplifierChance, AmplifierChanceBoost)
                .duration(256).EUt(VA[LV]).buildAndRegister();

        // Will be removed 1.3.0
        EXTRACTOR_RECIPES.recipeBuilder()
                .input(dust, MRMaterials.MatterAmplifier)
                .fluidOutputs(MRMaterials.MatterAmplifier.getFluid(10))
                .duration(BaseTime_D / 4).EUt(VA[EV]).buildAndRegister();

        EXTRACTOR_RECIPES.recipeBuilder()
                .input(dustTiny, MRMaterials.MatterAmplifier)
                .fluidOutputs(MRMaterials.MatterAmplifier.getFluid(1))
                .duration(BaseTime_D / 4).EUt(VA[MV]).buildAndRegister();
    }

    // check whether fluid material has only Molten
    public static boolean hasOnlyMolten(Material material) {
        Fluid gas = material.getFluid(FluidStorageKeys.GAS);
        Fluid liquid = material.getFluid(FluidStorageKeys.LIQUID);
        Fluid plasma = material.getFluid(FluidStorageKeys.PLASMA);
        Fluid molten = material.getFluid(GCYMFluidStorageKeys.MOLTEN);
        return molten != null && gas == null && liquid == null && plasma == null;
    }

}
