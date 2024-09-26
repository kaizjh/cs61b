package main;

import edu.princeton.cs.algs4.In;

import static utils.Utils.*;

public class FileReadDemo {
    public static void main(String[] args) {
        // 一个debug小技巧，用下面这行代码检查当前工作目录，再确定SHORT_WORDS_FILE有没有错误。
        // System.out.println(System.getProperty("user.dir"));
        In in = new In(SHORT_WORDS_FILE);
        int i = 0;

        while (!in.isEmpty()) {
            // Additionally, to check if there are any lines left in a file, you should use hasNextLine (and not isEmpty).
            i += 1;
            String nextLine = in.readLine();
            System.out.print("Line " + i + " is: ");
            System.out.println(nextLine);
            System.out.print("After splitting on tab characters, the first word is: ");
            String[] splitLine = nextLine.split("\t");
            System.out.println(splitLine[0]);
        }
    }
}
