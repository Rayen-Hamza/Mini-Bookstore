package com.example.bookstore.models;

import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Book book;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderItem() {}
    public OrderItem(Book book, int quantity, Order order) {
        this.book = book;
        this.quantity = quantity;
        this.order = order;
    }

    public Long getId() { return id; }
    public Book getBook() { return book; }
    public int getQuantity() { return quantity; }
    public void setBook(Book book) { this.book = book; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }

}
