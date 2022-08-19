package main.EndtermPreparation.DynamicProgramming.WeightedIntervalScheduling;

public class HouseRobber3 {
    public static int rob(TreeNode root) {
        // Base cases
        if (root == null) return 0;
        if(root.left == null && root.right == null) return root.val;

        int odd = root.val;

        if(root.left != null) {
            odd += rob(root.left.left);
            odd += rob(root.left.right);
        }

        if(root.right != null) {
            odd += rob(root.right.left);
            odd += rob(root.right.right);
        }

        int even = 0;

        if(root.left != null) even += rob(root.left);
        if(root.right != null) even += rob(root.right);


        return Math.max(odd, even);
    }

    public int[] robHelper(TreeNode root) {
        // Base case
        if (root == null) return new int[] {0, 0};

        int[] moneyLeft = robHelper(root.left);
        int[] moneyRight = robHelper(root.right);

        int moneyIncludeRoot = root.val + moneyLeft[1] + moneyRight[1];
        int moneyExcludeRoot = Math.max(moneyLeft[0], moneyLeft[1])
                + Math.max(moneyRight[0], moneyRight[1]);

        return new int[] {moneyIncludeRoot, moneyExcludeRoot};
    }

    public int robDP(TreeNode root) {
        int[] result = robHelper(root);
        return Math.max(result[0], result[1]);
    }
}