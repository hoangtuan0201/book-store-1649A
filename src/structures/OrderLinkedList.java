package structures;

import model.Order;

public class OrderLinkedList {
        OrderNode head;

        public OrderLinkedList(OrderNode head) {
            this.head = head;
        }

        // add to the last of queue
        public void addLast(Order o) {
            OrderNode newNode = new OrderNode(o);
            if (head == null) {
                head = newNode;
                return;
            }
            OrderNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        //remove first node of the queue
        public Order removeFirst() {
            if (head == null) return null;
            Order removed = head.data;
            head = head.next;
            return removed;
        }
        //get first node of the queue
        public Order getFirst() {
            if (head == null) return null;
            return head.data;
        }
        public int size(){
            int size = 0;
            OrderNode current = head;
            while (current != null) {
                size++;
                current = current.next;
            }
            return size;

        }

        //get head node of the queue
        public OrderNode getHead() {
            return head;
        }


        //check if the queue is empty
        public boolean isEmpty() {
            return head == null;
        }







}


