package com.example.bookstore.services;

import com.example.bookstore.models.Order;
import org.springframework.stereotype.Service;


import java.util.*;



@Service
public class OrderService {

    private final Map<String, List<Order>> orders = new HashMap<>();

    public void placeOrder(String email ,Order order) {
        orders.computeIfAbsent(email,k->new ArrayList<>()).add(order);
    }

    public List<Order> getOrders(String email) {
        return orders.getOrDefault(email,Collections.emptyList());
    }


}
