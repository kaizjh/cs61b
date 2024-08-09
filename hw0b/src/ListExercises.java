import java.util.ArrayList;
import java.util.List;

public class ListExercises {

    /** Returns the total sum in a list of integers */
	public static int sum(List<Integer> L) {
        // TODO: Fill in this function.
        int total = 0;
        for (Integer integer : L) {
            total += integer;
        }
        return total;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        // TODO: Fill in this function.
        List<Integer> lst = new ArrayList<>();
        for (Integer integer : L) {
            if (integer % 2 == 0) {
                lst.add(integer);
            }
        }
        return lst;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        // TODO: Fill in this function.
        List<Integer> lst = new ArrayList<>();
        for (int curr : L1) {
            if (L2.contains(curr)) {
                if (!lst.contains(curr)) {
                    lst.add(curr);
                }
            }
        }
        return lst;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        // TODO: Fill in this function.
        int num = 0;
        for (String word : words) {
            for (int j = 0; j < word.length(); j++) {
                if (c == word.charAt(j)) {
                    num++;
                }
            }
        }
        return num;
    }
}
