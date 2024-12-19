package com.gildedrose.updatableItems;

import com.gildedrose.Item;

public class BackstagePass extends UpdatableItem {

    public BackstagePass(Item item) {
        super(item);
    }

    @Override
    public void updateQualityAndSellIn() {
        item.sellIn -= 1;
        item.quality += 1;
        if (item.sellIn <= 10) {
            item.quality += 1;
        }
        if (item.sellIn <= 5) {
            item.quality += 1;
        }
        if(item.quality > 50) {
            item.quality = 50;
        }
        if(item.sellIn < 0) {
            item.quality = 0;
        }
    }
}
