package com.gildedrose;

import org.junit.jupiter.api.Test;

import static com.gildedrose.Util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void shouldDecreaseItemQualityEachDay() {

        int startingSellin = 5;
        int startingQuality = 7;
        final Item standardItem = new Item(STANDARD, startingSellin, startingQuality);
        GildedRose subject = new GildedRose(new Item[] {standardItem});

        subject.updateQuality();

        assertEquals(startingSellin - 1, standardItem.sellIn);
        assertEquals(startingQuality - 1, standardItem.quality);

    }

    @Test
    void shouldDegradeForMultipleItemsEachDay() {
        Item firstItem = new Item(STANDARD_1, 5, 4);
        Item secondItem = new Item(STANDARD_2, 3, 2);
        GildedRose subject = new GildedRose(new Item[] { firstItem, secondItem });

        subject.updateQuality();

        assertEquals(4, firstItem.sellIn);
        assertEquals(3, firstItem.quality);
        assertEquals(2 , secondItem.sellIn);
        assertEquals(1 , secondItem.quality);

    }

    @Test
    void shouldDegradesItemQualityTwiceAsFastPassedSellinDate() {
        Item item = new Item("foo", -1, 4);
        GildedRose subject = new GildedRose(new Item[] { item });

        subject.updateQuality();

        assertEquals(2, item.quality);
    }

    @Test
    void shouldNeverReturnNegativeValueOfQuality() {
        Item item = new Item("foo", 4, 0);
        GildedRose subject = new GildedRose(new Item[] { item });

        subject.updateQuality();

        assertEquals(0, item.quality);
    }

    @Test
    void shouldIncreaseQualityOverTimeForAgedItem() {
        Item item = new Item(AGED_BRIE, 5, 6);
        GildedRose subject = new GildedRose(new Item[] { item });

        subject.updateQuality();

        assertEquals(7, item.quality);
    }

    @Test
    void shouldNeverReturnQualityValueAboveOF50() {
        Item item = new Item(AGED_BRIE, 5, 50);
        GildedRose subject = new GildedRose(new Item[] { item });

        subject.updateQuality();

        assertEquals(50, item.quality);
    }

    @Test
    void shouldNeverSoldLegendaryItem() {
        Item item = new Item(SULFURAS, -1, 80);
        GildedRose subject = new GildedRose(new Item[] { item });
        subject.updateQuality();

        assertEquals(80, item.quality);

    }

    @Test
    void shouldNeverDecreaseQualityForLegendaryItem() {
        Item item = new Item(SULFURAS, -1, 80);
        GildedRose subject = new GildedRose(new Item[] { item });
        subject.updateQuality();

        assertEquals(80, item.quality);
    }

    @Test
    void shouldIncreaseQualityAsSellinDateApproachesForBackstageItem() {
        Item item = new Item(BACKSTAGE, 15, 20);
        GildedRose subject = new GildedRose(new Item[] { item });

        subject.updateQuality();
        assertEquals(21, item.quality);

    }

    @Test
    void shouldIncreaseQualityBy2WhenSellinIs10DaysOrLessForBackstageItem() {
        Item item = new Item(BACKSTAGE, 10, 20);
        GildedRose subject = new GildedRose(new Item[] { item });
        subject.updateQuality();

        assertEquals(22, item.quality);

    }

    @Test
    void shouldIncreaseQualityBy3WhenSellinIs5DaysOrLessForBackstageItem() {
        Item item = new Item(BACKSTAGE, 5, 20);
        GildedRose subject = new GildedRose(new Item[] { item });
        subject.updateQuality();

        assertEquals(23, item.quality);

    }

    @Test
    void shouldPassesQualityToZeroAfterConcertForBackstageItem() {
        Item item = new Item(BACKSTAGE, 0, 20);
        GildedRose subject = new GildedRose(new Item[] { item });
        subject.updateQuality();
        assertEquals(0, item.quality);

    }

    @Test
    void shouldDecreaseQualityTwiceAsNormalItemForConjuredItem() {
        Item item = new Item(CONJURED, 3, 24);
        GildedRose subject = new GildedRose(new Item[] { item });
        subject.updateQuality();
        assertEquals(20, item.quality);

    }

}
