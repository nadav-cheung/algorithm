package cn.com.nadav.structure.util;

import java.util.LinkedList;

// 使用双向链表实现List数据结构
public class DoublyLinkedList<E> implements List<E> {


    transient int size = 0;

    /**
     * Pointer to first node.
     */
    transient Node<E> first;

    /**
     * Pointer to last node.
     */
    transient Node<E> last;

    /**
     * Constructs an empty list.
     */
    public DoublyLinkedList() {
    }

    /**
     * Links e as first element.
     */
    private void linkFirst(E e) {
        final Node<E> f = first;
        final Node<E> newNode = new Node<>(null, e, f);
        first = newNode;
        if (f == null)
            last = newNode;
        else
            f.prev = newNode;
        size++;
    }

    /**
     * Unlinks non-null first node f.
     */
    private E unlinkFirst(Node<E> f) {
        // assert f == first && f != null;
        final E element = f.item;
        final Node<E> next = f.next;
        f.item = null;
        f.next = null; // help GC
        first = next;
        if (next == null)
            last = null;
        else
            next.prev = null;
        size--;
        return element;
    }


    /**
     * Links e as last element.
     */
    private void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
    }

    /**
     * Unlinks non-null last node l.
     */
    private E unlinkLast(Node<E> l) {
        // assert l == last && l != null;
        final E element = l.item;
        final Node<E> prev = l.prev;
        l.item = null;
        l.prev = null; // help GC
        last = prev;
        if (prev == null)
            first = null;
        else
            prev.next = null;
        size--;
        return element;
    }


    @Override
    public E get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    /**
     * 判断index位于链表的前半部还是后半部分，选择从头部还是尾部开始寻找
     * Returns the (non-null) Node at the specified element index.
     */
    Node<E> node(int index) {
        // assert isElementIndex(index);
        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    /**
     * Tells if the argument is the index of an existing element.
     */
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    @Override
    public E set(int index, E e) {
        checkElementIndex(index);
        Node<E> x = node(index);
        E oldVal = x.item;
        x.item = e;
        return oldVal;
    }

    @Override
    public void add(int index, E e) {
        checkPositionIndex(index);

        if (index == size)
            linkLast(e);
        else
            linkBefore(e, node(index));
    }

    /**
     * Inserts element e before non-null Node succ.
     */
    void linkBefore(E e, Node<E> succ) {
        // assert succ != null;
        final Node<E> pred = succ.prev;
        final Node<E> newNode = new Node<>(pred, e, succ);
        succ.prev = newNode;
        if (pred == null)
            first = newNode;
        else
            pred.next = newNode;
        size++;
    }



    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public int indexOf(E e) {
        return 0;
    }

    @Override
    public int lastIndexOf(E o) {
        return 0;
    }

    @Override
    public boolean contains(E o) {
        return false;
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public boolean remove(E o) {
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean empty() {
        return size() == 0;
    }

    // 双向链表
    static class Node<E> {
        E item;
        Node<E> prev;
        Node<E> next;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }


}
