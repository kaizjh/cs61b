import graph.MyGraph;
import graph.WordNet;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static com.google.common.truth.Truth.assertThat;

public class TestWordNet {

    @Test
    public void basicTest() {
        WordNet wn = new WordNet("./data/wordnet/synsets11.txt","./data/wordnet/hyponyms11.txt");
        wn.fileToGraph();
    }

    @Test
    public void testHyponymsSimple(){
        WordNet wn = new WordNet("./data/wordnet/synsets11.txt","./data/wordnet/hyponyms11.txt");
        assertThat(wn.hyponyms("antihistamine")).isEqualTo(List.of("actifed", "antihistamine"));
    }
}
