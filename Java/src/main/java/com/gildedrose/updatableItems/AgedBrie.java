package com.gildedrose.updatableItems;

import com.gildedrose.Item;

public class AgedBrie extends UpdatableItem {

    public AgedBrie(Item item) {
        super(item);
    }

    @Override
    public void updateQualityAndSellIn() {
        item.sellIn -= 1;
        item.quality += 1;
        if(item.sellIn < 0) {
            item.quality += 1;
        }
        if(item.quality > MAX_QUALITY) {
            item.quality = MAX_QUALITY;
        }
    }

}
