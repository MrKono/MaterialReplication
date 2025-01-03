package kono.ceu.materialreplication.client;

import static kono.ceu.materialreplication.api.util.MRValues.MODID;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;

import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtech.client.renderer.texture.cube.SimpleOverlayRenderer;

@Mod.EventBusSubscriber(modid = MODID, value = Side.CLIENT)
public class MRTextures {

    // CEu
    public static final OrientedOverlayRenderer SCRAPPER_OVERLAY = new OrientedOverlayRenderer("machines/recycler");

    // Original
    public static final OrientedOverlayRenderer DECONSTRUCTOR_OVERLAY = new OrientedOverlayRenderer(
            "machines/single/deconstructor");
    public static final OrientedOverlayRenderer REPLICATOR_OVERLAY = new OrientedOverlayRenderer(
            "machines/single/replicator");

    public static final OrientedOverlayRenderer LARGE_DECONSTRUCTOR_OVERLAY = new OrientedOverlayRenderer(
            "machines/multi/deconstructor");
    public static final OrientedOverlayRenderer LARGE_REPLICATOR_OVERLAY = new OrientedOverlayRenderer(
            "machines/multi/replicator");
    public static final OrientedOverlayRenderer LARGE_SCRAPPER_OVERLAY = new OrientedOverlayRenderer(
            "machines/multi/scrapper");

    // Casings
    public static SimpleOverlayRenderer ANTIMATTER_REACTOR_CASING;
    public static SimpleOverlayRenderer HELIUM_COOLANT;
    public static SimpleOverlayRenderer REACTOR_CORE;

    public static void preInit() {
        ANTIMATTER_REACTOR_CASING = new SimpleOverlayRenderer(
                "casings/antimatter/antimatter_reactor_casing");
        HELIUM_COOLANT = new SimpleOverlayRenderer(
                "casings/antimatter/helium_coolant");
        REACTOR_CORE = new SimpleOverlayRenderer(
                "casings/antimatter/antimatter_reactor_core");
    }
}
