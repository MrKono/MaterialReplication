package kono.ceu.materialreplication.client;

import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;

import static kono.ceu.materialreplication.api.util.MRValues.MODID;

@Mod.EventBusSubscriber(modid = MODID, value = Side.CLIENT)
public class MRTextures {
    //CEu
    public static final OrientedOverlayRenderer SCRAPPER_OVERLAY = new OrientedOverlayRenderer ("machines/recycler");

    //Original
    public static final OrientedOverlayRenderer DECONSTRUCTOR_OVERLAY = new OrientedOverlayRenderer("machines/single/deconstructor");
    public static final OrientedOverlayRenderer REPLICATOR_OVERLAY = new OrientedOverlayRenderer("machines/single/replicator");

    public static final OrientedOverlayRenderer LARGE_DECONSTRUCTOR_OVERLAY = new OrientedOverlayRenderer("machines/multi/deconstructor");
    public static final OrientedOverlayRenderer LARGE_REPLICATOR_OVERLAY = new OrientedOverlayRenderer("machines/multi/replicator");
    public static final OrientedOverlayRenderer LARGE_SCRAPPER_OVERLAY = new OrientedOverlayRenderer("machines/multi/scrapper");
    public static void preInit() {
    }
}
