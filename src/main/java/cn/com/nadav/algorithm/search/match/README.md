### KMP算法简介

KMP算法由Donald Knuth, Vaughan Pratt和James H. Morris共同发明。这种算法的关键在于它利用已匹配的部分信息来避免从头开始的重复匹配。

#### 核心思想

KMP算法的核心思想是，当一个字符不匹配时，我们可以知道其中一些字符是无需重新匹配的。为此，KMP算法保持一个所谓的“部分匹配”表（也称为“失配表”或“前缀表”），用于记录模式字符串中前缀和后缀的最长公共元素长度。

#### 应用场景

- 文本编辑器中的查找替换功能。
- DNA序列分析。
- 网络应用中的词汇匹配。

### KMP算法的工作原理

1. **构建部分匹配表**：这是KMP算法的预处理步骤。通过分析模式字符串，生成一个表来记录每个位置前的字符串中前缀和后缀的最长公共长度。
2. **执行搜索**：使用部分匹配表来决定在不匹配发生时下一步的移动。

### Java 实现

下面是KMP算法的Java实现。首先是构建部分匹配表的函数，然后是使用该表进行字符串搜索的函数。

```java
public class KMPStringSearch {

    private static int[] buildPartialMatchTable(String pattern) {
        int[] table = new int[pattern.length()];
        int index = 0;

        for (int i = 1; i < pattern.length(); i++) {
            if (pattern.charAt(i) == pattern.charAt(index)) {
                table[i] = ++index;
            } else {
                if (index != 0) {
                    index = table[index - 1];
                    i--;
                } else {
                    table[i] = 0;
                }
            }
        }
        return table;
    }

    public static int search(String text, String pattern) {
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

    public static void main(String[] args) {
        String text = "ABC ABCDAB ABCDABCDABDE";
        String pattern = "ABCDABD";
        System.out.println("匹配位置: " + search(text, pattern));
    }
}
```

### 结论

KMP算法相比传统的线性搜索算法，在最坏情况下提供了更优的时间复杂度。通过避免重复的比较，KMP算法对于长字符串的搜索尤其高效。学习KMP算法不仅可以提高您在字符串搜索方面的能力，还能加深您对算法设计和优化的理解。

KMP算法的理论和实践结合，使它成为了字符串搜索领域的一个重要里程碑，也是计算机科学和软件开发中的一个基本技能。

KMP算法中的部分匹配表（也称为前缀表）是算法的核心部分，用于在发生字符不匹配时确定搜索位置的下一个合理跳转。这个表基于模式字符串本身的前缀和后缀信息构建。下面是构建部分匹配表的详细步骤：

### 构建部分匹配表的步骤

1. **初始化**：创建一个与模式字符串长度相同的数组，用于存储每个位置的前缀和后缀的最长公共元素长度。数组的第一个元素始终为0，因为单个字符没有前缀或后缀。
2. **迭代比较**：遍历模式字符串中的每个字符，比较前缀和后缀，并更新数组。
    - 如果当前字符和前缀的下一个字符匹配，则当前位置的值是前一位置的值加1。
    - 如果不匹配，回溯到前一位置的前缀末尾处继续比较，直到找到匹配或整个前缀都不匹配。
3. **完成表**：重复上述步骤直到遍历完整个模式字符串，此时表就构建完成。

### Java 实现

以下是构建部分匹配表的Java代码示例：

```java
public static int[] buildPartialMatchTable(String pattern) {
    int patternLength = pattern.length();
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
```

### 解释

- **`table`数组**：存储每个位置的前缀和后缀的最长公共元素长度。
- **`length`变量**：表示当前已匹配的前缀长度。
- **循环中的条件**：如果当前字符与前缀的下一个字符匹配，则增加`length`。如果不匹配，通过`table[length - 1]`回溯到前一匹配位置。

### 重要性

部分匹配表的构建是KMP算法的预处理步骤，对整个搜索过程的效率至关重要。通过这个表，KMP算法能够在不匹配发生时快速跳过已知不可能匹配的部分，从而提高搜索效率。

### 演示

要使用表格说明KMP算法中部分匹配表的构建过程，我们首先需要选择一个示例模式字符串。假设模式字符串为 `"ABABAC"`
。下面是根据这个字符串构建部分匹配表的逐步过程：

### 示例模式字符串: "ABABAC"

| 索引 (i) | 模式字符串 (pattern[i]) | 已匹配前缀长度 (length) | 部分匹配表 (table[i]) |
|--------|--------------------|------------------|------------------|
| 0      | A                  | 0                | 0                |
| 1      | B                  | 0                | 0                |
| 2      | A                  | 1 (A)            | 1                |
| 3      | B                  | 2 (AB)           | 2                |
| 4      | A                  | 1 (ABA)          | 3                |
| 5      | C                  | 0                | 0                |

### 构建过程解释

1. **索引0 (A)**：没有前缀或后缀，因此匹配长度为0。
2. **索引1 (B)**：没有以B结尾的前缀，匹配长度仍为0。
3. **索引2 (A)**：存在一个字符"A"的前缀和后缀，因此匹配长度为1。
4. **索引3 (B)**：存在两个字符"AB"的前缀和后缀，因此匹配长度为2。
5. **索引4 (A)**：存在两个字符"ABC"的前缀和后缀，因此匹配长度为3。
6. **索引5 (C)**：没有以"C"结尾的前缀，匹配长度为0。