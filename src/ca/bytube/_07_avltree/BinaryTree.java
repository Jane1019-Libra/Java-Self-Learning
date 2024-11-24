package ca.bytube._07_avltree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree<E> {
    protected int size;
    protected Node<E> root;

    public int size() {return size;}

    public boolean isEmpty() {return size == 0;}

    public void clear() {
        size = 0;
        root = null;
    }

    public static abstract class Visitor<E>{
        boolean stop;
        public abstract boolean visit(E element);
    }

    public void preOrderTraversal(){
        preOrderTraversalStack(root);
    }

    public void preOrderTraversal(Visitor<E> visitor){
        if (visitor == null) return;
        preOrderTraversal(root, visitor);
    }

    public void preOrderTraversal(Node<E> node, Visitor<E> visitor){
        if (node == null || visitor == null) return;
        visitor.stop = visitor.visit(node.element);
        preOrderTraversal(node.left, visitor);
        preOrderTraversal(node.right, visitor);
    }

    public void preOrderTraversalStack(Node<E> node) {
        Stack<Node<E>> stack = new Stack<>();
        stack.push(node);
        while(!stack.isEmpty()) {
            Node<E> pop = stack.pop();
            System.out.print(pop.element + " ");
            if (pop.right != null) stack.push(pop.right);
            if (pop.left != null) stack.push(pop.left);
        }
    }

    public void inOrderTraversal() {inOrderTraversalStack(root);}

    public void inOrderTraversal(Visitor<E> visitor) {inOrderTraversal(root, visitor);}

    public void inOrderTraversal(Node<E> node,Visitor<E> visitor) {
        if (node == null || visitor == null) return;
        inOrderTraversal(node.left, visitor);
        visitor.stop = visitor.visit(node.element);
        inOrderTraversal(node.right, visitor);
    }

    public void inOrderTraversalStack(Node<E> node) {
        if (node != null) {
            Stack<Node<E>> stack = new Stack<>();
            while (!stack.isEmpty() || node != null) {
                if (node != null) {
                    stack.push(node);
                    node = node.left;
                } else {
                    Node<E> pop = stack.pop();
                    System.out.print(pop.element + " ");
                    node = pop.right;
                }
            }
        }
    }

    public void postOrderTraversal() {postOrderTraversalStack(root);}

    public void postOrderTraversal(Visitor<E> visitor) {postOrderTraversal(root, visitor);}

    public void postOrderTraversal(Node<E> node, Visitor<E> visitor){
        if (node == null || visitor == null) return;
        postOrderTraversal(node.left, visitor);
        postOrderTraversal(node.right, visitor);
        visitor.stop = visitor.visit(node.element);
        System.out.print(node.element + " ");
    }

    public void postOrderTraversalStack(Node<E> node) {
        Stack<Node<E>> stack = new Stack<>();
        Stack<Node<E>> helpStack = new Stack<>();
        stack.push(node);
        while(!stack.isEmpty()) {
            Node<E> pop = stack.pop();
            helpStack.push(node);
            if (pop.left != null) stack.push(pop.left);
            if (pop.right != null) stack.push(pop.right);
        }
        while(!helpStack.isEmpty()) System.out.print(helpStack.pop().element + " ");
    }

    public void levelOrderTraversal() {levelOrderTraversalQueue(root);}
    public void levelOrderTraversalQueue(Node<E> node) {
        if (node == null) return;
        Queue<Node<E>> queue =new LinkedList<>();
        queue.offer(node);
        while(!queue.isEmpty()) {
            Node<E> poll = queue.poll();
            System.out.print(poll.element + " ");
            if (poll.left != null) queue.offer(poll.left);
            if (poll.right != null) queue.offer(poll.right);
        }
    }

    public int height() {
        return height(root);
    }

    public int height0(Node<E> node){
        if (node == null) return 0;
        return Math.max(height0(node.left), height0(node.right)) + 1;
    }

    public int height(Node<E> node){
        if (node == null) return 0;
        Queue<Node<E>> queue =new LinkedList<>();
        queue.offer(node);
        int counter = 0;
        int levelSize = 1;
        while(!queue.isEmpty()) {
            Node<E> poll = queue.poll();
            levelSize--; //码值
            if (poll.left != null) queue.offer(poll.left);
            if (poll.right != null) queue.offer(poll.right);
            if (levelSize == 0) {
                levelSize = queue.size();
                counter++;
            }
        }
        return counter;
    }

    public boolean isComplete() {
        return isCompleteTree(root);
    }

    public boolean isCompleteTree(Node<E> head) {
        if (root == null) return false;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        boolean isLeaf = false;
        while(!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (isLeaf && !node.isLeafNode()) return false;
            if (node.hasTwoChildren()) {
                queue.offer(node.left);
                queue.offer(node.right);
            }
            else if (node.left == null && node.right != null) return false;
            else if (node.left != null) {
                isLeaf = true;
                queue.offer(node.left);
            } else {
                isLeaf = true;
            }
        }
        return true;
    }


    public static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node() {}

        public Node(E element) {
            this.element = element;
        }

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public Node(E element, Node<E> left, Node<E> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "element=" + element;
        }

        protected boolean hasTwoChildren() {
            return this.left != null && this.right != null;
        }

        protected boolean isLeafNode() {
            return this.left == null && this.right == null;
        }

        protected boolean isLeftChild() {
            return parent != null && this == this.parent.left;
        }

        protected boolean isRightChild() {
            return parent != null && this == this.parent.right;
        }
    }

}
