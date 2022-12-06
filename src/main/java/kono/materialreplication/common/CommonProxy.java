package kono.materialreplication.common;


import gregtech.api.GregTechAPI;
import kono.materialreplication.machines.MRMetaTileEntities;
import kono.materialreplication.materials.MRMaterials;
import kono.materialreplication.materials.flags.MRMaterialFlagAddition;
import kono.materialreplication.recipe.MRMachineRecipe;
import kono.materialreplication.recipe.MRRecipeLoader;
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


@Mod.EventBusSubscriber(modid = "materialreplication")
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent e) {
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

    @SubscribeEvent()
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event){
        MRRecipeLoader.init();
        MRMachineRecipe.init();
    }
}

