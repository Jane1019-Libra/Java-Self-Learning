package ca.bytube._07_avltree;

import java.util.Comparator;

public class AVLTree<E> extends BinarySearchTree<E>{
    public AVLTree() {}

    public void afterAdd(Node<E> node) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                calculateHeight(node);
            } else {
                rebalance(node);
                break;
            }
        }
    }

    public void afterRemove(Node<E> node) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                calculateHeight(node);
            } else {
                rebalance(node);
            }
        }
    }

    // Node = avlnode
    private void calculateHeight(Node<E> node) {
        ((AVLNode<E>) node).calculateHeight();
    }

    private boolean isBalanced(Node<E> node) {
        return Math.abs(((AVLNode<E>) node).balanceFactor()) <= 1;
    }

    private void rebalance(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();

        if (parent.isLeftChild()) {
            if (node.isLeftChild()) {
                rightRotation(grand);
            } else {
                leftRotation(parent);
                rightRotation(grand);
            }
        } else {
            if (node.isLeftChild()) {
                rightRotation(parent);
                leftRotation(grand);
            } else {
                leftRotation(grand);
            }
        }
    }

    private void rebalance2(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();

        if (parent.isLeftChild()) {
            if (node.isLeftChild()) {
                rotate(grand, node.left, node, node.right, parent, parent.right, grand, grand.right);
            } else {
                rotate(grand, parent.left, parent, node.left, node, node.right, grand, grand.right);
            }
        } else {
            if (node.isLeftChild()) {
                rotate(grand, grand.left, grand, node.left, node, node.right, parent, parent.right);
            } else {
                rotate(grand, grand.left, grand, parent.left, parent, node.left, node, node.right);
            }
        }
    }

    private void rotate(Node<E> r, Node<E> a, Node<E> b, Node<E> c, Node<E> d,
                        Node<E> e, Node<E> f, Node<E> g){
        d.parent = r.parent;
        if (r.isLeftChild()) {
            r.parent.left = d;
        } else if (r.isRightChild()) {
            r.parent.right = d;
        } else {
            root = d;
        }
        b.left = a;
        b.right = c;
        if (a != null) a.parent = b;
        if (c != null) c.parent = b;

        calculateHeight(b);

        f.left = e;
        f.right = g;
        if (e != null) e.parent = f;
        if (g != null) g.parent = f;

        calculateHeight(f);
        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;

        calculateHeight(d);
    }
    private void leftRotation(Node<E> grand) {
        Node<E> parent = grand.right;
        Node<E> child = parent.left;
        grand.right = child;
        parent.left = grand;
        afterRotation(grand, parent, child);
    }

    @Override
    protected Node<E> created_Node(E element, Node<E> parent) {
        return new AVLNode<>(element, parent);
    }

    private void rightRotation(Node<E> grand) {
        Node<E> parent = grand.left;
        Node<E> child = parent.right;
        grand.left = child;
        parent.right = grand;
        afterRotation(grand, parent, child);
    }

    public void afterRotation(Node<E> grand, Node<E> parent, Node<E> child) {
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else {
            root = parent;
        }
        if (child != null) {
            child.parent = grand;
        }
        grand.parent = parent;
        calculateHeight(grand);
        calculateHeight(parent);
    }


    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    private static class AVLNode<E> extends Node<E> {
        int height = 1;

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        // 为什么 需要 cast type
        // 因为this.left 是node 类型
        public int balanceFactor() {
            int leftHeight = this.left == null ? 0 : ((AVLNode<E>)this.left).height;
            int rightHeight = this.right == null ? 0 : ((AVLNode<E>)this.right).height;
            return leftHeight - rightHeight;
        }

        public void calculateHeight() {
            int leftHeight = this.left == null ? 0 : ((AVLNode<E>)this.left).height;
            int rightHeight = this.right == null ? 0 : ((AVLNode<E>)this.right).height;
            height = Math.max(leftHeight, rightHeight) + 1;
        }

        public Node<E>tallerChild() {
            int leftHeight = this.left == null ? 0 : ((AVLNode<E>)this.left).height;
            int rightHeight = this.right == null ? 0 : ((AVLNode<E>)this.right).height;
            if (leftHeight > rightHeight) return this.left;
            if (rightHeight > leftHeight) return this.right;
            return this == this.parent.left ? this.left : this.right;
        }
    }
}
