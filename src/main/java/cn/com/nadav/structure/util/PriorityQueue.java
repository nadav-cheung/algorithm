package cn.com.nadav.structure.util;

import java.util.Arrays;

public class PriorityQueue<E> implements Queue<E> {

    public static final int SOFT_MAX_ARRAY_LENGTH = Integer.MAX_VALUE - 8;
    private static final int DEFAULT_INITIAL_CAPACITY = 11;
    transient Object[] elements;
    int size;

    public PriorityQueue() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public PriorityQueue(int initialCapacity) {
        // Note: This restriction of at least one is not actually needed,
        // but continues for 1.5 compatibility
        if (initialCapacity < 1)
            throw new IllegalArgumentException();
        this.elements = new Object[initialCapacity];
    }


    public PriorityQueue(E[] nums) {
        initFromArray(nums);
    }

    // Ensures that elementData[0] exists, helping peek() and poll().
    private static Object[] ensureNonEmpty(Object[] nums) {
        return (nums.length > 0) ? nums : new Object[1];
    }

    private static <E> void siftDownComparable(int index, Object element, Object[] elements, int length) {
        int half = length >>> 1;
        Comparable<? super E> key = (Comparable<? super E>) element;
        while (index < half) {

            int child = leftChild(index);

            // 两个孩子中的较小者
            Object c = elements[child];

            // 右边孩子节点
            int rightChildIndex = child + 1;

            if (rightChildIndex < length &&
                    ((Comparable<? super E>) c).compareTo((E) elements[rightChildIndex]) > 0) {
                c = elements[child = rightChildIndex];
            }

            // key小于两个孩子节点 满足堆的性质
            if (key.compareTo((E) c) <= 0)
                break;

            elements[index] = c;
            index = child;
        }
        elements[index] = key;
    }

    private static int leftChild(int parent) {
        return parent << 1 + 1;
    }

    @SuppressWarnings("unchecked")
    private static <T> void siftUpComparable(int index, T element, Object[] elements) {
        Comparable<? super T> key = (Comparable<? super T>) element;
        while (index > 0) {
            int parent = parent(index);
            Object e = elements[parent];
            if (key.compareTo((T) e) >= 0)
                break;
            elements[index] = e;
            index = parent;
        }
        elements[index] = key;
    }

    private static int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 doesn't have parent");
        }
        return (index - 1) >>> 1;
    }

    public static int newLength(int oldLength, int minGrowth, int prefGrowth) {
        // preconditions not checked because of inlining
        // assert oldLength >= 0
        // assert minGrowth > 0

        int prefLength = oldLength + Math.max(minGrowth, prefGrowth); // might overflow
        if (0 < prefLength && prefLength <= SOFT_MAX_ARRAY_LENGTH) {
            return prefLength;
        } else {
            // put code cold in a separate method
            return hugeLength(oldLength, minGrowth);
        }
    }

    private static int hugeLength(int oldLength, int minGrowth) {
        int minLength = oldLength + minGrowth;
        if (minLength < 0) { // overflow
            throw new OutOfMemoryError(
                    "Required array length " + oldLength + " + " + minGrowth + " is too large");
        } else if (minLength <= SOFT_MAX_ARRAY_LENGTH) {
            return SOFT_MAX_ARRAY_LENGTH;
        } else {
            return minLength;
        }
    }

    private void initFromArray(E[] nums) {
        initElementsFromArray(nums);
        heapify();
    }

    /**
     * 初始化堆的内存
     *
     * @param nums 初始化传入的树组
     */
    private void initElementsFromArray(E[] nums) {
        int len = nums.length;
        for (Object e : nums)
            if (e == null)
                throw new NullPointerException();
        // 确保已经初始化
        this.elements = ensureNonEmpty(nums);
        this.size = len;
    }

    private void heapify() {
        final Object[] es = elements;
        // 容量
        int n = size;
        // 最后一个非叶子节点
        int i = (n >>> 1) - 1;
        for (; i >= 0; i--)
            siftDownComparable(i, es[i], es, n);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean empty() {
        return size() == 0;
    }

    @Override
    public boolean offer(E e) {
        if (e == null)
            throw new NullPointerException();
        int i = size;
        if (i >= elements.length)
            grow(i + 1);
        siftUp(i, e);
        size = i + 1;
        return true;
    }

    /**
     * 上浮操作
     * 在位置 k 处插入项 x，通过将 x 沿树向上提升直到它大于或等于其父项，或者是根，来保持堆不变性。
     * 与 siftDown 类似
     */
    private void siftUp(int k, E x) {
        siftUpComparable(k, x, elements);
    }

    /**
     * Increases the capacity of the array.
     *
     * @param minCapacity the desired minimum capacity
     */
    private void grow(int minCapacity) {
        int oldCapacity = elements.length;
        // Double size if small; else grow by 50%
        int newCapacity = newLength(oldCapacity,
                minCapacity - oldCapacity, /* minimum growth */
                oldCapacity < 64 ? oldCapacity + 2 : oldCapacity >> 1
                /* preferred growth */);
        elements = Arrays.copyOf(elements, newCapacity);
    }

    @Override
    public E poll() {
        final Object[] es;
        final E result;

        if ((result = (E) ((es = elements)[0])) != null) {
            final int n;
            final E x = (E) es[(n = --size)];
            es[n] = null;
            if (n > 0) {
                siftDownComparable(0, x, es, n);
            }
        }
        return result;
    }

    @Override
    public E peek() {
        return (E) elements[0];
    }
}
