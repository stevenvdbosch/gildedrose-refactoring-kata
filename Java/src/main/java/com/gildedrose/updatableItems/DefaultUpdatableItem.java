package com.gildedrose.updatableItems;

import com.gildedrose.Item;

public class DefaultUpdatableItem extends UpdatableItem {

    public DefaultUpdatableItem(Item item) {
        super(item);
    }

    @Override
    public void updateQualityAndSellIn() {
        item.sellIn -= 1;
        item.quality -= 1;
        if (item.sellIn < 0) {
            item.quality -= 1;
        }
        if (item.quality < 0) {
            item.quality = 0;
        }
    }
}
