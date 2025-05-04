package com.example.bookstore.controllers;

import com.example.bookstore.models.CartItem;
import com.example.bookstore.models.Order;
import com.example.bookstore.services.CartService;
import com.example.bookstore.services.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.*;
import java.time.LocalDateTime;



@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @PostMapping
    public String placeOrder(@AuthenticationPrincipal UserDetails user, HttpSession session) {
        Map<Long, CartItem> cart = cartService.getCart(session);


        if (cart.isEmpty()) {
            return "Cart is empty";
        }

        double total = cartService.getTotal(session);

        Order order = new Order(new ArrayList<>(cart.values()), total, LocalDateTime.now());

        orderService.placeOrder(user.getUsername(), order);

        session.removeAttribute("CART");

        return "Order Placed";

    }

    @GetMapping
    public List<Order> viewOrders(@AuthenticationPrincipal UserDetails user) {

        return orderService.getOrders(user.getUsername());
    }


}
