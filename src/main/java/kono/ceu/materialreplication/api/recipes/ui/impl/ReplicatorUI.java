package kono.ceu.materialreplication.api.recipes.ui.impl;

import org.jetbrains.annotations.NotNull;

import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.ui.RecipeMapUI;

import kono.ceu.materialreplication.api.gui.MRGuiTextures;

// Implemented for now (unused)
public class ReplicatorUI<R extends RecipeMap<?>> extends RecipeMapUI<R> {

    public ReplicatorUI(@NotNull R recipeMap) {
        super(recipeMap, true, true, true, true, false);
        setProgressBar(GuiTextures.PROGRESS_BAR_REPLICATOR, ProgressWidget.MoveType.HORIZONTAL);
        setItemSlotOverlay(MRGuiTextures.USB_OVERLAY, false, true);
        setItemSlotOverlay(GuiTextures.DUST_OVERLAY, true);
        setFluidSlotOverlay(GuiTextures.ATOMIC_OVERLAY_1, false, false);
        setFluidSlotOverlay(GuiTextures.ATOMIC_OVERLAY_2, false, true);
    }
}
