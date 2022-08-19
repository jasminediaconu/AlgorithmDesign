package main.Exams.Midterms.Midterm2020;

/**
 * Ninjas are evaluating the suitability of a row of trees for training,
 * with heights hi for 1≤i≤n.
 * From tree i a ninja is only allowed to directly jump to the tree i+1.
 * Going backwards is for ninja-poseurs (in plainer English: losers).
 * For example, to get to tree i+2 from tree i, the ninja must first
 * jump from tree i to tree i+1, and then from tree i+1 to tree i+2,
 * but a ninja is not allowed to go from tree i to tree i−1.
 *
 * A training session consists of tree steps:
 * 1. starting the session by climbing one of the trees,
 * 2. jumping to another tree (which may involve jumping to other intermediate trees), and
 * 3. ending the session by climbing down.
 *
 * Due to the philosophy of the clan, ninjas highly value jumping upwards
 * but punish jumping downwards.
 * More precisely, the value of a jump J(i,j) from tree i to tree i+1 is given as:
 *
 * J(i,j)=hj−hi if hj≥hi
 * J(i,j)=−(hj−hi)2 if hj<hi.
 *
 * The training session value is expressed as the sum of the values of each jump
 * in the training session. For example, for the training session with tree heights [5,10,12,10],
 * the value is 5+2−(22)=3, whereas omitting the last tree yields a greater value of 7.
 *
 * Given n heights h1,…,hn, return the maximum possible training session value achievable.
 *
 * For 1 point, implement a brute-force O(n2) solution to this problem.
 * For an additional 2.5 points, implement a more efficient Divide & Conquer solution to this problem.
 */
public class Question3 {
    /**
     * Jump evaluation as given in the assignment description.
     * There should be no need to modify this function!
     */
    public static int evaluateJump(int height1, int height2) {
        if (height1 <= height2) {
            return height2 - height1;
        } else {
            int diff = height1 - height2;
            return -(diff * diff);
        }
    }

    /**
     * You should implement this method.
     *
     * @param n the number of trees
     * @param h the height of the trees h_1 through h_n. Note you should only use entries h[1]
     *          up to and including h[n].
     * @return the score of the best possible training.
     */
    public static int findBestTrainingBruteForce(int n, int[] h) {
        // Initialize maximum
        int maximumJump = 0;

        for(int i = 1; i < n; i++) {
            int jumps = 0;
            for (int j = i ; j < n ; j++) {
                // Compare h[i] with h[j] and increase total jump value
                jumps += evaluateJump(h[j], h[j+1]);
                // Compare current value with previous value
                if(maximumJump < jumps) maximumJump = jumps;
            }
        }

        return maximumJump;
    }

    /**
     * You should implement this method.
     *
     * @param n the number of trees
     * @param h the height of the trees h_1 through h_n. Note you should only use entries h[1] up
     *         to and including h[n].
     * @return the score of the best possible training.
     */
    public static int findBestTrainingDivideAndConquer(int n, int[] h) {
        if(n == 1) return h[1];
        return divideAndConquer(h, 1, n);
    }

    public static int divideAndConquer(int[] h, int low, int high) {
        // Base case with one element
        if(low + 1 == high) return evaluateJump(h[low], h[high]);

        int middle = (high + low) / 2;

        // Split into two sub arrays
        int valueLeft = divideAndConquer(h, low, middle);
        int valueRight = divideAndConquer(h, middle, high);

        // Find the training session value
        int value = Math.max(Math.max(valueLeft, valueLeft + valueRight), valueRight);
        // Return the max value between the previous one and the next one
        return Math.max(value, mergeAndSum(h, low, middle, high));
    }

    public static int mergeAndSum(int h[], int low, int mid, int high) {
        int total = 0;

        int leftSum = 0;

        // From the middle to the left
        for (int i = mid; i > low; i--)
        {
            total += evaluateJump(h[i-1], h[i]);
            if (total > leftSum) leftSum = total;
        }

        total = 0;

        int rightSum = 0;


        // From the middle to the right
        for (int i = mid + 1; i < high; i++)
        {
            total += evaluateJump(h[i], h[i + 1]);
            if (total > rightSum) rightSum = total;
        }

        // Check max sum
        total = Math.max(leftSum, rightSum);

        // Check total sum vs partial sum
        return leftSum + rightSum;
    }
}
