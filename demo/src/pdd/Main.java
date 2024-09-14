package pdd;

import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[][] dices = new int[N][6];

        // 初始化
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 6; j++) {
                dices[i][j] = in.nextInt();
            }
        }

        int total = 0;
        // 骰子i 与 骰子j 进行两两匹配
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                // 接下来是除了对应方向的配对
                // 在两个骰子中
                int position = -1;
                for (int k1 = 0; k1 < 6; k1 += 2) {
                    for (int k2 = 0; k2 < 6; k2 += 2) {
                        // 找到数字完全相同的一组方向，记录
                        position = k2;
                        if (dices[i][k1] == dices[j][k2] && dices[i][k1 + 1] == dices[j][k2 + 1]) {
                            // 如果位置没有对应，则通过旋转对应位置
                            if (k1 != k2) {
                                rotate(i, k1, k2, dices);
                            }
                            break;
                        }
                    }
                }

                // 如果没有找到数字完全相同的一组方向，则不是同一类骰子，进行下一个对比，
                // 否则，进行对应方向的检验
                if (position < 0) {
                    continue;
                } else {
                    if (isCycle(position, dices[i], dices[j])) {
                        total++;
                    }
                }
            }
        }
        System.out.print(total);
    }

    // d1（一组方向两个面）通过一次旋转到达d2的位置，d1两个面的位置关系不会改变，d2则会改变
    public static void rotate(int i, int d1, int d2, int[][] dices) {
        int tmp1 = dices[i][d1];
        int tmp2 = dices[i][d1 + 1];
        dices[i][d1] = dices[i][d2 + 1];
        dices[i][d1 + 1] = dices[i][d2];
        dices[i][d2] = tmp1;
        dices[i][d2 + 1] = tmp2;
    }

    // 如果两个骰子是同类，那么去掉已经确定相同的一组面之后的四个面会是一个循环，沿着这个循环，两个骰子的面的数字是相等的。
    public static boolean isCycle(int position, int[] dice1, int[] dice2) {
        // 去掉已经对应的两面，剩余留下作对比
        int[] remaining1 = new int[4];
        int[] remaining2 = new int[4];
        for (int i = 0; i < 4; i++) {
            remaining1[i] = dice1[(position + 2 + i) % 6];
            remaining2[i] = dice2[(position + 2 + i) % 6];
        }

        // 找到剩余数列中的相等的数
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                // 以此为起点，查看之后的数列是否相等
                if (remaining1[i] == remaining2[j]) {
                    for (int k = 1; k < 4; k++) {
                        // 如果有一个不相等，则两个骰子不是同类
                        if (remaining1[(i + k) % 4] != remaining2[(j + k) % 4]) {
                            return false;
                        }
                    }
                    // 如果都相等，则两个骰子是同类
                    return true;
                }
            }
        }
        // 如果没有相同的，则不是同类
        return false;
    }
}