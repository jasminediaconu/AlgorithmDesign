package main.MidtermPreparation.Tutorials.DivideAndConquer;

import java.util.List;

/**
 * Implement the decisionTree method, which takes an integer d and a list of
 * samples and returns an Integer.
 * The list of samples is a list of Pair where the first element is an
 * Array of 1 or 0 values representing the sample and the second element is an Integer
 * either zero or one, which represents the label of the sample.
 * The integer d represents the number of allowed internal node layers in a decision tree.
 * The output will be highest amount of correctly classified samples a decision tree
 * with d internal layers can have on the given list of samples.
 *
 * Your task is to report the optimal result for a binary classification tree.
 * A binary classification tree is a binary tree where each leaf is assigned a label
 * (which is either zero or one) and each internal node is assigned an index.
 * Given an array of 0-1 values, we may use the classification tree to classify it as
 * follows. Start with the root node and repeat the following recursive procedure:
 * If the node is a leaf node, return the label of the node. Otherwise, recurse on
 * the left child node if the index-th element of A is zero, otherwise recurse on
 * the right node.
 */
public class OptimalDecisionTree {
    public static int findMax(List<Pair<Integer[], Integer>> samples) {
        int leftSize = 0;
        int rightSize = 0;

        for(Pair<Integer[], Integer> p : samples) {
            if(p.getR() == 0) leftSize++;
            else rightSize++;
        }

        return Math.max(leftSize, rightSize);
    }

    public static int decisionTree(int d, List<Pair<Integer[], Integer>> samples) {

        return findOptimalScore(d, samples, 0, samples.size());
    }

    public static int findOptimalScore(int d, List<Pair<Integer[], Integer>> samples, int left, int right) {
        if(d == 0) return findMax(samples);

        int maxScore = 0;

        int middle = (right+left)/2;

        List<Pair<Integer[], Integer>> leftSide = samples.subList(left, middle);
        List<Pair<Integer[], Integer>> rightSide = samples.subList(middle, right);

        int leftClassification = decisionTree(d - 1, leftSide);
        int rightClassification = decisionTree(d - 1, rightSide);

        maxScore = Math.max(leftClassification + rightClassification, maxScore);

        return maxScore;
    }
}
