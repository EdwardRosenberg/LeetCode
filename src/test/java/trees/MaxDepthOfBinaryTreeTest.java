package trees;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaxDepthOfBinaryTreeTest {
    @Test
    public void testMaxDepth() {
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

        // Calculate max depth
        MaxDepthOfBinaryTree maxDepthBinaryTree = new MaxDepthOfBinaryTree();
        int depth = maxDepthBinaryTree.maxDepth(root);

        // Check if the max depth is correct
        assertEquals(3, depth);
    }

    @Test
    public void testMaxDepthWithNull() {
        // Test with null root
        MaxDepthOfBinaryTree maxDepthBinaryTree = new MaxDepthOfBinaryTree();
        int depth = maxDepthBinaryTree.maxDepth(null);
        assertEquals(0, depth);
    }

    @Test
    public void testMaxDepthSingleNode() {
        // Test with single node
        TreeNode root = new TreeNode(1);
        MaxDepthOfBinaryTree maxDepthBinaryTree = new MaxDepthOfBinaryTree();
        int depth = maxDepthBinaryTree.maxDepth(root);
        assertEquals(1, depth);
    }

}