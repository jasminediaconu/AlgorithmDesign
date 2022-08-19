package main.EndtermPreparation.DynamicProgramming.WeightedIntervalScheduling;

public class BitcoinsAndEuros {
    public static double optimalTrade(int t, double[] r) {
        double[] buy = new double[t+1];
        double[] trade = new double[t+1];

        buy[0] = 0.1;

        for(int i = 1; i <=t; i++) {
            buy[i] = Math.max(buy[i-1], (trade[i-1] - 5)/r[i] );
            trade[i] = Math.max(trade[i-1], buy[i-1] *  0.95 * r[i]);
        }


        return Math.max(buy[t], trade[t]);
    }
}
