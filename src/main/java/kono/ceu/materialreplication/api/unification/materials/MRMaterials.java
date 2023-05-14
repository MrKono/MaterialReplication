package kono.ceu.materialreplication.api.unification.materials;

import gregtech.api.unification.material.Material;

public class MRMaterials {

    //ID 31000 - 31003
    public static Material PrimalMatter;
    public static Material ChargedMatter;
    public static Material NeutralMatter;
    public static Material MatterAmplifier;

    public static void init() {
        MRMaterial.init();
    }
}
