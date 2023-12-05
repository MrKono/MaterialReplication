package kono.ceu.materialreplication.api.recipes.machines;

import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.recipes.ingredients.GTRecipeItemInput;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.util.List;

import static gregtech.api.GTValues.LV;
import static gregtech.api.GTValues.VA;
import static kono.ceu.materialreplication.api.util.MRValues.ScrapChance;
import static kono.ceu.materialreplication.api.util.MRValues.ScrapChanceBoost;
import static kono.ceu.materialreplication.common.items.MRMetaItems.SCRAP;

public class RecipeMapScrapMaker extends RecipeMap<SimpleRecipeBuilder> {
    public RecipeMapScrapMaker(String unlocalizedName, int maxInputs, int maxOutputs, int maxFluidInputs, int maxFluidOutputs, SimpleRecipeBuilder defaultRecipe, boolean isHidden) {
        super(unlocalizedName, maxInputs, maxOutputs, maxFluidInputs, maxFluidOutputs, defaultRecipe, isHidden);
    }

    @Override
    @Nullable
    public Recipe findRecipe(long voltage, List<ItemStack> inputs, List<FluidStack> fluidInputs, boolean exactVoltage) {
        Recipe recipe = super.findRecipe(voltage, inputs, fluidInputs, exactVoltage);
        if (recipe != null) return recipe;
            for (ItemStack input : inputs) {
                if (input != null && input != ItemStack.EMPTY) {
                    ItemStack inputStack = input.copy();
                    inputStack.setCount(1);
                        return recipeBuilder().input(GTRecipeItemInput.getOrCreate(inputStack, 1))
                            .chancedOutput(SCRAP, ScrapChance, ScrapChanceBoost)
                            .duration(128).EUt(VA[LV]).build().getResult();
                }
            }
        if (!fluidInputs.isEmpty()) {
            for (FluidStack inputFluid : fluidInputs) {
                if (inputFluid == null) {
                    continue;
                }
                inputFluid = inputFluid.copy();
                if (inputFluid.amount > 0) {
                    return recipeBuilder()
                            .fluidInputs(new FluidStack(inputFluid, 1000))
                            .chancedOutput(SCRAP, ScrapChance, ScrapChanceBoost)
                            .duration(128).EUt(VA[LV]).build().getResult();
                }
            }
        }
        return null;
    }

}
