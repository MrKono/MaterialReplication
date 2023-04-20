package kono.ceu.materialreplication.common.items.behaviors;

import gregtech.api.GregTechAPI;
import gregtech.api.items.metaitem.stats.IItemBehaviour;
import gregtech.api.unification.material.Material;
import gregtech.client.utils.TooltipHelper;
import kono.ceu.materialreplication.api.recipes.machines.IReplicatorRecipeMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nonnull;
import java.util.List;

import static kono.ceu.materialreplication.api.unification.materials.MRMaterials.ChargedMatter;
import static kono.ceu.materialreplication.api.unification.materials.MRMaterials.NeutralMatter;
import static net.minecraft.util.text.TextFormatting.*;

public class ReplicationBehaviors implements IItemBehaviour {

    public static TooltipHelper.GTFormatCode BLINKING_GRAY_FAST = TooltipHelper.createNewCode(5, GRAY, DARK_GRAY);

    @Override
    public void addInformation(@Nonnull ItemStack itemStack, List<String> lines) {
        NBTTagCompound replicateMaterialNBT = itemStack.getSubCompound(IReplicatorRecipeMap.REPLICATE_NBT_TAG);
        if (replicateMaterialNBT != null) {
            String string = replicateMaterialNBT.getString(IReplicatorRecipeMap.REPLICATE_MATERIAL);
            Material materialStack = GregTechAPI.MaterialRegistry.get(string);
            String materialName = I18n.format(materialStack.getUnlocalizedName());
            String Charged = I18n.format(ChargedMatter.getUnlocalizedName());
            String Neutral = I18n.format(NeutralMatter.getUnlocalizedName());

            lines.add(I18n.format("behavior.usb.replicate"));
            lines.add(I18n.format("behavior.usb.replicate_data1", materialName));
            if (TooltipHelper.isShiftDown()){
                lines.add(BLINKING_GRAY_FAST + I18n.format("behavior.usb.replicate_data2"));
                if (materialStack.getNeutrons() > 0) {
                    lines.add(I18n.format("behavior.usb.replicate_data3", Neutral, materialStack.getNeutrons()));
                }
                if (materialStack.getProtons() > 0) {
                    lines.add(I18n.format("behavior.usb.replicate_data4", Charged, materialStack.getProtons()));
                }
            }
        } else {
            lines.add(I18n.format("behavior.usb.no_data"));
        }
    }
}
