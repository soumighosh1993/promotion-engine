package com.maersk.promotions;

import com.maersk.cart.Cart;
import com.maersk.cart.CartItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class CombinedPromotionRule implements PromotionRule{
    public final List<String> SKUs;
    public final double fixedPrice;

    public CombinedPromotionRule(List<String> skUs,double fixedPrice) {
        this.SKUs = skUs;
        this.fixedPrice=fixedPrice;
    }

    @Override
    public boolean isValidForPromotion(Cart cart) {

        if(!cart.cartItems.isEmpty())
        {
           List<String> cartItemsWithoutPromotions= cart.cartItems.stream().filter(c->!c.isPromoApplied).map(ct->ct.SKU).collect(Collectors.toList());
           AtomicBoolean applicable= new AtomicBoolean(true);
           SKUs.stream().forEach(sk->{
               if(!cartItemsWithoutPromotions.contains(sk))
                   applicable.set(false);
           });
           return applicable.get();
        }

        return false;
    }

    @Override
    public void applyPromotion(Cart cart) {

        while (isValidForPromotion(cart))
        {
            List<String> unusedSKUs=new ArrayList<>(SKUs);
            List<CartItem> cartItemsWithoutPromotions= cart.cartItems.stream().filter(c->!c.isPromoApplied).collect(Collectors.toList());

            cartItemsWithoutPromotions.stream().forEach(ciwp->{
              Optional<String> unused= unusedSKUs.stream().filter(us->us.equals(ciwp.SKU)).findFirst();
             if(unused.isPresent()) {
                 ciwp.finalPrice = fixedPrice / SKUs.size();
                 ciwp.isPromoApplied = true;
                 unusedSKUs.remove(unused.get());
             }
            });
        }

    }
}
