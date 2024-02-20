package cn.com.nadav.structure.util;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * email nadav.cheung@gmail.com
 * date 2024-02-07 11:05:58
 * description 使用链表实现数据结构栈
 *
 * @author Nadav Cheung
 * @since 1.0.x
 */
public class BinarySearchTree<K extends Comparable<? super K>, V> {

    private Node<K, V> root;
    private int size;

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    /**
     * 如果此列表不包含任何元素，则返回true
     */
    public boolean empty() {
        return getSize() == 0;
    }

    /**
     * 返回此队列中的元素数量
     */
    public int getSize() {
        return size;
    }

    /**
     * 向二分搜索树中添加新的元素(key, value)
     */
    public void add(K key, V value) {
        if (key == null) {
            throw new NullPointerException("key is null");
        }
        root = add(root, key, value);
    }

    /**
     * 向以node为根的二分搜索树中添加键值对key value
     * 返回插入新结算后二分搜索树的根
     */
    private Node<K, V> add(Node<K, V> node, K key, V value) {
        if (node == null) {
            ++size;
            return new Node<>(key, value);
        }
        if (key.compareTo(node.key) < 0) {
            return node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            return node.right = add(node.right, key, value);
        } else {
            // key.compareTo(node.key) = 0 update
            node.value = value;
            return node;
        }
    }

    /**
     * 查询二分搜索树中是否包含元素e
     */
    public boolean contains(K key) {
        Node<K, V> node = getNode(root, key);
        return node != null;
    }

    /**
     * 查询二分搜索树中是否包含元素e
     */
    private Node<K, V> getNode(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            return getNode(node.right, key);
        } else { // key.compareTo(node.key) = 0
            return node;
        }
    }

    /**
     * 查询二分搜索树中是否包含元素e
     */
    public V get(K key) {
        Node<K, V> node = getNode(root, key);
        return node != null ? node.value : null;
    }

    /**
     * 将此列表中指定位置的元素替换为指定元素（可选操作）
     *
     * @param key      key
     * @param newValue 新值
     */
    public void set(K key, V newValue) {
        Node<K, V> node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException();
        }
        node.value = newValue;
    }

    /**
     * 从二分搜索树中删除键为key的节点
     */
    public V remove(K key) {
        Node<K, V> removeNode = getNode(root, key);
        if (removeNode != null) {
            root = remove(root, key);
            return removeNode.value;
        } else {
            return null;
        }
    }

    /**
     * @param node 二叉树
     * @param key  要移除的Key
     * @return 移除Key节点后的的二叉树
     */
    private Node<K, V> remove(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else {

            //key.compareTo(node.key) = 0
            if (node.left == null) {
                Node<K, V> rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            } else if (node.right == null) {
                Node<K, V> leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            } else {
                Node<K, V> successor = minimum(node.right);
                successor.left = node.left;
                successor.right = remove(node.right, successor.key);
                node.left = node.right = null;
                return successor;
            }
        }
    }


    private Node<K, V> removeV2(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            node.left = removeV2(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = removeV2(node.right, key);
            return node;
        } else {
            //key.compareTo(node.key) = 0
            if (node.left == null) {
                Node<K, V> rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            if (node.right == null) {
                Node<K, V> leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            Node<K, V> successor = minimum(node.right);
            successor.left = node.left;
            successor.right = removeMin(node.right);
            node.left = node.right = null;
            return successor;
        }
    }


    /**
     * 删除掉以node为根的二分搜索树中的最小节点
     * 返回删除节点后新的二分搜索树的根
     */
    private Node<K, V> removeMin(Node<K, V> node) {
        if (node.left == null) {
            Node<K, V> rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }


    /**
     * 返回以node为根的二分搜索树的最小值所在的节点
     */
    private Node<K, V> minimum(Node<K, V> node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    /**
     * preOrder 前序遍历
     */

    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node<K, V> root) {
        if (root == null) {
            return;
        }
        System.out.println(root.key);
        preOrder(root.left);
        preOrder(root.right);
    }


    /**
     * inOrder 中序遍历
     */
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node<K, V> root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.println(root.key);
        inOrder(root.right);
    }

    /**
     * postOrder 后序遍历
     */
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node<K, V> root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.key);
    }

    /**
     * 层序遍历
     */
    public void levelOrder() {
        if (root == null) {
            return;
        }
        Queue<Node<K, V>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<K, V> node = queue.poll();
            System.out.println(node.key);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    // 实现非递归的前序遍历
    public void preOrderNR() {
        if (root == null) {
            return;
        }
        Deque<Node<K, V>> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node<K, V> node = stack.pop();
            System.out.println(node.key);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    // 实现非递归的中序遍历
    public void inOrderNR() {
        if (root == null) {
            return;
        }
        Deque<Node<K, V>> stack = new LinkedList<>();
        Node<K, V> cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            System.out.println(cur.key);
            cur = cur.right;
        }
    }


    // 实现非递归的后序遍历
    public void postOrderNR() {
        if (root == null) {
            return;
        }
        Deque<Node<K, V>> stack = new LinkedList<>();
        Deque<Node<K, V>> output = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node<K, V> node = stack.pop();
            output.push(node);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        while (!output.isEmpty()) {
            System.out.println(output.pop().key);
        }
    }


    static class Node<K, V> {
        public K key;
        public V value;
        public Node<K, V> left, right;

        public Node(K key, V value, Node<K, V> left, Node<K, V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public Node(K key, V value) {
            this(key, value, null, null);
        }
    }

}
