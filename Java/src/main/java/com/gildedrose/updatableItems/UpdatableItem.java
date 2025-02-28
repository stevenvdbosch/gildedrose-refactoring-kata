package com.gildedrose.updatableItems;

import com.gildedrose.Item;

public abstract class UpdatableItem implements QualityUpdater {

    public static final int MAX_QUALITY = 50;

    protected Item item;

    public UpdatableItem(Item item) {
        this.item = item;
    }

    @Override
    public abstract void updateQualityAndSellIn();

    @Override
    public void updateSellIn() {
        item.sellIn -= 1;
    }

    @Override
    public void updateQuality() {
        item.quality = item.sellIn < 0 ? item.quality - 2 : item.quality - 1;
        if (item.quality < 0) {
            item.quality = 0;
        }
    }
}
