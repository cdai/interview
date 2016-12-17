package hackerrank.algorithm.sorting.quicksort;

import java.util.Scanner;

/**
 * Standard quicksort implementation
 */
public class QuicksortInPlace {

    public static void main(String[] args) {
        QuicksortInPlace sol = new QuicksortInPlace();
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
    }

    private int partition(int[] A, int low, int high) {
        int j = low; /* [low,j): <= pivot; [j,i): > pivot; [i,high]: unknown */
        for (int i = low, pivot = A[high]; i < high; i++) // stop before pivot!
            if (A[i] <= pivot)
                swap(A, i, j++);
        swap(A, j, high);
        print(A);
        return j;
    }

    private void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    private void print(int[] A) {
        for (int i = 0; i < A.length; i++)
            System.out.printf("%d ", A[i]);
        System.out.println();
    }

}
