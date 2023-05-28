package kono.ceu.materialreplication.api.unification.materials.flags;

import gregtech.api.GregTechAPI;
import gregtech.api.unification.material.Material;
import kono.ceu.materialreplication.MRConfig;

import static kono.ceu.materialreplication.api.unification.materials.flags.MRMaterialFlags.DISABLE_DECONSTRUCTION;
import static kono.ceu.materialreplication.api.unification.materials.flags.MRMaterialFlags.DISABLE_REPLICATION;

public class MRMaterialFlagAddition {
    public static void init() {
        // Deconstruction & Replication Blacklist
        for (String both : MRConfig.blacklist.blacklistForMatter) {
            if (!both.isEmpty()) {
                Material blacklistNatter = GregTechAPI.MaterialRegistry.get(both);
                if (blacklistNatter == null) continue;
                blacklistNatter.addFlags(DISABLE_DECONSTRUCTION, DISABLE_REPLICATION);
            }
        }

        // Deconstruction Blacklist
        for (String deconstruction : MRConfig.blacklist.blacklistForDeconstruction) {
            if (!deconstruction.isEmpty()) {
                Material blacklistDeconstruct = GregTechAPI.MaterialRegistry.get(deconstruction);
                if (blacklistDeconstruct == null) continue;
                blacklistDeconstruct.addFlags(DISABLE_DECONSTRUCTION);
            }
        }

        // Replication Blacklist
        for (String replication : MRConfig.blacklist.blacklistForReplication) {
            if (!replication.isEmpty()) {
                Material blacklistReplicate = GregTechAPI.MaterialRegistry.get(replication);
                if (replication == null) continue;
                blacklistReplicate.addFlags(DISABLE_REPLICATION);
            }
        }
    }
}
