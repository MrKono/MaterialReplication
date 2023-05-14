package kono.ceu.materialreplication.common;


import gregtech.api.GregTechAPI;
import kono.ceu.materialreplication.api.MRValues;
import kono.ceu.materialreplication.common.items.MRMetaItems;
import kono.ceu.materialreplication.common.machines.MRMetaTileEntities;
import kono.ceu.materialreplication.api.unification.materials.MRMaterials;
import kono.ceu.materialreplication.api.unification.materials.flags.MRMaterialFlagAddition;
import kono.ceu.materialreplication.loaders.recipe.MRMiscRecipeLoader;
import kono.ceu.materialreplication.loaders.recipe.MRRecipes;
import kono.ceu.materialreplication.loaders.recipe.MRMachineRecipeLoader;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.function.Function;


@Mod.EventBusSubscriber(modid = MRValues.MODID)
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent e) {
        MRMetaItems.init();
        MRMetaTileEntities.init();
    }

    public void init(FMLInitializationEvent e) {
    }

    public void postInit(FMLPostInitializationEvent e) {
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
    }

    public static void registerItem(RegistryEvent.Register<Item> event) {
    }

    private static <T extends Block> ItemBlock createItemBlock(T block, Function<T, ItemBlock> producer) {
        ItemBlock itemBlock = producer.apply(block);
        itemBlock.setRegistryName(block.getRegistryName());
        return itemBlock;
    }
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void registerMaterials(GregTechAPI.MaterialEvent event) {
        MRMaterials.init();
        MRMaterialFlagAddition.init();
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void registerRecipesRemoval(RegistryEvent.Register<IRecipe> event) {
        MRMiscRecipeLoader.init();
    }

    @SubscribeEvent()
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event){
        MRMachineRecipeLoader.init();
        MRRecipes.init();
    }
}

