package kono.ceu.materialreplication.common;

import static kono.ceu.materialreplication.common.blocks.MRBlocks.*;

import java.util.function.Function;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import gregtech.api.block.VariantItemBlock;
import gregtech.loaders.recipe.RecyclingRecipes;

import kono.ceu.materialreplication.api.util.MRValues;
import kono.ceu.materialreplication.api.util.MaterialReplicationLog;
import kono.ceu.materialreplication.common.blocks.MRBlocks;
import kono.ceu.materialreplication.common.items.MRMetaItems;
import kono.ceu.materialreplication.common.machines.MRMetaTileEntities;
import kono.ceu.materialreplication.integration.forestry.MRIntegration;
import kono.ceu.materialreplication.loaders.recipe.MRRecipes;

@Mod.EventBusSubscriber(modid = MRValues.MODID)
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {
        MRMetaItems.init();
        MRBlocks.init();
    }

    public void init(FMLInitializationEvent e) {}

    public void postInit(FMLPostInitializationEvent e) {}

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();

        registry.register(ANTIMATTER_CASING);
    }

    public static void registerItem(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();

        registry.register(createItemBlock(ANTIMATTER_CASING, VariantItemBlock::new));
    }

    private static <T extends Block> ItemBlock createItemBlock(T block, Function<T, ItemBlock> producer) {
        ItemBlock itemBlock = producer.apply(block);
        ResourceLocation name = block.getRegistryName();
        if (name != null) {
            itemBlock.setRegistryName(name);
        }
        return itemBlock;
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        MaterialReplicationLog.logger.info("Registering recipes...");
        MRMetaTileEntities.init();
        MRMetaItems.init();
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void registerRecipesRemoval(RegistryEvent.Register<IRecipe> event) {
        MaterialReplicationLog.logger.info("Removing recipes...");
        MRRecipes.removeRecipe();
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerRecipesLow(RegistryEvent.Register<IRecipe> event) {
        MaterialReplicationLog.logger.info("Registering recipes...");
        MRRecipes.addRecipe();
        RecyclingRecipes.init();
        MRIntegration.registerRecipeLowest();
    }
}
