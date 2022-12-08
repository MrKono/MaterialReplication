package kono.materialreplication.materials;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

public class MRMaterial {

    public static void init() {

        MRMaterials.PrimalMatter = new Material.Builder(31000, "primal_matter")
                .dust().fluid()
                .fluidTemp(10)
                .color(0xc86edc)
                .iconSet(MaterialIconSet.NETHERSTAR).build();

        MRMaterials.ChargedMatter = new Material.Builder(32008, "charged_matter")
                .fluid()
                .fluidTemp(1)
                .color(0x0064c8)
                .build();

        MRMaterials.NeutralMatter = new Material.Builder(32009, "neutral_matter")
                .fluid()
                .fluidTemp(1)
                .color(0xc80a14)
                .build();
    }
}
