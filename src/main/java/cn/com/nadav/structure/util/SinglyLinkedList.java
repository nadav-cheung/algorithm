package cn.com.nadav.structure.util;

/**
 * 使用虚拟头节点实现的单向列表
 */
public class SinglyLinkedList<E> implements List<E> {

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
    transient Node<E> head;
    transient int size = 0;


    public SinglyLinkedList() {

    }


    @Override
    public void add(int index, E e) {
        head = add(head, index, e);
        size++;
    }

    // 递归的宏观语义   在以node为头结点的链表的index位置插入e 并返回node节点
    private Node<E> add(Node<E> node, int index, E e) {
        if (index == 0) {
            return new Node<E>(e, node);
        }

        node.next = add(node.next, index - 1, e);
        return node;
    }

}