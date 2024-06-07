package kono.ceu.materialreplication.common.items;

import static kono.ceu.materialreplication.common.items.MRMetaItems.*;

import gregtech.api.items.metaitem.StandardMetaItem;

import kono.ceu.materialreplication.common.items.behaviors.ReplicationBehaviors;

public class MRMetaItem1 extends StandardMetaItem {

    public MRMetaItem1() {
        super();
    }

    @Override
    public void registerSubItems() {
        USB_STICK = addItem(0, "usb_stick").addComponents(new ReplicationBehaviors());
        SCRAP = addItem(1, "scrap");
        SCRAP_BOX = addItem(2, "scrap_box");
    }
}
