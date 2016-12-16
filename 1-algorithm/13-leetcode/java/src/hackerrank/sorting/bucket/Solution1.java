package hackerrank.sorting.bucket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 */
public class Solution1 {

    public static void main(String[] args) {
        try (Scanner in = new Scanner(new BufferedInputStream(System.in));
             PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out))) {
            int total = Integer.parseInt(in.nextLine());

            Map<Integer, List<String>> buckets = new TreeMap<>();
            for (int i = 0; i < total; i++) {
                int num = in.nextInt();
                String str = in.next();
                if (!buckets.containsKey(num))
                    buckets.put(num, new ArrayList<>());
                buckets.get(num).add((i < total / 2) ? "-" : str);
            }

            for (List<String> strs : buckets.values())
                for (String str : strs)
                    out.printf("%s ", str);
            out.println();
        }
    }

    public static void main2(String[] args) {
        try (Scanner in = new Scanner(new BufferedInputStream(System.in));
             PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out))) {
            int total = Integer.parseInt(in.nextLine());
            Pair[] pairs = new Pair[total];
            for (int i = 0; i < total; i++)
                pairs[i] = new Pair(in.nextInt(), i, in.next()); // no need nextLine()

            Pair[] sorted = bucketSort(pairs);
            for (Pair p : sorted)
                out.printf("%s ", (p.idx < total / 2 ? "-" : p.data));
            out.println();
        }
    }

    private static Pair[] bucketSort(Pair[] pairs) {
        List<Pair>[] buckets = new List[pairs.length];
        for (Pair p : pairs) {
            if (buckets[p.key] == null)
                buckets[p.key] = new ArrayList<>();
            buckets[p.key].add(p);
        }

        Pair[] ret = new Pair[pairs.length];
        int i = 0;
        for (List<Pair> bucket : buckets) {
            if (bucket == null) continue;
            for (Pair p : bucket)
                ret[i++] = p;
        }
        return ret;
    }

    public static class Pair {
        int key; // How many numbers are less than or equal to current
        int idx;
        String data;

        Pair(int key, int idx, String data) {
            this.key = key;
            this.idx = idx;
            this.data = data;
        }
    }

}
