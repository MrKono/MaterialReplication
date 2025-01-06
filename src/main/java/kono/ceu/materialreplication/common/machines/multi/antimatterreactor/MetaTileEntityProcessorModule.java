package kono.ceu.materialreplication.common.machines.multi.antimatterreactor;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.jetbrains.annotations.NotNull;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.unification.material.Materials;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.MetaBlocks;

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
                .aisle("###############", "######OGO######", "###############")
                .aisle("######ICI######", "####GGAAAGG####", "######ICI######")
                .aisle("####CC###CC####", "###GAAOGOAAG###", "####CC###CC####")
                .aisle("###C#######C###", "##GHEG#F#GEHG##", "###C#######C###")
                .aisle("##C#########C##", "#GAE###F###EAG#", "##C#########C##")
                .aisle("##C#########C##", "#GAG###F###GAG#", "##C#########C##")
                .aisle("#C###########C#", "OBO####F####OBO", "#C###########C#")
                .aisle("#C###########C#", "GAGFFFFRFFFFGAG", "#C###########C#")
                .aisle("#C###########C#", "OBO####F####OBO", "#C###########C#")
                .aisle("##C#########C##", "#GAG###F###GAG#", "##C#########C##")
                .aisle("##C#########C##", "#GAE###F###EAG#", "##C#########C##")
                .aisle("###C#######C###", "##GHEG#F#GEHG##", "###C#######C###")
                .aisle("####CC###CC####", "###GAAOGOAAG###", "####CC###CC####")
                .aisle("######ICI######", "####GGAAAGG####", "######ICI######")
                .aisle("###############", "######OSO######", "###############")
                .where('S', selfPredicate())
                .where('G', states(getGlassState()).or(states(getCasingState())))
                .where('E',
                        states(getGlassState()).or(states(getCasingState()))
                                .or(abilities(MultiblockAbility.INPUT_ENERGY).setMinGlobalLimited(1)
                                        .setMaxGlobalLimited(3)))
                .where('C', states(getCasingState()))
                .where('H',
                        states(MRBlocks.ANTIMATTER_CASING
                                .getState(BlockAntimatterCasing.AntimatterCasingType.HELIUM_COOLANT)))
                .where('A', air())
                .where('I',
                        states(getCasingState()).or(abilities(MultiblockAbility.IMPORT_FLUIDS).setMinGlobalLimited(2))
                                .or(abilities(MultiblockAbility.IMPORT_ITEMS).setMinGlobalLimited(1)))
                .where('O', states(getGlassState()).or(abilities(MultiblockAbility.EXPORT_FLUIDS)))
                .where('R', states(getCoreState()))
                .where('F', states(MetaBlocks.FRAMES.get(Materials.NaquadahAlloy).getBlock(Materials.NaquadahAlloy)))
                .where('B', air().or(states(getCasingState())))
                .where('#', any())
                .build();
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
