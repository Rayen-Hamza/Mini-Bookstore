package com.example.bookstore.controllers;

import com.example.bookstore.models.Book;
import com.example.bookstore.services.BookService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.ModelAttribute;


import java.util.List;

@Controller

public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public String getBooksPage(Model model, HttpServletRequest request,@ModelAttribute("csrf") CsrfToken csrfToken ) {
        List<Book> books = bookService.getAllBooks();
        System.out.println("the books" + books);
        model.addAttribute("books", books);

        model.addAttribute("_csrf", csrfToken);
        return "books"; // maps to templates/books.html
    }
}
