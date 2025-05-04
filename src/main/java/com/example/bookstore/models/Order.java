package com.example.bookstore.models;


import java.time.LocalDateTime;
import java.util.List;



public class Order {

    private List<CartItem> items;
    private double total;
    private LocalDateTime orderDate;

    public Order(List<CartItem> items, double total, LocalDateTime orderDate) {
        this.items = items;
        this.total = total;
        this.orderDate = orderDate;

    }



    public List<CartItem> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }



}
