package kono.ceu.materialreplication.api.unification.materials.flags;

import gregtech.api.unification.material.info.MaterialFlag;

public class MRMaterialFlags {

    // 分解できないようにするFlag
    public static final MaterialFlag DISABLE_DECONSTRUCTION = new MaterialFlag.Builder("no_deconstruction").build();

    // 複製できないようにするFlag
    public static final MaterialFlag DISABLE_REPLICATION = new MaterialFlag.Builder("no_replication").build();
}
