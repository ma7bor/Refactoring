package com.gildedrose;


import static com.gildedrose.Util.*;

class GildedRose {

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
                qualityImpl(item);
        }
    }

    private void qualityImpl(Item item){
        switch (item.name) {

            case AGED_BRIE:
                item.quality++;
                item.sellIn--;
                if(item.sellIn < 0) item.quality++;
                break;

            case BACKSTAGE:
                if(item.sellIn <= 0 ) item.quality = 0;
                else if (item.sellIn < 6) item.quality = item.quality + 3;
                else if (item.sellIn < 11) item.quality = item.quality + 2;
                else item.quality++;
                break;

            case SULFURAS:
                break;

            case CONJURED:
                item.quality = item.quality - 4;
                break;

            default:
                item.quality = item.quality - 1;
                item.sellIn = item.sellIn - 1;
                if (item.sellIn < 0) item.quality = item.quality - 1;
                break;
        }
        if(!SULFURAS.equals(item.name)){
            item.quality = checkQualityValue(item.quality);
        }
    }
    public int checkQualityValue(int itemQuality) {
        if (itemQuality > 50) itemQuality = 50;
        else if (itemQuality < 0 ) itemQuality = 0;
        return itemQuality;
    }

}
