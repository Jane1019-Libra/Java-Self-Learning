package ca.bytube._06_Tree;

import java.util.Comparator;

public class BinarySearchTree<E> extends BinaryTree<E>{
    private Comparator<E> comparator;
    public BinarySearchTree() {}

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public void add(E element) {
        if (element == null) throw new IllegalArgumentException("element cannot be null");
        if (root == null) {
            root = new Node<>(element, null);
            size++;
            return;
        } else {
            Node<E> node = root;
            Node<E> parent = root;
            int cmp = 0;
            while (node != null) {
                cmp = compare(element, node.element);
                parent = node;
                if (cmp > 0) {
                    node = node.right;
                } else if (cmp < 0) {
                    node = node.left;
                } else {
                    node.element = element;
                    return;
                }
            }
            Node<E> newNode = new Node<>(element, parent);
            if (cmp > 0) parent.right = newNode;
            else parent.left = newNode;
            size++;
            return;
        }
    }

    public Node<E> node(E element) {
        Node<E> node = root;
        int cmp = 0;
        while (node != null) {
            cmp = compare(element, node.element);
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                return node;
            }
        }
        return null;
    }

    public Node<E> predecessor(Node<E> node) {
        if (node == null) return null;
        if (node.left != null) {
            Node<E> p = node.left;
            while (p.right != null) {
                p = p.right;
            }
            return p;
        } else {
            while (node.parent != null && node == node.parent.left) node = node.parent;
            return node.parent;
        }
    }

    public Node<E> successor(Node<E> node) {
        if (node == null) return null;
        if (node.right != null) {
            Node<E> p = node.right;
            while (p.left != null) {
                p = p.left;
            }
            return p;
        } else {
            while (node.parent != null && node == node.parent.right) node = node.parent;
            return node.parent;
        }
    }

    private int compare(E e1, E e2){
        if (comparator != null) return comparator.compare(e1, e2);
        else return ((Comparable<E>) e1).compareTo(e2);
    }

    public void remove(E element) {
        remove(node(element));
    }

    private void remove(Node<E> node) {
        size--;
        if (node.hasTwoChildren()) {
            Node<E> s = successor(node);
            node.element = s.element;
            node = s;
        }
        Node<E> replacement = node.left != null ? node.left : node.right;
        if (replacement != null) {
            replacement.parent = node.parent;
            if (node == root) root = replacement;
            else {
                if (node == node.parent.left) node.parent.left = replacement;
                else node.parent.right = replacement;
            }
        }
        if (node.parent == null) {
            root = null;
            return;
        } else {
            if (node == node.parent.left) node.parent.left = null;
            else node.parent.right = null;
        }
    }

    public boolean contains(E element) {
        return node(element) != null;
    }

}