package trees;

/**
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 * <p>
 * A valid BST is defined as follows:
 * <p>
 * The left  subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * <p>
 * Note: this implementation expects all values to be unique
 */
public class ValidBinarySearchTree {

    public boolean isValid(TreeNode root) {
        return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode node, int min, int max) {
        // Null node should not be considered a valid BST
        if (node == null) return false;

        // Check if subtree is internally consistent
        if (!hasBothNodes(node)) {
            return hasNoNodes(node); // If only one child is null, return true
        }

        // Check if left and right children are valid
        if (!isLeftSideValid(node) || !isRightSideValid(node)) {
            return false;
        }

        // Validate that values in the left and right subtrees are within the allowed range
        if (node.left.value >= node.value || node.left.value <= min) {
            return false;
        }
        if (node.right.value <= node.value || node.right.value >= max) {
            return false;
        }

        // Recursively check left and right subtrees
        return isValidBST(node.left, min, node.value) && isValidBST(node.right, node.value, max);
    }

    private boolean isLeftSideValid(TreeNode node) {
        return node.left.value < node.value;
    }

    private boolean isRightSideValid(TreeNode node) {
        return node.right.value > node.value;
    }

    private boolean hasBothNodes(TreeNode node) {
        return (node.left != null && node.right != null);
    }

    private boolean hasNoNodes(TreeNode node) {
        return node.left == null && node.right == null;
    }
}

