package kono.ceu.materialreplication.api.recipes;

import gregicality.multiblocks.api.fluids.GCYMFluidStorageKeys;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMapBuilder;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.recipes.ui.RecipeMapUI;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.core.sound.GTSoundEvents;
import kono.ceu.materialreplication.MRConfig;
import kono.ceu.materialreplication.api.gui.MRGuiTextures;
import kono.ceu.materialreplication.api.recipes.builders.ReplicatorRecipeBuilder;
import kono.ceu.materialreplication.api.recipes.machines.IReplicatorRecipeMap;
import kono.ceu.materialreplication.api.recipes.machines.RecipeMapScrapMaker;
import kono.ceu.materialreplication.api.unification.materials.flags.MRMaterialFlags;
import kono.ceu.materialreplication.common.items.MRMetaItems;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

import static kono.ceu.materialreplication.api.util.MRValues.mrId;
import static kono.ceu.materialreplication.loaders.recipe.MRMachineRecipeLoader.hasOnlyMolten;

public class MRRecipeMaps {

    // Deconstractor
    public static final RecipeMap<SimpleRecipeBuilder> DECONSTRUCTION_RECIPES = new RecipeMapBuilder<>("deconstruction",
            new SimpleRecipeBuilder())
                    .itemInputs(1).itemOutputs(1)
                    .fluidInputs(1).fluidOutputs(2)
                    .itemSlotOverlay(GuiTextures.DUST_OVERLAY, false)
                    .itemSlotOverlay(GuiTextures.DUST_OVERLAY, true)
                    .fluidSlotOverlay(GuiTextures.LIGHTNING_OVERLAY_2, false)
                    .fluidSlotOverlay(GuiTextures.MOLECULAR_OVERLAY_3, true, false)
                    .fluidSlotOverlay(GuiTextures.MOLECULAR_OVERLAY_4, true, true)
                    .sound(GTSoundEvents.CENTRIFUGE)
                    .progressBar(GuiTextures.PROGRESS_BAR_MASS_FAB, ProgressWidget.MoveType.HORIZONTAL)
                    .build();

    // Replicator
    // toDo Use RecipeMapReplicator<> instead of RecipeMapBuilder<>
    public static final RecipeMap<ReplicatorRecipeBuilder> REPLICATION_RECIPES = new RecipeMapBuilder<>("replication",
            new ReplicatorRecipeBuilder())
                    .itemInputs(1).itemOutputs(1)
                    .fluidInputs(2).fluidOutputs(1)
                    .itemSlotOverlay(MRGuiTextures.USB_OVERLAY, false)
                    .itemSlotOverlay(GuiTextures.DUST_OVERLAY, true)
                    .fluidSlotOverlay(GuiTextures.ATOMIC_OVERLAY_1, false, false)
                    .fluidSlotOverlay(GuiTextures.ATOMIC_OVERLAY_2, false, true)
                    .fluidSlotOverlay(GuiTextures.VIAL_OVERLAY_1, true)
                    .progressBar(GuiTextures.PROGRESS_BAR_REPLICATOR, ProgressWidget.MoveType.HORIZONTAL)
                    .sound(GTSoundEvents.ASSEMBLER)
                    .onBuild(mrId("replication_research"), recipeBuilder -> {
                        if (!recipeBuilder.scanRecipe()) return;

                        String replicateId = recipeBuilder.getReplicateID();
                        if (replicateId.isEmpty()) return;

                        NBTTagCompound compound = new NBTTagCompound();
                        compound.setTag(IReplicatorRecipeMap.REPLICATE_NBT_TAG,
                                ReplicatorRecipeBuilder.generateReplicateNBT(replicateId));

                        // NBT
                        ItemStack usb = MRMetaItems.USB_STICK.getStackForm();
                        usb.setTagCompound(compound);

                        Material replicateMaterial = recipeBuilder.getReplicationMaterial();

                        List<Material> materialDusts = new ArrayList<>();
                        List<Material> materialFluids = new ArrayList<>();
                        if (!replicateMaterial.hasFlag(MRMaterialFlags.DISABLE_REPLICATION)) {
                            // Has Dust Property?
                            if (replicateMaterial.hasProperty(PropertyKey.DUST)) {
                                materialDusts.add(replicateMaterial);

                                // Has Fluid Property?
                            } else if (replicateMaterial.hasProperty(PropertyKey.FLUID)) {
                                materialFluids.add(replicateMaterial);
                            }
                        }

                        for (Material materialDust : materialDusts) {
                            if (MRConfig.replication.ReplicateOnlyElements && !materialDust.isElement()) return;
                            RecipeMaps.SCANNER_RECIPES.recipeBuilder()
                                    .input(MRMetaItems.USB_STICK)
                                    .input(OrePrefix.dust, materialDust)
                                    .outputs(usb)
                                    .EUt(recipeBuilder.getVoltage())
                                    .duration(recipeBuilder.getDuration())
                                    .buildAndRegister();
                        }

                        for (Material materialFluid : materialFluids) {
                            if (MRConfig.replication.ReplicateOnlyElements && !materialFluid.isElement()) return;
                            RecipeBuilder<SimpleRecipeBuilder> scanner = RecipeMaps.SCANNER_RECIPES.recipeBuilder();
                            scanner.input(MRMetaItems.USB_STICK)
                                    .outputs(usb)
                                    .EUt(recipeBuilder.getVoltage())
                                    .duration(recipeBuilder.getDuration());

                            // check Molten
                            if (hasOnlyMolten(materialFluid)) {
                                scanner.fluidInputs(
                                        new FluidStack(materialFluid.getFluid(GCYMFluidStorageKeys.MOLTEN), 1000));
                            } else {
                                scanner.fluidInputs(materialFluid.getFluid(1000));
                            }
                            scanner.buildAndRegister();
                        }
                    }).build();

    // Scrapper
    public static final RecipeMap<SimpleRecipeBuilder> SCRAPMAKER_RECIPES = new RecipeMapScrapMaker("scrapper",
            new SimpleRecipeBuilder(), recipeMap -> {
                RecipeMapUI<?> ui = new RecipeMapUI<>(recipeMap, true, true, true, false, false);
                ui.setItemSlotOverlay(GuiTextures.DUST_OVERLAY, true);
                ui.setFluidSlotOverlay(GuiTextures.LIGHTNING_OVERLAY_2, false);
                return ui;
            });
}
