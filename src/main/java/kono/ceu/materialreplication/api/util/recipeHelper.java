package kono.ceu.materialreplication.api.util;

import gregtech.api.GTValues;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.common.items.MetaItems;

public class recipeHelper {

    public static MetaItem<?>.MetaValueItem MOTOR(int voltage) {
        return switch (voltage) {
            case 1 -> MetaItems.ELECTRIC_MOTOR_LV;
            case 2 -> MetaItems.ELECTRIC_MOTOR_MV;
            case 3 -> MetaItems.ELECTRIC_MOTOR_HV;
            case 4 -> MetaItems.ELECTRIC_MOTOR_EV;
            case 5 -> MetaItems.ELECTRIC_MOTOR_IV;
            case 6 -> MetaItems.ELECTRIC_MOTOR_LuV;
            case 7 -> MetaItems.ELECTRIC_MOTOR_ZPM;
            case 8 -> MetaItems.ELECTRIC_MOTOR_UV;
            default -> throw new IllegalStateException("Out of Voltage: " + GTValues.VN[voltage]);
        };
    }

    public static MetaItem<?>.MetaValueItem PUMP(int voltage) {
        return switch (voltage) {
            case 1 -> MetaItems.ELECTRIC_PUMP_LV;
            case 2 -> MetaItems.ELECTRIC_PUMP_MV;
            case 3 -> MetaItems.ELECTRIC_PUMP_HV;
            case 4 -> MetaItems.ELECTRIC_PUMP_EV;
            case 5 -> MetaItems.ELECTRIC_PUMP_IV;
            case 6 -> MetaItems.ELECTRIC_PUMP_LuV;
            case 7 -> MetaItems.ELECTRIC_PUMP_ZPM;
            case 8 -> MetaItems.ELECTRIC_PUMP_UV;
            default -> throw new IllegalStateException("Out of Voltage: " + GTValues.VN[voltage]);
        };
    }

    public static MetaItem<?>.MetaValueItem REGULATOR(int voltage) {
        return switch (voltage) {
            case 1 -> MetaItems.FLUID_REGULATOR_LV;
            case 2 -> MetaItems.FLUID_REGULATOR_MV;
            case 3 -> MetaItems.FLUID_REGULATOR_HV;
            case 4 -> MetaItems.FLUID_REGULATOR_EV;
            case 5 -> MetaItems.FLUID_REGULATOR_IV;
            case 6 -> MetaItems.FLUID_REGULATOR_LUV;
            case 7 -> MetaItems.FLUID_REGULATOR_ZPM;
            case 8 -> MetaItems.FLUID_REGULATOR_UV;
            default -> throw new IllegalStateException("Out of Voltage: " + GTValues.VN[voltage]);
        };
    }

    public static MetaItem<?>.MetaValueItem PISTON(int voltage) {
        return switch (voltage) {
            case 1 -> MetaItems.ELECTRIC_PISTON_LV;
            case 2 -> MetaItems.ELECTRIC_PISTON_MV;
            case 3 -> MetaItems.ELECTRIC_PISTON_HV;
            case 4 -> MetaItems.ELECTRIC_PISTON_EV;
            case 5 -> MetaItems.ELECTRIC_PISTON_IV;
            case 6 -> MetaItems.ELECTRIC_PISTON_LUV;
            case 7 -> MetaItems.ELECTRIC_PISTON_ZPM;
            case 8 -> MetaItems.ELECTRIC_PISTON_UV;
            default -> throw new IllegalStateException("Out of Voltage: " + GTValues.VN[voltage]);
        };
    }

    public static MetaItem<?>.MetaValueItem ROBOT_ARM(int voltage) {
        return switch (voltage) {
            case 1 -> MetaItems.ROBOT_ARM_LV;
            case 2 -> MetaItems.ROBOT_ARM_MV;
            case 3 -> MetaItems.ROBOT_ARM_HV;
            case 4 -> MetaItems.ROBOT_ARM_EV;
            case 5 -> MetaItems.ROBOT_ARM_IV;
            case 6 -> MetaItems.ROBOT_ARM_LuV;
            case 7 -> MetaItems.ROBOT_ARM_ZPM;
            case 8 -> MetaItems.ROBOT_ARM_UV;
            default -> throw new IllegalStateException("Out of Voltage: " + GTValues.VN[voltage]);
        };
    }

    public static MetaItem<?>.MetaValueItem FIELD_GENERATOR(int voltage) {
        return switch (voltage) {
            case 1 -> MetaItems.FIELD_GENERATOR_LV;
            case 2 -> MetaItems.FIELD_GENERATOR_MV;
            case 3 -> MetaItems.FIELD_GENERATOR_HV;
            case 4 -> MetaItems.FIELD_GENERATOR_EV;
            case 5 -> MetaItems.FIELD_GENERATOR_IV;
            case 6 -> MetaItems.FIELD_GENERATOR_LuV;
            case 7 -> MetaItems.FIELD_GENERATOR_ZPM;
            case 8 -> MetaItems.FIELD_GENERATOR_UV;
            default -> throw new IllegalStateException("Out of Voltage: " + GTValues.VN[voltage]);
        };
    }

    public static MetaItem<?>.MetaValueItem CONVEYOR_MODULE(int voltage) {
        return switch (voltage) {
            case 1 -> MetaItems.CONVEYOR_MODULE_LV;
            case 2 -> MetaItems.CONVEYOR_MODULE_MV;
            case 3 -> MetaItems.CONVEYOR_MODULE_HV;
            case 4 -> MetaItems.CONVEYOR_MODULE_EV;
            case 5 -> MetaItems.CONVEYOR_MODULE_IV;
            case 6 -> MetaItems.CONVEYOR_MODULE_LuV;
            case 7 -> MetaItems.CONVEYOR_MODULE_ZPM;
            case 8 -> MetaItems.CONVEYOR_MODULE_UV;
            default -> throw new IllegalStateException("Out of Voltage: " + GTValues.VN[voltage]);
        };
    }

    public static MetaItem<?>.MetaValueItem EMITTER(int voltage) {
        return switch (voltage) {
            case 1 -> MetaItems.EMITTER_LV;
            case 2 -> MetaItems.EMITTER_MV;
            case 3 -> MetaItems.EMITTER_HV;
            case 4 -> MetaItems.EMITTER_EV;
            case 5 -> MetaItems.EMITTER_IV;
            case 6 -> MetaItems.EMITTER_LuV;
            case 7 -> MetaItems.EMITTER_ZPM;
            case 8 -> MetaItems.EMITTER_UV;
            default -> throw new IllegalStateException("Out of Voltage: " + GTValues.VN[voltage]);
        };
    }

    public static MetaItem<?>.MetaValueItem SENSOR(int voltage) {
        return switch (voltage) {
            case 1 -> MetaItems.SENSOR_LV;
            case 2 -> MetaItems.SENSOR_MV;
            case 3 -> MetaItems.SENSOR_HV;
            case 4 -> MetaItems.SENSOR_EV;
            case 5 -> MetaItems.SENSOR_IV;
            case 6 -> MetaItems.SENSOR_LuV;
            case 7 -> MetaItems.SENSOR_ZPM;
            case 8 -> MetaItems.SENSOR_UV;
            default -> throw new IllegalStateException("Out of Voltage: " + GTValues.VN[voltage]);
        };
    }

    public static String oreDictCircuit(int voltage) {
        return switch (voltage) {
            case 0 -> "circuitUlv";
            case 1 -> "circuitLv";
            case 2 -> "circuitMv";
            case 3 -> "circuitHv";
            case 4 -> "circuitEv";
            case 5 -> "circuitIv";
            case 6 -> "circuitLuv";
            case 7 -> "circuitZpm";
            case 8 -> "circuitUv";
            case 9 -> "circuitUhv";
            case 10 -> "circuitUev";
            case 11 -> "circuitUiv";
            case 12 -> "circuitUxv";
            case 13 -> "circuitOpv";
            default -> throw new IllegalStateException("Out of Voltage: " + GTValues.VN[voltage]);
        };
    }
}
