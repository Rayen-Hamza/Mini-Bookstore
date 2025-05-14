package com.example.bookstore.controllers;


// Controller method




import com.example.bookstore.models.Book;
import com.example.bookstore.models.CartItem;
import com.example.bookstore.services.BookService;
import com.example.bookstore.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.*;



@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private BookService bookService;

    @PostMapping("/add/{bookId}")
    public String addBookToCart(@PathVariable Long bookId, HttpSession session) {
        Book bookOpt = bookService.getBookById(bookId);

        if (bookOpt!=null) {
            cartService.addToCart(bookOpt, session);
        } else {
            // You could log this or set a flash attribute for the user
            return "redirect:/books?error=notfound";
        }

        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removeBookFromCart(@RequestParam Long bookId, HttpSession session) {
        cartService.removeFromCart(bookId, session);
        return "redirect:/cart";
    }

    @PostMapping("/increment")
    public String incrementItem(@RequestParam Long bookId, HttpSession session) {
        cartService.incrementQuantity(bookId, session);
        return "redirect:/cart";
    }

    @PostMapping("/decrement")
    public String decrementItem(@RequestParam Long bookId, HttpSession session) {
        cartService.decrementQuantity(bookId, session);
        return "redirect:/cart";
    }




    @GetMapping
    public String viewCart(HttpSession session, Model model) {
        Map<Long, CartItem> cart = cartService.getCart(session);
        double total = cartService.getTotal(session);



        model.addAttribute("items", cart.values());
        model.addAttribute("total", total);
        return "cart";
    }
}
