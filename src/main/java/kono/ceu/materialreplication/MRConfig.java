package kono.ceu.materialreplication;


import kono.ceu.materialreplication.api.MRValues;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Config;

@Config(modid = MRValues.MODID)
public class MRConfig {
    private static class ItemKey
    {
        private Item item;
        private int metadata;

        @Override
        public int hashCode()
        {
            return item.hashCode() * 31 + metadata;
        }

        @Override
        public boolean equals(Object obj)
        {
            if (obj instanceof ItemKey)
            {
                ItemKey key = (ItemKey) obj;
                return item == key.item && metadata == key.metadata;
            }

            return false;
        }

        @Override
        public String toString()
        {
            return item.getRegistryName() + "@" + metadata;
        }
    }

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

    public static class DeconstructionOptions {
        @Config.Comment({"Deconstruction Base time in tick (int).","Default : 600 tick (30 Sec.)",
                "Duration : BaseTime * material's Average mass"})
        public int DeconstructionBaseTime = 600;

        @Config.Comment({"Deconstruction Base Voltage in int.", "Default : 30 (LV)"})
        public int DeconstructionVoltage = 30;

        /*
        @Config.Comment("Material Blacklist for Deconstruction")
        public String[] blacklistForDeconstruction ={
                "iron","steel","tin"
        };
         */
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
        /*
        @Config.Comment("test")
        public String[] blacklist = new String[] {
                "minecraft:cobblestone",
                "minecraft:sand",
                "minecraft:stone:*"
        };
         */
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
