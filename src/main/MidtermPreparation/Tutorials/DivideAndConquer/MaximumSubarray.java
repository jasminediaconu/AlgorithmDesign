package main.MidtermPreparation.Tutorials.DivideAndConquer;

/**
 * Implement the function largestSum(int[] arr), that takes as input an array
 * of integers, find the largest sum using consecutive elements in this array
 * using a divide and conquer approach.
 *
 * As an example, given the input array [2, -3 , 2, 1], the largest consecutive
 * sum would use the last two elements to sum to 3.
 *
 **/
public class MaximumSubarray {

    public static int largestSum(int[] arr) {
        return divideAndConquer(arr, 0, arr.length - 1);
    }

    public static int divideAndConquer(int[] arr, int low, int high) {
        // BC: There is only one element
        if(low == high) return arr[low];

        int middle = (low + high)/2;

        // Split into two sub arrays
        int maxLeft = divideAndConquer(arr, low, middle);
        int maxRight = divideAndConquer(arr, middle+1, high);

        // Find the maximum
        int maximum = Math.max(maxLeft, maxRight);

        return Math.max(maximum, mergeAndSum(arr, low, middle, high));
    }

    public static int mergeAndSum(int arr[], int low, int mid, int high) {
        int total = 0;

        int leftSum = 0;

        // From the middle to the left
        for (int i = mid; i >= low; i--)
        {
            total += arr[i];
            if (total > leftSum) leftSum = total;
        }

        total = 0;

        int rightSum = 0;

        // From the middle to the right
        for (int i = mid + 1; i <= high; i++)
        {
            total += arr[i];
            if (total > rightSum) rightSum = total;
        }

        // Check max sum
        total = Math.max(leftSum, rightSum);

        // Check total sum vs partial sum
        return Math.max(leftSum + rightSum, total);
    }
}
