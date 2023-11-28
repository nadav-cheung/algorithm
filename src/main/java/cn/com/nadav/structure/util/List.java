package cn.com.nadav.structure.util;

public interface List<E> {

    /**
     * 返回此列表中指定位置的元素。
     *
     * @param index 列表中指定的下标
     * @return 下标中包含的元素
     */

    E get(int index);

    /**
     * 将此列表中指定位置的元素替换为指定元素（可选操作）
     *
     * @param index
     * @param e     原来下标中的旧值
     */
    E set(int index, E e);

    /**
     * 在此列表中的指定位置插入指定元素（可选操作）。
     * 将当前位于该位置的元素（如果有）和任何后续元素向右移动（将其索引加一）。
     *
     * @param index
     * @param e
     */
    void add(int index, E e);

    /**
     * 将指定元素追加到此列表的末尾（可选操作）。
     * 支持此操作的列表可能会对可以添加到此列表的元素进行限制。
     * 特别是，一些列表将拒绝添加空元素，而另一些列表将对可能添加的元素的类型施加限制。
     * 列表类应在其文档中明确指定对可以添加的元素的任何限制
     */
    boolean add(E e);

    /**
     * 列表中是否包含元素e
     *
     * @param e
     * @return
     */
    int indexOf(E e);

    int lastIndexOf(E o);

    /**
     * 如果此列表包含指定元素，则返回 <tt>true<tt>。
     * 更正式地说，当且仅当此列表包含至少一个元素 <tt>e<tt>
     * 且满足 <tt>(o==null ? e==null : o.equals(e ))<tt>。
     */
    boolean contains(E o);

    /**
     * 删除此列表中指定位置的元素（可选操作）。
     * 将所有后续元素向左移动（从索引中减去 1）。返回从列表中删除的元素。
     */

    E remove(int index);

    /**
     * 从此列表中删除第一次出现的指定元素（如果存在）（可选操作）。
     * 如果此列表不包含该元素，则它不会更改。更正式地说，删除具有最低索引i 的元素，
     * 使得(o==null ? get(i)==null : o.equals(get(i))) （如果存在这样的元素）。
     * 如果此列表包含指定的元素（或者等效地，如果此列表由于调用的结果而更改），则返回true 。
     */
    boolean remove(E o);

    /**
     * 返回此列表中的元素数量
     *
     * @return
     */
    int size();

    /**
     * 如果此列表不包含任何元素，则返回true
     */
    boolean empty();

}
