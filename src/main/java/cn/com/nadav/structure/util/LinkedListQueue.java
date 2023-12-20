package cn.com.nadav.structure.util;

/**
 * 基于链表实现的队列数据结构
 */
public class LinkedListQueue<E> implements Queue<E> {

    private static class Node<E> {
        E data;

        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }


    /**
     * 头指针和尾指针
     */
    Node<E> head, tail;
    transient int size = 0;

    public LinkedListQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }


    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean empty() {
        return size() == 0;
    }

    @Override
    public boolean offer(E e) {
        Node<E> newNode = new Node<>(e);
        if (tail == null) {
            head = tail = newNode;
            size++;
            return true;
        }
        tail.next = newNode;
        tail = newNode;
        size++;
        return true;
    }

    @Override
    public E poll() {
        if (head == null) {
            return null;
        }
        E data = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return data;
    }

    @Override
    public E peek() {
        if (head == null) {
            return null;
        }
        return head.data;
    }
}
