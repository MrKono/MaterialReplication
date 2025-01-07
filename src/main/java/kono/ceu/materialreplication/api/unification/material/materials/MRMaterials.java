package kono.ceu.materialreplication.api.unification.material.materials;

import static gregtech.api.unification.ore.OrePrefix.dustSmall;
import static kono.ceu.materialreplication.api.util.MRValues.isAntimatter;

import gregtech.api.unification.material.Material;

import kono.ceu.materialreplication.api.unification.material.flags.MRMaterialFlagAddition;

public class MRMaterials {

    // ID 31000 -
    public static Material PrimalMatter;
    public static Material ChargedMatter;
    public static Material NeutralMatter;
    public static Material MatterAmplifier;
    public static Material AntiChargedMatter;
    public static Material AntiNeutralMatter;
    public static Material AntiMatter;

    public static void init() {
        MRMaterial.init();
        MRMaterialFlagAddition.init();
        if (isAntimatter) {
            MRMaterial.antimatter();
        }
    }

    public static void orePrefix() {
        dustSmall.setIgnored(PrimalMatter);
        dustSmall.setIgnored(MatterAmplifier);
    }
}
