package cn.com.nadav.structure.util;

/**
 * email nadav.cheung@gmail.com
 * date 2024-02-07 11:05:58
 * description 使用链表实现数据结构栈
 *
 * @author Nadav Cheung
 * @since 1.0.x
 */
public class LinkedStack<E> implements Stack<E> {

    private transient int size;

    private Node<E> top; // 栈顶元素

    @Override
    public E push(E e) {
        return null;
    }


    /**
     * 演示采用递归方法添加元素
     *
     * @param e
     */
    private void add(E e) {
        this.add(0, e);
    }

    private void add(int index, E e) {
        top = add(top, index, e);
    }

    // 递归的宏观语义   在以node为头结点的链表的index位置插入e
    // 并返回插入元素后的node节点
    private Node<E> add(Node<E> node, int index, E e) {
        if (index == 0) {
            return new Node<>(e, node);
        }
        node.next = add(node.next, index - 1, e);
        return node;
    }


    @Override
    public E pop() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public boolean empty() {
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }


    // 双向链表
    static class Node<E> {
        E item;
        Node<E> next;

        Node(E element) {
            this(element, null);
        }


        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }

}
