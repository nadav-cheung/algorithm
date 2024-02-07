package cn.com.nadav.structure.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * email nadav.cheung@gmail.com
 * date 2024-02-07 20:15:16
 * description
 *
 * @author Nadav Cheung
 * @since 1.0.x
 */
class ArrayListTest {


    private ArrayList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new ArrayList<>();
    }

    @Test
    @DisplayName("新创建的ArrayList应该为空")
    void newListShouldBeEmpty() {
        assertTrue(list.empty(), "新创建的列表应该为空");
    }

    @Test
    @DisplayName("通过构造函数设置的初始容量应该正确")
    void initialCapacityShouldBeSetCorrectly() {
        ArrayList<Integer> customCapacityList = new ArrayList<>(20);
        // 无法直接测试内部容量，所以我们添加足够多的元素来检测是否发生了扩容
        for (int i = 0; i < 20; i++) {
            customCapacityList.add(i);
        }
        assertEquals(20, customCapacityList.size(), "添加的元素数量应该与初始容量匹配，且不触发扩容");
    }

    @Test
    @DisplayName("向ArrayList添加元素应该成功")
    void addElementsShouldIncreaseSize() {
        list.add(1);
        list.add(2);
        assertFalse(list.empty(), "添加元素后列表不应为空");
        assertEquals(2, list.size(), "列表的大小应该为添加的元素数量");
    }

    @Test
    @DisplayName("应该能够正确获取ArrayList中的元素")
    void getElementShouldReturnCorrectElement() {
        list.add(1);
        list.add(2);
        assertEquals(Integer.valueOf(1), list.get(0), "索引0处的元素应该是1");
        assertEquals(Integer.valueOf(2), list.get(1), "索引1处的元素应该是2");
    }

    @Test
    @DisplayName("设置ArrayList中的元素应该成功")
    void setElementShouldReplaceElement() {
        list.add(1);
        list.add(2);
        list.set(0, 10);
        assertEquals(Integer.valueOf(10), list.get(0), "索引0处的元素应该被替换为10");
    }

    @Test
    @DisplayName("从ArrayList中移除元素应该成功")
    void removeElementShouldDecreaseSize() {
        list.add(1);
        list.add(2);
        list.remove(0);
        assertEquals(1, list.size(), "移除元素后列表的大小应该减1");
        assertEquals(Integer.valueOf(2), list.get(0), "索引0处的元素应该是先前索引1处的元素");
    }

    @Test
    @DisplayName("访问无效索引应该抛出IndexOutOfBoundsException")
    void accessingInvalidIndexShouldThrowException() {
        list.add(1);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(5), "访问无效索引应该抛出IndexOutOfBoundsException");
    }

    @Test
    @DisplayName("添加null元素应该成功")
    void addingNullShouldBePossible() {
        list.add(null);
        assertNull(list.get(0), "列表应该接受null元素");
    }

    @Test
    @DisplayName("扩容机制应该正确工作")
    void ensureCapacityShouldWorkCorrectly() {
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        assertEquals(1000, list.size(), "列表应该能够扩容以存储更多元素");
    }

    @Test
    @DisplayName("构造函数传入负初始容量应抛出IllegalArgumentException")
    void constructorWithNegativeInitialCapacityShouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> new ArrayList<>(-1), "传入负初始容量应抛出IllegalArgumentException");
    }


    @Test
    @DisplayName("添加元素到列表头部")
    void addElementToFront() {
        list.add(0, 1);
        list.add(0, 2);
        assertEquals(Integer.valueOf(2), list.get(0), "头部添加的元素应为2");
        assertEquals(Integer.valueOf(1), list.get(1), "第二个元素应为1");
    }

    @Test
    @DisplayName("添加元素到列表中间")
    void addElementToMiddle() {
        list.add(1);
        list.add(3);
        list.add(1, 2);
        assertEquals(Integer.valueOf(2), list.get(1), "中间添加的元素应为2");
    }

    @Test
    @DisplayName("列表中添加多个null元素")
    void addMultipleNulls() {
        for (int i = 0; i < 5; i++) {
            list.add(null);
        }
        assertEquals(5, list.size(), "应该能够添加多个null元素");
    }

    @Test
    @DisplayName("连续调用add和remove操作")
    void continuousAddAndRemove() {
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        for (int i = 0; i < 5; i++) {
            list.remove(Integer.valueOf(i));
        }
        assertEquals(5, list.size(), "连续添加后移除元素，列表大小应该减少");
    }

    @Test
    @DisplayName("在列表中添加相同的元素")
    void addDuplicateElements() {
        list.add(1);
        list.add(1);
        assertEquals(2, list.size(), "应该能够添加重复元素");
    }

    @Test
    @DisplayName("移除列表中的null元素")
    void removeNullElement() {
        list.add(null);
        list.add(1);
        assertTrue(list.remove(null), "应该能够移除null元素");
        assertEquals(1, list.size(), "列表大小应该减少");
    }

    @Test
    @DisplayName("移除不存在的元素")
    void removeNonExistentElement() {
        list.add(1);
        assertFalse(list.remove(Integer.valueOf(2)), "移除不存在的元素应该返回false");
    }

    @Test
    @DisplayName("检查contains方法对null元素的处理")
    void checkContainsForNull() {
        list.add(null);
        assertTrue(list.contains(null), "contains方法应该能够正确识别null元素");
    }

    @Test
    @DisplayName("indexOf方法对于重复元素应返回第一个匹配元素的索引")
    void indexOfWithDuplicates() {
        list.add(1);
        list.add(1);
        assertEquals(0, list.indexOf(1), "indexOf应返回第一个匹配元素的索引");
    }

    @Test
    @DisplayName("lastIndexOf方法对于重复元素应返回最后一个匹配元素的索引")
    void lastIndexOfWithDuplicates() {
        list.add(1);
        list.add(1);
        assertEquals(1, list.lastIndexOf(1), "lastIndexOf应返回最后一个匹配元素的索引");
    }

}