package AaDS.Tasks.Trees;


import com.sun.source.tree.Tree;

public class LC112 {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5, new TreeNode(5, new TreeNode(7), new TreeNode(10)), new TreeNode(8));
        System.out.println(new LC112().hasPathSum(root, 12));
    }
    public boolean hasPathSum(TreeNode root, int targetSum) {
        int sum = 0;
        return f(root, sum, targetSum);
    }
    public boolean f (TreeNode root, int sum, int targetSum){
        if(root == null){
            return false;
        }
        if(root.left == null && root.right == null){
            sum = sum + root.val;
            if(sum == targetSum)return true;
        }
        return f(root.right, sum + root.val, targetSum) || f(root.left, sum + root.val, targetSum);
    }
}
