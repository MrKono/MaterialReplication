package kono.ceu.materialreplication.api.recipes;

import static kono.ceu.materialreplication.loaders.recipe.MRMachineRecipeLoader.hasOnlyMolten;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;

import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.core.sound.GTSoundEvents;

import gregicality.multiblocks.api.fluids.GCYMFluidStorageKeys;

import kono.ceu.materialreplication.api.gui.MRGuiTextures;
import kono.ceu.materialreplication.api.recipes.builders.ReplicatorRecipeBuilder;
import kono.ceu.materialreplication.api.recipes.machines.IReplicatorRecipeMap;
import kono.ceu.materialreplication.api.recipes.machines.RecipeMapReplicator;
import kono.ceu.materialreplication.api.recipes.machines.RecipeMapScrapMaker;
import kono.ceu.materialreplication.api.unification.materials.flags.MRMaterialFlags;
import kono.ceu.materialreplication.common.items.MRMetaItems;

public class MRRecipeMaps {

    // Deconstractor
    public static final RecipeMap<SimpleRecipeBuilder> DECONSTRUCTION_RECIPES = new RecipeMap<>("deconstruction",
            1, 1, 1, 2, // Max(itemIn, itemOut, fluidIn, fluidOut)
            new SimpleRecipeBuilder(), false)
                    .setSound(GTSoundEvents.CENTRIFUGE)
                    .setSlotOverlay(false, false, GuiTextures.DUST_OVERLAY) // Item Input
                    .setSlotOverlay(false, true, GuiTextures.LIGHTNING_OVERLAY_2) // Fluid Input
                    .setSlotOverlay(true, true, false, GuiTextures.MOLECULAR_OVERLAY_3) // Fluid Output1
                    .setSlotOverlay(true, true, true, GuiTextures.MOLECULAR_OVERLAY_4) // Fluid Output2
                    .setProgressBar(GuiTextures.PROGRESS_BAR_MASS_FAB, ProgressWidget.MoveType.HORIZONTAL);

    // Replicator
    public static final RecipeMap<ReplicatorRecipeBuilder> REPLICATION_RECIPES = new RecipeMapReplicator("replication",
            1, 1, 2, 1, new ReplicatorRecipeBuilder(), false)
                    .setSound(GTSoundEvents.ASSEMBLER)
                    .setSlotOverlay(false, false, GuiTextures.DATA_ORB_OVERLAY)
                    .setSlotOverlay(false, false, MRGuiTextures.USB_OVERLAY) // Item Input
                    .setSlotOverlay(false, true, false, GuiTextures.ATOMIC_OVERLAY_1) // Fluid Input 1
                    .setSlotOverlay(false, true, true, GuiTextures.ATOMIC_OVERLAY_2) // Fluid Input 2
                    .setSlotOverlay(true, false, GuiTextures.DUST_OVERLAY) // Item Output
                    .setSlotOverlay(true, true, GuiTextures.VIAL_OVERLAY_1) // Fluid Output
                    .setProgressBar(GuiTextures.PROGRESS_BAR_REPLICATOR, ProgressWidget.MoveType.HORIZONTAL)
                    .onRecipeBuild(recipeBuilder -> {
                        if (!recipeBuilder.scanRecipe()) return;

                        String replicateId = recipeBuilder.getReplicateID();
                        if (replicateId.isEmpty()) return;

                        NBTTagCompound compound = new NBTTagCompound();
                        compound.setTag(IReplicatorRecipeMap.REPLICATE_NBT_TAG,
                                ReplicatorRecipeBuilder.generateReplicateNBT(replicateId));

                        // NBT作成
                        ItemStack usb = MRMetaItems.USB_STICK.getStackForm();
                        usb.setTagCompound(compound);

                        Material replicateMaterial = recipeBuilder.getReplicationMaterial();

                        List<Material> materialDusts = new ArrayList<>();
                        List<Material> materialFluids = new ArrayList<>();
                        // 対象を仕分け
                        if (!replicateMaterial.hasFlag(MRMaterialFlags.DISABLE_REPLICATION)) {
                            if (replicateMaterial.hasProperty(PropertyKey.DUST)) { // Has Dust Property?
                                                                                   // Propertyにdustがあるか
                                materialDusts.add(replicateMaterial);
                            } else if (replicateMaterial.hasProperty(PropertyKey.FLUID)) { // Has Fluid Property?
                                                                                           // ないならPropertyにFluidがあるか
                                materialFluids.add(replicateMaterial);
                            }
                        }

                        for (Material materialDust : materialDusts) {
                            RecipeMaps.SCANNER_RECIPES.recipeBuilder()
                                    .input(MRMetaItems.USB_STICK)
                                    .input(OrePrefix.dust, materialDust)
                                    .outputs(usb)
                                    .EUt(recipeBuilder.getVoltage())
                                    .duration(recipeBuilder.getDuration())
                                    .buildAndRegister();

                        }

                        for (Material materialFluid : materialFluids) {
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
                    });

    // Scrapper
    public static final RecipeMap<SimpleRecipeBuilder> SCRAPMAKER_RECIPES = new RecipeMapScrapMaker("scrapper",
            1, 1, 1, 0,
            new SimpleRecipeBuilder(), false)
                    .setSound(GTSoundEvents.MACERATOR)
                    .setSlotOverlay(true, false, GuiTextures.DUST_OVERLAY)
                    .setSlotOverlay(false, true, GuiTextures.LIGHTNING_OVERLAY_2) // Fluid Input
                    .setProgressBar(GuiTextures.PROGRESS_BAR_RECYCLER, ProgressWidget.MoveType.HORIZONTAL);
}
