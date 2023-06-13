package kono.ceu.materialreplication;


import kono.ceu.materialreplication.api.MRValues;
import net.minecraftforge.common.config.Config;

@Config(modid = MRValues.MODID)
public class MRConfig {

    @Config.Comment("Config option of materials for Deconstruction/Replication")
    @Config.Name("Material Options")
    @Config.RequiresMcRestart
    public static MaterialOption materialOption = new MaterialOption();

    @Config.Comment("Config option for Deconstruction")
    @Config.Name("Deconstruction Settings")
    @Config.RequiresMcRestart
    public static DeconstructionOptions deconstruction = new DeconstructionOptions();

    @Config.Comment("Config option for Replication")
    @Config.Name("Replication Settings")
    @Config.RequiresMcRestart
    public static ReplicationOptions replication = new ReplicationOptions();

    @Config.Comment("Config option for Scrap")
    @Config.Name("Scrap Settings")
    @Config.RequiresMcRestart
    public static ScrapOptions scrap = new ScrapOptions();

    @Config.Comment("Config option for recipe")
    @Config.Name("Recipe Options")
    @Config.RequiresMcRestart
    public static RecipeOptions recipe = new RecipeOptions();

    public static class MaterialOption {
        // TODO Make Blacklist from Config
        @Config.Comment({"Can deconstruct/replicate material which does not have chemical formula"})
        public String[] WhitelistMaterial = new String[] {
                "glowstone"
        };

        @Config.Comment({"Material Blacklist for Deconstruction and Replication",
                "This setting is valid only for materials with chemical formulas or materials specified in \"WhitelistMaterial\"",
                "Default: concrete, stone, "})
        public String[] blacklistForMatter = new String[]{
        };

        @Config.Comment({"Material Blacklist for Deconstruction",
                "This setting is valid only for materials with chemical formulas or materials specified in \"WhitelistMaterial\"",
                "Default: "})
        public String[] blacklistForDeconstruction = new String[]{
                "granite", "andesite", "diorite", "clay", "brick", "obsidian", "flint", "ice", "charcoal", "gunpowder", "sugar",
                "granite_black","granite_red", "marble", "basalt", "quicklime", "redrock", "ash", "concrete",
                "rare_earth", "dark_ash", "water", "distilled_water", "steam"
        };

        @Config.Comment({"Material Blacklist for Replication",
                "This setting is valid only for materials with chemical formulas or materials specified in \"WhitelistMaterial\"",
                "Default: "})
        public String[] blacklistForReplication = new String[]{
        };
    }

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

    public static  class ScrapOptions {
        @Config.Comment({"Output chance of Scrap for Scrap Maker (Int).", "10000 is a 100% chance, 0 is a 0% chance.", "Default : 1 (0.01 %"})
        @Config.RangeInt(min = 0, max = 10000)
        public int ScrapChance = 1;

        @Config.Comment({"Tier output chance boost for producing Scrap (Int).", "10000 is a 100% chance, 0 is a 0% chance.", "Default : 0 (0.00 %"})
        @Config.RangeInt(min = 0, max = 10000)
        public int ScrapChanceBoost = 0;

        @Config.Comment({"Output chance of Tiny pile of Amplifier for Sifter (Int).", "10000 is a 100% chance, 0 is a 0% chance.", "Default : 1 (0.01 %"})
        @Config.RangeInt(min = 0, max = 10000)
        public int AmplifierChance = 1;

        @Config.Comment({"Tier output chance boost for producing Tiny pile of Amplifier (Int).", "10000 is a 100% chance, 0 is a 0% chance.", "Default : 0 (0.00 %"})
        @Config.RangeInt(min = 0, max = 10000)
        public int AmplifierChanceBoost = 0;
    }

    public static class RecipeOptions {
        @Config.Comment({"Whether to add the UUMatter recipe.", "If you install Gregification and Forestry, this will be ignored.",
                "Default : false"})
        public boolean addUUMatterRecipe = false;
    }
}
