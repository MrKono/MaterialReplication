package kono.ceu.materialreplication.integration.forestry.loader;

import net.minecraft.item.ItemStack;

import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.CleanroomType;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.util.GTUtility;
import gregtech.api.util.Mods;
import gregtech.common.items.MetaItems;
import gregtech.integration.forestry.ForestryUtil;
import gregtech.integration.forestry.bees.GTCombType;

import kono.ceu.materialreplication.api.recipes.MRRecipeMaps;

import appeng.core.Api;

public class CombRecipeLoader {

    public static void init() {
        // Organic
        addProcess(GTCombType.COAL, new Material[] { Materials.Coal }, Voltage.LV);
        addProcess(GTCombType.COKE, new Material[] { Materials.Coke }, Voltage.LV);
        addProcess(GTCombType.OIL, new Material[] { Materials.Oilsands }, Voltage.LV);
        addProcess(GTCombType.APATITE, new Material[] { Materials.Apatite, Materials.TricalciumPhosphate }, Voltage.LV);
        addProcess(GTCombType.ASH, new Material[] { Materials.DarkAsh, Materials.Ash }, Voltage.ULV);
        addProcess(GTCombType.BIOMASS, new Material[] { Materials.Biomass, Materials.Ethanol }, Voltage.ULV);
        addProcess(GTCombType.PHOSPHORUS, new Material[] { Materials.Phosphorus, Materials.TricalciumPhosphate },
                Voltage.HV);
        addProcess(GTCombType.COAL, new Material[] { Materials.Coal }, Voltage.ULV);
        addProcess(GTCombType.COKE, new Material[] { Materials.Coke }, Voltage.ULV);
        addProcess(GTCombType.OIL, new Material[] { Materials.Oilsands }, Voltage.ULV);

        // Industrial
        // Special EnergyComb Recipe
        MRRecipeMaps.REPLICATION_RECIPES.recipeBuilder()
                .inputs(ForestryUtil.getCombStack(GTCombType.ENERGY, 9))
                .fluidInputs(
                        Materials.UUMatter.getFluid(10 * Voltage.HV.getUUMatterAmount()))
                .output(MetaItems.ENERGIUM_DUST, 4)
                .duration(1960)
                .EUt(Voltage.HV.getReplicateEnergy())
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();

        // Special LapotronComb Recipe
        MRRecipeMaps.REPLICATION_RECIPES.recipeBuilder()
                .inputs(ForestryUtil.getCombStack(GTCombType.LAPOTRON, 9))
                .fluidInputs(
                        Materials.UUMatter.getFluid(10 * Voltage.HV.getUUMatterAmount()))
                .output(OrePrefix.dust, Materials.Lapotron, 4)
                .duration(1960)
                .EUt(Voltage.HV.getReplicateEnergy())
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();

        // Alloy
        addProcess(GTCombType.REDALLOY, new Material[] { Materials.RedAlloy, Materials.Redstone, Materials.Copper },
                Voltage.LV);
        addProcess(GTCombType.STAINLESSSTEEL, new Material[] { Materials.StainlessSteel, Materials.Iron,
                Materials.Chrome, Materials.Manganese, Materials.Nickel }, Voltage.HV);
        addProcess(GTCombType.REDALLOY, new Material[] { Materials.RedAlloy }, Voltage.ULV);
        addProcess(GTCombType.STAINLESSSTEEL, new Material[] { Materials.StainlessSteel }, Voltage.HV);

        // Gem
        addProcess(GTCombType.STONE, new Material[] { Materials.Soapstone, Materials.Talc, Materials.Apatite,
                Materials.Phosphate, Materials.TricalciumPhosphate }, Voltage.LV);
        addProcess(GTCombType.CERTUS,
                new Material[] { Materials.CertusQuartz, Materials.Quartzite, Materials.Barite }, Voltage.LV);
        addProcess(GTCombType.REDSTONE, new Material[] { Materials.Redstone, Materials.Cinnabar }, Voltage.LV);
        addProcess(GTCombType.RAREEARTH, new Material[] { Materials.RareEarth }, Voltage.ULV);
        addProcess(GTCombType.LAPIS,
                new Material[] { Materials.Lapis, Materials.Sodalite, Materials.Lazurite, Materials.Calcite },
                Voltage.LV);
        addProcess(GTCombType.RUBY, new Material[] { Materials.Ruby, Materials.Redstone }, Voltage.LV);
        addProcess(GTCombType.SAPPHIRE,
                new Material[] { Materials.Sapphire, Materials.GreenSapphire, Materials.Almandine, Materials.Pyrope },
                Voltage.LV);
        addProcess(GTCombType.DIAMOND, new Material[] { Materials.Diamond, Materials.Graphite }, Voltage.LV);
        addProcess(GTCombType.OLIVINE, new Material[] { Materials.Olivine, Materials.Bentonite, Materials.Magnesite,
                Materials.GlauconiteSand }, Voltage.LV);
        addProcess(GTCombType.EMERALD, new Material[] { Materials.Emerald, Materials.Beryllium, Materials.Thorium },
                Voltage.LV);
        addProcess(GTCombType.PYROPE,
                new Material[] { Materials.Pyrope, Materials.Aluminium, Materials.Magnesium, Materials.Silicon },
                Voltage.LV);
        addProcess(GTCombType.GROSSULAR,
                new Material[] { Materials.Grossular, Materials.Aluminium, Materials.Silicon }, Voltage.LV);
        addProcess(GTCombType.STONE,
                new Material[] { Materials.Stone, Materials.GraniteBlack, Materials.GraniteRed, Materials.Basalt,
                        Materials.Marble },
                Voltage.ULV);

        // Metals
        addProcess(GTCombType.COPPER, new Material[] { Materials.Copper, Materials.Tetrahedrite,
                Materials.Chalcopyrite, Materials.Malachite, Materials.Pyrite, Materials.Stibnite }, Voltage.LV);
        addProcess(GTCombType.TIN, new Material[] { Materials.Tin, Materials.Cassiterite, Materials.CassiteriteSand },
                Voltage.LV);
        addProcess(GTCombType.LEAD, new Material[] { Materials.Lead, Materials.Galena }, Voltage.LV);
        addProcess(GTCombType.NICKEL, new Material[] { Materials.Nickel, Materials.Garnierite, Materials.Pentlandite,
                Materials.Cobaltite, Materials.Wulfenite, Materials.Powellite }, Voltage.LV);
        addProcess(GTCombType.ZINC, new Material[] { Materials.Sphalerite, Materials.Sulfur }, Voltage.LV);
        addProcess(GTCombType.SILVER, new Material[] { Materials.Silver, Materials.Galena }, Voltage.LV);
        addProcess(GTCombType.GOLD, new Material[] { Materials.Gold, Materials.Magnetite }, Voltage.LV);
        addProcess(GTCombType.SULFUR, new Material[] { Materials.Sulfur, Materials.Pyrite, Materials.Sphalerite },
                Voltage.LV);
        addProcess(GTCombType.GALLIUM, new Material[] { Materials.Gallium, Materials.Niobium }, Voltage.LV);
        addProcess(GTCombType.ARSENIC, new Material[] { Materials.Realgar, Materials.Cassiterite, Materials.Zeolite },
                Voltage.LV);
        addProcess(
                GTCombType.IRON, new Material[] { Materials.Iron, Materials.Magnetite, Materials.BrownLimonite,
                        Materials.YellowLimonite, Materials.VanadiumMagnetite, Materials.BandedIron, Materials.Pyrite },
                Voltage.LV);
        addProcess(GTCombType.SLAG,
                new Material[] { Materials.Stone, Materials.GraniteBlack, Materials.GraniteRed }, Voltage.ULV);
        addProcess(GTCombType.COPPER, new Material[] { Materials.Copper }, Voltage.ULV);
        addProcess(GTCombType.TIN, new Material[] { Materials.Tin }, Voltage.ULV);
        addProcess(GTCombType.LEAD, new Material[] { Materials.Lead }, Voltage.ULV);
        addProcess(GTCombType.IRON, new Material[] { Materials.Iron }, Voltage.ULV);
        addProcess(GTCombType.STEEL, new Material[] { Materials.Steel }, Voltage.ULV);
        addProcess(GTCombType.SILVER, new Material[] { Materials.Silver }, Voltage.ULV);

        // Rare Metals
        addProcess(GTCombType.BAUXITE, new Material[] { Materials.Bauxite, Materials.Aluminium }, Voltage.LV);
        addProcess(GTCombType.ALUMINIUM, new Material[] { Materials.Aluminium, Materials.Bauxite }, Voltage.LV);
        addProcess(GTCombType.MANGANESE, new Material[] { Materials.Manganese, Materials.Grossular,
                Materials.Spessartine, Materials.Pyrolusite, Materials.Tantalite }, Voltage.LV);
        addProcess(GTCombType.TITANIUM,
                new Material[] { Materials.Titanium, Materials.Ilmenite, Materials.Bauxite, Materials.Rutile },
                Voltage.EV);
        addProcess(GTCombType.MAGNESIUM, new Material[] { Materials.Magnesium, Materials.Magnesite }, Voltage.LV);
        addProcess(GTCombType.CHROME, new Material[] { Materials.Chrome, Materials.Ruby, Materials.Chromite,
                Materials.Redstone, Materials.Neodymium, Materials.Bastnasite }, Voltage.HV);
        addProcess(GTCombType.TUNGSTEN,
                new Material[] { Materials.Tungsten, Materials.Tungstate, Materials.Scheelite, Materials.Lithium },
                Voltage.IV);
        addProcess(GTCombType.PLATINUM,
                new Material[] { Materials.Platinum, Materials.Cooperite, Materials.Palladium }, Voltage.HV);
        addProcess(GTCombType.MOLYBDENUM, new Material[] { Materials.Molybdenum, Materials.Molybdenite,
                Materials.Powellite, Materials.Wulfenite }, Voltage.LV);
        addProcess(GTCombType.LITHIUM,
                new Material[] { Materials.Lithium, Materials.Lepidolite, Materials.Spodumene }, Voltage.MV);
        addProcess(GTCombType.SALT, new Material[] { Materials.Salt, Materials.RockSalt, Materials.Saltpeter },
                Voltage.MV);
        addProcess(GTCombType.ELECTROTINE,
                new Material[] { Materials.Electrotine, Materials.Electrum, Materials.Redstone }, Voltage.MV);
        addProcess(GTCombType.SALT,
                new Material[] { Materials.Salt, Materials.RockSalt, Materials.Saltpeter }, Voltage.MV);
        addProcess(GTCombType.INDIUM, new Material[] { Materials.Indium, Materials.IridiumMetalResidue }, Voltage.IV);
        addProcess(GTCombType.OSMIUM, new Material[] { Materials.Osmium, Materials.AcidicOsmiumSolution }, Voltage.IV);
        addProcess(GTCombType.INDIUM, new Material[] { Materials.Indium }, Voltage.EV);

        // Radioactive
        addProcess(GTCombType.ALMANDINE,
                new Material[] { Materials.Almandine, Materials.Pyrope, Materials.Sapphire, Materials.GreenSapphire },
                Voltage.LV);
        addProcess(GTCombType.URANIUM, new Material[] { Materials.Uranium238, Materials.Pitchblende,
                Materials.Uraninite, Materials.Uranium235 }, Voltage.EV);
        addProcess(GTCombType.PLUTONIUM, new Material[] { Materials.Plutonium239, Materials.Uranium235 }, Voltage.EV);
        addProcess(GTCombType.NAQUADAH,
                new Material[] { Materials.Naquadah, Materials.NaquadahEnriched, Materials.Naquadria }, Voltage.IV);
        addProcess(GTCombType.NAQUADRIA,
                new Material[] { Materials.Naquadria, Materials.NaquadahEnriched, Materials.Naquadah }, Voltage.LUV);
        addProcess(GTCombType.THORIUM, new Material[] { Materials.Thorium, Materials.Uranium238, Materials.Coal },
                Voltage.EV);
        addProcess(GTCombType.LUTETIUM, new Material[] { Materials.Lutetium, Materials.Thorium }, Voltage.IV);
        addProcess(GTCombType.AMERICIUM, new Material[] { Materials.Americium, Materials.Lutetium }, Voltage.LUV);
        addProcess(GTCombType.TRINIUM, new Material[] { Materials.Trinium, Materials.Naquadah, Materials.Naquadria },
                Voltage.ZPM);

        // Special Neutronium Recipe
        addProcess(GTCombType.NEUTRONIUM, new Material[] { Materials.Neutronium }, Voltage.UV);

        if (Mods.MagicBees.isModLoaded()) {
            addProcess(GTCombType.SPARKLING, new Material[] { Materials.NetherStar }, Voltage.EV);

        }

        addProcess(GTCombType.HELIUM, new Material[] { Materials.Helium }, Voltage.HV);
        addProcess(GTCombType.ARGON, new Material[] { Materials.Argon }, Voltage.MV);
        addProcess(GTCombType.XENON, new Material[] { Materials.Xenon }, Voltage.IV);
        addProcess(GTCombType.NEON, new Material[] { Materials.Neon }, Voltage.IV);
        addProcess(GTCombType.KRYPTON, new Material[] { Materials.Krypton }, Voltage.IV);
        addProcess(GTCombType.NITROGEN, new Material[] { Materials.Nitrogen }, Voltage.MV);
        addProcess(GTCombType.OXYGEN, new Material[] { Materials.Oxygen }, Voltage.MV);
        addProcess(GTCombType.HYDROGEN, new Material[] { Materials.Hydrogen }, Voltage.MV);
        addProcess(GTCombType.FLUORINE, new Material[] { Materials.Fluorine }, Voltage.MV);

        if (Mods.AppliedEnergistics2.isModLoaded()) {
            ItemStack fluixDust = OreDictUnifier.get("dustFluix");
            if (fluixDust == ItemStack.EMPTY) {
                fluixDust = Api.INSTANCE.definitions().materials().fluixDust().maybeStack(1).orElse(ItemStack.EMPTY);
            }
            if (fluixDust != ItemStack.EMPTY) {
                MRRecipeMaps.REPLICATION_RECIPES.recipeBuilder()
                        .inputs(ForestryUtil.getCombStack(GTCombType.FLUIX, 9))
                        .fluidInputs(
                                Materials.UUMatter.getFluid(10 * Voltage.ULV.getUUMatterAmount()))
                        .outputs(GTUtility.copy(4, fluixDust))
                        .duration(512)
                        .EUt(Voltage.ULV.getReplicateEnergy())
                        .cleanroom(CleanroomType.CLEANROOM)
                        .buildAndRegister();
            }
        }
    }

    public static void addProcess(GTCombType comb, Material[] material, Voltage v) {
        for (int i = 0; i < material.length; i++) {
            if (OreDictUnifier.get(OrePrefix.crushedPurified, material[i], 4).isEmpty() &&
                    OreDictUnifier.get(OrePrefix.dust, material[i], 4).isEmpty()) {
                addReplicationProcessFluid(comb, material[i], v, i + 1);
            }
            addReplicationProcess(comb, material[i], v, i + 1);
        }
    }

    public static void addReplicationProcess(GTCombType comb, Material material, Voltage v, int number) {
        OrePrefix prefix = OreDictUnifier.get(OrePrefix.crushedPurified, material).isEmpty() ?
                OrePrefix.dust : OrePrefix.crushedPurified;
        CleanroomType type = v.compareTo(Voltage.EV) > 0 ? CleanroomType.STERILE_CLEANROOM : CleanroomType.CLEANROOM;
        MRRecipeMaps.REPLICATION_RECIPES.recipeBuilder()
                .inputs(GTUtility.copy(9, ForestryUtil.getCombStack(comb)))
                .circuitMeta(number)
                .fluidInputs(
                        Materials.UUMatter.getFluid(10 * (int) Math.max(1, material.getMass() + v.getUUMatterAmount())))
                .output(prefix, material, 4)
                .duration((int) (material.getMass() * 512))
                .EUt(v.getReplicateEnergy())
                .cleanroom(type)
                .buildAndRegister();
    }

    public static void addReplicationProcessFluid(GTCombType comb, Material material, Voltage v, int number) {
        CleanroomType type = v.compareTo(Voltage.EV) > 0 ? CleanroomType.STERILE_CLEANROOM : CleanroomType.CLEANROOM;
        MRRecipeMaps.REPLICATION_RECIPES.recipeBuilder()
                .inputs(GTUtility.copy(9, ForestryUtil.getCombStack(comb)))
                .circuitMeta(number)
                .fluidInputs(
                        Materials.UUMatter.getFluid(10 * (int) Math.max(1, material.getMass() + v.getUUMatterAmount())))
                .fluidOutputs(material.getFluid(1000))
                .duration((int) (material.getMass() * 512))
                .EUt(v.getReplicateEnergy())
                .cleanroom(type)
                .buildAndRegister();
    }

    private enum Voltage {

        ULV,
        LV,
        MV,
        HV,
        EV,
        IV,
        LUV,
        ZPM,
        UV,
        UHV,
        UEV,
        UIV,
        UXV,
        OPV,
        MAX;

        public int getVoltage() {
            return (int) GTValues.V[ordinal()];
        }

        public int getReplicateEnergy() {
            return (int) ((getVoltage() * 3 / 4) * Math.max(1, Math.pow(2, 5 - ordinal())));
        }

        public int getUUMatterAmount() {
            return 9 * ((this.compareTo(Voltage.MV) < 0) ? 10 : 10 * this.compareTo(Voltage.MV));
        }
    }
}
