package com.maersk.cart;

import com.maersk.store.StoreItem;

public class CartItem {

    public final String SKU;
    public double finalPrice;
    public boolean isPromoApplied;

    public CartItem(StoreItem item) {
        this.SKU = item.SKU;
        this.finalPrice=item.price;
        this.isPromoApplied=false;
    }
}
