package bstmap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author qiushui
 * @Date 2023/9/14
 */
public class LLRBTreeMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class EntryNode{

        final static boolean BLACK = true;
        final static boolean RED = false;

        K key;

        V value;

        EntryNode left;

        EntryNode right;

        EntryNode parent;

        boolean color;

        public EntryNode(K key, V value, EntryNode parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
            color = RED;
        }
    }

    private class InnerIterator<K> implements Iterator<K>{

        private final Object[] items;

        private int idx = 0;

        public InnerIterator() {
            items = new Object[size];
            init(root);
            idx = 0;
        }

        private void init(EntryNode root){
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


    private EntryNode root;

    private int size = 0;

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return getNode(key, root) != null;
    }

    private EntryNode getNode(K key, EntryNode node){
        if(node == null){
            return null;
        }
        int cmp = key.compareTo(node.key);
        if(cmp == 0){
            return node;
        }else if(cmp > 0){
            return getNode(key, node.right);
        }else {
            return getNode(key, node.left);
        }
    }

    @Override
    public V get(K key) {
        EntryNode node = getNode(key, root);
        return node == null ? null : node.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if(key != null){
            root = insert(key, value, root, null);
        }
    }

    private EntryNode rotateLeft(EntryNode p){
        if(p != null && p.right != null){
            EntryNode r = p.right;
            Boolean rcolor = r.color;
            r.color = p.color;
            p.color = rcolor;
            p.right = r.left;
            if(r.left != null){
                r.left.parent = p;
            }
            r.parent = p.parent;
            if(p.parent == null){

            }else if(p.parent.left == p){
                p.parent.left = r;
            }else {
                p.parent.right = r;
            }
            r.left = p;
            p.parent = r;
            return r;
        }else {
            return p;
        }
    }

    private EntryNode rotateRight(EntryNode p){
        if(p != null && p.left != null){
            EntryNode l = p.left;
            Boolean lcolor = l.color;
            l.color = p.color;
            p.color = lcolor;
            p.left = l.right;
            if(l.right != null){
                l.right.parent = p;
            }
            l.parent = p.parent;
            if(p.parent == null){

            }else if(p.parent.left == p){
                p.parent.left = l;
            }else{
                p.parent.right = l;
            }
            l.right = p;
            p.parent = l;
            return l;
        }else{
            return p;
        }
    }

    private void flip(EntryNode p){
        p.left.color = !p.left.color;
        p.right.color = !p.right.color;
        p.color = !p.color;
    }

    private boolean isRed(EntryNode p){
        return p != null && p.color == EntryNode.RED;

    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        keySet(set,root);
        return set;
    }

    private void keySet(Set<K> set, EntryNode root){
        if(root == null){
            return;
        }
        keySet(set,root.left);
        set.add(root.key);
        keySet(set,root.right);
    }

    @Override
    public V remove(K key) {
        EntryNode node = getNode(key,root);
        V v = node.value;
        if(node == null){
            return null;
        }
        root = remove(key,root);
        return v;
    }

    @Override
    public V remove(K key, V value) {
        EntryNode node = getNode(key,root);
        V v = node.value;
        if(node == null || !value.equals(v)){
            return null;
        }
        root = remove(key,root);
        return v;
    }

    private EntryNode insert(K key, V value, EntryNode node, EntryNode parent){
        if(node == null){
            size++;
            return new EntryNode(key,value,parent);
        }
        int cmp = key.compareTo(node.key);
        if(cmp == 0){
            node.value = value;
        }else if(cmp > 0){
            node.right = insert(key, value, node.right, node);
        }else{
            node.left = insert(key, value, node.left, node);
        }

        return balance(node);
    }

    private EntryNode remove(K key, EntryNode node){
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
                EntryNode minNode = findMin(node.right);
                node.key = minNode.key;
                node.value = minNode.value;
                node.right = removeMin(node.right);
            }
        }
        return balance(node);
    }

    private EntryNode balance(EntryNode node){
        if(isRed(node.right) && !isRed(node.left)){
            node = rotateLeft(node);
        }
        if(isRed(node.left) && isRed(node.left.left)){
            node = rotateRight(node);
        }
        if(isRed(node.left) && isRed(node.right)){
            flip(node);
        }
        return node;
    }

    private EntryNode findMin(EntryNode node){
        while (node.left != null){
            node = node.left;
        }
        return node;
    }

    private EntryNode removeMin(EntryNode node){
        if(node.left == null){
            return node.right;
        }
        node.left = removeMin(node.left);
        return balance(node);
    }

    @Override
    public Iterator<K> iterator() {
        return new InnerIterator<>();
    }

    public void printInOrder(){
//        printInOrder(root);
        checkBlance(root);
    }

    private void printInOrder(EntryNode root){
        if(root == null){
            return;
        }
        printInOrder(root.left);
        System.out.print(String.format("[key=%s, value=%s],",root.key, root.value));
        printInOrder(root.right);
    }

    private void checkBlance(EntryNode node){
        if(node == null){
            return;
        }
        if(isRed(node.right)){
            throw new RuntimeException("right red");
        }else if(isRed(node.left) && isRed(node.left.left)){
            throw new RuntimeException("continue left red");
        }
        checkBlance(node.left);
        checkBlance(node.right);
    }
}
