package hackerrank.algorithm.sorting.insertion;

/**
 * Insert only one number (rightmost) to the correct position
 */
public class Solution1 {

    public static void insertIntoSorted(int[] ar) {
        int len = ar.length;
        int key = ar[len - 1];
        int i = len - 2;
        while (i >= 0 && ar[i] > key) {
            ar[i + 1] = ar[i];
            i--;
            print(ar);
        }

        /* i==0 or ar[i] <= key */
        ar[i + 1] = key;
        print(ar);
    }

    // Wrong!
    public static void insertIntoSorted2(int[] ar) {
        int ins = ar[ar.length - 1];
        for (int i = ar.length - 1; i > 0; i--) {
            if (ins < ar[i - 1]) {
                ar[i] = ar[i - 1];
            } else {
                ar[i] = ins;
                break;
            }
            print(ar);
        } // Didn't handle i==0
        print(ar);
    }

    private static void print(int[] ar) {
        for (int a : ar)
            System.out.printf("%d ", a);
        System.out.println();
    }

}
