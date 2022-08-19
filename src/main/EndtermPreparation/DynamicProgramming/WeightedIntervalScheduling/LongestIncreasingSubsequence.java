package main.EndtermPreparation.DynamicProgramming.WeightedIntervalScheduling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LongestIncreasingSubsequence {
    public static int LIS(int[] nums) {
        int n = nums.length;

        int mem[] = new int[n];

        mem[0] = 1;
        int max = 1;

        for(int i = 1; i < n; i++) {
            int maxValue = 0;
            for(int j = 0; j<i; j++) {
                maxValue = nums[i] > nums[j] ? Math.max(maxValue, mem[j]) : maxValue;

                mem[i] = maxValue + 1;

                max = Math.max(mem[i], max);
            }
        }

        return max;
    }

    public static List<Integer> findElements(int[] nums, int max, int mem[]) {

        ArrayList<Integer> res = new ArrayList<Integer>();

        int current = max;

        for(int i = nums.length-1; i >= 1; i--) {
            if(current == mem[i]) {
                res.add(nums[i]);
                current -= 1;
            }
        }

        Collections.reverse(res);
        return res;
    }
}
