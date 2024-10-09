package utils;

/** A collection of file paths to data files. Make sure your data folder is on the same
 *  level as "src".
 */
public class Utils {
    // 在NGramMap.test 中和在Main.java 中，这个PREFIX 是不同的，最大的问题就是当前工作目录的问题，通过下面这句代码即可测得
    // System.out.println(System.getProperty("user.dir"));
    // 我不清楚当前工作目录在这里为什么会发生变化，因为在intellij，我不是在终端使用命令来运行java文件的。
    private static final String PREFIX = "./proj2a/data/ngrams/";

    public static final String TOP_14337_WORDS_FILE = PREFIX + "top_14377_words.csv";

    public static final String TOP_49887_WORDS_FILE = PREFIX + "top_49887_words.csv";

    public static final String Q_WORDS_FILE = PREFIX + "words_that_start_with_q.csv";

    public static final String SHORT_WORDS_FILE = PREFIX + "very_short.csv";
    public static final String TOTAL_COUNTS_FILE = PREFIX + "total_counts.csv";

}

