实现部分匹配表（Partial Match Table，PMT），也称为失效函数表或者next数组，是KMP算法的关键部分。它用于在字符串匹配过程中跳过已经比较过的字符，提高匹配效率。

实现思路如下：

1. 初始化：创建一个长度等于模式串长度的数组pmt，并将所有元素初始化为0。同时设置两个指针i和j，其中i表示当前正在计算pmt值的位置（从1开始），j表示与之前已经计算好的最长相同前后缀子串进行比较时所处位置（初始值为0）。

2. 比较：比较模式串中i和j位置上的字符是否相等。
    - 如果相等，则说明找到了一个更长的相同前后缀子串。此时更新pmt[i] = j + 1，并将i和j都向右移动一位。
    - 如果不相等：
        - 若j大于0，则将其更新为pmt[j-1]，即回溯到之前最长公共前后缀子串末尾继续进行比较；
        - 若j已经回溯至起始位置仍然无法找到公共子串，则直接将pmt[i]设为0，并向右移动一位。

3. 循环：重复步骤2直至遍历完整个模式串。

4. 结果：得到填充完成的部分匹配表PMT。

以下是Python代码实现示例：

```python
def generate_pmt(pattern):
    p_len = len(pattern)
    pmt = [0] * p_len
    i, j = 1, 0

    while i < p_len:
        if pattern[i] == pattern[j]:
            # 找到更长公共子序列
            j += 1
            pmt[i] = j
            i += 1
        else:
            if j > 0:
                # 回溯查找更短公共子序列
                j = pmt[j-1]
            else:
                # 已回溯至起始点仍未发现公共子序列
                pmt[i] = 0 
                i += 1
                
    return pmt

pattern_str="ABCDABD"
print(generate_pmt(pattern_str))
```

输出结果:

[0, 0, 0, 0, 1, 2, 3]

这意味着对应每个字符在该字符之前存在多少个连续且相同地字母组成的前缀和后缀。例如，对于模式串"ABCDABD"，其部分匹配表为[0, 0, 0, 0, 1, 2]。



LPS（Longest Proper Prefix which is also suffix）数组是一个用于存储字符串中最长公共前后缀长度的数组。它在计算机科学和字符串匹配算法中，尤其是KMP（Knuth-Morris-Pratt）算法中有着重要应用。

LPS数组的定义：给定一个字符串str，lps[i]表示从索引0到i范围内的子串str[0…i]具有的最长相同前缀和后缀长度。换句话说，lps[i]就是这个子串中最大长度k满足条件：str[0…k-1]=str[i-k+1…i]。

例如：
对于字符串“ABCDABCA”，我们可以得到以下LPS数组：

```
Index:  0   1   2   3   4   5   6   7
String: A | B | C | D | A | B | C | A
LPS:    [0,|_0_,|_0_,|_0_,|_1_,|_2_,|_3_, |_4_] 
```

解释：
- 对于索引为 i = {1,2,3} 的字符，“A”、“AB” 和 “ABC”的没有相同的非空前缀和后缀。
- 对于索引为 i = {4} 的字符，“ABCD”的最长相同前后缀为“A”，所以 lps[4]=1。
- 对于索引为 i = {5} 的字符，“ABCDA”的最长相同前后缘为“AB”，所以 lps[5]=2。
- 对于索引为 i = {6} 的字符，“ABCDAB”的最长相同前后边界为“ABC”，所以 lps[6]=3。
- 对于索引位 i = {7} 的字符，“ABCDABC”的最长相同前后边界为“BCD”, 所以 lps[7]=4。

通过构建 LPS 数组，我们可以更高效地进行模式匹配操作，在 KMP 算法等场景下发挥关键作用。



ABABABBABAB

A
AB
ABA
ABAB             AB AB
ABABA            ABA ABA
ABABAB           ABAB ABAB
ABABABB         