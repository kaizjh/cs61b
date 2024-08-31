package gh2;

// TODO: maybe more imports
import deque.Deque61B;
import deque.MaxArrayDeque61B;

//Note: This file will not compile until you complete the Deque61B implementations
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    // TODO: uncomment the following line once you're ready to start this portion
    private Deque61B<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        // TODO: Initialize the buffer with capacity = SR / frequency. You'll need to
        //       cast the result of this division operation into an int. For
        //       better accuracy, use the Math.round() function before casting.
        //       Your should initially fill your buffer with zeros.
        int capacity = (int) Math.round(SR / frequency);

        /** I choose arrayDeque not likedListDeque, because:
         *  1. ad uses less space to store an item than lld;
         *  2. ad and lld do all that same stuff, but when we use deque.get(), ad takes less time;
         *  3. the two aforementioned advantages will become increasingly evident as the scale expands significantly.
         */
        buffer = new MaxArrayDeque61B<>();
        // TODO: Dequeue everything in buffer, and replace with random numbers
        for (int i = 0; i < capacity; i ++) {
            buffer.addFirst(0.0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        // TODO: Dequeue everything in buffer, and replace with random numbers
        //       between -0.5 and 0.5. You can get such a number by using:
        //       double r = Math.random() - 0.5;
        //
        //       Make sure that your random numbers are different from each
        //       other. This does not mean that you need to check that the numbers
        //       are different from each other. It means you should repeatedly call
        //       Math.random() - 0.5 to generate new random numbers for each array index.
        for (int i = 0; i < buffer.size(); i ++) {
            buffer.removeLast();
            buffer.addFirst(Math.random() - 0.5);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        // TODO: Dequeue the front sample and enqueue a new sample that is
        //       the average of the two multiplied by the DECAY factor.
        //       **Do not call StdAudio.play().**
        buffer.addLast(sample());
        buffer.removeFirst();
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        // TODO: Return the correct thing.
        double first = buffer.get(0);
        double second = buffer.get(1);
        return DECAY * 0.5 * (first + second);
    }
}
    // TODO: Remove all comments that say TODO when you're done.
