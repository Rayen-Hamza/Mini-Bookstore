package com.example.bookstore.models;

public class CartItem {

    private Book book;
    private int quantity;

    public CartItem(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;

    }

    public Book getBook() {
        return book;
    }

    public int getQuantity() {
        return quantity;
    }


    public void increment() {
        quantity++;
    }

    public void decrement() {
        if (quantity > 0) {
            quantity--;
        }
    }

    public double getTotalPrice() {

        return book.getPrice() * quantity;

    }

}
