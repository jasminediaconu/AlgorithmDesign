package main.DivideAndConquer.SortingThroughMerging;

public class SortingThroughMerging {
    /**
     * Takes an array and sorts it in an ascending order.
     *
     * @param arr
     *     - the array that needs to be sorted.
     */
    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        int size = arr.length;

        int[] left = new int[size/2];
        int[] right = new int[size-left.length];

        for (int i = 0; i < left.length; i++) left[i] = arr[i];

        for (int j = 0; j < right.length; j++) right[j] = arr[left.length + j];

        sort(left);
        sort(right);
        merge(arr, left, right);
    }

    public void merge(int[] arr, int[] left, int[] right) {
        int i = 0;
        int j = 0;
        int k = 0;

        while (i != left.length && j != right.length) {
            if (left[i] < right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }

        while (i != left.length) {
            arr[k] = left[i];
            i++;
            k++;
        }

        while (j != right.length) {
            arr[k] = right[j];
            j++;
            k++;
        }
    }
}
