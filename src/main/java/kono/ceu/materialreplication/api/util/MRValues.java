package kono.ceu.materialreplication.api.util;

import net.minecraft.util.ResourceLocation;

import org.jetbrains.annotations.NotNull;

import kono.ceu.materialreplication.MRConfig;

public class MRValues {

    public static final String MODNAME = "Material Replication";
    public static final String MODID = "materialreplication";
    public static int baseID = rangeUtil(MRConfig.id.startId, 11000, 32200, "MetaTileEntityID") ?
            MRConfig.id.startId : 20000,
            BaseTime_D = rangeTime(MRConfig.deconstruction.DeconstructionBaseTime) ?
                    MRConfig.deconstruction.DeconstructionBaseTime : 600,
            BaseTime_R = rangeTime(MRConfig.replication.ReplicationBaseTime) ?
                    MRConfig.replication.ReplicationBaseTime : 1200,
            BaseTime_S = rangeTime(MRConfig.replication.ScanTime) ?
                    MRConfig.replication.ScanTime : 1200,
            Voltage_D = rangeVoltage(MRConfig.deconstruction.DeconstructionVoltage) ?
                    MRConfig.deconstruction.DeconstructionVoltage : 30,
            Voltage_R = rangeVoltage(MRConfig.replication.ReplicationVoltage) ?
                    MRConfig.replication.ReplicationVoltage : 30,
            Voltage_S = rangeVoltage(MRConfig.replication.ScanVoltage) ?
                    MRConfig.replication.ScanVoltage : 30,
            ScrapChance = rangeChance(MRConfig.scrap.ScrapChance) ?
                    MRConfig.scrap.ScrapChance : 1,
            ScrapChanceBoost = rangeChance(MRConfig.scrap.ScrapChanceBoost) ?
                    MRConfig.scrap.ScrapChanceBoost : 0,
            AmplifierChance = rangeChance(MRConfig.scrap.AmplifierChance) ?
                    MRConfig.scrap.AmplifierChance : 1,
            AmplifierChanceBoost = rangeChance(MRConfig.scrap.AmplifierChanceBoost) ?
                    MRConfig.scrap.AmplifierChanceBoost : 0,
            tierDeconstruct = rangeUtil(MRConfig.tier.tierDeconstruct, 1, 8, "tier") ?
                    MRConfig.tier.tierDeconstruct : 1,
            tierReplicate = rangeUtil(MRConfig.tier.tierReplicate, 1, 8, "tier") ?
                    MRConfig.tier.tierReplicate : 1,
            tierLargeDeconstruct = rangeUtil(MRConfig.tier.tierLargeDeconstruct, 6, 8, "tier") ?
                    MRConfig.tier.tierLargeDeconstruct : 6,
            ChargedMatterAmount = MRConfig.recipe.matterRatio[0],
            NeutralMatterAmount = MRConfig.recipe.matterRatio[1],
            UUMatterAmount = MRConfig.recipe.matterRatio[2] > 0 ? MRConfig.recipe.matterRatio[2] : 1;

    public static @NotNull ResourceLocation mrId(@NotNull String path) {
        return new ResourceLocation(MODID, path);
    }

    private static boolean rangeTime(int time) {
        if (time <= 0) {
            MaterialReplicationLog.logger.error("Base Time must be greater than 0! Set to default value.");
            return false;
        }
        return true;
    }

    private static boolean rangeVoltage(int voltage) {
        if (voltage <= 0) {
            MaterialReplicationLog.logger.error("Base Voltage must be greater than 0! Set to default value.");
            return false;
        }
        return true;
    }

    private static boolean rangeChance(int chance) {
        if (chance < 0 || 100000 < chance) {
            MaterialReplicationLog.logger.error("Output Chance must be 0 to 100000! Set to default value.");
            return false;
        }
        return true;
    }

    private static boolean rangeUtil(int val, int min, int max, String str) {
        if (val < min || max < val) {
            MaterialReplicationLog.logger.error("Start" + str + "is over range! Set to default value.");
            return false;
        }
        return true;
    }
}
