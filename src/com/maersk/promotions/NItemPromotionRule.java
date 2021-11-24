package com.maersk.promotions;

import com.maersk.cart.Cart;
import com.maersk.cart.CartItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NItemPromotionRule implements PromotionRule{
    public final List<String> SKUs = new ArrayList<>();
    public final double fixedPrice;

    public NItemPromotionRule(String SKU,int numOfItem,double fixedPrice) {
     for(int i=1;i<=numOfItem;i++){
         this.SKUs.add(SKU);
     }
     this.fixedPrice=fixedPrice;
    }

    @Override
    public boolean isValidForPromotion(Cart cart) {

        if(!cart.cartItems.isEmpty())
        {
            List<String> cartItemsWithoutPromotions= cart.cartItems.stream().filter(c->!c.isPromoApplied && c.SKU.equalsIgnoreCase(SKUs.get(0))).map(ct->ct.SKU).collect(Collectors.toList());

            return cartItemsWithoutPromotions.size()>=SKUs.size() ;
        }

        return false;
    }

    @Override
    public void applyPromotion(Cart cart) {

        while (isValidForPromotion(cart))
        {

            int skuCount=SKUs.size();
            List<CartItem> cartItemsWithoutPromotions= cart.cartItems.stream().filter(c->!c.isPromoApplied && c.SKU.equalsIgnoreCase(SKUs.get(0))).collect(Collectors.toList());

            for(int i=0;i<skuCount;i++){
                cartItemsWithoutPromotions.get(i).finalPrice=fixedPrice/skuCount;
                cartItemsWithoutPromotions.get(i).isPromoApplied=true;
            }

        }

    }
}
