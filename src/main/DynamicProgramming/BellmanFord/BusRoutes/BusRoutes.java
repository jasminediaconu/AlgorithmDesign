package main.DynamicProgramming.BellmanFord.BusRoutes;

import java.util.HashMap;

/**
 * The Kingdom of Kharagpur has N cities, which are numbered from 1 to N.
 * There are many bus routes which go directly from one city to another.
 * A bus route from City i to City j does not mean that there is a route from
 * City j to City i.
 *
 * Yesterday, the King had a dream, and he has decided that for the country to become prosperous,
 * in every city, the number of bus routes coming in should be equal to the number of bus routes
 * going out of that city. He called his transport minister and directed him to implement this.
 * The minister realized that he cannot add any new bus routes, because of financial constraints.
 * So, he can only cancel some of the existing routes. But he also knows that some of these routes
 * are Vital, and hence he cannot cancel them. So, he also wants to cancel as few non-Vital routes
 * as possible. Help the minister find the minimum number of non-Vital routes that he has to cancel,
 * so as to satisfy the King's condition.
 */
public class BusRoutes {

    public static int findBusRoutes(int n, int v, int w, int[][] vital, int[][] nonVital) {

        //int mem[][] = vital;

        int[] incoming = new int[n];
        int[] outgoing = new int[n];

        int count = 0;

        // Initialize arrays
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                // I don't care about the same city
                if (i == j) continue;
                if(vital[i][j] == 1) {
                    incoming[j]++;
                    outgoing[i]++;
                }

                if(nonVital[i][j] == 1) {
                    incoming[j]++;
                    outgoing[i]++;
                }
            }
        }

        for(int k = 0; k < n*n; k++) {
            for (int i = 0; i < n; i++) {
                if(incoming[i] == outgoing[i]) continue;
                for (int j = 0; j < n; j++) {
                    // I don't care about the same city
                    if (i == j) continue;
                    // Idk if I need this
                    //if(incoming[j] == outgoing[j]) continue;

                    // If the current element has more incoming than outgoing we should remove one edge from incoming
                    if(incoming[i] < outgoing[i] && incoming[j] > outgoing[j] && nonVital[i][j] == 1) {
                        outgoing[i]--;
                        incoming[j]--;
                        count++;
                    }

                }
            }
        }

        return count;
    }
}
