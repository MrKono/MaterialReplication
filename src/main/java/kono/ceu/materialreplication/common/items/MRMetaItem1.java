package kono.ceu.materialreplication.common.items;

import gregtech.api.items.metaitem.StandardMetaItem;

import static kono.ceu.materialreplication.common.items.MRMetaItems.*;

public class MRMetaItem1 extends StandardMetaItem {

    public MRMetaItem1() {
        super();
    }

    @Override
    public void registerSubItems() {
        USB_STICK = addItem(0, "usb_stick");
    }
}