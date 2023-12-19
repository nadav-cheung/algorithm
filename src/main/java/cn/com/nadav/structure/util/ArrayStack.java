package cn.com.nadav.structure.util;

import java.util.Arrays;

public class ArrayStack<E> implements Stack<E> {


    /**
     * 默认容量
     */
    private static final int DEFAULT_CAPACITY = 16;
    /**
     * 数组允许分配的最大值
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    /**
     * 表示栈中元素的数量
     */
    protected int elementCount;
    transient Object[] elements;


    /**
     * 构造一个空数组队列，其初始容量足以容纳 16 个元素。
     */
    public ArrayStack() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    /**
     * 构造一个空数组队列，
     * 其初始容量足以容纳指定数量的元素。
     */
    public ArrayStack(int numElements) {
        elements = new Object[Math.max(numElements, DEFAULT_CAPACITY)];
    }

    /**
     * 可以申请的最大内存
     */
    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0)
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    /**
     * 返回数组索引 i 处的元素。这是对泛型的轻微滥用，已被 javac 接受。
     */
    @SuppressWarnings("unchecked")
    static final <E> E elementAt(Object[] es, int i) {
        return (E) es[i];
    }

    @Override
    public boolean empty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return elementCount;
    }

    @Override
    public E push(E e) {
        // 确保申请的底层数组的容量可以存入当前这个元素
        ensureCapacityInternal(size() + 1);
        // 将元素插入数组 并将size+1
        elements[elementCount++] = e;
        return e;
    }

    private void ensureCapacityInternal(int minCapacity) {
        ensureExplicitCapacity(minCapacity);
    }

    /**
     * 判断是否需要扩容
     */
    private void ensureExplicitCapacity(int minCapacity) {

        if (minCapacity - elements.length > 0)
            grow(minCapacity);
    }

    /**
     * 扩容
     *
     * @param minCapacity
     */
    private void grow(int minCapacity) {

        int oldCapacity = elements.length;
        // 默认增加原有素组大小的一半
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        // 选择 newCapacity 和 minCapacity 中的较大者
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;

        // 如果新数组大于我们设定的最大数组长度 MAX_ARRAY_SIZE 则重新计算新数组大小
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        // 申请一个新的数组 数组大小为 newCapacity  并将原来数组的中数据拷贝到新数组
        elements = Arrays.copyOf(elements, newCapacity);
    }

    @Override
    public E pop() {
        int len = size();
        if (len == 0) {
            return null;
        }
        E oldValue = elementAt(elements, len - 1);
        // size-1
        elements[--elementCount] = null; // clear to let GC do its work
        return oldValue;
    }

    @Override
    public E peek() {
        int len = size();
        if (len == 0) {
            return null;
        }
        return elementAt(elements, len - 1);
    }
}
