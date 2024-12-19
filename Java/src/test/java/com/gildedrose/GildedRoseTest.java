package com.gildedrose;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

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
     * The quality of an item is never more than 50.
     * This can be tested with Aged Brie and Backstage passes, as those increase in quality.
     */
    @Test
    void testQualityOfItemIsNeverMoreThan50() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Aged Brie", 5, 50));
        items.add(new Item("Backstage passes", 5, 50));

        GildedRose app = new GildedRose(items.toArray(new Item[0]));

        app.updateQuality();

        assertEquals(50, app.items[0].quality);
    }

    @Test
    void testSulfurasDoesNotDecreaseInSellInValue() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 5, 80) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(5, app.items[0].sellIn);
    }

    @Test
    void testSulfurasDoesNotDegradeInQuality() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 5, 80) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(80, app.items[0].quality);
    }

    /**
     * Sulfuras can have a quality higher than 50, and it's quality should never change.
     */
    @Test
    void testSulfurasKeepsQuality80() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", -1, 80) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(80, app.items[0].quality);
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
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 5) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(7, app.items[0].quality);
    }

    @Test
    void testBackstagePassesWhen5DaysOrLessIncreaseInQualityBy3() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 5) };
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
