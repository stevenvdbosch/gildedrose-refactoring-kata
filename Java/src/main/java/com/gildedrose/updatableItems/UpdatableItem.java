package com.gildedrose.updatableItems;

import com.gildedrose.Item;

public abstract class UpdatableItem implements QualityUpdater {

    protected Item item;

    public UpdatableItem(Item item) {
        this.item = item;
    }

    @Override
    public abstract void updateQualityAndSellIn();
}
