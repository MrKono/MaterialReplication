package kono.ceu.materialreplication.common.machines.multi;

import gregicality.multiblocks.api.metatileentity.GCYMRecipeMapMultiblockController;
import gregicality.multiblocks.api.render.GCYMTextures;
import gregicality.multiblocks.common.block.GCYMMetaBlocks;
import gregicality.multiblocks.common.block.blocks.BlockLargeMultiblockCasing;
import gregicality.multiblocks.common.block.blocks.BlockUniqueCasing;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtech.common.blocks.BlockFusionCasing;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.MetaBlocks;
import kono.ceu.materialreplication.api.recipes.MRRecipeMaps;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static kono.ceu.materialreplication.api.util.MRValues.tierLargeDeconstruct;
import static kono.ceu.materialreplication.client.MRTextures.LARGE_DECONSTRUCTOR_OVERLAY;

public class MetaTileEntityLargeDeconstructor extends GCYMRecipeMapMultiblockController {

    public MetaTileEntityLargeDeconstructor(ResourceLocation metaTileEntityID) {
        super(metaTileEntityID, MRRecipeMaps.DECONSTRUCTION_RECIPES);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity metaTileEntityID) {
        return new MetaTileEntityLargeDeconstructor(this.metaTileEntityId);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXXXX", "XGGGX", "XFVFX", "XGGGX", "XXXXX")
                .aisle("XXXXX", "GAAAG", "FACAF", "GAAAG", "XXXXX")
                .aisle("XXVXX", "GACAG", "VCTCV", "GACAG", "XXVXX")
                .aisle("XXXXX", "GAAAG", "FACAF", "GAAAG", "XXXXX")
                .aisle("XXXXX", "XGGGX", "XFSFX", "XGGGX", "XXXXX")
                .where('S', selfPredicate())
                .where('X', states(getCasingState()).setMinGlobalLimited(45).or(autoAbilities()))
                .where('V', states(getCasingState2()))
                .where('F', states(getCasingState3()))
                .where('C', states(getCasingStateCoil()))
                .where('G', states(getCasingStateGlass()))
                .where('T', tieredCasing().or(states(getCasingState())))
                .where('A', air())
                .build();
    }

    private static IBlockState getCasingState() {
        return GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.ATOMIC_CASING);
    }

    private static IBlockState getCasingState2() {
        return GCYMMetaBlocks.UNIQUE_CASING.getState(BlockUniqueCasing.UniqueCasingType.HEAT_VENT);
    }

    private static IBlockState getCasingState3() {
        return MetaBlocks.FUSION_CASING.getState(switch (tierLargeDeconstruct) {
            case 6 -> BlockFusionCasing.CasingType.FUSION_CASING;
            case 7 -> BlockFusionCasing.CasingType.FUSION_CASING_MK2;
            case 8 -> BlockFusionCasing.CasingType.FUSION_CASING_MK3;
            default -> throw new IllegalStateException("Unexpected value: " + tierLargeDeconstruct);
        });
    }

    private static IBlockState getCasingStateCoil() {
        return MetaBlocks.FUSION_CASING.getState(BlockFusionCasing.CasingType.SUPERCONDUCTOR_COIL);
    }

    private static IBlockState getCasingStateGlass() {
        return MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.FUSION_GLASS);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GCYMTextures.ATOMIC_CASING;
    }

    @Override
    protected @NotNull OrientedOverlayRenderer getFrontOverlay() {
        return LARGE_DECONSTRUCTOR_OVERLAY;
    }
}
