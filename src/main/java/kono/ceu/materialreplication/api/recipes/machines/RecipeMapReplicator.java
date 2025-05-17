package kono.ceu.materialreplication.api.recipes.machines;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;

import org.jetbrains.annotations.NotNull;

import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.ui.RecipeMapUIFunction;
import gregtech.api.util.EnumValidationResult;
import gregtech.api.util.ValidationResult;
import gregtech.core.sound.GTSoundEvents;

import kono.ceu.materialreplication.api.recipes.properties.impl.ReplicateProperty;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;

// Implemented for now (unused)
public class RecipeMapReplicator<R extends RecipeBuilder<R>> extends RecipeMap<R> implements IReplicatorRecipeMap {

    private final Map<String, Set<Recipe>> replicateEntries = new Object2ObjectOpenHashMap<>();

    public RecipeMapReplicator(@NotNull String unlocalizedName, @NotNull R defaultRecipe,
                               @NotNull RecipeMapUIFunction recipeMapUI) {
        super(unlocalizedName, defaultRecipe, recipeMapUI, 2, 1, 2, 1);
        setSound(GTSoundEvents.ASSEMBLER);
    }

    @Override
    public boolean addRecipe(@NotNull ValidationResult<Recipe> validationResult) {
        super.addRecipe(validationResult);
        if (validationResult.getType() == EnumValidationResult.INVALID) {
            Recipe recipe = validationResult.getResult();
            if (recipe.hasProperty(ReplicateProperty.getInstance())) {
                String replicateID = recipe.getProperty(ReplicateProperty.getInstance(), "");
                if (!replicateID.isEmpty()) addUSBEntry(replicateID, recipe);
            }
        }
        return false;
    }

    @Override
    public boolean removeRecipe(@NotNull Recipe recipe) {
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
