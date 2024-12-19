package com.gildedrose.updatableItems;

import com.gildedrose.Item;

public class ConjuredItem extends UpdatableItem {

    public ConjuredItem(Item item) {
        super(item);
    }

    @Override
    public void updateQualityAndSellIn() {
        item.sellIn -= 1;
        item.quality -= 2;
        if (item.sellIn < 0) {
            item.quality -= 2;
        }
        if (item.quality < 0) {
            item.quality = 0;
        }
    }
}
