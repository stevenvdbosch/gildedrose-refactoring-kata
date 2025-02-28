package com.gildedrose.updatableItems;

import com.gildedrose.Item;

public abstract class UpdatableItem implements QualityUpdater {

    public static final int MIN_QUALITY = 0;
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
    public void decreaseQuality() {
        item.quality = item.sellIn < 0 ? item.quality - 2 : item.quality - 1;
        if (item.quality < MIN_QUALITY) {
            item.quality = MIN_QUALITY;
        }
    }

    @Override
    public void increaseQuality() {
        item.quality = item.sellIn < 0 ? item.quality + 2 : item.quality + 1;
        if(item.quality > MAX_QUALITY) {
            item.quality = MAX_QUALITY;
        }
    }

}
