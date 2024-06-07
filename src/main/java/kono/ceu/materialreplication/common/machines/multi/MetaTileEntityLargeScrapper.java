package kono.ceu.materialreplication.common.machines.multi;

import static kono.ceu.materialreplication.client.MRTextures.LARGE_SCRAPPER_OVERLAY;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.jetbrains.annotations.NotNull;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtech.client.utils.TooltipHelper;

import gregicality.multiblocks.api.metatileentity.GCYMRecipeMapMultiblockController;
import gregicality.multiblocks.api.render.GCYMTextures;
import gregicality.multiblocks.common.block.GCYMMetaBlocks;
import gregicality.multiblocks.common.block.blocks.BlockLargeMultiblockCasing;
import gregicality.multiblocks.common.block.blocks.BlockUniqueCasing;

import kono.ceu.materialreplication.api.recipes.MRRecipeMaps;
import kono.ceu.materialreplication.api.util.MRValues;

public class MetaTileEntityLargeScrapper extends GCYMRecipeMapMultiblockController {

    public MetaTileEntityLargeScrapper(ResourceLocation metaTileEntityID) {
        super(metaTileEntityID, MRRecipeMaps.SCRAPMAKER_RECIPES);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity metaTileEntityID) {
        return new MetaTileEntityLargeScrapper(this.metaTileEntityId);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXXXX", "XXXXX", "XXXXX", "XXXXX", "XXXXX")
                .aisle("XXXXX", "XCCCX", "XBBBX", "XCCCX", "XXXXX")
                .aisle("XXXXX", "XCCCX", "XBTBX", "XCCCX", "XXXXX")
                .aisle("XXXXX", "XCCCX", "XBBBX", "XCCCX", "XXXXX")
                .aisle("XXXXX", "XXSXX", "XXXXX", "XXXXX", "XXXXX")
                .where('S', selfPredicate())
                .where('X', states(getCasingState()).setMinGlobalLimited(45).or(autoAbilities()))
                .where('C', states(getCasingState2()))
                .where('B', states(getCasingState3()))
                .where('T', tieredCasing().or(air()))
                .build();
    }

    private static IBlockState getCasingState() {
        return GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                .getState(BlockLargeMultiblockCasing.CasingType.VIBRATION_SAFE_CASING);
    }

    private static IBlockState getCasingState2() {
        return GCYMMetaBlocks.UNIQUE_CASING.getState(BlockUniqueCasing.UniqueCasingType.CRUSHING_WHEELS);
    }

    private static IBlockState getCasingState3() {
        return GCYMMetaBlocks.UNIQUE_CASING.getState(BlockUniqueCasing.UniqueCasingType.SLICING_BLADES);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GCYMTextures.VIBRATION_SAFE_CASING;
    }

    @Override
    protected @NotNull OrientedOverlayRenderer getFrontOverlay() {
        return LARGE_SCRAPPER_OVERLAY;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("materialreplication.machine.large_scrapper.description"));
        tooltip.add(I18n.format("materialreplication.machine.scrapmaker.warning2.1") +
                TooltipHelper.BLINKING_RED + I18n.format("materialreplication.machine.scrapmaker.warning2.2"));
        if (TooltipHelper.isShiftDown()) {
            double chance = MRValues.ScrapChance / 100.0;
            double boost = MRValues.ScrapChanceBoost / 100.0;
            tooltip.add(I18n.format("materialreplication.machine.scrapmaker.warning3.1") + TooltipHelper.BLINKING_CYAN +
                    I18n.format("materialreplication.machine.scrapmaker.warning3.2", chance, boost));
        }
    }
}
