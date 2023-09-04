package deque;

import java.util.Iterator;

/**
 * @author qiushui
 * @Date 2023/9/2
 */
public class LinkedListDeque<T> implements Deque<T> , Iterable<T>{

    private LinkNode<T> front;
    private LinkNode<T> end;

    private int size;

    public LinkedListDeque() {
        front = new LinkNode(null);
        end = new LinkNode(null);
        front.next = end;
        end.prev = front;
    }

    @Override
    public void addFirst(T item) {
        LinkNode t = new LinkNode(item);
        t.next = front.next;
        t.prev = front;
        front.next.prev = t;
        front.next = t;
        size++;
    }

    @Override
    public void addLast(T item) {
        LinkNode t = new LinkNode(item);
        t.prev = end.prev;
        t.next = end;
        end.prev.next = t;
        end.prev = t;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Iterator<T> it = iterator();
        while (it.hasNext()){
            T t = it.next();
            System.out.print(t + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if(size() == 0)
            return null;
        LinkNode<T> first = front.next;
        front.next = first.next;
        first.next.prev = front;
        size--;
        return first.item;
    }

    @Override
    public T removeLast() {
        if(size() == 0)
            return null;
        LinkNode<T> last = end.prev;
        end.prev = last.prev;
        last.prev.next = end;
        size--;
        return last.item;
    }

    @Override
    public T get(int index) {
        if(index < 0 || index >= size()){
            throw new IllegalArgumentException("out of index");
        }
        LinkNode<T> p = front.next;
        while (index > 0){
            p = p.next;
            index--;
        }
        return p.item;
    }

    public T getRecursive(int index){
        if(index < 0 || index >= size()){
            throw new IllegalArgumentException("out of index");
        }
        LinkNode<T> p = getRecursiveHelper(front.next, index);
        return p.item;
    }

    private LinkNode<T> getRecursiveHelper(LinkNode<T> p, int index){
        if(index == 0)
            return p;
        return getRecursiveHelper(p.next, --index);
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T>{

        private LinkNode<T> p;

        public MyIterator() {
            p = front;
        }

        @Override
        public boolean hasNext() {
            return p.next != end;
        }

        @Override
        public T next() {
            LinkNode<T> t = p.next;
            p = p.next;
            return t.item;
        }
    }

    private static class LinkNode<T>{

        T item;

        LinkNode next;

        LinkNode prev;

        public LinkNode(T item) {
            this(item,null,null);
        }

        public LinkNode(T item, LinkNode next, LinkNode prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

}
