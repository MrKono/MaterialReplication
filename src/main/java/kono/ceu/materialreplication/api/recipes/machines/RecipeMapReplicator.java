package kono.ceu.materialreplication.api.recipes.machines;

import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.EnumValidationResult;
import gregtech.api.util.ValidationResult;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import kono.ceu.materialreplication.api.recipes.builders.ReplicatorRecipeBuilder;
import kono.ceu.materialreplication.api.recipes.properties.ReplicateProperty;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class RecipeMapReplicator extends RecipeMap<ReplicatorRecipeBuilder> implements IReplicatorRecipeMap {
    private final Map<String, Set<Recipe>> replicateEntries = new Object2ObjectOpenHashMap<>();

    public RecipeMapReplicator(String unlocalizedName, int maxInputs, int maxOutputs, int maxFluidInputs, int maxFluidOutputs, ReplicatorRecipeBuilder defaultRecipe, boolean isHidden) {
        super(unlocalizedName, maxInputs, maxOutputs, maxFluidInputs, maxFluidOutputs, defaultRecipe, isHidden);
    }

    @Override
    public void addRecipe(ValidationResult<Recipe> validationResult) {
        super.addRecipe(validationResult);
        if (validationResult.getType() == EnumValidationResult.INVALID) {
            Recipe recipe = validationResult.getResult();
            if (recipe.hasProperty(ReplicateProperty.getInstance())) {
                String replicateID = recipe.getProperty(ReplicateProperty.getInstance(), "");
                if (!replicateID.isEmpty()) addUSBEntry(replicateID, recipe);
            }
        }
    }

    @Override
    public boolean removeRecipe(Recipe recipe) {
        boolean result = super.removeRecipe(recipe);
        if (result && recipe.hasProperty(ReplicateProperty.getInstance())) {
            String replicateId = recipe.getProperty(ReplicateProperty.getInstance(), "");
            if (!replicateId.isEmpty()) removeUSBEntry(replicateId, recipe);
        }
        return result;
    }

    @Override
    public void addUSBEntry(@Nonnull String relicateId, @Nonnull Recipe recipe) {
        Set<Recipe> recipes = replicateEntries.get(relicateId);
        if (recipe == null) {
            recipes = new ObjectOpenHashSet<>();
            recipes.add(recipe);
            replicateEntries.put(relicateId, recipes);
        } else {
            recipes.add(recipe);
        }
    }

    @Nonnull
    public Set<Recipe> getUSBEntry(@Nonnull String replicateId) {
        Set<Recipe> recipes = replicateEntries.get(replicateId);
        return recipes == null ? Collections.emptySet() : recipes;
    }

    @Override
    public boolean removeUSBEntry(@Nonnull String replicateId, @Nonnull Recipe recipe) {
        Set<Recipe> recipes = replicateEntries.get(replicateId);
        if (recipes == null) return false;
        return recipes.remove(recipe);
    }
}
