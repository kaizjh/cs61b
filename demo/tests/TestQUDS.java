import bk14.DisjointSets;
import bk14.QuickUnionDS;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class TestQUDS {

    @Test
    public void testFind() {
        DisjointSets q1 = new QuickUnionDS(5);
        q1.connect(0, 1);
        q1.connect(0, 2);
        q1.connect(0, 3);
        q1.connect(0, 4);
        assertThat(q1.isConnected(4, 1)).isTrue();
    }
}
