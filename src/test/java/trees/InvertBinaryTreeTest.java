package trees;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class InvertBinaryTreeTest {

    @Test
    public void testInvertTree() {
        // Create a binary tree
        //      1
        //     / \
        //    2   3
        //   / \
        //  4   5
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        // Invert the binary tree
        InvertBinaryTree invertBinaryTree = new InvertBinaryTree();
        TreeNode invertedTree = invertBinaryTree.invertTree(root);


        // Inverted tree
        //      1
        //     / \
        //    3   2
        //       / \
        //      5   4
        // Check if the tree is inverted properly
        assertEquals(1, invertedTree.value);
        assertEquals(3, invertedTree.left.value);
        assertEquals(2, invertedTree.right.value);
        assertEquals(5, invertedTree.right.left.value);
        assertEquals(4, invertedTree.right.right.value);
    }

    @Test
    public void testInvertTreeWithNull() {
        // Test with null root
        InvertBinaryTree invertBinaryTree = new InvertBinaryTree();
        TreeNode invertedTree = invertBinaryTree.invertTree(null);
        assertNull(invertedTree);
    }

}