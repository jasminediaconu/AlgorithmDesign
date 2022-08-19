package main.EndtermPreparation.DynamicProgramming.WeightedIntervalScheduling;

/**
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed.
 * All houses at this place are arranged in a circle.
 * That means the first house is the neighbor of the last one.
 * Meanwhile, adjacent houses have a security system connected,
 * and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given a list of non-negative integers nums representing the amount of money of each house,
 * return the maximum amount of money you can rob tonight without alerting the police.
 */
public class HouseRobber2 {

    public static int rob(int[] nums) {
        int n = nums.length;

        if(n == 0) return 0;
        if(n == 1) return nums[0];

        int[] even = new int[n];
        int[] odd = new int[n];

        even[0] = nums[0];
        even[1] = nums[0];
        odd[1] = nums[1];

        for(int i = 2; i < n; i++) {
            boolean isAdjacent = i == n-1;

            if(isAdjacent) even[i] = Integer.max(even[i - 1], even[i - 2]);
            else even[i] = Integer.max(even[i - 1], even[i - 2] + nums[i]);

            odd[i] = Integer.max(odd[i - 1], odd[i - 2] + nums[i]);
        }

        return Math.max(even[n-1], odd[n-1]);
    }
}
