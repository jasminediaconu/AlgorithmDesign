package main.MidtermPreparation.DivideAndConquer;

public class MergeSort {

    public static void mergeSort(int[] array) {
        if (array == null || array.length < 2) return;

        int[] leftSide = new int[array.length/2];
        int[] rightSide = new int[array.length - array.length/2];

        for (int i = 0; i < leftSide.length; i++) leftSide[i] = array[i];
        for (int i = 0; i < rightSide.length; i++) rightSide[i] = array[leftSide.length + i];

        mergeSort(leftSide);
        mergeSort(rightSide);
        merge(array, leftSide, rightSide);

    }

    public static void merge(int[] array, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        while(i < left.length && j < right.length) {
            if (left[i] >= right[j]) {
                array[k] = right[j];
                j++;
            } else {
                array[k] = left[i];
                i++;
            }
            k++;
        }

        // add residuals
        while(i != left.length) {
            array[k] = left[i];
            i++;
            k++;
        }

        while(j != right.length) {
            array[k] = right[j];
            j++;
            k++;
        }
    }
}
