package kono.ceu.materialreplication.api.unification.materials.flags;

import gregtech.api.GregTechAPI;
import gregtech.api.unification.material.Material;
import kono.ceu.materialreplication.MRConfig;

import static kono.ceu.materialreplication.api.unification.materials.flags.MRMaterialFlags.DISABLE_DECONSTRUCTION;
import static kono.ceu.materialreplication.api.unification.materials.flags.MRMaterialFlags.DISABLE_REPLICATION;

public class MRMaterialFlagAddition {
    public static void init() {

        // Does not have chemical formula
        for (Material material : GregTechAPI.MATERIAL_REGISTRY) {
            for (String white : MRConfig.materialOption.WhitelistMaterial) {
                Material whitelist = GregTechAPI.MaterialRegistry.get(white);
                if (material.getChemicalFormula().isEmpty()) {
                    if (material != whitelist) {
                        material.addFlags(DISABLE_REPLICATION, DISABLE_DECONSTRUCTION);
                    }
                }
            }
        }

        // Deconstruction & Replication Blacklist
        for (String both : MRConfig.materialOption.blacklistForMatter) {
            if (!both.isEmpty()) {
                Material blacklistMatter = GregTechAPI.MaterialRegistry.get(both);
                if (blacklistMatter == null) continue;
                blacklistMatter.addFlags(DISABLE_DECONSTRUCTION, DISABLE_REPLICATION);
            }
        }

        // Deconstruction Blacklist
        for (String deconstruction : MRConfig.materialOption.blacklistForDeconstruction) {
            if (!deconstruction.isEmpty()) {
                Material blacklistDeconstruct = GregTechAPI.MaterialRegistry.get(deconstruction);
                if (blacklistDeconstruct == null) continue;
                blacklistDeconstruct.addFlags(DISABLE_DECONSTRUCTION);
            }
        }

        // Replication Blacklist
        for (String replication : MRConfig.materialOption.blacklistForReplication) {
            if (!replication.isEmpty()) {
                Material blacklistReplicate = GregTechAPI.MaterialRegistry.get(replication);
                if (blacklistReplicate == null ) continue;
                blacklistReplicate.addFlags(DISABLE_REPLICATION);
            }
        }
    }
}
