package main.FinalExam.Question1;

import java.util.Arrays;

public class VacinationTime {
    /**
     * You should implement this method.
     *
     * @param t the number of days we receive patients for.
     * @param p the number of patients per day from index _1_ to _n_, you should ignore p[0].
     * @param c the cost of a new shipment of vaccines, regardless of how many you order.
     * @param f the cost _per_ vaccines stored overnight.
     * @param s the maximum number of vaccines we can store over night.
     * @return the minimal cost to vaccinate all patients.
     */
    public static int minimiseCosts(int t, int[] p, int c, int f, int s) {

        int mem[][] = new int[t+1][s+1];

        for(int i = 0; i <= t; i++) {
            for(int j = 0; j <= s; j++) {
                // Initialize with the number of patients we have on day i
                if(j < p[i]) mem[i][j] = Integer.MAX_VALUE/2;
                else mem[i][j] = (j - p[i] * f) + c * i;
            }
        }

        for(int i = 0; i <= t; i++) {
            for(int j = 0; j <= s; j++) {

                // Base cases (we don't necessarly need them)
                if(i > t && j == 0) mem[i][j] = 0;
                if(i > t && j > 0) mem[i][j] = Integer.MAX_VALUE/2;

                // We need to check whether the patients are less than the
                for(int k = p[i]; k <= j; k++) {
                    // We need to check if we have space for storing extra vaccines
                        // We compute the amount of vaccines we can store and multiply by the unit cost
                        int costOfStoringVaccines = f * (k - p[i]);
                        mem[i][j] =
                                Math.min(mem[i][j], costOfStoringVaccines + (c * i)  + mem[i][k-p[i]]);
                    }

//                    else {
//                        // We need to take the previous minimum
//                        // Something is probably breaking here
//                        // something like: OPT(i, j) = OPT(i, s - p[i])
//                        mem[i][j] = mem[i][k];
//                    }


            }
        }

        return mem[0][s];
    }
}
