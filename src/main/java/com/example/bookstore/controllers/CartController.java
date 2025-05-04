package com.example.bookstore.controllers;

import com.example.bookstore.models.Book;
import com.example.bookstore.models.CartItem;
import com.example.bookstore.services.BookService;
import com.example.bookstore.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private BookService bookService;

    @PostMapping("/add/{bookId}")
    public String addBookToCart(@PathVariable Long bookId, HttpSession session) {
        Optional<Book> book = bookService.getAllBooks().stream()
                .filter(b -> b.getId().equals(bookId))
                .findFirst();

        if (book.isEmpty()) {
            return "Book not found";
        }

        cartService.addToCart(book.get(), session);
        return "Book added to cart";
    }

    @GetMapping
    public Map<String, Object> viewCart(HttpSession session) {
        Map<Long, CartItem> cart = cartService.getCart(session);
        double total = cartService.getTotal(session);

        Map<String, Object> response = new HashMap<>();
        response.put("items", cart.values());
        response.put("total", total);
        return response;
    }
}
