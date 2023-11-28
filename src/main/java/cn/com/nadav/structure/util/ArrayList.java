package cn.com.nadav.structure.util;

import java.util.Arrays;

public class ArrayList<E> implements List<E> {

    /**
     * 默认容量
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 数组允许分配的最大值
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * 可共享的空实例数组 capacity == 0
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};

    /**
     * 默认数组
     */
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};


    /**
     * 底层数据结构 数组 真正存放数据的数据结构
     */
    transient Object[] elementData;


    /**
     * 已经存放的数据大小
     *
     * @serial
     */
    private int size;

    /**
     * @param initialCapacity 初始化数组大小
     */
    public ArrayList(int initialCapacity) {
        // 直接初始化
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
            // 设置共享成员变量
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    /**
     * 默认初始化
     */
    public ArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public boolean empty() {
        return size == 0;
    }


    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    public E remove(int index) {
        // 判断待删除元素下标是否越界
        rangeCheck(index);

        // 待删除的旧值
        E oldValue = elementData(index);

        // 计算需要移动位置元素的数量
        int numMoved = size - index - 1;
        if (numMoved > 0)
            // 移动位置
            System.arraycopy(elementData, index + 1, elementData, index,
                    numMoved);
        // size-1
        elementData[--size] = null; // clear to let GC do its work

        // 返回删除元素
        return oldValue;
    }


    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elementData[i] == null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size - 1; i >= 0; i--)
                if (elementData[i] == null)
                    return i;
        } else {
            for (int i = size - 1; i >= 0; i--)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    E elementData(int index) {
        return (E) elementData[index];
    }

    @Override
    public E get(int index) {
        rangeCheck(index);

        return elementData(index);
    }

    @Override
    public E set(int index, E element) {
        // 检查下标是否越界
        rangeCheck(index);
        // 旧值
        E oldValue = elementData(index);
        // 插入新值
        elementData[index] = element;
        // 返回旧值
        return oldValue;
    }


    private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }


    @Override
    public boolean add(E e) {
        // 确保申请的底层数组的容量可以存入当前这个元素
        ensureCapacityInternal(size + 1);
        // 将元素插入数组 并将size+1
        elementData[size++] = e;
        return true;
    }


    @Override
    public void add(int index, E element) {
        // 确保index的位置是合法的
        rangeCheckForAdd(index);

        // 判断是否扩容
        ensureCapacityInternal(size + 1);

        // 从待插入元素位置开始到结尾的所有元素 向后移动一个位置，将待插入位置空出
        System.arraycopy(elementData, index, elementData, index + 1,
                size - index);
        // 设置元素
        elementData[index] = element;
        // 数量+1
        size++;
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }


    /**
     * 确保底层数组的容量是大于当前需要的最小容量
     * 如果容量不够就进行扩容
     *
     * @param minCapacity 所需最小容量
     */
    private void ensureCapacityInternal(int minCapacity) {
        ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
    }

    /**
     * 计算容量 选择默认容量和所需容量中的较大者
     */
    private static int calculateCapacity(Object[] elementData, int minCapacity) {
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            return Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        return minCapacity;
    }


    /**
     * 判断是否需要扩容
     */
    private void ensureExplicitCapacity(int minCapacity) {

        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }


    /**
     * 扩容
     *
     * @param minCapacity
     */
    private void grow(int minCapacity) {

        int oldCapacity = elementData.length;
        // 默认增加原有素组大小的一半
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        // 选择 newCapacity 和 minCapacity 中的较大者
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;

        // 如果新数组大于我们设定的最大数组长度 MAX_ARRAY_SIZE 则重新计算新数组大小
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        // 申请一个新的数组 数组大小为 newCapacity  并将原来数组的中数据拷贝到新数组
        elementData = Arrays.copyOf(elementData, newCapacity);
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


    public boolean remove(Object o) {
        final Object[] es = elementData;
        final int size = this.size;
        int i = 0;
        found:
        {
            if (o == null) {
                for (; i < size; i++)
                    if (es[i] == null)
                        break found;
            } else {
                for (; i < size; i++)
                    if (o.equals(es[i]))
                        break found;
            }
            return false;
        }
        fastRemove(es, i);
        return true;
    }

    /**
     * Private remove method that skips bounds checking and does not
     * return the value removed.
     */
    private void fastRemove(Object[] es, int i) {
        final int newSize;
        if ((newSize = size - 1) > i)
            System.arraycopy(es, i + 1, es, i, newSize - i);
        es[size = newSize] = null;
    }


}
