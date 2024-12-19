package com.gildedrose.updatableItems;

import com.gildedrose.Item;

public class Sulfuras extends UpdatableItem {

    public Sulfuras(Item item) {
        super(item);
    }

    @Override
    public void updateQualityAndSellIn() {
        // Do nothing to the sellIn and quality, sulfuras never changes
    }
}
