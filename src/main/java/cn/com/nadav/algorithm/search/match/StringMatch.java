package cn.com.nadav.algorithm.search.match;

/**
 * This class provides methods for string matching using brute force search and Knuth-Morris-Pratt (KMP) algorithm.
 * It includes methods to perform brute force search, KMP search, and compute the Longest Proper Prefix which is also Suffix (LPS) array.
 * The class also contains a method to build the Partial Match Table for the KMP algorithm.
 * @author nadav cheung
 * @date 8/16/23
 */
public class StringMatch {


    /**
     * brute force search
     *
     * @param source
     * @param pattern
     * @return
     */
    public static int bruteForce(String source, String pattern) {
        if (source.length() < pattern.length()) {
            return -1;
        }

        // s[i,i+pattern.length-1] == pattern 判断s的子串是否和t相同
        for (int i = 0; i + pattern.length() - 1 < source.length(); i++) {

            int j = 0;
            for (; j < pattern.length(); j++) {
                if (source.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
            }
            if (j == pattern.length()) {
                return i;
            }
        }
        return -1;
    }


    /**
     * kmp search
     *
     * @param text
     * @param pattern
     * @return
     */
    public static void kmpSearch(String text, String pattern) {

        int[] lpsArray = computeLPSArray(pattern);

        int i = 0; // index for text
        int j = 0; // index for pattern

        while (i < text.length()) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }
            if (j == pattern.length()) {
                System.out.println("Pattern found at index: " + (i - j));
                j = lpsArray[j - 1];
            } else if (i < text.length() && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0)
                    j = lpsArray[j - 1];
                else
                    i++;
            }
        }
    }

    public static int search(String text, String pattern) {
        // 检查pattern是否为空
        if (pattern.isEmpty()) {
            return 0;  // 如果pattern为空字符串，则认为它出现在text的开始位置
        }

        // 检查text是否比pattern短
        if (text.length() < pattern.length()) {
            return -1;  // 如果text比pattern短，则pattern不可能出现在text中
        }

        int[] table = buildPartialMatchTable(pattern);
        int j = 0;

        for (int i = 0; i < text.length(); i++) {
            while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
                j = table[j - 1];
            }
            if (text.charAt(i) == pattern.charAt(j)) {
                j++;
                if (j == pattern.length()) {
                    return i - j + 1; // 匹配的起始索引
                }
            }
        }
        return -1; // 未找到模式
    }


    /**
     * 计算目标子串的最长公共前缀后缀数组(LPS 数组)
     * 部分匹配表 也叫失败函数或者next数组 该表格会记录模式子串中每个位置前缀子串和后缀子串相同的最长长度
     * longest proper prefix which is also suffix
     *
     * @param pattern
     * @return
     */
    private static int[] computeLPSArray(String pattern) {
        int[] lps = new int[pattern.length()];
        lps[0] = 0;

        int len = 0; // 最长公共前缀和后缀的长度
        int i = 1;

        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                // 找到更长公共子序列
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    // 回溯查找更短公共子序列   len = lps[len--];
                    len = lps[len - 1];
                } else {
                    // 已回溯至起始点仍未发现公共子序列
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    public static int[] buildPartialMatchTable(String pattern) {
        int patternLength = pattern.length();


        // fix bug index out of range
        if (patternLength == 0) {
            return new int[0];
        }

        int[] table = new int[patternLength];
        int length = 0; // 最长公共前缀和后缀的长度

        table[0] = 0; // 第一个元素总是0

        for (int i = 1; i < patternLength; i++) {
            while (length > 0 && pattern.charAt(length) != pattern.charAt(i)) {
                length = table[length - 1];
            }

            if (pattern.charAt(length) == pattern.charAt(i)) {
                length++;
            }

            table[i] = length;
        }

        return table;
    }

}
