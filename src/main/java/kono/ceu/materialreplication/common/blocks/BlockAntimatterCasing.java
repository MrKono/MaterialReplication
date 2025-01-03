package kono.ceu.materialreplication.common.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import org.jetbrains.annotations.NotNull;

import gregtech.api.block.VariantBlock;
import gregtech.api.items.toolitem.ToolClasses;

public class BlockAntimatterCasing extends VariantBlock<BlockAntimatterCasing.AntimatterCasingType> {

    public BlockAntimatterCasing() {
        super(Material.IRON);
        setTranslationKey("mr_antimatter_casing");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel(ToolClasses.WRENCH, 3);
        setDefaultState(getState(AntimatterCasingType.ANTIMATTER_REACTOR_CASING));
    }

    @Override
    public boolean canCreatureSpawn(@NotNull IBlockState state, @NotNull IBlockAccess world, @NotNull BlockPos pos,
                                    @NotNull EntityLiving.SpawnPlacementType type) {
        return false;
    }

    public enum AntimatterCasingType implements IStringSerializable {

        ANTIMATTER_REACTOR_CASING("antimatter_reactor_casing"),
        ANTIMATTER_REACTOR_CORE("antimatter_reactor_core"),
        HELIUM_COOLANT("helium_coolant");

        private final String name;

        AntimatterCasingType(String name) {
            this.name = name;
        }

        @NotNull
        @Override
        public String getName() {
            return this.name;
        }
    }
}
