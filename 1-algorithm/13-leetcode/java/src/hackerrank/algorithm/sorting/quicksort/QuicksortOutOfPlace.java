package hackerrank.algorithm.sorting.quicksort;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 */
public class QuicksortOutOfPlace {

    public static void main(String[] args) {
        QuicksortOutOfPlace sol = new QuicksortOutOfPlace();
        try (Scanner in = new Scanner(System.in)) {
            int[] A = new int[in.nextInt()];
            for (int i = 0; i < A.length; i++)
                A[i] = in.nextInt();

            sol.quicksort(A, 0, A.length - 1);
        }
    }

    private void quicksort(int[] A, int low, int high) {
        if (low >= high) return;
        int mid = partition(A, low, high);
        quicksort(A, low, mid - 1);
        quicksort(A, mid + 1, high);
        print(A, low, high);
    }

    private int partition(int[] A, int low, int high) {
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        int pivot = A[low];
        for (int i = low + 1; i <= high; i++) {
            if (A[i] < pivot) left.add(A[i]);
            else right.add(A[i]);
        }

        for (int i = low, j = 0; i <= high; i++, j++) {
            if (j < left.size()) A[i] = left.get(j);
            else if (j < left.size() + 1) A[i] = pivot;
            else A[i] = right.get(j - left.size() - 1);
        }
        return low + left.size();
    }

    private void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    private void print(int[] A, int low, int high) {
        for (int i = low; i <= high; i++)
            System.out.printf("%d ", A[i]);
        System.out.println();
    }

}
