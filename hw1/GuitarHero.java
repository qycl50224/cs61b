/** A client that uses the synthesizer package to replicate a plucked guitar string sound */
import es.datastructur.synthesizer.GuitarString;
import edu.princeton.cs.introcs.StdDraw;
public class GuitarHero {
    public static GuitarString[] GS;
    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        GS =  new GuitarString[37];
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        for(int i = 0; i < 37; i++) {
            GS[i] = new GuitarString( Math.pow(2.0, (i-24)/12.)*440);
        }
        int index = 0;
        while (true) {
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                index = keyboard.indexOf(key);
                if(index == -1) {
                    index = 0;
                } else{
                    GS[index].pluck();
                }

            }

            /* compute the superposition of samples */
            double sample = GS[index].sample();

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            GS[index].tic();

        }
    }
}

