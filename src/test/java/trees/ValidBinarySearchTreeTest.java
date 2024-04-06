package trees;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidBinarySearchTreeTest {
    @Test
    void testValidBinarySearchTree() {
        // Creating a valid binary search tree
        //      5
        //     / \
        //    3   7
        //   / \ / \
        //  1  4 6  8
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(8);

        assertTrue(new ValidBinarySearchTree().isValid(root));
    }

    @Test
    void testInvalidBinarySearchTree() {
        // Creating an invalid binary search tree
        //      5
        //     / \
        //    3   7
        //   / \ / \
        //  1  4 0  8
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(0); // has to be > 5
        root.right.right = new TreeNode(8);

        assertFalse(new ValidBinarySearchTree().isValid(root));
    }

    @Test
    void testInvalidBinarySearchTreeMissingNode() {
        // Creating an invalid binary search tree
        //      5
        //     / \
        //    3   7
        //   / \ /
        //  1  4 6
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(6);

        assertFalse(new ValidBinarySearchTree().isValid(root));
    }

    @Test
    void testValidBinarySearchTreeWithNegativeValues() {
        // Creating a valid binary search tree with negative values
        //       0
        //     /   \
        //   -3     5
        //  /   \
        // -10   -1
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(-3);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(-10);
        root.left.right = new TreeNode(-1);

        assertTrue(new ValidBinarySearchTree().isValid(root));
    }

    @Test
    void testSingleNode() {
        // Single node is always a valid BST
        //  5
        TreeNode root = new TreeNode(5);

        assertTrue(new ValidBinarySearchTree().isValid(root));
    }

    @Test
    void testNullNode() {
        // Null node should not be considered as a valid BST
        assertFalse(new ValidBinarySearchTree().isValid(null));
    }
}