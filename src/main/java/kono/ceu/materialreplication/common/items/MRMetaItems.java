package kono.ceu.materialreplication.common.items;

import gregtech.api.items.metaitem.MetaItem;

public final class MRMetaItems {

    private MRMetaItems() {}

    public static MetaItem<?>.MetaValueItem USB_STICK;
    public static MetaItem<?>.MetaValueItem SCRAP;
    public static MetaItem<?>.MetaValueItem SCRAP_BOX;

    public static void init() {
        MRMetaItem1 metaItem1 = new MRMetaItem1();
        metaItem1.setRegistryName("meta_item_1");
    }
}
