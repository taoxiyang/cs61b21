package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE

    @Test
    public void testThreeAddThreeRemove(){
        AListNoResizing aList = new AListNoResizing();
        BuggyAList bList = new BuggyAList();
        aList.addLast(4);
        bList.addLast(4);

        aList.addLast(5);
        bList.addLast(5);

        aList.addLast(6);
        bList.addLast(6);

        Assert.assertEquals(aList.removeLast(),bList.removeLast());
        Assert.assertEquals(aList.removeLast(),bList.removeLast());
        Assert.assertEquals(aList.removeLast(),bList.removeLast());
    }

    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList B = new BuggyAList();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                Assert.assertEquals(size,B.size());
                System.out.println("size: " + size);
            } else if (operationNumber == 2) {
                // size
                if(L.size() > 0){
                    Integer last = L.removeLast();
                    Assert.assertEquals(last,B.removeLast());
                    System.out.println("removeLast: " + last);
                }

            }
        }
    }
}
