package main.MidtermPreparation.GreedyAlgorithms;

public class BetterAverage {
    public static double solve(int n, double[] list) {
        mergeSort(list);

        if(n % 2 == 0) return (list[n/2] + list[n/2 + 1])/2;

        return list[n/2];
    }

    public static void merge(double[] arr, double[] left, double[] right) {
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

    public static void mergeSort(double[] arr) {
        if (arr == null || arr.length < 2) return;

        int size = arr.length;

        double[] left = new double[size / 2];
        double[] right = new double[size - size / 2];

        for (int i = 0; i < left.length; i++) left[i] = arr[i];

        for (int j = 0; j < right.length; j++) right[j] = arr[left.length + j];

        mergeSort(left);
        mergeSort(right);
        merge(arr, left, right);
    }
}
