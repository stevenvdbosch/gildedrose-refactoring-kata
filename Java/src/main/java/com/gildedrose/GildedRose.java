package com.gildedrose;

class GildedRose {
    Item[] items;

    public static final int DEFAULT_QUALITY_CHANGE = 1;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if (items[i].name.startsWith("Conjured")) {
                changeQualityAndSellInForConjuredItems(i);
            } else if (items[i].name.startsWith("Sulfuras")) {
                //Sulfuras always stays the same
                continue;
            } else if (items[i].name.startsWith("Backstage passes")) {
                changeQualityAndSellInForBackstagePasses(i);
            } else if (items[i].name.startsWith("Aged Brie")) {
                changeQualityAndSellInForAgedBrie(i);
            } else {
                changeQualityAndSellInForDefaultProducts(i);
            }
        }
    }

    private void changeQualityAndSellInForAgedBrie(int i) {
        items[i].quality += DEFAULT_QUALITY_CHANGE;
        items[i].sellIn -= 1;
        if(items[i].sellIn < 0) {
            items[i].quality += DEFAULT_QUALITY_CHANGE;
        }
        if(items[i].quality > 50) {
            items[i].quality = 50;
        }
    }

    private void changeQualityAndSellInForBackstagePasses(int i) {
        items[i].quality += DEFAULT_QUALITY_CHANGE;
        items[i].sellIn -= 1;
        if (items[i].sellIn <= 10) {
            items[i].quality += DEFAULT_QUALITY_CHANGE;
        }
        if (items[i].sellIn <= 5) {
            items[i].quality += DEFAULT_QUALITY_CHANGE;
        }
        if(items[i].quality > 50) {
            items[i].quality = 50;
        }
        if(items[i].sellIn < 0) {
            items[i].quality = 0;
        }
    }

    private void changeQualityAndSellInForConjuredItems(int i) {
        items[i].quality -= DEFAULT_QUALITY_CHANGE * 2;
        items[i].sellIn -= 1;
        if (items[i].sellIn < 0) {
            items[i].quality -= DEFAULT_QUALITY_CHANGE * 2;
        }
        if (items[i].quality < 0) {
            items[i].quality = 0;
        }
    }

    private void changeQualityAndSellInForDefaultProducts(int i) {
        items[i].quality -= DEFAULT_QUALITY_CHANGE;
        items[i].sellIn -= 1;
        if(items[i].sellIn < 0) {
            items[i].quality -= DEFAULT_QUALITY_CHANGE;
        }
        if(items[i].quality < 0) {
            items[i].quality = 0;
        }
    }
}
