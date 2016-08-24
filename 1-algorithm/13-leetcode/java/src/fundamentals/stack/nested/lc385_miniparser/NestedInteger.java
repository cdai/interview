package fundamentals.stack.nested.lc385_miniparser;

import java.util.ArrayList;
import java.util.List;

class NestedInteger {

    private int integer;

    private List<NestedInteger> list = new ArrayList<>();

    public NestedInteger() {
    }

    public NestedInteger(int value) {
        this.integer = value;
    }

    public void setInteger(int integer) {
        this.integer = integer;
    }

    public int getInteger() {
        return integer;
    }

    public void add(NestedInteger ni) {
        list.add(ni);
    }

    public List<NestedInteger> getList() {
        return list;
    }
}
