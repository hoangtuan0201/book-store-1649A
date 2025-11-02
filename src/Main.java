import model.Book;
import model.Order;
import structures.Queue;
import structures.MergeSort;
import structures.BinarySearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Queue orderQueue = new Queue();
    private static List<Order> processed = new ArrayList<>();
    private static int nextOrderId = 1001;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while (running) {
            showMenu();
            System.out.print("Choose an option: ");
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1" -> placeOrder(sc);
                case "2" -> processNextOrder();
                case "3" -> searchOrder(sc);
                case "4" -> viewAllOrders();
                case "5" -> showStats();
                case "0" -> {
                    running = false;
                    System.out.println("Exiting. Goodbye.");
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
        sc.close();
    }

    private static void showMenu() {
        System.out.println("\n=== Bookstore Menu ===");
        System.out.println("1) Place Order");
        System.out.println("2) Process Next Order");
        System.out.println("3) Search Order by ID");
        System.out.println("4) View All Orders");
        System.out.println("5) Stats");
        System.out.println("0) Exit");
    }

    private static void placeOrder(Scanner sc) {
        System.out.print("Customer name: ");
        String name = sc.nextLine().trim();
        System.out.print("Shipping address: ");
        String addr = sc.nextLine().trim();
        List<Book> books = new ArrayList<>();
        while (true) {
            System.out.print("Add a book? (y/n): ");
            String a = sc.nextLine().trim().toLowerCase();
            if (!a.equals("y")) break;
            try {
                System.out.print("Book id (int): ");
                int id = Integer.parseInt(sc.nextLine().trim());
                System.out.print("Title: ");
                String title = sc.nextLine();
                System.out.print("Author: ");
                String author = sc.nextLine();
                System.out.print("Price: ");
                double price = Double.parseDouble(sc.nextLine().trim());
                books.add(new Book(id, title, author, price));
            } catch (NumberFormatException e) {
                System.out.println("Invalid number, try adding the book again.");
            }
        }
        Order o = new Order(nextOrderId++, name, addr, books);
        orderQueue.enqueue(o);
        System.out.println("Order added to queue with ID: " + o.getOrderId());
    }

    private static void processNextOrder() {
        Order o = orderQueue.dequeue();
        if (o == null) {
            System.out.println("No orders to process.");
            return;
        }
        System.out.println("Processing Order ID: " + o.getOrderId());
        List<Book> sorted = MergeSort.sort(o.getBookList());
        o.setBookList(sorted);
        o.setShipped(true);
        processed.add(o);
        System.out.println(o);
    }

    private static void searchOrder(Scanner sc) {
        System.out.print("Enter order ID to search: ");
        try {
            int id = Integer.parseInt(sc.nextLine().trim());
            // build a combined list: processed + queue
            List<Order> all = new ArrayList<>(processed);
            all.addAll(orderQueue.toList());
            // sort by orderId to use binary search
            all.sort((a, b) -> Integer.compare(a.getOrderId(), b.getOrderId()));
            int idx = BinarySearch.search(all, id);
            if (idx == -1) System.out.println("Order not found.");
            else System.out.println(all.get(idx));
        } catch (NumberFormatException e) {
            System.out.println("Invalid id.");
        }
    }

    private static void viewAllOrders() {
        System.out.println("\n-- Orders in Queue --");
        if (orderQueue.isEmpty()) System.out.println("(none)");
        else System.out.println(orderQueue);
        System.out.println("\n-- Processed Orders --");
        if (processed.isEmpty()) System.out.println("(none)");
        else {
            for (Order o : processed) System.out.println(o + "\n-----\n");
        }
    }

    private static void showStats() {
        int totalOrders = processed.size() + orderQueue.size();
        double revenue = 0;
        for (Order o : processed) revenue += o.getTotalPrice();
        System.out.println("Total orders: " + totalOrders);
        System.out.println("Processed orders: " + processed.size());
        System.out.println("Pending in queue: " + orderQueue.size());
        System.out.println("Total revenue (processed): $" + revenue);
    }
}
