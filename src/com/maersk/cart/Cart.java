package com.maersk.cart;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    public List<CartItem> cartItems=new ArrayList<>();

    /*

     */
    public void addItem(CartItem cartItem){

        cartItems.add(cartItem);
    }

    /*

    */
    public double getCartValue(){
        double total= cartItems.stream().mapToDouble(cItem -> cItem.finalPrice).sum();
        return total;
    }


}
