package com.gildedrose.updatableItems;

import com.gildedrose.Item;

public class BackstagePass extends UpdatableItem {

    public static final int FIRST_THRESHOLD = 10;
    public static final int SECOND_THRESHOLD = 5;

    public BackstagePass(Item item) {
        super(item);
    }

    @Override
    public void updateQualityAndSellIn() {
        this.updateSellIn();
        this.updateQuality();
    }

    @Override
    public void updateQuality() {
        item.quality += 1;
        if (item.sellIn <= FIRST_THRESHOLD) {
            item.quality += 1;
        }
        if (item.sellIn <= SECOND_THRESHOLD) {
            item.quality += 1;
        }
        if(item.quality > MAX_QUALITY) {
            item.quality = MAX_QUALITY;
        }
        if(item.sellIn < 0) {
            item.quality = 0;
        }
    }
}
