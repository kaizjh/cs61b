package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

/**
 *  A client that uses the synthesizer package to replicate a plucked guitar string sound
 *  如果运行程序之后按A，C键没有声音可以试一下 win+空格键（或者手动将电脑输入法切换成英语）
 */
public class GuitarHeroLite {
    private static final double CONCERT_A = 440.0;
    private static final double CONCERT_C = CONCERT_A * Math.pow(2.0, - 5.0 / 6.0);
    // 计算D4的频率
    private static final double D4 = CONCERT_C * Math.pow(2, 1.0 / 6.0);

    // 计算E4的频率
    private static final double E4 = D4 * Math.pow(2, 1.0 / 6.0);

    // 计算F4的频率
    private static final double F4 = E4 * Math.pow(2, 1.0 / 6.0);

    // 计算G4的频率
    private static final double G4 = F4 * Math.pow(2, 1.0 / 6.0);

    // 计算B4的频率
    private static final double B4 = G4 * Math.pow(2, 2.0 / 6.0);

    private static final int WIDTH = 512;
    private static final int HEIGHT = 512;

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        GuitarString stringA = new GuitarString(CONCERT_A);
        GuitarString stringC = new GuitarString(CONCERT_C);
        GuitarString stringD = new GuitarString(D4);
        GuitarString stringE = new GuitarString(E4);
        GuitarString stringF = new GuitarString(F4);
        GuitarString stringG = new GuitarString(G4);
        GuitarString stringB = new GuitarString(B4);

        StdDraw.setCanvasSize(WIDTH, HEIGHT);
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.05);
        StdDraw.text(WIDTH / 2, (HEIGHT + 16) / 2, "Play the guitar!");
        StdDraw.text(WIDTH / 2, (HEIGHT - 32) / 2, "Type A or C");
        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (key == 'A') {
                    StdDraw.clear();
                    StdDraw.text(WIDTH / 2, HEIGHT / 2, "A");

                    StdDraw.show();
                    stringA.pluck();

                } else if (key == 'C') {
                    StdDraw.clear();
                    StdDraw.text(WIDTH / 2, HEIGHT / 2, "C");
                    StdDraw.show();

                    stringC.pluck();
                } else if (key == 'D') {
                    StdDraw.clear();
                    StdDraw.text(WIDTH / 2, HEIGHT / 2, "D");
                    StdDraw.show();

                    stringD.pluck();
                } else if (key == 'E') {
                    StdDraw.clear();
                    StdDraw.text(WIDTH / 2, HEIGHT / 2, "E");
                    StdDraw.show();

                    stringE.pluck();
                } else if (key == 'F') {
                    StdDraw.clear();
                    StdDraw.text(WIDTH / 2, HEIGHT / 2, "F");
                    StdDraw.show();

                    stringF.pluck();
                } else if (key == 'G') {
                    StdDraw.clear();
                    StdDraw.text(WIDTH / 2, HEIGHT / 2, "G");
                    StdDraw.show();

                    stringG.pluck();
                } else if (key == 'B') {
                    StdDraw.clear();
                    StdDraw.text(WIDTH / 2, HEIGHT / 2, "B");
                    StdDraw.show();

                    stringB.pluck();
                }
            }

            /* compute the superposition of samples */
            double sample = stringA.sample() + stringC.sample() + stringD.sample() + stringE.sample() + stringF.sample() + stringG.sample() + stringB.sample();

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            stringA.tic();
            stringC.tic();
            stringD.tic();
            stringE.tic();
            stringF.tic();
            stringG.tic();
            stringB.tic();
        }
    }
}

//        1 1 | 5 5 | 6 6 | 5 - |
//        4 4 | 3 3 | 2 2 | 1 - |
//        5 5 | 4 4 | 3 2 | 1 - |
//        5 5 | 4 3 | 2 - | 1 1 |
        //1（中央C，C4）：261.63Hz
        //2（D4）：293.66Hz
        //3（E4）：329.63Hz
        //4（F4）：349.23Hz
        //5（G4）：392.00Hz
        //6（A4）：440.00Hz，您已经定义了 CONCERT_A
        //7（B4）：493.88Hz