package deque;

/**
 * @author qiushui
 * @Date 2023/9/2
 */
public interface Deque<T> {

    void addFirst(T item);

    void addLast(T item);

    default boolean isEmpty(){
        return size() == 0;
    }

    int size();

    void printDeque();

    T removeFirst();

    T removeLast();

    T get(int index);
}
