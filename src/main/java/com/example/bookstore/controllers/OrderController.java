package com.example.bookstore.controllers;

import com.example.bookstore.models.CartItem;
import com.example.bookstore.models.Order;
import com.example.bookstore.services.CartService;
import com.example.bookstore.services.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired private CartService cartService;
    @Autowired private OrderService orderService;


    @PostMapping
    public String placeOrder(@AuthenticationPrincipal UserDetails user, HttpSession session, RedirectAttributes redirectAttributes) {
        Map<Long, CartItem> cart = cartService.getCart(session);
        if (cart.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Cart is empty!");
            return "redirect:/cart";
        }

        orderService.placeOrder(user, session);
        session.removeAttribute("CART");

        return "redirect:/cart?success=true";
    }

    @GetMapping("/history")
    public String viewOrderHistory(@AuthenticationPrincipal UserDetails user, Model model) {
        List<Order> orders = orderService.getOrders(user.getUsername());
        model.addAttribute("orders", orders);
        return "order"; // maps to order-history.html
    }




}
