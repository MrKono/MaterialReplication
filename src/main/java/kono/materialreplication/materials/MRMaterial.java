package kono.materialreplication.materials;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

public class MRMaterial {

    public static void init() {
        MRMaterials.TestMaterial = new Material.Builder(31000, "testmaterial")
                .dust().ingot().fluid()
                .fluidTemp(4000)
                .color(0x072743)
                .iconSet(MaterialIconSet.METALLIC).build();

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
