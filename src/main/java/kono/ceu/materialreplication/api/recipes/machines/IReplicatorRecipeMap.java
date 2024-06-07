package kono.ceu.materialreplication.api.recipes.machines;

import java.util.Set;

import javax.annotation.Nonnull;

import gregtech.api.recipes.Recipe;

public interface IReplicatorRecipeMap {

    String REPLICATE_NBT_TAG = "ReplicateResearch"; // RESEARCH_NBT_TAG, assemblylineResearch"
    String REPLICATE_MATERIAL = "Material"; // RESEARCH_ID_NBT_TAG, researchID

    void addUSBEntry(@Nonnull String replicateMaterial, @Nonnull Recipe recipe);

    @Nonnull
    Set<Recipe> getUSBEntry(@Nonnull String replicateMaterial);

    boolean removeUSBEntry(@Nonnull String replicateMaterial, @Nonnull Recipe recipe);
}
