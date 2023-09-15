package bstmap;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.Random;

/**
 * @author qiushui
 * @Date 2023/9/14
 */
public class BSTMapTest {

    private Map61B<Integer, String> bstMap = new LLRBTreeMap<>();

    @Test
    public void test(){
        Assert.assertTrue(bstMap.size() == 0);
        bstMap.put(1,"b");
        Assert.assertTrue(bstMap.size() == 1);
        Assert.assertTrue(bstMap.containsKey(1));
        Assert.assertTrue(bstMap.get(1) == "b");
        Assert.assertTrue(!bstMap.containsKey(2));
        bstMap.put(1,"a");
        Assert.assertTrue(bstMap.size() == 1);
        Assert.assertTrue(bstMap.get(1) == "a");
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

        for(int i = 100; i < 50000; i++){
            bstMap.put(i,"x");
        }

        Random random = new Random();
        for(int i = 0; i < 100; i++){
            int k = random.nextInt(100000000);
            bstMap.put(k,"x");
            Assert.assertTrue(bstMap.containsKey(k));
        }
        bstMap.printInOrder();

//        Iterator it = bstMap.iterator();
//        while (it.hasNext()){
//            System.out.print(it.next() + " ");
//        }

        Assert.assertEquals("x",bstMap.remove(-1));
        Assert.assertEquals("d",bstMap.remove(4));
        Assert.assertEquals("e",bstMap.remove(5,"e"));
        Assert.assertEquals(null,bstMap.remove(7,"e"));
//
//        Assert.assertEquals(6,bstMap.size());
//        Assert.assertTrue(!bstMap.containsKey(5));
//        Assert.assertTrue(bstMap.containsKey(7));
//
        Assert.assertEquals("z",bstMap.remove(0));
        Assert.assertEquals("a",bstMap.remove(1));
        Assert.assertEquals("b",bstMap.remove(2));
        Assert.assertEquals("c",bstMap.remove(3));
        Assert.assertEquals("f",bstMap.remove(6));
        bstMap.printInOrder();
//        System.out.println();
//        bstMap.printInOrder();
//        Assert.assertEquals("g",bstMap.remove(7));
//        System.out.println();
//        bstMap.printInOrder();
//        Assert.assertEquals(0,bstMap.size());
//        System.out.println();
//        bstMap.printInOrder();
//
//        bstMap.put(-1,"x");
//        Assert.assertEquals(1,bstMap.size());
//        System.out.println();
//        bstMap.printInOrder();
//
//        bstMap.clear();
//        Assert.assertTrue(bstMap.size() == 0);
//        Assert.assertTrue(!bstMap.containsKey(-1));
    }
}
