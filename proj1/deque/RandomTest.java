package deque;

import java.util.Random;

/**
 * @author qiushui
 * @Date 2023/9/4
 */
public class RandomTest {
    public static void main(String[] args) {

        Random random = new Random();
        for(int i = 0; i < 500; i++){
            System.out.println(random.nextInt(2));
        }
    }
}
