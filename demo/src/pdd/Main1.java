package pdd;

import java.util.Arrays;
import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        char[] b = in.nextLine().trim().toCharArray();
        Arrays.sort(b);
        for (int i = 0; i < n; i++) {
            System.out.print(b[i]);
        }
        System.out.println();
    }
}