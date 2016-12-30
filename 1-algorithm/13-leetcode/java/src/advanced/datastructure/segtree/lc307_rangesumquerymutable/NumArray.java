package advanced.datastructure.segtree.lc307_rangesumquerymutable;

/**
 */
public class NumArray {

    public static void main(String[] args) {
        NumArray arr = new NumArray(new int[]{0, -3, -3, 1, 1, 2});
        System.out.println(arr.sumRange(2, 3));
        System.out.println(arr.sumRange(1, 3));

        arr.update(3, -1);
        System.out.println(arr.sumRange(2, 3));
        System.out.println(arr.sumRange(1, 3));
    }

    private int[] st;

    private int end;

    public NumArray(int[] nums) {
        if (nums.length == 0) return;
        int height = (int) (Math.ceil(Math.log(nums.length) / Math.log(2)));
        st = new int[2 * (int) Math.pow(2, height) - 1];
        end = nums.length - 1;
        build(nums, 0, nums.length - 1, 0);
    }

    void update(int i, int val) {
        modify(0, end, 0, i, val);
    }

    public int sumRange(int i, int j) {
        return query(0, end, 0, i, j);
    }

    private int build(int[] nums, int from, int to, int idx) {
        if (from == to) {
            st[idx] = nums[from];
            return st[idx];
        }
        int mid = from + (to - from) / 2;
        st[idx] = build(nums, from, mid, idx * 2 + 1) +
                build(nums, mid + 1, to, idx * 2 + 2);
        return st[idx];
    }

    private void modify(int from, int to, int idx, int i, int val) {
        if (from == to) {
            st[idx] = val;
            return;
        }
        int mid = from + (to - from) / 2;
        if (i <= mid) modify(from, mid, idx * 2 + 1, i, val);
        else modify(mid + 1, to, idx * 2 + 2, i, val);
        st[idx] = st[idx * 2 + 1] + st[idx * 2 + 2];
    }

    private int query(int from, int to, int idx, int lower, int upper) {
        //if (from == to) return st[idx];
        if (lower <= from && to <= upper) return st[idx];
        int mid = from + (to - from) / 2;
        if (upper <= mid) return query(from, mid, idx * 2 + 1, lower, upper);
        if (mid < lower) return query(mid + 1, to, idx * 2 + 2, lower, upper);
        return query(from, mid, idx * 2 + 1, lower, upper) +
                query(mid + 1, to, idx * 2 + 2, lower, upper);
    }

}
