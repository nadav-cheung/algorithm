package cn.com.nadav.structure.util;

public class BinarySearchTree<K extends Comparable<? super K>, V> {

    private Node<K, V> root;
    private int size;

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public int getSize() {
        return size;
    }

    public void add(K key, V value) {
        root = add(root, key, value);
    }

    /**
     * 向以node为根的二分搜索树中添加键值对key value
     * 返回插入新结算后二分搜索树的根
     */
    private Node<K, V> add(Node<K, V> node, K key, V value) {
        if (node == null) {
            ++size;
            return new Node<K, V>(key, value);
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

    public boolean contains(K key) {
        Node<K, V> node = getNode(root, key);
        return node != null;
    }

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

    public V get(K key) {
        Node<K, V> node = getNode(root, key);
        return node != null ? node.value : null;
    }

    public void set(K key, V newValue) {
        Node<K, V> node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException();
        }
        node.value = newValue;
    }

    public V remove(K key) {
        Node<K, V> node = remove(root, key);
        return node != null ? node.value : null;
    }

    /**
     * @param node
     * @param key
     * @return 移除Key节点后的的二叉树
     */
    private Node<K, V> remove(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            return node.left = remove(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            return node.right = remove(node.right, key);
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
                successor.right = remove(node, successor.key);
                node.left = node.right = null;
                return successor;
            }
        }
    }

    private Node<K, V> minimum(Node<K, V> node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
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
