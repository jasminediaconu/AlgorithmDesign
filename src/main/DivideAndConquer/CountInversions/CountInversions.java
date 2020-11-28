package main.DivideAndConquer.CountInversions;

/** * Counting the number of inversions in a list allows us to figure out how many
 * elements are out of order in a list. Implement the Sort-and-Count algorithm out
 * of the book. This algorithm calculates the number of inversions while sorting
 * a list. Note that the Merge-and-Count part of this algorithm computes how
 * different two lists are.
 *
 * For example if you get the list [2, 1, 0, 8], your algorithm should print
 * the number of inversion, which is 3 in this example.
 *
 */
public class CountInversions {
    public static int countInversions(int[] array) {
        int temp[] = new int[array.length];
        return mergeSort(array, temp, 0, array.length - 1);
    }

    static int mergeSort(int arr[], int temp[], int left, int right)
    {
        int totalInversions = 0;

        if (left < right) {
            // Divide the array into two parts
            int mid = (right + left) / 2;

            totalInversions = mergeSort(arr, temp, left, mid);
            totalInversions += mergeSort(arr, temp, mid + 1, right);

            // Merge the two parts
            totalInversions += mergeAndCount(arr, temp, left, mid + 1, right);
        }
        return totalInversions;
    }

    static int mergeAndCount(int arr[], int temp[], int left, int mid, int right)
    {
        int inversions = 0;

        int i = left; // Index for left subarray
        int j = mid; // Index for right subarray
        int k = left; // Index for resultant merged subarray

        while ((i <= mid - 1) && (j <= right)) {
            if (arr[i] <= arr[j]) temp[k++] = arr[i++];
            else {
                temp[k++] = arr[j++];
                inversions += mid - i;
            }
        }

        // Copy the remaining elements of left subarray
        while (i <= mid - 1) temp[k++] = arr[i++];

        // Copy the remaining elements of right subarray
        while (j <= right) temp[k++] = arr[j++];

        // Copy back the merged elements to original array
        for (i = left; i <= right; i++) arr[i] = temp[i];

        return inversions;
    }
}
