package hackerrank.sorting.insertion;

import java.util.Scanner;

/**
 * Full version of insertion sort
 */
public class Solution2 {

    public static void insertionSortPart2(int[] A) {
        for (int i = 1; i < A.length; i++) {
            int key = A[i];
            int j = i - 1;
            for (; j >= 0 && A[j] > key; j--)
                A[j + 1] = A[j];
            A[j + 1] = key;
            printArray(A);
        }
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int s = in.nextInt();
        int[] ar = new int[s];
        for(int i=0;i<s;i++){
            ar[i]=in.nextInt();
        }
        insertionSortPart2(ar);

    }

    private static void printArray(int[] ar) {
        for(int n: ar){
            System.out.print(n+" ");
        }
        System.out.println("");
    }

}
