package kono.ceu.materialreplication.client;

import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;

import static gregtech.client.renderer.texture.cube.OrientedOverlayRenderer.OverlayFace.FRONT;

@Mod.EventBusSubscriber(modid =  "materialreplication", value = Side.CLIENT)
public class MRTextures {
    //CEu
    public static OrientedOverlayRenderer SCRAPPER_OVERLAY = new OrientedOverlayRenderer ("machines/recycler");

    //Original
    public static OrientedOverlayRenderer DECONSTRUCTOR_OVERLAY = new OrientedOverlayRenderer("machines/deconstructor", FRONT);
    public static OrientedOverlayRenderer REPLICATOR_OVERLAY = new OrientedOverlayRenderer("machines/replicator", FRONT);
    public static void preInit() {
    }
}
