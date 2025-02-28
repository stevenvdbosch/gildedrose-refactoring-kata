package com.gildedrose.updatableItems;

import com.gildedrose.Item;

public class AgedBrie extends UpdatableItem {

    public AgedBrie(Item item) {
        super(item);
    }

    @Override
    public void updateQualityAndSellIn() {
        this.updateSellIn();
        this.increaseQuality();
    }
}
