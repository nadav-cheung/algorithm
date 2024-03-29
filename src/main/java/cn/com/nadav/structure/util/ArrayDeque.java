package cn.com.nadav.structure.util;

public class ArrayDeque<E> extends ArrayQueue<E> implements Deque<E> {

    /**
     * 构造一个空数组队列，其初始容量足以容纳 16 个元素。
     */
    public ArrayDeque() {
        super();
    }

    /**
     * 构造一个空数组队列，
     * 其初始容量足以容纳指定数量的元素。
     */
    public ArrayDeque(int numElements) {
        super(numElements);
    }


    /**
     * 构造一个包含指定集合的元素的数组队列，
     *
     * @param e 集合
     * @return 数组队列
     */
    @Override
    public boolean offerFirst(E e) {
        addFirst(e);
        return true;
    }


    /**
     * 构造一个包含指定集合的元素的数组队列，
     *
     * @param e 集合
     * @return 数组队列
     */
    private void addFirst(E e) {
        if (e == null)
            throw new NullPointerException();
        final Object[] es = elements;
        // 计算head的位置 并存放元素
        es[head = dec(head, es.length)] = e;
        // 判断是否需要扩容
        if (head == tail)
            grow(1);
    }


    @Override
    public E pollLast() {
        final Object[] es;
        // 临时存放队尾元素下标
        final int t;

        // 队尾元素
        E e = elementAt(es = elements, t = dec(tail, es.length));
        if (e != null)
            // 更新tail指针
            es[tail = t] = null;
        return e;
    }


}
