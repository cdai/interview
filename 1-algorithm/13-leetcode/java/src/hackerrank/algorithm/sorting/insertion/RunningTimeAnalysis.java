package hackerrank.algorithm.sorting.insertion;

import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 * Analyze time complexity of insertion time
 */
public class RunningTimeAnalysis {

    public static void main(String[] args) {
        try (Scanner in = new Scanner(new BufferedInputStream(System.in))) {
            int[] A = new int[in.nextInt()];
            for (int i = 0; i < A.length; i++)
                A[i] = in.nextInt();
            System.out.println(analyzeInsertSort(A));
        }
    }

    private static int analyzeInsertSort(int[] A) {
        int time = 0;
        for (int i = 1; i < A.length; i++) {
            int key = A[i];
            int j = i - 1;
            for ( ;j >= 0 && A[j] > key; j--, time++) // Only change: increment time
                A[j + 1] = A[j];
            A[j + 1] = key;
        }
        return time;
    }

}
