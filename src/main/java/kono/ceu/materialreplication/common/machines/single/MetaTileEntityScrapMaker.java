package kono.ceu.materialreplication.common.machines.single;

import java.util.List;
import java.util.function.Function;

import javax.annotation.Nullable;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import gregtech.api.GTValues;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.recipes.RecipeMap;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.utils.TooltipHelper;

import kono.ceu.materialreplication.api.util.MRValues;

public class MetaTileEntityScrapMaker extends SimpleMachineMetaTileEntity {

    public MetaTileEntityScrapMaker(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, ICubeRenderer renderer,
                                    int tier,
                                    Function<Integer, Integer> tankScalingFunction) {
        super(metaTileEntityId, recipeMap, renderer, tier, true, tankScalingFunction);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityScrapMaker(this.metaTileEntityId, workable.getRecipeMap(),
                renderer, this.getTier(), this.getTankScalingFunction());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("materialreplication.machine.scrapmaker.warning1"));
        tooltip.add(I18n.format("gregtech.universal.tooltip.max_voltage_in", energyContainer.getInputVoltage(),
                GTValues.VNF[getTier()]));
        tooltip.add(
                I18n.format("gregtech.universal.tooltip.energy_storage_capacity", energyContainer.getEnergyCapacity()));
        tooltip.add(I18n.format("materialreplication.machine.scrapmaker.warning2.1") +
                TooltipHelper.BLINKING_RED + I18n.format("materialreplication.machine.scrapmaker.warning2.2"));
        if (TooltipHelper.isShiftDown()) {
            double chance = MRValues.ScrapChance / 100.0;
            double boost = MRValues.ScrapChanceBoost / 100.0;
            tooltip.add(I18n.format("materialreplication.machine.scrapmaker.warning3.1") + TooltipHelper.BLINKING_CYAN +
                    I18n.format("materialreplication.machine.scrapmaker.warning3.2", chance, boost));
        }
    }
}
