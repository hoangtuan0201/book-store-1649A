package model;

import java.util.List;

/**
 * The Order class represents a customer's order in the bookstore.
 * Each order contains an ID, customer details, and a list of books.
 */
public class Order {

    // --- Fields ---
    private int orderId;               // Unique order number
    private String customerName;       // Name of the customer
    private String shippingAddress;    // Address to deliver books
    private List<Book> bookList;       // List of books included in the order
    private boolean isShipped;         // Track whether the order has been shipped

    // --- Constructor ---
    public Order(int orderId, String customerName, String shippingAddress, List<Book> bookList) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.shippingAddress = shippingAddress;
        this.bookList = bookList;
        this.isShipped = false; // default when created
    }

    // --- Getters and Setters ---
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public boolean isShipped() {
        return isShipped;
    }

    public void setShipped(boolean shipped) {
        isShipped = shipped;
    }

    // --- Method to calculate total price of the order ---
    public double getTotalPrice() {
        double total = 0;
        for (Book b : bookList) {
            total += b.getPrice();
        }
        return total;
    }

    // --- toString() for displaying order info ---
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nOrder ID: ").append(orderId)
                .append("\nCustomer: ").append(customerName)
                .append("\nAddress: ").append(shippingAddress)
                .append("\nBooks Ordered:\n");

        for (Book b : bookList) {
            sb.append("   - ").append(b.toString()).append("\n");
        }

        sb.append("Total: $").append(getTotalPrice())
                .append("\nStatus: ").append(isShipped ? "Shipped" : "Pending");
        return sb.toString();
    }
}
