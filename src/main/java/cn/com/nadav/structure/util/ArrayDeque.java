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


    @Override
    public boolean offerFirst(E e) {
        addFirst(e);
        return true;
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
