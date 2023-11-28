package cn.com.nadav.structure.util;

public interface Queue<E> {


    /**
     * 返回此队列中的元素数量
     *
     * @return
     */
    int size();

    /**
     * 如果此列表不包含任何元素，则返回true
     *
     * @return
     */
    boolean empty();


    /**
     * 如果可以在不违反容量限制的情况下立即执行此操作，则将指定元素插入此队列。
     * 当使用容量受限的队列时，此方法通常优于 {@link add}，后者只能通过抛出异常来插入元素失败。
     *
     * @param e
     * @return
     */
    boolean offer(E e);


    /**
     * 检索并删除此队列的头部，如果此队列为空，则返回 {@code null}。
     */
    E poll();


    /**
     * 检索但不删除此队列的头部，或者如果此队列为空则返回 {@code null}。
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    E peek();

}
