import ngrams.TimeSeries;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/** Unit Tests for the TimeSeries class.
 *  @author Josh Hug
 */
public class TimeSeriesTest {
    @Test
    public void testFromSpec() {
        TimeSeries catPopulation = new TimeSeries();
        catPopulation.put(1991, 0.0);
        catPopulation.put(1992, 100.0);
        catPopulation.put(1994, 200.0);

        TimeSeries dogPopulation = new TimeSeries();
        dogPopulation.put(1994, 400.0);
        dogPopulation.put(1995, 500.0);

        TimeSeries totalPopulation = catPopulation.plus(dogPopulation);
        // expected: 1991: 0,
        //           1992: 100
        //           1994: 600
        //           1995: 500

        List<Integer> expectedYears = new ArrayList<>
                (Arrays.asList(1991, 1992, 1994, 1995));

        assertThat(totalPopulation.years()).isEqualTo(expectedYears);

        List<Double> expectedTotal = new ArrayList<>
                (Arrays.asList(0.0, 100.0, 600.0, 500.0));

        for (int i = 0; i < expectedTotal.size(); i += 1) {
            assertThat(totalPopulation.data().get(i)).isWithin(1E-10).of(expectedTotal.get(i));
        }
    }

    @Test
    public void testEmptyBasic() {
        TimeSeries catPopulation = new TimeSeries();
        TimeSeries dogPopulation = new TimeSeries();

        assertThat(catPopulation.years()).isEmpty();
        assertThat(catPopulation.data()).isEmpty();

        TimeSeries totalPopulation = catPopulation.plus(dogPopulation);

        assertThat(totalPopulation.years()).isEmpty();
        assertThat(totalPopulation.data()).isEmpty();
    }

    @Test
    /** Assume that divideBy operation never divides by zero. */
    public void testDivideBy() {
        TimeSeries ts1 = new TimeSeries();
        ts1.put(1991, 0.0);
        ts1.put(1992, 100.0);

        TimeSeries ts2 = new TimeSeries();
        assertThrows(IllegalArgumentException.class, () -> ts1.dividedBy(ts2));
        ts2.put(1991, 400.0);
        ts2.put(1992, 50.0);
        ts2.put(1993, 500.0);
        TimeSeries newTS = ts1.dividedBy(ts2);
        List<Double> expectedQuotient = new ArrayList<>
                (Arrays.asList(0.0, 2.0));
        for (int i = 0; i < expectedQuotient.size(); i += 1) {
            assertThat(newTS.data().get(i)).isWithin(1E-10).of(expectedQuotient.get(i));
        }
    }

    @Test
    /** 本来没有这个test，后面写NGramMap.java 的时候出现了问题，所以写了这个test，虽说最后得知plus没有问题，但也算是一个安慰测试了吧。
     *  最后debug成功之后才发现，其实不用这个test也行，直接用断点，仔细判断一下也可以发现问题。不过既然已经写了这个test，就放在这里吧。
     */
    public void testPlus() {
        TimeSeries ts1 = new TimeSeries();
        TimeSeries ts2 = new TimeSeries();

        // empty plus empty -> empty
        assertThat(ts1.plus(ts2).years()).isEmpty();

        List<Integer> expectedYears = new ArrayList<>
                (Arrays.asList(1991, 1992));
        ts1.put(1991, 0.0);
        ts1.put(1992, 100.0);

        // not empty plus empty
        assertThat(ts1.plus(ts2).years()).isEqualTo(expectedYears);

        // empty plus not empty
        assertThat(ts2.plus(ts1).years()).isEqualTo(expectedYears);

    }
} 