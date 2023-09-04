package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

/**
 * @author qiushui
 * @Date 2023/9/4
 */
public class GuitarHero {

    static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public static void main(String[] args) {

        GuitarString[] guitars = new GuitarString[keyboard.length()];

        for(int i = 0; i < keyboard.length(); i++){
            guitars[i] = new GuitarString(440 * Math.pow(2,(i-24) / 12));
        }

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if(keyboard.indexOf(key) != -1){
                    guitars[keyboard.indexOf(key)].pluck();
                }
            }

            /* compute the superposition of samples */
            double sample = 0;
            for(int i = 0; i < keyboard.length(); i++){
                sample += guitars[i].sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for(int i = 0; i < keyboard.length(); i++){
                guitars[i].tic();
            }
        }
    }
}
