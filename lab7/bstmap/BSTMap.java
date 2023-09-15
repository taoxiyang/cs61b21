package bstmap;

import java.util.Iterator;
import java.util.Set;

/**
 * @author qiushui
 * @Date 2023/9/14
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{

    private Map61B<K,V> map = new LLRBTreeMap<>();

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    @Override
    public V get(K key) {
        return map.get(key);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void put(K key, V value) {
        map.put(key,value);
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public V remove(K key) {
        return map.remove(key);
    }

    @Override
    public V remove(K key, V value) {
        return map.remove(key,value);
    }

    @Override
    public Iterator<K> iterator() {
        return map.iterator();
    }

    public void printInOrder(){
        map.printInOrder();
    }
}
