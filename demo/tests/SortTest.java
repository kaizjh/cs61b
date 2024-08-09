import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class SortTest {

    /** Tests the sort method of the Sort class. */
    @Test
    public void testSort() {
        String[] input = {"cows", "dwell", "above", "clouds"};
        String[] expected = {"above", "clouds", "cows", "dwell"};
        Sort.sort(input);
        assertThat(input).isEqualTo(expected);
    }
}

