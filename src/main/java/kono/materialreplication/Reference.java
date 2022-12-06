package kono.materialreplication;

import gregtech.api.GTValues;
import kono.materialreplication.common.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid= "materialreplication",
        name = "Material Replication",
        acceptedMinecraftVersions = "[1.12, 1.12.2]",
        dependencies = GTValues.MOD_VERSION_DEP)

public class Reference {
    @SidedProxy(modId = "materialreplication", clientSide = "kono.materialreplication.client.ClientProxy", serverSide = "kono.materialreplication.common.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static Reference instance;

    public static Logger logger;
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
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
}
