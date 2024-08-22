package kono.ceu.materialreplication.loaders.recipe;

import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustTiny;
import static kono.ceu.materialreplication.api.util.MRValues.*;
import static kono.ceu.materialreplication.common.items.MRMetaItems.SCRAP;
import static kono.ceu.materialreplication.common.items.MRMetaItems.SCRAP_BOX;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import org.jetbrains.annotations.NotNull;

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

import gregicality.multiblocks.api.fluids.GCYMFluidStorageKeys;

import kono.ceu.materialreplication.MRConfig;
import kono.ceu.materialreplication.api.recipes.MRRecipeMaps;
import kono.ceu.materialreplication.api.recipes.builders.ReplicatorRecipeBuilder;
import kono.ceu.materialreplication.api.recipes.machines.IReplicatorRecipeMap;
import kono.ceu.materialreplication.api.unification.materials.MRMaterials;
import kono.ceu.materialreplication.api.unification.materials.flags.MRMaterialFlags;
import kono.ceu.materialreplication.common.items.MRMetaItems;

public class MRMachineRecipeLoader {

    public static void register() {
        recipeMatter();
        recipeScrap();
    }

    public static void recipeMatter() {
        List<Material> materialDusts = new ArrayList<>();
        List<Material> materialFluids = new ArrayList<>();
        for (Material material : GregTechAPI.materialManager.getRegisteredMaterials()) {
            // Has Dust Property?
            if (material.hasProperty(PropertyKey.DUST)) {
                materialDusts.add(material);
            } else if (material.hasProperty(PropertyKey.FLUID) && material.getFluid() != null) {
                // Has Fluid Property?
                materialFluids.add(material);
            }
        }

        for (Material materialDust : materialDusts) {
            if (!materialDust.hasFlag(MRMaterialFlags.DISABLE_DECONSTRUCTION)) {
                if (!MRConfig.deconstruction.DeconstructOnlyElements) {
                    registerDeconstructorRecipe(materialDust);
                } else if (materialDust.isElement()) {
                    registerDeconstructorRecipe(materialDust);
                }
            }
            // Replication
            if (!materialDust.hasFlag(MRMaterialFlags.DISABLE_REPLICATION)) {
                if (!MRConfig.replication.ReplicateOnlyElements) {
                    registerReplicatorRecipe(materialDust);
                } else if (materialDust.isElement()) {
                    registerReplicatorRecipe(materialDust);
                }
            }
        }

        for (Material materialFluid : materialFluids) {
            // Deconstruction
            if (!materialFluid.hasFlag(MRMaterialFlags.DISABLE_DECONSTRUCTION)) {
                if (!MRConfig.deconstruction.DeconstructOnlyElements) {
                    registerDeconstructorFluidRecipe(materialFluid);
                } else if (materialFluid.isElement()) {
                    registerDeconstructorFluidRecipe(materialFluid);
                }
            }
            // Replication
            if (!materialFluid.hasFlag(MRMaterialFlags.DISABLE_REPLICATION)) {
                if (!MRConfig.replication.ReplicateOnlyElements) {
                    registerReplicatorFluidRecipe(materialFluid);
                } else if (materialFluid.isElement()) {
                    registerReplicatorFluidRecipe(materialFluid);
                }
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
    public static boolean hasOnlyMolten(@NotNull Material material) {
        Fluid gas = material.getFluid(FluidStorageKeys.GAS);
        Fluid liquid = material.getFluid(FluidStorageKeys.LIQUID);
        Fluid plasma = material.getFluid(FluidStorageKeys.PLASMA);
        Fluid molten = material.getFluid(GCYMFluidStorageKeys.MOLTEN);
        return molten != null && gas == null && liquid == null && plasma == null;
    }

    public static void registerReplicatorRecipe(@NotNull Material material) {
        RecipeBuilder<ReplicatorRecipeBuilder> builder = MRRecipeMaps.REPLICATION_RECIPES.recipeBuilder();

        // check the amount of Protons and Neutrons
        if (material.getProtons() == 0) {
            builder.fluidInputs(MRMaterials.NeutralMatter.getFluid((int) material.getNeutrons()));
        } else if (material.getNeutrons() == 0) {
            builder.fluidInputs(MRMaterials.ChargedMatter.getFluid((int) material.getProtons()));
        } else {
            builder.fluidInputs(MRMaterials.ChargedMatter.getFluid((int) material.getProtons()))
                    .fluidInputs(MRMaterials.NeutralMatter.getFluid((int) material.getNeutrons()));
        }

        builder.duration(BaseTime_R * (int) material.getMass())
                .input(GTRecipeItemInput.getOrCreate(MRMetaItems.USB_STICK.getStackForm())
                        .setNBTMatchingCondition(NBTMatcher.RECURSIVE_EQUAL_TO,
                                NBTCondition.create(NBTTagType.COMPOUND, IReplicatorRecipeMap.REPLICATE_NBT_TAG,
                                        NBTCondition.create(NBTTagType.STRING,
                                                IReplicatorRecipeMap.REPLICATE_MATERIAL,
                                                material.toString())))
                        .setNonConsumable())
                .replicate(material)
                .EUt(Voltage_R)
                .output(dust, material)
                .buildAndRegister();
    }

    public static void registerReplicatorFluidRecipe(@NotNull Material material) {
        RecipeBuilder<ReplicatorRecipeBuilder> builder = MRRecipeMaps.REPLICATION_RECIPES.recipeBuilder();

        // check the amount of Protons and Neutrons
        if (material.getProtons() == 0) {
            builder.fluidInputs(MRMaterials.NeutralMatter.getFluid((int) material.getNeutrons()));
        } else if (material.getNeutrons() == 0) {
            builder.fluidInputs(MRMaterials.ChargedMatter.getFluid((int) material.getProtons()));
        } else {
            builder.fluidInputs(MRMaterials.ChargedMatter.getFluid((int) material.getProtons()))
                    .fluidInputs(MRMaterials.NeutralMatter.getFluid((int) material.getNeutrons()));
        }

        builder.input(GTRecipeItemInput.getOrCreate(MRMetaItems.USB_STICK.getStackForm())
                .setNBTMatchingCondition(NBTMatcher.RECURSIVE_EQUAL_TO,
                        NBTCondition.create(NBTTagType.COMPOUND, IReplicatorRecipeMap.REPLICATE_NBT_TAG,
                                NBTCondition.create(NBTTagType.STRING, IReplicatorRecipeMap.REPLICATE_MATERIAL,
                                        material.toString())))
                .setNonConsumable())
                .replicate(material)
                .duration(BaseTime_R * (int) material.getMass())
                .EUt(Voltage_R);

        // check Molten
        if (hasOnlyMolten(material)) {
            builder.fluidOutputs(new FluidStack(material.getFluid(GCYMFluidStorageKeys.MOLTEN), 1000));
        } else {
            builder.fluidOutputs(material.getFluid(1000));
        }
        builder.buildAndRegister();
    }

    public static void registerDeconstructorRecipe(@NotNull Material material) {
        MRRecipeMaps.DECONSTRUCTION_RECIPES.recipeBuilder()
                .input(dust, material)
                .fluidOutputs(MRMaterials.ChargedMatter.getFluid((int) material.getProtons()))
                .fluidOutputs(MRMaterials.NeutralMatter.getFluid((int) material.getNeutrons()))
                .chancedOutput(dustTiny, MRMaterials.PrimalMatter, 5, 5)
                .duration(BaseTime_D * (int) material.getMass())
                .EUt(Voltage_D)
                .buildAndRegister();
    }

    public static void registerDeconstructorFluidRecipe(@NotNull Material material) {
        RecipeBuilder<SimpleRecipeBuilder> builder = MRRecipeMaps.DECONSTRUCTION_RECIPES.recipeBuilder();
        // check Molten
        if (hasOnlyMolten(material)) {
            builder.fluidInputs(new FluidStack(material.getFluid(GCYMFluidStorageKeys.MOLTEN), 1000));
        } else {
            builder.fluidInputs(material.getFluid(1000));
        }
        builder.fluidOutputs(MRMaterials.ChargedMatter.getFluid((int) material.getProtons()))
                .fluidOutputs(MRMaterials.NeutralMatter.getFluid((int) material.getNeutrons()))
                .chancedOutput(dustTiny, MRMaterials.PrimalMatter, 5, 5)
                .duration(BaseTime_D * (int) material.getMass())
                .EUt(Voltage_D)
                .buildAndRegister();
    }
}
