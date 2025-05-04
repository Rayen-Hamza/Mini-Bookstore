package com.example.bookstore.services;

import com.example.bookstore.models.Book;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BookService {

    private final List<Book> books = Arrays.asList(
            new Book(1L, "1984", "George Orwell", 9.99),
            new Book(2L, "The Alchemist", "Paulo Coelho", 12.50),
            new Book(3L, "Clean Code", "Robert C. Martin", 29.99)
    );

    public List<Book> getAllBooks() {
        return books;
    }
}
