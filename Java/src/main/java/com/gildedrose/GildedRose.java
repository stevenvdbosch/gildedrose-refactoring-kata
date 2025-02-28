package com.gildedrose;

import com.gildedrose.updatableItems.*;

import java.util.ArrayList;
import java.util.List;

class GildedRose {
    Item[] items;
    List<UpdatableItem> updatableItems;

    public GildedRose(Item[] items) {
        this.items = items;
        transformItemsToUpdatableItems();
    }

    public void updateQuality() {
        for(UpdatableItem item : updatableItems) {
            item.updateQualityAndSellIn();
        }
    }

    private void transformItemsToUpdatableItems() {
        updatableItems = new ArrayList<>();

        for (Item item : items) {
            if (item.name.startsWith("Sulfuras")) {
                updatableItems.add(new Sulfuras(item));
            } else if (item.name.startsWith("Conjured")) {
                updatableItems.add(new ConjuredItem(item));
            } else if (item.name.startsWith("Backstage passes")) {
                updatableItems.add(new BackstagePass(item));
            } else if (item.name.startsWith("Aged Brie")) {
                updatableItems.add(new AgedBrie(item));
            } else {
                updatableItems.add(new DefaultUpdatableItem(item));
            }
        }
    }
}
