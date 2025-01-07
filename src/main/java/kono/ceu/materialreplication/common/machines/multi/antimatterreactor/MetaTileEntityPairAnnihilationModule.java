package kono.ceu.materialreplication.common.machines.multi.antimatterreactor;

import static gregtech.api.GTValues.*;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.jetbrains.annotations.NotNull;

import gregtech.api.GTValues;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.*;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.MultiblockShapeInfo;
import gregtech.api.unification.material.Materials;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;

import kono.ceu.materialreplication.api.recipes.MRRecipeMaps;
import kono.ceu.materialreplication.client.MRTextures;
import kono.ceu.materialreplication.common.blocks.BlockAntimatterCasing;
import kono.ceu.materialreplication.common.blocks.MRBlocks;
import kono.ceu.materialreplication.common.machines.MRMetaTileEntities;

public class MetaTileEntityPairAnnihilationModule extends FuelMultiblockController {

    public MetaTileEntityPairAnnihilationModule(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, MRRecipeMaps.PAIR_ANNIHILATION_FUELS, GTValues.UIV);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityPairAnnihilationModule(metaTileEntityId);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("###############", "######CCC######", "####CC###CC####", "###C#######C###", "##C#########C##",
                        "##C#########C##", "#C###########C#", "#C###########C#", "#C###########C#", "##C#########C##",
                        "##C#########C##", "###C#######C###", "####CC###CC####", "######CCC######", "###############")
                .aisle("######PGP######", "####GGAAAGG####", "###PAAPGPAAP###", "##PHPG#F#GPHP##", "#GAP###F###PAG#",
                        "#GAG###F###GAG#", "GBG####P####GBG", "GAGFFFPRPFFFGAG", "GBG####P####GBG", "#GAG###F###GAG#",
                        "#GAP###F###PAG#", "##PHPG#F#GPHP##", "###PAAPGPAAP###", "####GGAAAGG####", "######PSP######")
                .aisle("###############", "######CCC######", "####CC###CC####", "###C#######C###", "##C#########C##",
                        "##C#########C##", "#C###########C#", "#C###########C#", "#C###########C#", "##C#########C##",
                        "##C#########C##", "###C#######C###", "####CC###CC####", "######CCC######", "###############")
                .where('S', selfPredicate())
                .where('G', states(getGlassState()).or(states(getCasingState())))
                .where('C', states(getCasingState()))
                .where('H',
                        states(MRBlocks.ANTIMATTER_CASING
                                .getState(BlockAntimatterCasing.AntimatterCasingType.HELIUM_COOLANT)))
                .where('A', air())
                .where('P',
                        states(getGlassState()).or(states(getCasingState()))
                                .or(autoAbilities(false, false, true, true, true, true, false))
                                .or(abilities(MultiblockAbility.OUTPUT_ENERGY).setMinGlobalLimited(1)
                                        .setMaxGlobalLimited(2)))
                .where('R', states(getCoreState()))
                .where('F', states(MetaBlocks.FRAMES.get(Materials.NaquadahAlloy).getBlock(Materials.NaquadahAlloy)))
                .where('B', air().or(states(getCasingState())))
                .where('#', any())
                .build();
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        List<MultiblockShapeInfo> shapeInfos = new ArrayList<>();

        MultiblockShapeInfo.Builder baseBuilder = MultiblockShapeInfo.builder()
                .aisle("###############", "######CCC######", "####CC###CC####", "###C#######C###", "##C#########C##",
                        "##C#########C##", "#C###########C#", "#C###########C#", "#C###########C#", "##C#########C##",
                        "##C#########C##", "###C#######C###", "####CC###CC####", "######CCC######", "###############")
                .aisle("######GGG######", "####GGAAAGG####", "###GAAGGGAAG###", "##GHGG#F#GGHG##", "#GAG###F###GAG#",
                        "#GAG###F###GAG#", "GBG####G####GBG", "GAGFFFGRGFFFGAG", "GBG####G####GBG", "#GAG###F###GAG#",
                        "#GAG###F###GAG#", "##IHEG#F#GeHi##", "###GAAGGGAAG###", "####GGAAAGG####", "######GSG######")
                .aisle("###############", "######CCC######", "####CC###CC####", "###C#######C###", "##C#########C##",
                        "##C#########C##", "#C###########C#", "#C###########C#", "#C###########C#", "##C#########C##",
                        "##C#########C##", "###C#######C###", "####CC###CC####", "######CCC######", "###############")
                .where('S', MRMetaTileEntities.MODULE_PAIR_ANNIHILATION, EnumFacing.SOUTH)
                .where('G', getGlassState())
                .where('C', getCasingState())
                .where('H',
                        MRBlocks.ANTIMATTER_CASING.getState(BlockAntimatterCasing.AntimatterCasingType.HELIUM_COOLANT))
                .where('A', Blocks.AIR.getDefaultState())
                .where('E', MetaTileEntities.ENERGY_OUTPUT_HATCH[UV], EnumFacing.EAST)
                .where('e', MetaTileEntities.ENERGY_OUTPUT_HATCH[UV], EnumFacing.WEST)
                .where('I', MetaTileEntities.FLUID_IMPORT_HATCH[LV], EnumFacing.WEST)
                .where('i', MetaTileEntities.FLUID_IMPORT_HATCH[LV], EnumFacing.EAST)
                .where('R', getCoreState())
                .where('F', MetaBlocks.FRAMES.get(Materials.NaquadahAlloy).getBlock(Materials.NaquadahAlloy))
                .where('B', getCasingState())
                .where('#', Blocks.AIR.getDefaultState());

        // Glass -> Casing
        shapeInfos.add(baseBuilder.shallowCopy()
                .where('G', getCasingState())
                .build());

        // Air -> Casing
        shapeInfos.add(baseBuilder.shallowCopy()
                .where('B', getCasingState())
                .build());

        // Glass -> Casing, Air -> Casing
        shapeInfos.add(baseBuilder.shallowCopy()
                .where('G', getCasingState())
                .where('B', getCasingState())
                .build());

        shapeInfos.add(baseBuilder.build());
        return shapeInfos;
    }

    private IBlockState getCasingState() {
        return MRBlocks.ANTIMATTER_CASING
                .getState(BlockAntimatterCasing.AntimatterCasingType.ANTIMATTER_REACTOR_CASING);
    }

    private IBlockState getGlassState() {
        return MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.FUSION_GLASS);
    }

    private IBlockState getCoreState() {
        return MRBlocks.ANTIMATTER_CASING.getState(BlockAntimatterCasing.AntimatterCasingType.ANTIMATTER_REACTOR_CORE);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return MRTextures.ANTIMATTER_REACTOR;
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return Textures.FUSION_REACTOR_OVERLAY;
    }

    @Override
    public boolean hasMaintenanceMechanics() {
        return false;
    }
}
