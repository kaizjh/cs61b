import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Set;

import static com.google.common.truth.Truth.assertThat;

/** Tests by Brendan Hu, Spring 2015, revised for 2016 by Josh Hug and for 2023 by Noah Adhikari */
public class TestBSTMap {

    @Test
    public void sanityGenericsTest() {
        try {
            BSTMap<String, String> a = new BSTMap<>();
            BSTMap<String, Integer> b = new BSTMap<>();
            BSTMap<Integer, String> c = new BSTMap<>();
            BSTMap<Boolean, Integer> e = new BSTMap<>();
        } catch (Exception e) {
            fail();
        }
    }

    // This test assumes put/size/containsKey/get are implemented properly.
    @Test
    public void sanityClearTest() {
        BSTMap<String, Integer> b = new BSTMap<>();
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, 1+i);
            //make sure put is working via containsKey and get
            assertThat(b.get("hi" + i)).isEqualTo(1 + i);
            assertThat(b.containsKey("hi" + i)).isTrue();
        }
        assertThat(b.size()).isEqualTo(455);
        b.clear();
        assertThat(b.size()).isEqualTo(0);
        for (int i = 0; i < 455; i++) {
            assertThat(b.get("hi" + i)).isNull();
            assertThat(b.containsKey("hi" + i)).isFalse();
        }
    }

    // Assumes `put` is implemented properly.
    @Test
    public void sanityContainsKeyTest() {
        BSTMap<String, Integer> b = new BSTMap<>();
        assertThat(b.containsKey("waterYouDoingHere")).isFalse();
        b.put("waterYouDoingHere", 0);
        assertThat(b.containsKey("waterYouDoingHere")).isTrue();
    }

    // Assumes `put` is implemented properly.
    @Test
    public void sanityGetTest() {
        BSTMap<String, Integer> b = new BSTMap<>();
        assertThat(b.get("starChild")).isNull();
        assertThat(b.size()).isEqualTo(0);
        b.put("starChild", 5);
        assertThat(b.get("starChild")).isEqualTo(5);
        assertThat(b.size()).isEqualTo(1);
        b.put("KISS", 5);
        assertThat(b.get("KISS")).isEqualTo(5);
        assertThat(b.get("starChild")).isNotNull();
        assertThat(b.size()).isEqualTo(2);
    }

    // Assumes `put` is implemented properly.
    @Test
    public void sanitySizeTest() {
        BSTMap<String, Integer> b = new BSTMap<>();
        assertThat(b.size()).isEqualTo(0);
        b.put("hi", 1);
        assertThat(b.size()).isEqualTo(1);
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, 1);
        }
        assertThat(b.size()).isEqualTo(456);
    }

    // Assumes `get` and `containsKey` are implemented properly.
    @Test
    public void sanityPutTest() {
        BSTMap<String, Integer> b = new BSTMap<>();
        b.put("hi", 1);
        assertThat(b.containsKey("hi")).isTrue();
        assertThat(b.get("hi")).isEqualTo(1);
    }

    // Assumes `put` is implemented properly. This test is a bit tricky - remember that
    // `containsKey` should only care about the keys, not the values!
    @Test
    public void containsKeyNullTest() {
        BSTMap<String, Integer> b = new BSTMap<>();
        b.put("hi", null);
        assertThat(b.get("hi")).isNull();
        assertThat(b.containsKey("hi")).isTrue();
    }

    @Test
    public void treeTest() {
        BSTMap<String, String> b = new BSTMap<>();
        b.put("d", "parmesan");
        b.put("a", "mozzarella");
        b.put("c", "swiss");
        b.put("b", "pepper jack");
        b.put("e", "gouda");

        assertThat(b.size()).isEqualTo(5);
        assertThat(b.get("d")).isEqualTo("parmesan");
        assertThat(b.get("a")).isEqualTo("mozzarella");
        assertThat(b.get("c")).isEqualTo("swiss");
        assertThat(b.get("b")).isEqualTo("pepper jack");
        assertThat(b.get("e")).isEqualTo("gouda");

        b.put("b", "provolone");
        assertThat(b.size()).isEqualTo(5);
        assertThat(b.get("b")).isEqualTo("provolone");
    }

    @Test
    public void keySetTest() {
        BSTMap<String, String> b = new BSTMap<>();
        System.out.println(b.keySet());
        b.put("d", "parmesan");
        b.put("a", "mozzarella");
        System.out.println(b.keySet());
        b.put("c", "swiss");
        b.put("b", "pepper jack");
        b.put("e", "gouda");
        System.out.println(b.keySet());
    }

    @Test
    public void iteratorTest() {
        BSTMap<String, String> b = new BSTMap<>();
        b.put("d", "parmesan");
        b.put("a", "mozzarella");
        b.put("c", "swiss");
        b.put("b", "pepper jack");
        b.put("e", "gouda");

        for (String s : b) {
            System.out.println(s);
        }
    }

    @Test
    public void removeTest() {
        BSTMap<String, Integer> b = new BSTMap<>();
        b.put("d", 34);
        b.put("b", 2);
        b.put("a", 75);
        b.put("c", 334);
        b.put("h", 5);
        b.put("i", 95);
//        b.put("f", 49);
//        b.put("e", 111);
//        b.put("g", 785);
        assertThat(b.remove("b")).isEqualTo(2); // Two children
        assertThat(b.remove("c")).isEqualTo(334); // One child
        assertThat(b.remove("i")).isEqualTo(95); // No child
        assertThat(b.remove("d")).isEqualTo(34); // Remove root, two children

        for (String s : b) {
            System.out.println(s);
        }
    }

}
