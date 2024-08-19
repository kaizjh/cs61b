import jh61b.utils.Reflection;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

//     @Test
//     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
//     void noNonTrivialFields() {
//         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
//                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
//                 .toList();
//
//         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
//     }

    @Test
    /** Using debugger and the Java Visualizer plugin to test addFirst and addLast.
     *  Tests addFirst and addLast, resizeAdd, secondResizeAdd. */
    public void testAddFirstAndAddLast() {
        ArrayDeque61B<Integer> ad1 = new ArrayDeque61B<> ();
        ad1.addLast(0);
        ad1.addFirst(1);
        ad1.addFirst(2);
        ad1.addFirst(3);
        ad1.addLast(4);
        ad1.addLast(5);
        ad1.addLast(6);
        ad1.addLast(7); // [1, 0, 4, 5, 6, 7, 3, 2]
        ad1.addLast(8); // Resize [3, 2, 1, 0, 4, 5, 6, 7, 8]
        ad1.addLast(9);
        ad1.addFirst(10);
        ad1.addFirst(1);
        ad1.addFirst(1);
        ad1.addFirst(1);
        ad1.addFirst(1);
        ad1.addFirst(1); // [3, 2, 1, 0, 4, 5, 6, 7, 8, 9, 1, 1, 1, 1, 1, 10]
        ad1.addFirst(2); // Resize [2, 1, 1, 1, 1, 1, 10, 3, 2, 1, 0, 4, 5, 6, 7, 8, 9]
        ad1.addLast(2);
    }

    @Test
    /** Tests empty array, too larger index, negative index, valid index, after resize's get. */
    public void testGet() {
        ArrayDeque61B<Integer> ad1 = new ArrayDeque61B<> ();
        assertThat(ad1.get(1)).isEqualTo(null);

        ad1.addLast(0);
        ad1.addFirst(1);
        ad1.addFirst(2);
        ad1.addFirst(3);
        ad1.addLast(4);
        ad1.addLast(5);
        ad1.addLast(6);
        ad1.addLast(7); // [1, 0, 4, 5, 6, 7, 3, 2]
        assertThat(ad1.get(8)).isEqualTo(null);
        assertThat(ad1.get(23423)).isEqualTo(null);
        assertThat(ad1.get(-1)).isEqualTo(null);
        assertThat(ad1.get(1)).isEqualTo(2);
        assertThat(ad1.get(3)).isEqualTo(0);

        ad1.addFirst(8); // Resize [3, 2, 1, 0, 4, 5, 6, 7, 8]
        ad1.addLast(9);
        assertThat(ad1.get(0)).isEqualTo(8);
        assertThat(ad1.get(9)).isEqualTo(9);
        assertThat(ad1.get(4)).isEqualTo(0);
    }

    @Test
    /** Tests empty-array, small-size array, full-size array, resized array. */
    public void testIsEmpty() {
        ArrayDeque61B<Integer> ad1 = new ArrayDeque61B<>();
        assertThat(ad1.isEmpty()).isTrue();
        ad1.addLast(0);
        ad1.addFirst(1);
        ad1.addFirst(2);
        ad1.addFirst(3);
        assertThat(ad1.isEmpty()).isFalse();
        ad1.addLast(4);
        ad1.addLast(5);
        ad1.addLast(6);
        ad1.addLast(7); // [1, 0, 4, 5, 6, 7, 3, 2]
        assertThat(ad1.isEmpty()).isFalse();
        ad1.addFirst(8); // Resize [3, 2, 1, 0, 4, 5, 6, 7, 8]
        assertThat(ad1.isEmpty()).isFalse();
    }

    @Test
    /** Tests 0 size, small size, full size, resize. */
    public void testSize() {
        ArrayDeque61B<Integer> ad1 = new ArrayDeque61B<> ();
        assertThat(ad1.size()).isEqualTo(0);
        ad1.addLast(0);
        ad1.addFirst(1);
        ad1.addFirst(2);
        ad1.addFirst(3);
        assertThat(ad1.size()).isEqualTo(4);
        ad1.addLast(4);
        ad1.addLast(5);
        ad1.addLast(6);
        ad1.addLast(7); // [1, 0, 4, 5, 6, 7, 3, 2]
        assertThat(ad1.size()).isEqualTo(8);
        ad1.addFirst(8); // Resize [3, 2, 1, 0, 4, 5, 6, 7, 8]
        assertThat(ad1.size()).isEqualTo(9);
    }

    @Test
    /** Tests small array, full array, resized array. */
    public void testToList() {
        ArrayDeque61B<Integer> ad1 = new ArrayDeque61B<>();
        ad1.addLast(0);
        ad1.addFirst(1);
        ad1.addFirst(2);
        ad1.addFirst(3);
        assertThat(ad1.toList()).containsExactly(3, 2, 1, 0).inOrder();
        ad1.addLast(4);
        ad1.addLast(5);
        ad1.addLast(6);
        ad1.addLast(7); // [1, 0, 4, 5, 6, 7, 3, 2]
        assertThat(ad1.toList()).containsExactly(3, 2, 1, 0, 4, 5, 6, 7).inOrder();
        ad1.addFirst(8); // Resize [3, 2, 1, 0, 4, 5, 6, 7, 8]
        assertThat(ad1.toList()).containsExactly(8, 3, 2, 1, 0, 4, 5, 6, 7).inOrder();
    }

    @Test
    /** Tests removeFirst and removeLast, remove empty array, remove full array, remove-resize. */
    public void testRemoveFirstAndRemoveLast() {
        ArrayDeque61B<Integer> ad1 = new ArrayDeque61B<> ();
        assertThat(ad1.removeFirst()).isEqualTo(null);
        assertThat(ad1.removeLast()).isEqualTo(null);
        ad1.addLast(0);
        ad1.addFirst(1);
        ad1.addFirst(2);
        ad1.addFirst(3);
        ad1.addLast(4);
        ad1.addLast(5);
        ad1.addLast(6);
        ad1.addLast(7);
        ad1.addLast(8); // Resize [3, 2, 1, 0, 4, 5, 6, 7, 8]
        assertThat(ad1.removeLast()).isEqualTo(8);
        assertThat(ad1.removeFirst()).isEqualTo(3);
        assertThat(ad1.removeFirst()).isEqualTo(2);
        assertThat(ad1.removeFirst()).isEqualTo(1);
        assertThat(ad1.removeLast()).isEqualTo(7);
        assertThat(ad1.removeLast()).isEqualTo(6); // Resize [0, 4, 5]
    }

    @Test
    public void testGetRecursive() {
        System.out.println("No need to implement and test getRecursive for proj 1b");
    }

}
