package algorithms;

import java.util.Comparator;

public class QuickSort { // Unstable sorting algorithm. Average time complexity is O(nLog(n)). Worst case - O(n^2) if all the elements are in a reversed order.

    private QuickSort() {
    }

    public static Person[] sort(Person[] people, Comparator<Person> comparator) {
        return sort(people, comparator, 0, people.length - 1);
    }

    public static Person[] sort(Person[] people, Comparator<Person> comparator, int lowerBound, int upperBound) {
        quickSort(people, lowerBound, upperBound, comparator);
        return people;
    }

    private static void quickSort(Person[] arr, int low, int high, Comparator<Person> comparator) {
        if (low < high) {
            int pi = partition(arr, low, high, comparator);
            quickSort(arr, low, pi - 1, comparator);
            quickSort(arr, pi + 1, high, comparator);
        }
    }

    private static int partition(Person[] arr, int low, int high, Comparator<Person> comparator) {
        Person pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j <= high - 1; j++) {
            if (comparator.compare(arr[j], pivot) < 0) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    private static void swap(Object[] arr, int i, int j) {
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
