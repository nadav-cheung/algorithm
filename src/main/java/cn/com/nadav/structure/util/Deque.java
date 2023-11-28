package cn.com.nadav.structure.util;

/**
 * 双端队列
 */
public interface Deque<E> extends Queue<E> {

    /**
     * 在此双端队列的前面插入指定的元素
     */
    boolean offerFirst(E e);

    /**
     * 检索并删除此队列的尾部，如果此队列为空，则返回 {@code null}。
     */
    E pollLast();

}
