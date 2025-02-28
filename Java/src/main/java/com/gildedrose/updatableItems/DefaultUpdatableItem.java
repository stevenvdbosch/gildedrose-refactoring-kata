package com.gildedrose.updatableItems;

import com.gildedrose.Item;

public class DefaultUpdatableItem extends UpdatableItem {

    public DefaultUpdatableItem(Item item) {
        super(item);
    }

    @Override
    public void updateQualityAndSellIn() {
        this.updateSellIn();
        this.updateQuality();
    }
}
