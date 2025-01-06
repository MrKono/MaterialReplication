package kono.ceu.materialreplication.common.machines.multi.antimatterreactor;

import net.minecraft.block.state.IBlockState;
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
import gregtech.api.unification.material.Materials;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.MetaBlocks;

import kono.ceu.materialreplication.api.recipes.MRRecipeMaps;
import kono.ceu.materialreplication.client.MRTextures;
import kono.ceu.materialreplication.common.blocks.BlockAntimatterCasing;
import kono.ceu.materialreplication.common.blocks.MRBlocks;

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
