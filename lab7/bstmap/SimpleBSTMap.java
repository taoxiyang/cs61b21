package bstmap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author qiushui
 * @Date 2023/9/14
 */
public class SimpleBSTMap<K extends Comparable, V> implements Map61B<K, V> {

    private class Node{
        K key;

        V value;

        Node left;

        Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public boolean isLeaf(){
            return left == null && right == null;
        }

    }

    private class InnerIterator implements Iterator{

        private Object[] items;

        private int idx = 0;

        public InnerIterator() {
            items = new Object[size];
            init(root);
            idx = 0;
        }

        private void init(Node root){
            if(root == null){
                return;
            }
            init(root.left);
            items[idx++] = root.key;
            init(root.right);
        }

        @Override
        public boolean hasNext() {
            return idx < items.length;
        }

        @Override
        public K next() {
            return (K) items[idx++];
        }
    }

    private Node root;

    private int size = 0;

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        Node node = get(key,root);
        return node == null ? null : node.value;
    }

    private Node get(K key, Node root){
        if(root == null){
            return null;
        }
        int c = key.compareTo(root.key);
        if(c == 0){
            return root;
        }
        return c > 0 ? get(key, root.right) : get(key, root.left);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        assert key != null;
        root = insert(key, value, root);
    }

    private Node insert(K key, V value, Node root){
        if(root == null){
            size += 1;
            return new Node(key,value);
        }
        int c = key.compareTo(root.key);
        if(c == 0){
            root.value = value;
            return root;
        }else if(c > 0){
            root.right = insert(key, value, root.right);
        }else {
            root.left = insert(key, value, root.left);
        }
        return root;
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        keySet(set,root);
        return set;
    }

    private void keySet(Set<K> set, Node root){
        if(root == null){
            return;
        }
        keySet(set,root.left);
        set.add(root.key);
        keySet(set,root.right);
    }

    @Override
    public V remove(K key) {
        Node node = get(key, root);
        if(node == null){
            return null;
        }
        V value = node.value;
        root = remove(key, root);
        size--;
        return value;
    }

    @Override
    public V remove(K key, V value) {
        Node node = get(key, root);
        if(node == null || !node.value.equals(value)){
            return null;
        }
        root = remove(key, root);
        size--;
        return value;
    }

    private Node remove(K key, Node node){
        if(node == null){
            return null;
        }
        int cmp = key.compareTo(node.key);
        if(cmp < 0){
            node.left = remove(key, node.left);
        }else if(cmp > 0){
            node.right = remove(key, node.right);
        }else{
            if(node.left == null){
                return node.right;
            }else if(node.right == null){
                return node.left;
            }else{
                Node minNode = findMin(node.right);
                node.key = minNode.key;
                node.value = minNode.value;
                node.right = removeMin(node.right);
            }
        }
        return node;
    }

    private Node findMin(Node node){
        while (node.left != null){
            node = node.left;
        }
        return node;
    }

    private Node removeMin(Node node){
        if(node.left == null){
            return node.right;
        }
        node.left = removeMin(node.left);
        return node;
    }

    @Override
    public Iterator<K> iterator() {
        return new InnerIterator();
    }

    public void printInOrder(){
        printInOrder(root);
    }

    private void printInOrder(Node root){
        if(root == null){
            return;
        }
        printInOrder(root.left);
        System.out.print(String.format("[key=%s, value=%s],",root.key, root.value));
        printInOrder(root.right);
    }
}
