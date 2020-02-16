import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;


public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V> {
    private Node root;
    private class Node {
        private K key;
        private V value;
        private int size;
        private Node left;
        private Node right;
        public Node(K k, V val, int s) {
            this.value = val;
            this.key = k;
            this.size = s;
        }
    }

    /**
     * initializiation an instance
     */
    public BSTMap() {
    }

    /**
     * clear all nodes in the tree
     */
    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        return containsKey(root, key);
    }
    /**
     * a helper function to find if the tree contains key
     * @param n
     * @param key
     * @return
     */
    private boolean containsKey(Node n, K key) {
        if(key == null) {
            throw new IllegalArgumentException();
        } else {
            return get(n, key) != null;
        }
    }

    @Override
    public int size() {
        return size(root);
    }
    /**
     * a helper function to get size of Node n
     * @param n
     * @return
     */
    private int size(Node n) {
        if (n == null) {
            return 0;
        } else {
            return n.size;
        }

    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException();
        } else{
            root = put(root, key, value);
        }
    }
    /**
     * a helper func to put key and value to Node n
     * in this section i made a mistake, when i try to make it with void return,
     * which means I just want to add and change the origin input Node n,
     * but finally i find it is incorrect, because Node n is a type we designed,
     * so it's type is reference type. It's un changeable, so we need to return Node
     * and reassign it
     * @param n
     * @param key
     * @param value
     */
    private Node put(Node n, K key, V value) {
        if (n == null) {
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(n.key);
        if (cmp < 0) {
            n.left = put(n.left, key, value);
        } else if( cmp > 0) {
            n.right = put(n.right, key, value);
        } else {
            n.value = value;
        }
        n.size = 1 + size(n.left) + size(n.right);
        return n;
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    /**
     * a helper function to get value of key
     * @param n
     * @param key
     * @return
     */
    private V get(Node n, K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if(n == null) {
            return null;
        }
        int cmp = key.compareTo(n.key);
        if (cmp < 0) {
            return get(n.left, key);
        } else if (cmp > 0) {
            return get(n.right, key);
        } else {
            return n.value;
        }
    }

    public void printInOrder() {
        printInOrder(root);
    }

    /**
     * a helper function to printInOrder of the the size of key
     */
    private void printInOrder(Node n) {
        if (n == null) {
            return;
        }
        System.out.println(n.key);
        if (n.left != null && n.right != null) {
            printInOrder(n.left);
            printInOrder(n.right);
        } else if (n.left != null) {
            printInOrder(n.left);
        } else {
            printInOrder(n.right);
        }
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }
    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException();
    }

}
