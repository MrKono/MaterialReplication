package kono.materialreplication;


import net.minecraftforge.common.config.Config;

@Config(modid = "materialreplication")
public class MRConfig {
    @Config.Comment("Config option for Deconstruction")
    @Config.Name("Deconstruction Settings")
    @Config.RequiresMcRestart
    public static DeconstructionOptions deconstruction = new DeconstructionOptions();

    @Config.Comment("Config option for Replication")
    @Config.Name("Replication Settings")
    @Config.RequiresMcRestart
    public static ReplicationOptions replication = new ReplicationOptions();

    public static class DeconstructionOptions {
        @Config.Comment({"Deconstruction Base time in tick (int)","Default : 600 tick (30 Sec.)",
                "Duration : BaseTime * material's Average mass"})
        public int DeconstructionBaseTime = 600;

        @Config.Comment({"Deconstruction Base Voltage in int", "Default : 30 (LV)"})
        public int DeconstructionVoltage = 30;
    }

    public static class ReplicationOptions {
        @Config.Comment({"Replication Base time in tick (int)","Default : 1200 tick (60 Sec.)",
                "Duration : BaseTime * material's Average mass"})
        public int ReplicationBaseTime = 1200;

        @Config.Comment({"Replication Base Voltage in int", "Default : 30 (LV)"})
        public int ReplicationVoltage = 30;
    }
}
