package cn.com.nadav.structure.util;


import java.util.Arrays;

/**
 * 必要条件
 * 元素必须实现Comparable接口 是可比较的
 */
public class ComparableMinHeap<E extends Comparable<? super E>> {

    /**
     * A soft maximum array length imposed by array growth computations.
     * Some JVMs (such as HotSpot) have an implementation limit that will cause
     * <p>
     * OutOfMemoryError("Requested array size exceeds VM limit")
     * <p>
     * to be thrown if a request is made to allocate an array of some length near
     * Integer.MAX_VALUE, even if there is sufficient heap available. The actual
     * limit might depend on some JVM implementation-specific characteristics such
     * as the object header size. The soft maximum value is chosen conservatively so
     * as to be smaller than any implementation limit that is likely to be encountered.
     */
    public static final int SOFT_MAX_ARRAY_LENGTH = Integer.MAX_VALUE - 8;
    // 1+2+4+8
    private static final int DEFAULT_INITIAL_CAPACITY = 15;
    int size = 0;
    //用来存放数据
    private Object[] elementData;


    public ComparableMinHeap() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public ComparableMinHeap(int initialCapacity) {
        if (initialCapacity < 1)
            throw new IllegalArgumentException();
        this.elementData = new Object[initialCapacity];
    }

    public ComparableMinHeap(E[] data) {
        initFromArray(data);
    }

    /**
     * 在位置 k 处插入项 x，通过将 x 沿着树重复降级直到它小于或等于其子项或者是叶子来保持堆不变性。
     */
    @SuppressWarnings("unchecked")
    private static <T> void siftDownComparable(int k, T x, Object[] es, int n) {
        // assert n > 0;
        Comparable<? super T> key = (Comparable<? super T>) x;
        int half = n >>> 1;           // loop while a non-leaf

        // 将 x 沿着树重复降级直到它小于或等于其子项或者是叶子来保持堆不变性。
        while (k < half) {
            // 记录两个孩子节点中的较小节点
            int child = (k << 1) + 1; // assume left child is least
            // 记录 两个孩子节点中的较小指
            Object c = es[child];
            // 右边孩子节点
            int right = child + 1;
            // 右边孩子节点存在 并且 左边孩子节点大于右边孩子节点
            if (right < n &&
                    ((Comparable<? super T>) c).compareTo((T) es[right]) > 0)
                c = es[child = right];

            // key比 较小节点还小
            if (key.compareTo((T) c) <= 0)
                break;
            es[k] = c;
            // 下一轮开始 孩子节点比较
            k = child;
        }
        // 在位置 k 处插入项 x，
        es[k] = key;
    }

    // Ensures that elementData[0] exists, helping peek() and poll().
    private static Object[] ensureNonEmpty(Object[] es) {
        return (es.length > 0) ? es : new Object[1];
    }

    private static <T> void siftUpComparable(int k, T x, Object[] es) {
        Comparable<? super T> key = (Comparable<? super T>) x;
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Object e = es[parent];
            if (key.compareTo((T) e) >= 0)
                break;
            es[k] = e;
            k = parent;
        }
        es[k] = key;
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

    private void initFromArray(E[] data) {
        initElementsFromArray(data);
        heapify();
    }

    /**
     * Establishes the heap
     */
    @SuppressWarnings("unchecked")
    private void heapify() {
        final Object[] es = elementData;
        int n = size;
        int i = (n >>> 1) - 1;
        for (; i >= 0; i--)
            siftDownComparable(i, (E) es[i], es, n);
    }

    public E peek() {
        return (E) elementData[0];
    }

    public E poll() {
        final Object[] es;
        final E result;

        if ((result = (E) ((es = elementData)[0])) != null) {
            final int n;
            // 数组的最后一个元素
            final E x = (E) es[(n = --size)];
            // help gc
            es[n] = null;

            if (n > 0) {
                // 将数组的最后一位元素放入堆顶 然后执行siftDown
                siftDownComparable(0, x, es, n);
            }
        }
        return result;
    }

    /**
     * 初始化堆的内存
     *
     * @param data 初始化传入的树组
     */
    private void initElementsFromArray(E[] data) {
        Object[] es = data;
        int len = es.length;
        if (len == 1)
            for (Object e : es)
                if (e == null)
                    throw new NullPointerException();
        this.elementData = ensureNonEmpty(es);
        this.size = len;
    }

    public int size() {
        return size;
    }

    public boolean add(E e) {
        return offer(e);
    }

    /**
     * Inserts the specified element into this priority queue.
     *
     * @return {@code true} (as specified by {@link Queue#offer})
     * @throws ClassCastException   if the specified element cannot be
     *                              compared with elements currently in this priority queue
     *                              according to the priority queue's ordering
     * @throws NullPointerException if the specified element is null
     */
    public boolean offer(E e) {
        if (e == null)
            throw new NullPointerException();
        int i = size;
        if (i >= elementData.length)
            grow(i + 1);
        siftUp(i, e);
        size = i + 1;
        return true;
    }

    /**
     * Increases the capacity of the array.
     *
     * @param minCapacity the desired minimum capacity
     */
    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        // Double size if small; else grow by 50%
        int newCapacity = newLength(oldCapacity,
                minCapacity - oldCapacity, /* minimum growth */
                oldCapacity < 64 ? oldCapacity + 2 : oldCapacity >> 1
                /* preferred growth */);
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    /**
     * 上浮操作
     * 在位置 k 处插入项 x，通过将 x 沿树向上提升直到它大于或等于其父项，或者是根，来保持堆不变性。
     * 为了简化和加速强制和比较 （与 siftDown 类似。）
     */
    private void siftUp(int k, E x) {
        siftUpComparable(k, x, elementData);
    }


}