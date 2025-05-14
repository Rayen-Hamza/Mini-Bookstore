package com.example.bookstore.services;

import com.example.bookstore.models.Book;
import com.example.bookstore.models.CartItem;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpSession;


import java.util.*;

@Service
public class CartService {

    private static final String SESSION_CART = "CART";

    @SuppressWarnings("unchecked")
    public Map<Long, CartItem> getCart(jakarta.servlet.http.HttpSession session) {
        Map<Long, CartItem> cart = (Map<Long, CartItem>) session.getAttribute(SESSION_CART);
        if (cart == null) {
            cart = new HashMap<>();
            session.setAttribute(SESSION_CART, cart);
        }
        return cart;
    }

    public void addToCart(Book book, jakarta.servlet.http.HttpSession session) {
        Map<Long, CartItem> cart = getCart(session);
        CartItem item = cart.get(book.getId());
        if (item == null) {
            cart.put(book.getId(), new CartItem(book, 1));
        } else {
            item.increment();
        }
    }

    public double getTotal(jakarta.servlet.http.HttpSession session) {
        return getCart(session).values().stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }

    public void removeFromCart(Long bookId, HttpSession session) {
        Map<Long, CartItem> cart = getCart(session);
        cart.remove(bookId);
    }

    public void incrementQuantity(Long bookId, HttpSession session) {
        Map<Long, CartItem> cart = getCart(session);
        CartItem item = cart.get(bookId);
        if (item != null) {
            item.increment();
        }
    }

    public void decrementQuantity(Long bookId, HttpSession session) {
        Map<Long, CartItem> cart = getCart(session);
        CartItem item = cart.get(bookId);
        if (item != null) {
            item.decrement();
            if (item.getQuantity() <= 0) {
                cart.remove(bookId);
            }
        }
    }


}
