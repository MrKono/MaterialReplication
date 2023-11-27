package kono.ceu.materialreplication.api.unification.materials;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static gregtech.api.util.GTUtility.gregtechId;

public class MRMaterial {

    public static void init() {

        MRMaterials.PrimalMatter = new Material.Builder(31000, gregtechId("primal_matter"))
                .dust()
                .liquid(new FluidBuilder().temperature(10))
                .color(0xc86edc)
                .iconSet(MaterialIconSet.NETHERSTAR).build();

        MRMaterials.ChargedMatter = new Material.Builder(31001, gregtechId("charged_matter"))
                .liquid(new FluidBuilder().temperature(1))
                .color(0x0064c8)
                .build();

        MRMaterials.NeutralMatter = new Material.Builder(31002, gregtechId("neutral_matter"))
                .liquid(new FluidBuilder().temperature(1))
                .color(0xc80a14)
                .build();

        MRMaterials.MatterAmplifier = new Material.Builder(31003, gregtechId("matter_amplifier"))
                .dust()
                .liquid(new FluidBuilder().temperature(300))
                .color(0xda70d6)
                .build();
    }
}
