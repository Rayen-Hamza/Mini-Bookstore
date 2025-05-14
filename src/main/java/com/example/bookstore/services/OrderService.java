package com.example.bookstore.services;

import com.example.bookstore.models.*;
import com.example.bookstore.repositories.BookRepository;
import com.example.bookstore.repositories.OrderRepository;
import com.example.bookstore.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {

    @Autowired private OrderRepository orderRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private CartService cartService;
    public void placeOrder(@AuthenticationPrincipal UserDetails userDetails,HttpSession session) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = new Order();
        order.setUser(userRepository.findByEmail(userDetails.getUsername()).orElseThrow());
        order.setTotal(cartService.getTotal(session));

        List<OrderItem> items = new ArrayList<>();
        for (CartItem cartItem : cartService.getCart(session).values()) {
            OrderItem item = new OrderItem();
            item.setBook(cartItem.getBook());
            item.setQuantity(cartItem.getQuantity());
            item.setOrder(order);
            items.add(item);
        }

        order.setItems(items);
        orderRepository.save(order);
        session.removeAttribute("CART");
    }

    public List<Order> getOrders(String username) {
        return userRepository.findByEmail(username)
                .map(orderRepository::findByUser)
                .orElse(Collections.emptyList());
    }
}
