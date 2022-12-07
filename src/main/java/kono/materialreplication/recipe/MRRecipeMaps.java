package kono.materialreplication.recipe;

import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.sound.GTSounds;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenProperty;

@ZenClass("mods.materialreplicaion.recipe.MRRecipeMaps")
public class MRRecipeMaps {
    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> DECONSTRUCTION_RECIPES = new RecipeMap<>("deconstruction",
            0, 1, // ItemInput (min/max)
            0, 1, // ItemOutput (min/max)
            0, 1, // FluidInput (min/max)
            2, 2, // FluidOutput (min/max)
            new SimpleRecipeBuilder(), false)
            .setSound(GTSounds.ELECTROLYZER)
            .setSlotOverlay(false, false, GuiTextures.LIGHTNING_OVERLAY_1) // Item Input
            .setSlotOverlay(false, true, GuiTextures.LIGHTNING_OVERLAY_2) // Fluid Input
            .setSlotOverlay(true, true, false, GuiTextures.MOLECULAR_OVERLAY_3) // Fluid Output1
            .setSlotOverlay(true, true, true, GuiTextures.MOLECULAR_OVERLAY_4) // Fluid Output2
            .setProgressBar(GuiTextures.PROGRESS_BAR_EXTRACT, ProgressWidget.MoveType.HORIZONTAL);

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> REPLICATION_RECIPES = new RecipeMap<>("replication",
            0, 1,
            0, 1,
            0, 3,
            0, 1,
            new SimpleRecipeBuilder(), false)
            .setSound(GTSounds.ELECTROLYZER)
            .setSlotOverlay(false, false, GuiTextures.DUST_OVERLAY) // Item Input
            .setSlotOverlay(false, true, false, GuiTextures.MOLECULAR_OVERLAY_3) // Fluid Input 1 & 2
            .setSlotOverlay(false, true, true, GuiTextures.VIAL_OVERLAY_2) // Fluid Input 3
            .setSlotOverlay(true, false, GuiTextures.MOLECULAR_OVERLAY_1) // Item Output
            .setSlotOverlay(true, true, GuiTextures.VIAL_OVERLAY_2) // Fluid Output
            .setProgressBar(GuiTextures.PROGRESS_BAR_CIRCUIT, ProgressWidget.MoveType.HORIZONTAL);
}
