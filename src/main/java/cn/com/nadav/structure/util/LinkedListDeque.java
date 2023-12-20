package cn.com.nadav.structure.util;

/**
 * 基于链表实现的队列数据结构
 */
public class LinkedListDeque<E> implements Deque<E> {

    /**
     * 头指针和尾指针
     */
    Node<E> head, tail;
    transient int size;
    public LinkedListDeque() {
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
        newNode.prev = tail;
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
        } else {
            head.prev = null;
        }
        size--;
        return data;
    }

    @Override
    public E peek() {
        if (empty()) {
            return null;
        }
        return head.data;
    }

    @Override
    public boolean offerFirst(E e) {
        Node<E> newNode = new Node<>(e);
        if (head == null) {
            head = tail = newNode;
            size++;
            return true;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            size++;
        }
        return true;
    }

    @Override
    public E pollLast() {
        if (tail == null) {
            return null;
        }
        E data = tail.data;
        tail = tail.prev;
        if (tail == null) {
            head = null;
        } else {
            tail.next = null;
        }
        size--;
        return data;
    }

    private static class Node<E> {
        E data;

        Node<E> prev, next;

        Node(E data) {
            this.data = data;
        }
    }
}
