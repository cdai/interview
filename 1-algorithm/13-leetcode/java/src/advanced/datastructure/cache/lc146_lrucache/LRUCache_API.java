package advanced.datastructure.cache.lc146_lrucache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 */
public class LRUCache_API {

    private Map<Integer,Integer> map;

    public LRUCache_API(int capacity) {
        this.map = new LinkedHashMap<Integer,Integer>(16, 0.75F, true) { /* true for access order than insert order */
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > capacity;
            }
        };
    }

    public int get(int key) {
        return map.getOrDefault(key, -1);
    }

    public void set(int key, int value) {
        map.put(key, value);
    }

}
