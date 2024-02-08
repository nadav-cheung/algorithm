package cn.com.nadav.structure.util;

import java.util.Arrays;

/**
 * 基于数组实现的队列
 */
public class ArrayQueue<E> implements Queue<E> {

    /**
     * 要分配的数组的最大大小。
     * 一些虚拟机在数组中保留一些头字。尝试分配更大的数组可能会导致 OutOfMemoryError：
     * 请求的数组大小超出 VM 限制
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    /**
     * 使用循环队列的抽象概念
     * front  == tail 队列为空
     * tail + 1 == front 队列满
     */
    transient Object[] elements;
    /**
     * 队列头部元素的索引（将通过remove()或pop()删除的元素）；
     * 或任意数字 0 <= head < elements.length 等于 tail 如果队列为空。
     */
    transient int head;
    /**
     * 下一个元素将添加到队列尾部的索引（通过 addLast(E)、add(E) 或 push(E)）；
     * elements[tail] 始终为空。
     */
    transient int tail;


    /**
     * 构造一个空数组队列，其初始容量足以容纳 16 个元素。
     */
    public ArrayQueue() {
        elements = new Object[16 + 1];
    }

    /**
     * 构造一个空数组队列，
     * 其初始容量足以容纳指定数量的元素。
     */
    public ArrayQueue(int numElements) {
        int capacity;
        if (numElements < 1) {
            capacity = 1;
        } else if (numElements == Integer.MAX_VALUE) {
            capacity = Integer.MAX_VALUE;
        } else {
            capacity = numElements + 1;
        }
        elements = new Object[capacity];
    }

    /**
     * 计算环形数组中的元素数量
     */
    static final int sub(int i, int j, int modulus) {

        /*
         * tail 指针永远指向一个待插入的空位置 这里没有元素
         * 当 tail>head 数量=tail-head
         * 当 head>tail 说明进入循环数组概念，参照时钟 此时数量= tail-head + elements.length
         */

        if ((i -= j) < 0) {
            i += modulus;
        }
        return i;
    }

    /**
     * 循环队列计算下标
     */
    static final int inc(int i, int modulus) {
        if (++i >= modulus) {
            i = 0;
        }
        return i;
    }

    /**
     * 循环队列前移计算下标
     */
    static final int dec(int i, int modulus) {
        if (--i < 0) {
            i = modulus - 1;
        }
        return i;
    }

    /**
     * 返回数组索引 i 处的元素。这是对泛型的轻微滥用
     */
    @SuppressWarnings("unchecked")
    static final <E> E elementAt(Object[] es, int i) {
        return (E) es[i];
    }

    public int capacity() {
        return elements.length - 1;
    }

    /**
     * 返回此队列中的元素数量。
     */
    @Override
    public int size() {
        return sub(tail, head, elements.length);
    }

    @Override
    public boolean empty() {
        return head == tail;
    }

    /**
     * 添加元素
     */
    public boolean offer(E e) {
        return offerLast(e);
    }

    public boolean offerLast(E e) {
        addLast(e);
        return true;
    }

    public void addLast(E e) {
        // 插入元素不可以为空
        if (e == null)
            throw new NullPointerException();

        final Object[] es = elements;
        // 在尾部插入
        es[tail] = e;
        // head == tail + 1 则队列满
        // 因为 es[tail] 永远指向待插入的位置
        // 判断是否需要扩容
        if (head == (tail = inc(tail, es.length)))
            // 数组扩容
            grow(1);
    }

    /**
     * 扩容接口，在原有容量的基础上至少增加 needed 容量
     *
     * @param needed the required minimum extra capacity; must be positive
     */
    protected void grow(int needed) {

        // oldCapacity
        final int oldCapacity = elements.length;

        int newCapacity;

        // jump增加的容量 小于64容量加倍；其他增长 50%
        int jump = (oldCapacity < 64) ? (oldCapacity + 2) : (oldCapacity >> 1);

        // jump < needed || newCapacity > MAX_ARRAY_SIZE
        if (jump < needed
                || (newCapacity = (oldCapacity + jump)) - MAX_ARRAY_SIZE > 0) {
            // 增长后的容量仍然不够 或者 已经超过设定的最大值
            newCapacity = newCapacity(needed, jump);
        }

        // 创建新数组 大小 newCapacity
        // 将旧数组中的值负值给新数组
        // 新数组取代旧数组
        final Object[] es = elements = Arrays.copyOf(elements, newCapacity);

        // 例外的是，这里 tail == head 需要消除歧义
        if (tail < head || (tail == head && es[head] != null)) {
            // 环绕；将第一条腿滑动到阵列末尾
            int newSpace = newCapacity - oldCapacity;

            System.arraycopy(es, head, es, head + newSpace, oldCapacity - head);

            for (int i = head, to = (head += newSpace); i < to; i++)
                es[i] = null;
        }
    }

    /**
     * jump < needed || newCapacity > MAX_ARRAY_SIZE
     * 重新计算扩容后的容量的大小
     * 边缘条件的容量计算，特别是溢出。
     */
    private int newCapacity(int needed, int jump) {

        final int oldCapacity = elements.length, minCapacity;

        if ((minCapacity = oldCapacity + needed) - MAX_ARRAY_SIZE > 0) {
            // 溢出抛异常
            if (minCapacity < 0)
                throw new IllegalStateException("Sorry, queue too big");
            return Integer.MAX_VALUE;
        }
        // 取较大者
        if (needed > jump)
            return minCapacity;
        // 边界计算
        return (oldCapacity + jump - MAX_ARRAY_SIZE < 0)
                ? oldCapacity + jump
                : MAX_ARRAY_SIZE;
    }

    public E poll() {
        return pollFirst();
    }

    public E pollFirst() {
        final Object[] es;
        final int h;
        // 队首元素
        E e = elementAt(es = elements, h = head);
        if (e != null) {
            es[h] = null;
            // 重新计算head值
            head = inc(h, es.length);
        }
        return e;
    }

    public E peek() {
        return peekFirst();
    }

    public E peekFirst() {
        return elementAt(elements, head);
    }
}
