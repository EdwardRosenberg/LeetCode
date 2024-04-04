package trees;

public class InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        swapNodes(root);
        return root;
    }

    private void swapNodes(TreeNode treeNode) {
        if (treeNode != null) {
            TreeNode temp = treeNode.left;
            treeNode.left = treeNode.right;
            treeNode.right = temp;

            swapNodes(treeNode.left);
            swapNodes(treeNode.right);
        }
    }
}