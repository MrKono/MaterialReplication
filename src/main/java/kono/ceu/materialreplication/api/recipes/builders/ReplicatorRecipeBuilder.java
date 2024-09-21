package kono.ceu.materialreplication.api.recipes.builders;

import static kono.ceu.materialreplication.api.util.MRValues.BaseTime_S;
import static kono.ceu.materialreplication.api.util.MRValues.Voltage_S;

import javax.annotation.Nonnull;

import net.minecraft.nbt.NBTTagCompound;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.jetbrains.annotations.NotNull;

import gregtech.api.GregTechAPI;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.util.EnumValidationResult;
import gregtech.api.util.GTLog;
import gregtech.api.util.ValidationResult;

import kono.ceu.materialreplication.api.recipes.machines.IReplicatorRecipeMap;
import kono.ceu.materialreplication.api.recipes.properties.impl.ReplicateProperty;

public class ReplicatorRecipeBuilder extends RecipeBuilder<ReplicatorRecipeBuilder> {

    private final String materialName = Materials.NULL.getName();
    private Material replicationMaterial = GregTechAPI.materialManager.getMaterial(materialName);
    private int Duration = BaseTime_S; // Default : 1200 tick
    private int Voltage = Voltage_S; // Default : 30 EU/t
    private boolean scanRecipe = true;

    public ReplicatorRecipeBuilder() {}

    @SuppressWarnings("unused")
    public ReplicatorRecipeBuilder(Recipe recipe, RecipeMap<ReplicatorRecipeBuilder> recipeMap) {
        super(recipe, recipeMap);
    }

    public ReplicatorRecipeBuilder(@NotNull ReplicatorRecipeBuilder recipeBuilder) {
        super(recipeBuilder);
        this.replicationMaterial = recipeBuilder.replicationMaterial;
        this.Duration = recipeBuilder.Duration;
        this.Voltage = recipeBuilder.Voltage;
        this.scanRecipe = recipeBuilder.scanRecipe;
    }

    public ReplicatorRecipeBuilder copy() {
        return new ReplicatorRecipeBuilder(this);
    }

    @Override
    public boolean applyPropertyCT(@Nonnull String key, @NotNull Object value) {
        if (key.equals(ReplicateProperty.KEY)) {
            this.replicate(value.toString());
            return true;
        }
        return super.applyPropertyCT(key, value);
    }

    protected ReplicatorRecipeBuilder replicate(@Nonnull String replicateID) {
        this.applyProperty(ReplicateProperty.getInstance(), replicateID);
        return this;
    }

    public ReplicatorRecipeBuilder replicate(@Nonnull Material replicationMaterial, boolean generateRecipe) {
        this.scanRecipe = generateRecipe;
        return replicate(replicationMaterial);
    }

    public ReplicatorRecipeBuilder replicate(@Nonnull Material replicationMaterial) {
        return replicate(replicationMaterial, Duration, Voltage);
    }

    public ReplicatorRecipeBuilder replicate(@Nonnull Material replicationMaterial, int time, int EUt) {
        return replicate(replicationMaterial, replicationMaterial.toString(), time, EUt);
    }

    public ReplicatorRecipeBuilder replicate(@Nonnull Material replicationMaterial, @Nonnull String replicateID,
                                             int time, int EUt) {
        if (replicationMaterial == null) {
            GTLog.logger.error("Materials cannot be empty", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        } else {
            this.replicationMaterial = replicationMaterial;
            replicate(replicateID);
        }
        if (time <= 0) {
            GTLog.logger.error("Duration must be greater than 0", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        } else {
            this.Duration = time;
        }
        if (EUt <= 0) {
            GTLog.logger.error("EUt must be greater than 0", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        } else {
            this.Voltage = EUt;
        }
        return this;
    }

    @Override
    public ValidationResult<Recipe> build() {
        ValidationResult<Recipe> result = super.build();
        if (result.getType() == EnumValidationResult.VALID && !this.outputs.isEmpty() &&
                !this.outputs.get(0).isEmpty()) {
            this.applyProperty(ReplicateProperty.getInstance(), this.outputs.get(0).getTranslationKey());
        }
        return result;
    }

    @Nonnull
    public Material getReplicationMaterial() {
        return replicationMaterial;
    }

    public int getDuration() {
        return Duration;
    }

    public int getVoltage() {
        return Voltage;
    }

    public boolean scanRecipe() {
        return scanRecipe;
    }

    public String getReplicateID() {
        return this.recipePropertyStorage == null ? "" :
                this.recipePropertyStorage.get(ReplicateProperty.getInstance(), "");
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append(ReplicateProperty.getInstance().getKey(), getReplicateID())
                .toString();
    }

    @Nonnull
    public static NBTTagCompound generateReplicateNBT(@Nonnull String replicateId) {
        if (replicateId.isEmpty()) throw new IllegalArgumentException("ReplicateId cannot be empty");
        NBTTagCompound compound = new NBTTagCompound();
        compound.setString(IReplicatorRecipeMap.REPLICATE_MATERIAL, replicateId);
        return compound;
    }
}
