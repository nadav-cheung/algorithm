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


    public LinkedStack() {
        this.size = 0;
        this.top = null;
    }


    @Override
    public E push(E e) {
        top = new Node<>(e, top);
        size++;
        return e;
    }


    /**
     * 演示采用递归方法添加元素
     *
     * @param e 要添加的元素
     */
    private void add(E e) {
        this.add(0, e);
        size++;
    }

    /**
     * 递归的宏观语义   在链表的index位置插入e
     *
     * @param index 插入位置
     * @param e     要插入的元素
     */
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


    /**
     * 演示采用递归方法删除元素
     */
    @Override
    public E pop() {
        if (size == 0) {
            return null;
        }
        E e = top.item;
        top = top.next;
        size--;
        return e;
    }


    /**
     * 查看栈顶元素，不删除
     *
     * @return 栈顶元素
     */
    @Override
    public E peek() {
        if (size == 0) {
            return null;
        }
        return top.item;
    }

    @Override
    public boolean empty() {
        return size == 0;
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
