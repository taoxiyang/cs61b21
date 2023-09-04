package deque;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ucb.util.Stopwatch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * @author qiushui
 * @Date 2023/9/4
 */
public class ArrayDequeTest {

    private Deque deque;

    @Before
    public void setUp(){
        deque = new ArrayDeque();
    }

    @Test
    public void basicTest(){
        /*deque is empty*/
        Assert.assertTrue(deque.isEmpty());

        /*add first*/
        deque.addFirst(1);
        Assert.assertEquals(new Integer(1),deque.get(0));
        Assert.assertTrue(!deque.isEmpty());
        Assert.assertEquals(1,deque.size());

        /*remove last*/
        Assert.assertEquals(new Integer(1),deque.removeLast());
        Assert.assertTrue(deque.isEmpty());

        /*add last*/
        deque.addLast(1);
        Assert.assertTrue(!deque.isEmpty());
        Assert.assertEquals(1,deque.size());

        /*remove first*/
        Assert.assertEquals(new Integer(1),deque.removeFirst());
        Assert.assertTrue(deque.isEmpty());


        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addLast(4);
        Assert.assertEquals(4,deque.size());
        Assert.assertEquals(new Integer(3),deque.removeFirst());
        Assert.assertEquals(new Integer(4),deque.removeLast());
        Assert.assertEquals(new Integer(1),deque.removeLast());
        Assert.assertEquals(new Integer(2),deque.removeLast());
        Assert.assertTrue(deque.isEmpty());

        for(int i = 1; i <= 14; i++){
            deque.addFirst(i);
        }
        for(int i = 32; i >= 20; i--){
            deque.addLast(i);
        }
        for(int i = 14; i >= 5; i--){
            Assert.assertEquals(new Integer(i),deque.removeFirst());
        }
        for(int i = 20; i <= 32; i++){
            Assert.assertEquals(new Integer(i),deque.removeLast());
        }
        Assert.assertEquals(4,deque.size());
    }

    @Test
    public void resizeTest(){
        /*deque is empty*/
        Assert.assertTrue(deque.isEmpty());
        for(int i = 0; i <= 5000; i++){
            deque.addFirst(i);
        }
        Assert.assertEquals(new Integer(5000),deque.removeFirst());
        Assert.assertEquals(new Integer(0),deque.removeLast());
        Assert.assertEquals(new Integer(4989),deque.get(10));

        for(int i = -4000; i <= -1000; i++){
            deque.addLast(i);
        }
        Assert.assertEquals(new Integer(-1000),deque.removeLast());
        Assert.assertEquals(new Integer(4999),deque.removeFirst());

        for(int i = 0; i < 7990; i++){
            deque.removeLast();
        }
        Assert.assertEquals(8,deque.size());
        deque.removeFirst();
        Assert.assertEquals(7,deque.size());
        deque.removeFirst();
        deque.removeFirst();
        deque.removeFirst();
        Assert.assertEquals(4,deque.size());
        deque.removeFirst();
        Assert.assertEquals(3,deque.size());

    }

    @Test
    public void randomTest(){
        /*deque is empty*/
        Stopwatch stopwatch = new Stopwatch();
        stopwatch.start();
        Assert.assertTrue(deque.isEmpty());
        ArrayList list = new ArrayList();
        Random random = new Random();
        int size = 50000;
        int testCount = 10000;
        int valueBound = 100000;
        for(int i = 0; i < size; i++){
            int value = random.nextInt(valueBound);
            int x = random.nextInt(2);
            if(x == 0){
                list.add(value);
                deque.addLast(value);
            }else{
                list.add(0,value);
                deque.addFirst(value);
            }
        }

        for(int i = 0; i < testCount; i++){
            int randomIndex = random.nextInt(size);
            Assert.assertEquals(list.get(randomIndex),deque.get(randomIndex));
        }
        Assert.assertTrue("array deque time out",stopwatch.getElapsed() < 500);
    }

    @Test
    public void iteratorTest(){
        /*deque is empty*/
        Assert.assertTrue(deque.isEmpty());
        for(int i = 0; i < 35; i++){
            deque.addLast(i);
        }
        Iterable arrayDeque = (Iterable) deque;
        Iterator it = arrayDeque.iterator();
        int i = 0;
        while (it.hasNext()){
            Assert.assertEquals(it.next(),new Integer(i));
            i++;
        }

        it = arrayDeque.iterator();
        i = 0;
        while (it.hasNext()){
            Assert.assertEquals(it.next(),new Integer(i));
            i++;
        }
    }
}
