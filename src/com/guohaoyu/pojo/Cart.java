package com.guohaoyu.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {

    private Map<Integer,CartItem> item=new LinkedHashMap<>();

    public Cart() {
    }

    public Cart(Map<Integer, CartItem> item) {
        this.item = item;
    }

    public void addItem(CartItem cartitem){
        Integer id = cartitem.getId();
        CartItem cartItem = item.get(id);
        if (cartItem!=null){
           cartItem.setCount(cartItem.getCount()+1);
           cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }else {
            item.put(id, cartitem);
        }
    }

    public void delItem(Integer id){
        item.remove(id);
    }
    public void clear(){
        item.clear();
    }
    public void updateItem(){

    }

    public Integer getCount() {
        Integer count=0;
        for (CartItem value : item.values()) {
            count+=value.getCount();
        }
        return count;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice=new BigDecimal(0);
        for (CartItem value : item.values()) {
            totalPrice=totalPrice.add(value.getTotalPrice());
        }
        return totalPrice;
    }

    public Map<Integer, CartItem> getItem() {
        return item;
    }

    public void setItem(Map<Integer, CartItem> item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Cart{"+"Count=" +getCount()+"TotalPrice="+getTotalPrice()+
                "item=" + item +
                '}';
    }
}
