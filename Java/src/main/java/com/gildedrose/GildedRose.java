package com.gildedrose;

import java.util.List;

class GildedRose {
    Item[] items;
    List<String> specials = List.of("Aged Brie", "Backstage passes to a TAFKAL80ETC concert");

    public static final int DEFAULT_QUALITY_DECREASE = 1;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if (items[i].name.startsWith("Conjured")) {
                items[i].quality -= DEFAULT_QUALITY_DECREASE * 2;
                items[i].sellIn -= 1;
                if (items[i].sellIn < 0) {
                    items[i].quality -= DEFAULT_QUALITY_DECREASE * 2;
                }
                if (items[i].quality < 0) {
                    items[i].quality = 0;
                }
            } else if (items[i].name.startsWith("Sulfuras")) {
                //No change for Sulfuras
                continue;
            } else {
                if (!specials.contains(items[i].name)) {
                    changeQualityAndSellInForDefaultProducts(i);
                } else {
                    updateSpecials(i);
                }
            }
        }
    }

    private void changeQualityAndSellInForDefaultProducts(int i) {
        items[i].quality -= DEFAULT_QUALITY_DECREASE;
        items[i].sellIn -= 1;
        if(items[i].sellIn < 0) {
            items[i].quality -= DEFAULT_QUALITY_DECREASE;
        }
        if(items[i].quality < 0) {
            items[i].quality = 0;
        }
    }

    private void updateSpecials(int i) {
        // change quality of items being aged brie or backstage passes
        if (items[i].quality < 50) {
            items[i].quality = items[i].quality + 1;

            if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (items[i].sellIn < 11) {
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1;
                    }
                }

                if (items[i].sellIn < 6) {
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1;
                    }
                }
            }
        }

        //change the sellIn value
        items[i].sellIn = items[i].sellIn - 1;

        if (items[i].sellIn < 0) {
            // if sellin lower than 0 and item is not aged brie: lower quality extra
            if (!items[i].name.equals("Aged Brie")) {
                if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (items[i].quality > 0) {
                        items[i].quality = items[i].quality - 1;
                    }
                    //quality of backstage passes when sellIn <0 is zero
                } else {
                    items[i].quality = 0;
                }
                //if sellin lower than 0 add quality extra
            } else {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;
                }
            }
        }
    }
}
