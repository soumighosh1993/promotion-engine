package com.maersk.test;

import com.maersk.store.Store;

public class StoreTest {
    public static Store store=null;


    public static void main(String [] args){

        test1();
        test2();
        test3();


    }

    private static void test1() {
        store=new Store();
        store.addSKUItems(TestData.itemA);
        store.addSKUItems(TestData.itemB);
        store.addSKUItems(TestData.itemC);
        store.addSKUItems(TestData.itemD);

        store.addPromotion(TestData.promo1);
        store.addPromotion(TestData.promo2);
        store.addPromotion(TestData.promo3);

        store.addItemToCart("A");
        store.addItemToCart("B");
        store.addItemToCart("C");

        System.out.println(store.checkOut());
    }

    private static void test2() {
        store=new Store();
        store.addSKUItems(TestData.itemA);
        store.addSKUItems(TestData.itemB);
        store.addSKUItems(TestData.itemC);
        store.addSKUItems(TestData.itemD);

        store.addPromotion(TestData.promo1);
        store.addPromotion(TestData.promo2);
        store.addPromotion(TestData.promo3);

        store.addItemToCart("A");
        store.addItemToCart("A");
        store.addItemToCart("A");
        store.addItemToCart("A");
        store.addItemToCart("A");
        store.addItemToCart("B");
        store.addItemToCart("B");
        store.addItemToCart("B");
        store.addItemToCart("B");
        store.addItemToCart("B");
        store.addItemToCart("C");

        System.out.println(store.checkOut());
    }

    private static void test3() {
        store=new Store();
        store.addSKUItems(TestData.itemA);
        store.addSKUItems(TestData.itemB);
        store.addSKUItems(TestData.itemC);
        store.addSKUItems(TestData.itemD);

        store.addPromotion(TestData.promo1);
        store.addPromotion(TestData.promo2);
        store.addPromotion(TestData.promo3);

        store.addItemToCart("A");
        store.addItemToCart("A");
        store.addItemToCart("A");
        store.addItemToCart("B");
        store.addItemToCart("B");
        store.addItemToCart("B");
        store.addItemToCart("B");
        store.addItemToCart("B");
        store.addItemToCart("C");
        store.addItemToCart("D");

        System.out.println(store.checkOut());
    }
}
