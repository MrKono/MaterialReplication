package kono.materialreplication.client;

import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;

import static gregtech.client.renderer.texture.cube.OrientedOverlayRenderer.OverlayFace.FRONT;

@Mod.EventBusSubscriber(modid =  "materialreplication", value = Side.CLIENT)
public class MRTextures {
    public static OrientedOverlayRenderer DECONSTRUCTOR_OVERRAY = new OrientedOverlayRenderer("deconstructor", FRONT);
    public static OrientedOverlayRenderer REPLICATOR_OVERRAY = new OrientedOverlayRenderer("replicator", FRONT);
    public static void preInit() {
    }
}
