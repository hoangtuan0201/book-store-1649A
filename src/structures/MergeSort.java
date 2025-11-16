package structures;

import java.util.List;
import java.util.ArrayList;
import model.Book;


public class MergeSort {

    public static List<Book> sort(List<Book> input) {
        if (input == null) return null;
        if (input.size() <= 1) return new ArrayList<>(input);

        return mergeSort(new ArrayList<>(input));
    }

    private static List<Book> mergeSort(List<Book> list) {
        int n = list.size();
        if (n <= 1) return list;
        int mid = n / 2;
        // 1. Divide
        List<Book> left = mergeSort(new ArrayList<>(list.subList(0, mid)));
        List<Book> right = mergeSort(new ArrayList<>(list.subList(mid, n)));
        // 2. Conquer (Merge)
        return merge(left, right);
    }

    private static List<Book> merge(List<Book> left, List<Book> right) {
        List<Book> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            // Compare book titles alphabetically
            if (left.get(i).getTitle().compareToIgnoreCase(right.get(j).getTitle()) <= 0) {
                result.add(left.get(i++));
            } else {
                result.add(right.get(j++));
            }
        }

        while (i < left.size()) result.add(left.get(i++));
        while (j < right.size()) result.add(right.get(j++));
        return result;
    }
}

