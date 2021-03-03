package main.MidtermPreparation.DivideAndConquer.SmallIsBetter;

/**
 *
 * Implement the findMin method, this method should use divide and conquer
 * to find the value x for which the difference between two discrete functions
 * is minimal.
 *
 * Take for example the linear equation f(x)=8x−240 and g(x)=6x−156,
 * these two lines will intersect each other in the point (42,96),
 * so the result of this algorithm should be 42.
 *
 */
public class SmallIsBetter {
    /**
     *
     * Finds the x coordinate with the smallest distance between two linear equations,
     * by recursively evaluating the median of the range and that integer + 1.
     * Depending on the value, a new evaluation is made with a smaller range to find
     * the x coordinate with the smallest distance.
     * @param e1 the first equation to evaluate
     * @param e2 the second equation to evaluate
     * @param low the lower boundary of the range
     * @param high the upper boundary of the range
     * @return the x coordinate with the minimum difference between e1 and e2
     *
     */
    public static long findMin(Equation e1, Equation e2, long low, long high) {
        if (low == high) return low;
        // e1 = 160
        // e2 = 144

        // e1 = 168
        // e2 = 150
        long median = (high + low) / 2;
        long result = Math.abs(e1.evaluate(median) - e2.evaluate(median))
                - Math.abs(e1.evaluate(median+1) - e2.evaluate(median+1));

        if(result == 0) return median;

        else if (result < 0) return findMin(e1, e2, low, median);

        return findMin(e1, e2, median + 1, high);
    }
}