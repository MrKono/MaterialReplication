package kono.materialreplication.materials.flags;

import static gregtech.api.unification.material.Materials.Concrete;
import static kono.materialreplication.materials.flags.MRMaterialFlags.DISABLE_DECONSTRUCTION;
import static kono.materialreplication.materials.flags.MRMaterialFlags.DISABLE_REPLICATION;

public class MRMaterialFlagAddition {
    public static void init() {
        //no_destruction
        Concrete.addFlags(DISABLE_DECONSTRUCTION);

        //no_replication
        Concrete.addFlags(DISABLE_REPLICATION);
    }
}
