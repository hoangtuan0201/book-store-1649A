package structures;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import model.Order;

/**
 * Simple FIFO Queue for Order objects.
 */
public class Queue {
    private OrderLinkedList data;

    public Queue() {

        data = new OrderLinkedList(null);
    }

    // Add new order to the end of the queue
    public void enqueue(Order o) {
        data.addLast(o);
    }

    // Remove and return the first order
    public Order dequeue() {
        if (data.isEmpty()) {
            return null;
        } else {
            return data.removeFirst();
        }
    }

    // View the next order without removing it
    public Order peek() {
        if (data.isEmpty()) {
            return null;
        }else {
            return data.getFirst();
        }
    }

    // Check if queue is empty
    public boolean isEmpty() {
        return data.isEmpty();
    }


    // Return number of orders waiting
    public int size() {
        return data.size();
    }

    public List<Order> toList() {
        List<Order> list = new ArrayList<>();
        OrderNode current = data.getHead(); // lấy node đầu tiên trong LinkedList
        while (current != null) {
            list.add(current.data);
            current = current.next;
        }
        return list;
    }

    @Override
    public String toString() {
        String result = "";
        OrderNode current = data.getHead();

        while (current != null) {
            result += current.data.toString() + "\n";
            result += "---------\n";
            current = current.next;

        }

        if (result.isEmpty()) {
            result = "Queue is empty.\n";
        }

        return result;
    }
}
