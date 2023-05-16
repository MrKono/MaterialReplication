package kono.ceu.materialreplication;


import kono.ceu.materialreplication.api.MRValues;
import net.minecraftforge.common.config.Config;

@Config(modid = MRValues.MODID)
public class MRConfig {
    @Config.Comment("Config option for Deconstruction")
    @Config.Name("Deconstruction Settings")
    @Config.RequiresMcRestart
    public static DeconstructionOptions deconstruction = new DeconstructionOptions();

    @Config.Comment("Config option for Replication")
    @Config.Name("Replication Settings")
    @Config.RequiresMcRestart
    public static ReplicationOptions replication = new ReplicationOptions();

    @Config.Comment("Config option for recipe")
    @Config.Name("Recipe Options")
    @Config.RequiresMcRestart
    public static RecipeOptions recipe = new RecipeOptions();

    public static class DeconstructionOptions {
        @Config.Comment({"Deconstruction Base time in tick (int).","Default : 600 tick (30 Sec.)",
                "Duration : BaseTime * material's Average mass"})
        public int DeconstructionBaseTime = 600;

        @Config.Comment({"Deconstruction Base Voltage in int.", "Default : 30 (LV)"})
        public int DeconstructionVoltage = 30;
    }

    public static class ReplicationOptions {
        @Config.Comment({"Replication Base time in tick (int).","Default : 1200 tick (60 Sec.)",
                "Duration : BaseTime * material's Average mass"})
        public int ReplicationBaseTime = 1200;

        @Config.Comment({"Replication Base Voltage in int.", "Default : 30 (LV)"})
        public int ReplicationVoltage = 30;

        @Config.Comment({"Material Scan time in tick (int).", "Default : 1200 tick (60 Sec.)"})
        public int ScanTime = 1200;

        @Config.Comment({"Material Scan Voltage in int.", "Default : 30 (LV)"})
        public int ScanVoltage = 30;
    }

    public static class RecipeOptions {
        @Config.Comment({"Whether to add the UUMatter recipe.", "If you install Gregification and Forestry, this will be ignored.",
        "Default : false"})
        public boolean addUUMatterRecipe = false;
    }
}
