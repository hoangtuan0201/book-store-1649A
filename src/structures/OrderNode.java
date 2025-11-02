package structures;

import model.Order;

public class OrderNode {
    Order data;
    OrderNode next;

    public OrderNode(Order data) {
        this.data = data;
        this.next = null;
    }

}
