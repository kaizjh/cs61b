package pdd;

import java.util.Arrays;
import java.util.Scanner;

class Pdd {

    public static void pddArray() {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        while (T-- > 0) {
            int length = scanner.nextInt();
            int theOne = scanner.nextInt();
            int last = scanner.nextInt();
            if (theOne != 1) {
                System.out.println("NO");
            } else {
                System.out.println(1 + length * (length - 1) / 2 <= last ? "YES" : "NO");
            }
        }
    }

    public static int pddMatch() {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int[] arr = new int[N];

        while (N-- > 0) {
            arr[N] = scanner.nextInt();
        }

        int counts = 0;
        Arrays.sort(arr);
        int len = arr.length;
        for (int i = 0; i < len - 1; i ++) {
            for (int j = i + 1; j < len; j ++) {
                if (arr[j] - arr[i] >= M) {
                    counts ++;
                    len --;
                    arr[j] = arr[len];
                }
            }
        }
        return counts;
    }

    public static void main(String[] args) {
        // pddArray();
        int i = pddMatch();
        System.out.println(i);
    }

}