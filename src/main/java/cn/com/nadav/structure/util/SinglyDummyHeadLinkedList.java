package cn.com.nadav.structure.util;

/**
 * 使用虚拟头节点实现的单向列表
 */
public class SinglyDummyHeadLinkedList<E> implements List<E> {

    /**
     * 单向节点
     */
    static class Node<E> {
        E item;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }

    /**
     * 虚拟头节点
     */
    transient Node<E> dummyHead;
    transient int size = 0;

    public SinglyDummyHeadLinkedList() {
        this.dummyHead = new Node<>(null, null);
    }

    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }


    @Override
    public int indexOf(E e) {
        int index = 0;

        if (e == null) {
            // 两种实现方式
            for (Node<E> cur = dummyHead.next; cur != null; cur = cur.next) {
                if (cur.item == null) {
                    return index;
                } else {
                    index++;
                }
            }
        } else {
            Node<E> cur = dummyHead.next;
            while (cur != null) {
                if (e.equals(cur.item)) {
                    return index;
                } else {
                    index++;
                    cur = cur.next;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E o) {
        throw new RuntimeException("singly dummyHead LinkedList not support lastIndexOf");
    }

    @Override
    public boolean remove(E e) {
        if (e == null) {
            for (Node<E> prev = dummyHead; prev != null; prev = prev.next) {
                if (prev.next.item == null) {
                    unlinkPrevNext(prev);
                    return true;
                }
            }
        } else {
            for (Node<E> prev = dummyHead; prev != null; prev = prev.next) {
                if (e.equals(prev.next.item)) {
                    unlinkPrevNext(prev);
                    return true;
                }
            }
        }
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


    public void addFist(E e) {
        add(0, e);
    }

    public void add(int index, E e) {
        checkPositionIndex(index);

        Node<E> prev = dummyHead;

        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        prev.next = new Node<>(e, prev.next);
        size++;
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    /**
     * Tells if the argument is the index of a valid position for an
     * iterator or an add operation.
     */
    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    public void addLast(E e) {
        add(size, e);
    }

    public E get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }


    /**
     * Returns the (non-null) Node at the specified element index.
     */
    Node<E> node(int index) {
        // assert isElementIndex(index);

        Node<E> cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    /**
     * Tells if the argument is the index of an existing element.
     */
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    public E getFirst() {
        return get(0);
    }


    public E getLast() {
        return get(size - 1);
    }


    public E set(int index, E item) {
        checkElementIndex(index);
        Node<E> x = node(index);
        E oldVal = x.item;
        x.item = item;
        return oldVal;
    }

    public boolean contains(E item) {
        return indexOf(item) >= 0;
    }


    public E remove(int index) {
        checkElementIndex(index);
        return unlink(index);
    }

    private E unlink(int index) {

        Node<E> prev = this.dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node<E> delNode = prev.next;
        prev.next = delNode.next;
        delNode.next = null;
        size--;
        return delNode.item;
    }


    public E removeFist() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }


    public void removeElement(E e) {

        Node<E> prev = this.dummyHead;

        if (e == null) {
            while (prev.next != null) {
                if (prev.next.item == null) {
                    break;
                }
                prev = prev.next;
            }
        } else {
            while (prev.next != null) {
                if (e.equals(prev.next.item)) {
                    break;
                }
                prev = prev.next;
            }
        }

        // 移除元素
        if (prev.next != null) {
            Node<E> delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
        }
    }

    /**
     * 移除 prev的下一个节点 因为是单向链表
     */
    private void unlinkPrevNext(Node<E> prev) {
        if (prev.next != null) {
            Node<E> delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
        }
    }

}
