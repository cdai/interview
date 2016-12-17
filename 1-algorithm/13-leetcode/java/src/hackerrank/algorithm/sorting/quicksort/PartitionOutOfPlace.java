package hackerrank.algorithm.sorting.quicksort;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Partition array into 3 parts of left, equal, right.
 */
public class PartitionOutOfPlace {

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int[] A = new int[in.nextInt()];
            for (int i = 0; i < A.length; i++)
                A[i] = in.nextInt();

            List<Integer> B = partition(A);

            for (int b : B)
                System.out.printf("%d ", b);
            System.out.println();
        }
    }

    private static List<Integer> partition(int[] A) {
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int a : A) {
            if (a < A[0]) left.add(a);
            else if (a > A[0]) right.add(a);
            /* Ignore a == A[0] */
        }

        for (int i = 0; i < A.length - left.size() - right.size(); i++)
            left.add(A[0]);
        left.addAll(right);
        return left;
    }

}
