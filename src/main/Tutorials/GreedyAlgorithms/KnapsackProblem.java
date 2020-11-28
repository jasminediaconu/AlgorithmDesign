package main.Tutorials.GreedyAlgorithms;

import java.util.ArrayList;
import java.util.Collections;

public class KnapsackProblem {
    /**
     * Return the minimum number of items we need to get to the weight we want to get to.
     * @param n the number of item categories
     * @param w the weight we want to achieve with as few items as possible.
     * @param num the number of items in each category c_1 through c_n stored in num[1] through num[n] (NOTE: you should ignore num[0]!)
     * @param weight the weight of items in each category c_1 through c_n stored in weight[1] through weight[n] (NOTE: you should ignore weight[0]!)
     * @return minimum number of items needed to get to the required weight
     */
    public static int run(int n, int w, int[] num, int[] weight) {
        return new KnapsackProblem().solve(n, w, num, weight);
    }

    public int solve(int n, int w, int[] num, int[] weight) {
        if (n == 0 || w == 0) return 0;

        ArrayList<Knapsack> knapsacks = new ArrayList<>();

        for(int i = 1; i <= n; i++) knapsacks.add(new Knapsack(num[i], weight[i]));

        Collections.sort(knapsacks);

        int numberOfItems = 0;

        for(int i = 0; i < knapsacks.size(); i++){
            while(knapsacks.get(i).num != 0 && knapsacks.get(i).weight <= w) {
                if(knapsacks.get(i).weight <= w) {
                    w -= knapsacks.get(i).weight;
                    knapsacks.get(i).num--;
                    numberOfItems++;
                    if(knapsacks.get(i).num == 0) knapsacks.remove(i);
                    if(w == 0) break;
                }
            }

        }

        return numberOfItems;
    }

    public class Knapsack implements Comparable<Knapsack> {
        int num;
        int weight;

        public Knapsack(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }


        @Override
        public int compareTo(Knapsack k) {
            return k.weight - this.weight;
        }
    }
}


