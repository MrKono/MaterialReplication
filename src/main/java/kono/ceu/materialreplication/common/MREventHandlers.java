package kono.ceu.materialreplication.common;


import gregtech.api.unification.material.event.MaterialEvent;
import kono.ceu.materialreplication.api.MRValues;
import kono.ceu.materialreplication.api.unification.materials.MRMaterials;
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

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerMaterialsPost(MaterialEvent event) {
        //MRMaterialFlagAddition.intLate();
    }

}
