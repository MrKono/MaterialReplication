package kono.ceu.materialreplication.api.recipes.machines;

import gregtech.api.recipes.Recipe;

import javax.annotation.Nonnull;
import java.util.Set;

public interface IReplicatorRecipeMap {

    String REPLICATE_NBT_TAG = "ReplicateResearch"; //RESEARCH_NBT_TAG, assemblylineResearch"
    String REPLICATE_MATERIAL = "Material"; //RESEARCH_ID_NBT_TAG, researchID

    void addUSBEntry(@Nonnull String replicateMaterial, @Nonnull Recipe recipe);

    @Nonnull
    Set<Recipe> getUSBEntry(@Nonnull String replicateMaterial);

    boolean removeUSBEntry(@Nonnull String replicateMaterial, @Nonnull Recipe recipe);
}
