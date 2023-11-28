package kono.ceu.materialreplication.api.util;

import kono.ceu.materialreplication.MRConfig;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class MRValues {
    public static final String MODNAME = "Material Replication";
    public static final String MODID = "materialreplication",
            MODID_FFM = "forestry";
    public static final int BaseTime_D = MRConfig.deconstruction.DeconstructionBaseTime,
            BaseTime_R = MRConfig.replication.ReplicationBaseTime,
            BaseTime_S = MRConfig.replication.ScanTime,
            Voltage_D = MRConfig.deconstruction.DeconstructionVoltage,
            Voltage_R = MRConfig.replication.ReplicationVoltage,
            Voltage_S = MRConfig.replication.ScanVoltage,
            ScrapChance = MRConfig.scrap.ScrapChance,
            ScrapChanceBoost = MRConfig.scrap.ScrapChanceBoost,
            AmplifierChance = MRConfig.scrap.AmplifierChance,
            AmplifierChanceBoost = MRConfig.scrap.AmplifierChanceBoost;

    public static @NotNull ResourceLocation mrId (@NotNull String path) {
        return new ResourceLocation(MODID, path);
    }
}
