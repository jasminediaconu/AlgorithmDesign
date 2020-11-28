package main.Tutorial.DivideAndConquer;

import java.util.HashSet;
import java.util.Set;

/**
 * Implement the function partialSums(Integer[] arr),
 * which takes as input an array of integers and returns a set of all possible sums
 * of the subsets.
 *
 * For this exercise you should use a divide and conquer approach instead of brute forcing.
 *
 * An example input would be the array [1, 2], resulting in the output set {0, 1, 2, 3}.
 */
public class PartialSums {

    /**
     * Computes all possible partial sums given an array of integers.
     *
     * @param arr - all values in the input set
     * @return set of sums
     */
    public static Set<Integer> partialSums(Integer[] arr) {
        Set<Integer> result = new HashSet<>();
        result.add(0);

        if(arr.length == 0) return result;

        return divideAndConquer(arr, result, 0, arr.length);
    }

    public static Set<Integer> divideAndConquer(Integer[] array, Set<Integer> result, int index, int end) {
        int index_middle = (index + end) / 2;

        // BC: If there is only one element
        if(index + 1 == end) {
            result.add(index);
            return result;
        }

        Set<Integer> leftSet = divideAndConquer(array, result, index, index_middle);
        Set<Integer> rightSet = divideAndConquer(array, result, index_middle, end);

        mergeAndSum(leftSet, rightSet, result);

        return result;
    }

    public static void mergeAndSum(Set<Integer> left, Set<Integer> right, Set<Integer> result) {
        for(Integer first : left) {
            for (Integer second : right)
                result.add(first + second);
        }
    }

}
