package cn.com.nadav.structure.util;

/**
 * 单项链表递归插入元素
 */
public class LinkedListRecursion<E> {
    private Node<E> head;

    public LinkedListRecursion() {
        head = null;
    }

    public void add(int index, E e) {
        head = add(head, index, e);
    }

    // 递归的宏观语义   在以node为头结点的链表的index位置插入e
    // 并返回插入元素后的node节点
    private Node<E> add(Node<E> node, int index, E e) {
        if (index == 0) {
            return new Node<E>(e, node);
        }
        node.next = add(node.next, index - 1, e);
        return node;
    }

    static class Node<E> {
        public E e;
        public Node<E> next;

        public Node(E e, Node<E> next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }
    }


}
