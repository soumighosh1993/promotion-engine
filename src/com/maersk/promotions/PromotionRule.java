package com.maersk.promotions;

import com.maersk.cart.Cart;

public interface PromotionRule {

    public boolean isValidForPromotion(Cart cart);
    public void applyPromotion(Cart cart);

}
