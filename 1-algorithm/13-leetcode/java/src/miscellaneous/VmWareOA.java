package miscellaneous;

/**
 */
public class VmWareOA {

    static String rollingString(String s, String[] operations) {
        if (s.isEmpty() || operations.length == 0) return s;
        char[] chars = s.toCharArray();
        for (String op : operations) {
            String[] inst = op.split(" ");
            for (int i = Integer.valueOf(inst[0]); i <= Integer.valueOf(inst[1]); i++) {
                chars[i] = (char) ("R".equals(inst[2]) ? chars[i] + 1 : chars[i] - 1);
                if (chars[i] < 'a') chars[i] = 'z';
                else if (chars[i] > 'z') chars[i] = 'a';
            }
        }
        return new String(chars);
    }

    static void calculate(int[] arr) {
        for (int n : arr) {
            int total = (1 + ((n % 2 == 0) ? (n - 1) : n)) * ((n + 1) / 2) / 2;
            System.out.println(total);
        }
    }

    static String mergeStrings(String a, String b) {
        StringBuilder ret = new StringBuilder();
        int i = 0, len = Math.min(a.length(), b.length());
        for (; i < len; i++)
            ret.append(a.charAt(i)).append(b.charAt(i));
        ret.append(i < a.length() ? a.substring(i) : b.substring(i));
        return ret.toString();
    }

    private static int isPresent(Node root, int val){
        if (root == null) return 0;
        if (root.data == val) return 1;
        return val < root.data ? isPresent(root.left, val) : isPresent(root.right, val);
    }

    class Node {
        Node left, right;
        int data;
        Node(int newData) {
            left = right = null;
            data = newData;
        }
    }

}
