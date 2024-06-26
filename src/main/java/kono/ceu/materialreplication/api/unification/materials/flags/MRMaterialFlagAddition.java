package kono.ceu.materialreplication.api.unification.materials.flags;

import static kono.ceu.materialreplication.api.unification.materials.flags.MRMaterialFlags.DISABLE_DECONSTRUCTION;
import static kono.ceu.materialreplication.api.unification.materials.flags.MRMaterialFlags.DISABLE_REPLICATION;

import gregtech.api.GregTechAPI;
import gregtech.api.unification.material.Material;

import kono.ceu.materialreplication.MRConfig;

public class MRMaterialFlagAddition {

    public static void init() {
        // Deconstruction & Replication Blacklist
        for (String both : MRConfig.materialOption.blacklistForMatter) {
            if (!both.isEmpty()) {
                Material blacklistMatter = GregTechAPI.materialManager.getMaterial(both);
                if (blacklistMatter == null) continue;
                blacklistMatter.addFlags(DISABLE_DECONSTRUCTION, DISABLE_REPLICATION);
            }
        }

        // Deconstruction Blacklist
        for (String deconstruction : MRConfig.materialOption.blacklistForDeconstruction) {
            if (!deconstruction.isEmpty()) {
                Material blacklistDeconstruct = GregTechAPI.materialManager.getMaterial(deconstruction);
                if (blacklistDeconstruct == null) continue;
                blacklistDeconstruct.addFlags(DISABLE_DECONSTRUCTION);
            }
        }

        // Replication Blacklist
        for (String replication : MRConfig.materialOption.blacklistForReplication) {
            if (!replication.isEmpty()) {
                Material blacklistReplicate = GregTechAPI.materialManager.getMaterial(replication);
                if (blacklistReplicate == null) continue;
                blacklistReplicate.addFlags(DISABLE_REPLICATION);
            }
        }
    }

    public static void intLate() {
        // Does not have chemical formula
        for (Material material : GregTechAPI.materialManager.getRegisteredMaterials()) {
            for (String white : MRConfig.materialOption.WhitelistMaterial) {
                Material whitelist = GregTechAPI.materialManager.getMaterial(white);
                if (material.getChemicalFormula().isEmpty()) {
                    if (material != whitelist) {
                        material.addFlags(DISABLE_REPLICATION, DISABLE_DECONSTRUCTION);
                    }
                }
            }
        }
    }
}
