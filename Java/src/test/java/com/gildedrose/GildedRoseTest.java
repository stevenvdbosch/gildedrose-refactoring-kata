package com.gildedrose;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.gildedrose.updatableItems.BackstagePass.FIRST_THRESHOLD;
import static com.gildedrose.updatableItems.BackstagePass.SECOND_THRESHOLD;
import static com.gildedrose.updatableItems.UpdatableItem.MAX_QUALITY;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    private static final int SULFURAS_QUALITY = 80;

    @Test
    void testGivenNameStaysTheSame() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    void testItemDegradesInQuality() {
        Item[] items = new Item[] { new Item("foo", 5, 5) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(4, app.items[0].quality);
    }

    @Test
    void testWhenSellDateHasPassedQualityDegradesTwiceAsFast() {
        Item[] items = new Item[] { new Item("foo", 0, 5) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(3, app.items[0].quality);
    }

    @Test
    void testQualityIsNeverNegative() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void testAgedBrieIncreasesInQuality() {
        Item[] items = new Item[] { new Item("Aged Brie", 5, 5) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(6, app.items[0].quality);
    }

    /**
     * The quality of an item is never more than UpdatableItem.MAX_QUALITY. (except for Sulfuras, see next test)
     * This can be tested with Aged Brie and Backstage passes, as those increase in quality.
     */
    @Test
    void testQualityOfItemIsNeverMoreThanMAX() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Aged Brie", 5, MAX_QUALITY));
        items.add(new Item("Backstage passes", 5, MAX_QUALITY));

        GildedRose app = new GildedRose(items.toArray(new Item[0]));

        app.updateQuality();

        assertEquals(MAX_QUALITY, app.items[0].quality);
    }

    @Test
    void testSulfurasDoesNotDecreaseInSellInValue() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 5, SULFURAS_QUALITY) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(5, app.items[0].sellIn);
    }

    @Test
    void testSulfurasDoesNotDegradeInQuality() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 5, SULFURAS_QUALITY) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(SULFURAS_QUALITY, app.items[0].quality);
    }

    /**
     * Sulfuras always at SULFURAS_QUALITY, and it's quality should never change.
     */
    @Test
    void testSulfurasKeepsQuality() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", -1, SULFURAS_QUALITY) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(SULFURAS_QUALITY, app.items[0].quality);
    }

    @Test
    void testBackstagePassesIncreaseInQuality() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 15, 5) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(6, app.items[0].quality);
    }

    @Test
    void testBackstagePassesWhen10DaysOrLessIncreaseInQualityBy2() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", FIRST_THRESHOLD, 5) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(7, app.items[0].quality);
    }

    @Test
    void testBackstagePassesWhen5DaysOrLessIncreaseInQualityBy3() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", SECOND_THRESHOLD, 5) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(8, app.items[0].quality);
    }

    @Test
    void testBackstagePassesWhenSellInValueIsPastThenQualityEqualsZero() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 5) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(0, app.items[0].quality);
    }

    @Test
    void testConjuredItemsDegradeFasterInQuality() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Conjured item", 4, 5));
        items.add(new Item("Conjured item with sellIn under zero", -1, 5));

        GildedRose app = new GildedRose(items.toArray(new Item[0]));

        app.updateQuality();

        assertEquals(3, app.items[0].quality);
        assertEquals(1, app.items[1].quality);
    }
}
