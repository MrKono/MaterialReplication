package kono.ceu.materialreplication;


import kono.ceu.materialreplication.api.MRValues;
import net.minecraftforge.common.config.Config;

@Config(modid = MRValues.MODID)
public class MRConfig {
    /*
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
    }*/

    @Config.Comment("Config option for Blacklist")
    @Config.Name("Blacklist Options")
    @Config.RequiresMcRestart
    public static BlackListOptions blacklist = new  BlackListOptions();

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


    public static class BlackListOptions {
        // TODO Make Blacklist from Config
        @Config.Comment({"Material Blacklist for Deconstruction and Replication","Default: concrete, stone, "})
        public String[] blacklistForMatter = new String[]{
                "lapotron", "oilsands",
                "oil", "oil_light", "oil_medium", "oil_heavy", "natural_gas", "sulfuric_light_fuel", "light_fuel",
                "lightly_hydrocracked_light_fuel", "severely_hydrocracked_light_fuel", "lightly_steamcracked_light_fuel",
                "severely_steamcracked_light_fuel", "sulfuric_heavy_fuel", "heavy_fuel", "lightly_hydrocracked_heavy_fuel",
                "severely_hydrocracked_heavy_fuel", "lightly_steamcracked_heavy_fuel", "severely_steamcracked_heavy_fuel",
                "sulfuric_naphtha", "naphtha", "lightly_hydrocracked_naphtha", "severely_hydrocracked_naphtha",
                "lightly_steamcracked_naphtha", "severely_steamcracked_naphtha", "sulfuric_gas", "refinery_gas",
                "lightly_hydrocracked_gas", "severely_hydrocracked_gas", "lightly_steamcracked_gas", "severely_steamcracked_gas",
                "hydrocracked_butene", "hydrocracked_propene", "hydrocracked_butane", "hydrocracked_propane",
                "hydrocracked_ethane", "hydrocracked_ethylene", "hydrocracked_butadiene", "steamcracked_butene",
                "steamcracked_butadiene", "steamcracked_butadiene", "steamcracked_ethane", "steamcracked_butane",
                "steamcracked_propene", "steamcracked_propane","steamcracked_ethylene","fish_oil", "raw_gasoline", "biomass",
                "fermented_biomass", "bacteria", "nitro_fuel", "bacterial_sludge",
                "bio_diesel", "seed_oil", "gasoline_premium", "rocket_fuel", "raw_growth_medium", "sapphire_slurry",
                "wood_tar", "charcoal_byproducts", "coal_tar", "sterilized_growth_medium", "lpg", "wood_gas", "wood_vinegar",
                "coal_gas", "mutagen", "enriched_bacterial_sludge", "coal_gas", "ruby_slurry", "construction_foam",
                "green_sapphire_slurry", "lava", "gasoline"

        };

        @Config.Comment({"Material Blacklist for Deconstruction","Default: "})
        public String[] blacklistForDeconstruction = new String[]{
                "stone","granite", "andesite", "diorite", "clay", "brick", "obsidian", "flint", "netherrack","bone",
                "endstone", "ice", "cocoa", "paper", "wood", "meat", "charcoal", "wheat", "gunpowder", "sugar",
                "granite_black","granite_red", "marble", "basalt", "redrock", "quicklime", "metal_mixture", "ash",
                "rare_earth", "dark_ash", "treated_wood","concrete",
                "water", "milk", "distilled_water", "steam", "glue", "lubricant", "creosote","dye_black", "dye_red",
                "dye_green", "dye_brown", "dye_blue", "dye_purple", "dye_cyan", "dye_light_gray", "dye_gray", "dye_pink",
                "dye_lime", "dye_yellow", "dye_light_blue", "dye_magenta", "dye_orange", "dye_white", "salt_water", "drilling_fluid"
        };

        @Config.Comment({"Material Blacklist for Replication", "Default: "})
        public String[] blacklistForReplication = new String[]{
                "nether_star", "matter_amplifier", "primal_matter", "charged_matter", "neutral_matter", "uu_matter",
                "impure_enriched_naquadah_solution", "enriched_naquadah_solution", "acidic_enriched_naquadah_solution",
                "enriched_naquadah_waste", "impure_naquadria_solution", "naquadria_solution", "acidic_naquadria_solution",
                "naquadria_waste"
        };

        @Config.Comment("Item Blacklist for ScrapMaker")
        public String[] blacklistForScrap = new String[]{};
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
