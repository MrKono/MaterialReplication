package kono.ceu.materialreplication.common;


import gregtech.api.unification.material.event.MaterialEvent;
import gregtech.api.unification.material.event.PostMaterialEvent;
import kono.ceu.materialreplication.api.util.MRValues;
import kono.ceu.materialreplication.api.unification.materials.MRMaterials;
import kono.ceu.materialreplication.api.unification.materials.flags.MRMaterialFlagAddition;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = MRValues.MODID)
public class MREventHandlers {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void registerMaterials(MaterialEvent event) {
        MRMaterials.init();
        MRMaterials.orePrefix();
    }

    @SubscribeEvent
    public static void registerMaterialsPost(PostMaterialEvent event) {
        MRMaterialFlagAddition.intLate();
    }

}
