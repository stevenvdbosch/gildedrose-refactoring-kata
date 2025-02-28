package com.gildedrose.updatableItems;

import com.gildedrose.Item;

public class ConjuredItem extends UpdatableItem {

    public ConjuredItem(Item item) {
        super(item);
    }

    @Override
    public void updateQualityAndSellIn() {
        this.updateSellIn();
        this.decreaseQuality();
        this.decreaseQuality();
    }
}
