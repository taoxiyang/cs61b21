package deque;

import java.util.Iterator;

/**
 * @author qiushui
 * @Date 2023/9/2
 */
public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

    private T[] items;

    private int size = 0;

    private int startIdx = 0;

    public ArrayDeque() {
        items = (T[]) new Object[8];
    }

    @Override
    public void addFirst(T item) {
        if(!isEmpty()){
            tryExpand();
            startIdx--;
            if(startIdx < 0){
                startIdx = items.length - 1;
            }
        }
        items[startIdx] = item;
        size++;
    }

    @Override
    public void addLast(T item) {
        tryExpand();
        int idx = startIdx + size;
        if(idx >= items.length){
            idx -= items.length;
        }
        items[idx] = item;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for(T item : this){
            System.out.print(item + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if(isEmpty()){
            return null;
        }
        tryShrink();
        T t = items[startIdx];
        startIdx++;
        if(startIdx >= items.length){
            startIdx = 0;
        }
        size--;
        return t;
    }

    @Override
    public T removeLast() {
        if(isEmpty()){
            return null;
        }
        tryShrink();
        int lastIdx = startIdx + size - 1;
        if(lastIdx >= items.length){
            lastIdx -= items.length;
        }
        T t = items[lastIdx];
        size--;
        return t;
    }

    @Override
    public T get(int index) {
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("index error");
        }
        int idx = startIdx + index;
        if(idx >= items.length){
            idx -= items.length;
        }
        return items[idx];
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator<T> implements Iterator<T>{

        private int pos;

        public MyIterator() {
            this.pos = startIdx;
        }

        @Override
        public boolean hasNext() {
            return (pos >= startIdx && pos < startIdx + size)
                    || (pos < startIdx + size - items.length);
        }

        @Override
        public T next() {
            T t = (T) items[pos];
            pos++;
            if(pos >= items.length){
                pos = 0;
            }
            return t;
        }
    }

    private void resize(int newSize){
        T[] newItems = (T[]) new Object[newSize];
        if(startIdx + size <= items.length){
            System.arraycopy(items, startIdx, newItems,0,size);
        }else {
            int firstHalf = items.length - startIdx;
            System.arraycopy(items, startIdx, newItems,0,firstHalf);
            System.arraycopy(items, 0, newItems,firstHalf,size - firstHalf);
        }
        items = newItems;
        startIdx = 0;
    }

    private void tryExpand(){
        if(size < items.length){
            return;
        }
        resize( 2 * items.length);
    }

    private void tryShrink(){
        if(size <= items.length / 4 && items.length >= 16){
            resize( items.length / 2);
        }
    }
}
