package bstmap;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

/**
 * @author qiushui
 * @Date 2023/9/14
 */
public class BSTMapTest {

    private Map61B<Integer, String> bstMap = new BSTMap<>();


    @Test
    public void test(){
        Assert.assertTrue(bstMap.size() == 0);
        bstMap.put(1,"a");
        Assert.assertTrue(bstMap.size() == 1);
        Assert.assertTrue(bstMap.containsKey(1));
        Assert.assertTrue(bstMap.get(1) == "a");
        Assert.assertTrue(!bstMap.containsKey(2));
        bstMap.put(1,"b");
        Assert.assertTrue(bstMap.size() == 1);
        Assert.assertTrue(bstMap.get(1) == "b");
        Assert.assertTrue(!bstMap.containsKey(3));
        Assert.assertTrue(bstMap.get(3) == null);
        bstMap.put(2,"b");
        bstMap.put(3,"c");
        bstMap.put(4,"d");
        bstMap.put(5,"e");
        bstMap.put(6,"f");
        Assert.assertTrue(bstMap.containsKey(6));
        Assert.assertTrue(bstMap.containsKey(5));
        Assert.assertTrue(bstMap.containsKey(4));
        Assert.assertTrue(bstMap.containsKey(3));
        Assert.assertTrue(bstMap.containsKey(2));
        Assert.assertTrue(bstMap.containsKey(1));
        Assert.assertTrue(bstMap.get(3) == "c");
        Assert.assertTrue(bstMap.get(4) == "d");
        Assert.assertTrue(bstMap.size() == 6);
        bstMap.put(7,"g");
        Assert.assertTrue(bstMap.size() == 7);
        bstMap.put(0,"z");
        Assert.assertTrue(bstMap.size() == 8);
        bstMap.put(-1,"x");
        Assert.assertTrue(bstMap.size() == 9);
        Assert.assertTrue(bstMap.containsKey(-1));
        Assert.assertTrue(bstMap.containsKey(0));
        bstMap.printInOrder();
        System.out.println();
        Iterator it = bstMap.iterator();
        while (it.hasNext()){
            System.out.print(it.next() + " ");
        }

        bstMap.clear();
        Assert.assertTrue(bstMap.size() == 0);
        Assert.assertTrue(!bstMap.containsKey(-1));


    }
}
