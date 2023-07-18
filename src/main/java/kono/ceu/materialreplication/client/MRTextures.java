package kono.ceu.materialreplication.client;

import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid =  "materialreplication", value = Side.CLIENT)
public class MRTextures {
    //CEu
    public static final OrientedOverlayRenderer SCRAPPER_OVERLAY = new OrientedOverlayRenderer ("recycler");

    //Original
    public static final OrientedOverlayRenderer DECONSTRUCTOR_OVERLAY = new OrientedOverlayRenderer("deconstructor");
    public static final OrientedOverlayRenderer REPLICATOR_OVERLAY = new OrientedOverlayRenderer("replicator");
    public static void preInit() {
    }
}
