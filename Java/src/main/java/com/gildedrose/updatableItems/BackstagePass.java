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
        this.increaseQuality();
    }

    @Override
    public void increaseQuality() {
        super.increaseQuality();
        if (item.sellIn <= FIRST_THRESHOLD) {
            super.increaseQuality();
        }
        if (item.sellIn <= SECOND_THRESHOLD) {
            super.increaseQuality();
        }
        if(item.sellIn < 0) {
            item.quality = 0;
        }
    }
}
