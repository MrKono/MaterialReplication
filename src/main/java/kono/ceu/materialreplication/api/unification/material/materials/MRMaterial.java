package kono.ceu.materialreplication.api.unification.material.materials;

import static kono.ceu.materialreplication.api.util.MRValues.mrId;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;
import kono.ceu.materialreplication.api.unification.material.info.MRMaterialIconSet;

public class MRMaterial {

    public static void init() {
        MRMaterials.PrimalMatter = new Material.Builder(31000, mrId("primal_matter"))
                .dust()
                .liquid(new FluidBuilder().temperature(10))
                .color(0xc86edc)
                .iconSet(MaterialIconSet.NETHERSTAR).build();

        MRMaterials.ChargedMatter = new Material.Builder(31001, mrId("charged_matter"))
                .liquid(new FluidBuilder().temperature(1))
                .color(0x0064c8)
                .build();

        MRMaterials.NeutralMatter = new Material.Builder(31002, mrId("neutral_matter"))
                .liquid(new FluidBuilder().temperature(1))
                .color(0xc80a14)
                .build();

        MRMaterials.MatterAmplifier = new Material.Builder(31003, mrId("matter_amplifier"))
                .dust()
                .liquid(new FluidBuilder().temperature(300))
                .color(0xda70d6)
                .build();

        MRMaterials.AntiChargedMatter = new Material.Builder(31004, mrId("anti_charged_matter"))
                .iconSet(MRMaterialIconSet.ANTIMATTER)
                .liquid(new FluidBuilder().temperature(1))
                .color(0xc86400)
                .build();

        MRMaterials.AntiNeutralMatter = new Material.Builder(31005, mrId("anti_neutral_matter"))
                .iconSet(MRMaterialIconSet.ANTIMATTER)
                .liquid(new FluidBuilder().temperature(1))
                .color(0x0ac8be )
                .build();

        MRMaterials.AntiMatter = new Material.Builder(31006, mrId("anti_matter"))
                .iconSet(MRMaterialIconSet.ANTIMATTER)
                .liquid(new FluidBuilder().temperature(300))
                .color(0x04360e)
                .build();

    }
}
