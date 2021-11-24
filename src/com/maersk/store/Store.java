package com.maersk.store;

import com.maersk.cart.Cart;
import com.maersk.cart.CartItem;
import com.maersk.promotions.PromotionEngine;
import com.maersk.promotions.PromotionRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Store {

    public Cart cart=new Cart();
    public PromotionEngine promotionEngine=new PromotionEngine();

    public List<PromotionRule> promotions=new ArrayList<>();

    public List<StoreItem> storeItems=new ArrayList<>();

    public void addSKUItems(StoreItem storeItem){
        storeItems.add(storeItem);
    }

    public void addPromotion(String promotion){

        Optional<PromotionRule> promo= Optional.ofNullable(promotionEngine.getPromotion(promotion));
        if(promo.isPresent())
            promotions.add(promo.get());

    }

    public  void addItemToCart(String itemSKU){
        Optional<StoreItem> item=storeItems.stream().filter(st->st.SKU.equalsIgnoreCase(itemSKU)).findFirst();
        cart.addItem(new CartItem(item.get()));
    }

    public  double checkOut(){

        promotions.stream().forEach(p->{
            p.applyPromotion(this.cart);
        });
        return this.cart.getCartValue();
    }

}
