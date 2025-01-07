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

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
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

public class MetaTileEntityProcessorModule extends RecipeMapMultiblockController {

    public MetaTileEntityProcessorModule(ResourceLocation metaTileEntity) {
        super(metaTileEntity, MRRecipeMaps.ANTIMATTER_PROCESSOR);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityProcessorModule(metaTileEntityId);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("###############", "######PGP######", "###############")
                .aisle("######CCC######", "####GGAAAGG####", "######CCC######")
                .aisle("####CC###CC####", "###PAAPGPAAP###", "####CC###CC####")
                .aisle("###C#######C###", "##PHPG#F#GPHP##", "###C#######C###")
                .aisle("##C#########C##", "#GAP###F###PAG#", "##C#########C##")
                .aisle("##C#########C##", "#GAG###F###GAG#", "##C#########C##")
                .aisle("#C###########C#", "GBG####P####GBG", "#C###########C#")
                .aisle("#C###########C#", "GAGFFFPRPFFFGAG", "#C###########C#")
                .aisle("#C###########C#", "GBG####P####GBG", "#C###########C#")
                .aisle("##C#########C##", "#GAG###F###GAG#", "##C#########C##")
                .aisle("##C#########C##", "#GAP###F###PAG#", "##C#########C##")
                .aisle("###C#######C###", "##PHPG#F#GPHP##", "###C#######C###")
                .aisle("####CC###CC####", "###PAAPGPAAP###", "####CC###CC####")
                .aisle("######CCC######", "####GGAAAGG####", "######CCC######")
                .aisle("###############", "######PSP######", "###############")
                .where('S', selfPredicate())
                .where('G', states(getGlassState()).or(states(getCasingState())))
                .where('C', states(getCasingState()))
                .where('H',
                        states(MRBlocks.ANTIMATTER_CASING
                                .getState(BlockAntimatterCasing.AntimatterCasingType.HELIUM_COOLANT)))
                .where('P',
                        states(getGlassState()).or(states(getCasingState()))
                                .or(autoAbilities(true, false, true, true, true, true, false)))
                .where('A', air())
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
                .aisle("###############", "######GGG######", "###############")
                .aisle("######CCC######", "####GGAAAGG####", "######CCC######")
                .aisle("####CC###CC####", "###GAAGGGAAG###", "####CC###CC####")
                .aisle("###C#######C###", "##GHOG#F#GMHG##", "###C#######C###")
                .aisle("##C#########C##", "#GAG###F###GAG#", "##C#########C##")
                .aisle("##C#########C##", "#GAG###F###GAG#", "##C#########C##")
                .aisle("#C###########C#", "GBG####G####GBG", "#C###########C#")
                .aisle("#C###########C#", "GAGFFFGRGFFFGAG", "#C###########C#")
                .aisle("#C###########C#", "GBG####G####GBG", "#C###########C#")
                .aisle("##C#########C##", "#GAG###F###GAG#", "##C#########C##")
                .aisle("##C#########C##", "#GAG###F###GAG#", "##C#########C##")
                .aisle("###C#######C###", "##IHEG#F#GeHi##", "###C#######C###")
                .aisle("####CC###CC####", "###GAAGGGAAG###", "####CC###CC####")
                .aisle("######CCC######", "####GGAAAGG####", "######CCC######")
                .aisle("###############", "######GSG######", "###############")
                .where('S', MRMetaTileEntities.MODULE_ANTIMATTER_PROCESSOR, EnumFacing.SOUTH)
                .where('G', getGlassState())
                .where('C', getCasingState())
                .where('H',
                        MRBlocks.ANTIMATTER_CASING.getState(BlockAntimatterCasing.AntimatterCasingType.HELIUM_COOLANT))
                .where('A', Blocks.AIR.getDefaultState())
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[UV], EnumFacing.EAST)
                .where('e', MetaTileEntities.ENERGY_INPUT_HATCH[UV], EnumFacing.WEST)
                .where('I', MetaTileEntities.FLUID_IMPORT_HATCH[LV], EnumFacing.WEST)
                .where('i', MetaTileEntities.FLUID_IMPORT_HATCH[LV], EnumFacing.EAST)
                .where('M', MetaTileEntities.ITEM_IMPORT_BUS[LV], EnumFacing.SOUTH)
                .where('O', MetaTileEntities.FLUID_EXPORT_HATCH[LV], EnumFacing.SOUTH)
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
