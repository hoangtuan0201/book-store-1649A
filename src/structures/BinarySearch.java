package structures;

import java.util.List;
import model.Order;


public class BinarySearch {

    private BinarySearch() {
    }
    public static int search(List<Order> orders, int targetId) {
        if (orders == null || orders.isEmpty()) {
            return -1;
        }
        int left = 0;
        int right = orders.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int currentId = orders.get(mid).getOrderId();

            if (currentId == targetId) {
                return mid;
            }
            if (currentId < targetId) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}
