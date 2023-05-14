package kono.ceu.materialreplication.api;

import kono.ceu.materialreplication.MRConfig;

public class MRValues {
    public static final String MODNAME = "Material Replication";
    public static final String MODID = "materialreplication";
    public static final int BaseTime_D = MRConfig.deconstruction.DeconstructionBaseTime,
            BaseTime_R = MRConfig.replication.ReplicationBaseTime,
            BaseTime_S = MRConfig.replication.ScanTime,
            Voltage_D = MRConfig.deconstruction.DeconstructionVoltage,
            Voltage_R = MRConfig.replication.ReplicationVoltage,
            Voltage_S = MRConfig.replication.ScanVoltage;
}
