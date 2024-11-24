package ca.bytube._06_Tree;

import ca.bytube._06_Tree.BinaryTree.Node;

import java.util.Random;

public class main {
    public static void main(String[] args) {
        /*
        Node root = new Node<>(7);
        root.left = new Node<>(4);
        root.right = new Node<>(9);
        root.left.left = new Node<>(2);
        root.left.right= new Node<>(5);
        root.right.right= new Node<>(11);
        root.left.left.left = new Node<>(1);
        root.left.left.right = new Node<>(3);
        root.right.right.left = new Node<>(10);
        root.right.right.right= new Node<>(12);

        BinaryTree<Integer> binaryTree = new BinaryTree<>();

         */
        test1();
    }

    public static void test1() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(8);
        bst.add(3);
        bst.add(10);
        bst.add(1);
        bst.add(6);
        bst.add(14);
        bst.add(4);
        bst.add(7);
        bst.add(13);
        System.out.println( bst.contains(12));
    }
}
