package kono.ceu.materialreplication;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import gregtech.GTInternalTags;

import kono.ceu.materialreplication.api.util.MRValues;
import kono.ceu.materialreplication.api.util.Mods;
import kono.ceu.materialreplication.common.CommonProxy;

@Mod(modid = MRValues.MODID,
     name = MRValues.MODNAME,
     version = Tags.VERSION,
     acceptedMinecraftVersions = "[1.12, 1.12.2]",
     dependencies = GTInternalTags.DEP_VERSION_STRING + "required-after:" + Mods.Names.GREGICALITY_MULTIBLOCKS + ";" +
             "after:" + Mods.Names.FORESTRY + ";")

public class MaterialReplication {

    @SidedProxy(modId = MRValues.MODID,
                clientSide = "kono.ceu.materialreplication.client.ClientProxy",
                serverSide = "kono.ceu.materialreplication.common.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static MaterialReplication instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }

    @SubscribeEvent
    public static void syncConfigValues(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(Tags.MODID)) {
            ConfigManager.sync(Tags.MODID, Config.Type.INSTANCE);
        }
    }
}
