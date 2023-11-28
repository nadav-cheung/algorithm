package cn.com.nadav.structure.util;

public class LinkedListRecursion<E> {

    public class Node {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }


        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }


    private Node head;

    private int size;


    public LinkedListRecursion() {
        head = null;
        size = 0;
    }


    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(int index, E e) {
        head = add(head, index, e);
        size++;
    }


    // 递归的宏观语义   在以node为头结点的链表的index位置插入e 并返回node节点
    private Node add(Node node, int index, E e) {
        if (index == 0) {
            return new Node(e, node);
        }

        node.next = add(node.next, index - 1, e);
        return node;
    }


}
