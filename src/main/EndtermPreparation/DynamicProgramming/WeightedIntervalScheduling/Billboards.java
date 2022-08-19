package main.EndtermPreparation.DynamicProgramming.WeightedIntervalScheduling;

import java.util.Arrays;

/**
 * Billboard constructions on a highway of length M.
 */
public class Billboards {

    public static int billboardConstructions(int M, int n, int[] sites, int[] values) {

        Billboard[] billboards = new Billboard[n+1];
        // Add them to a list (to sort them)
        for(int i = 0; i <= n; i++) billboards[i] = new Billboard(sites[i], values[i]);
        // Sort them
        Arrays.sort(billboards);

        int[] order = getSequence(M, n, billboards);

        int mem[] = new int[n+1];

        for(int i = 1; i <= n; i++) {
            if(order[i] == 0) mem[i] = Math.max(billboards[i].value, mem[i-1]);
            else mem[i] = Math.max(billboards[i].value + billboards[order[i]].value, mem[i-1]);
        }

        return mem[n];
    }

    public static int[] getSequence(int M, int n, Billboard[] billboards) {
        int[] order = new int[n+1];

        for(int i = 2; i <= n; i++) {
            for(int j = 1; j < i; j++) {
                if(billboards[i].site - 5 > billboards[j].site) order[i] = j;
            }
        }

        return order;
    }
}

class Billboard implements Comparable<Billboard> {
    int site;
    int value;

    public Billboard(int site, int value) {
        this.site = site;
        this.value = value;
    }

    @Override
    public int compareTo(Billboard b) {
        return this.site - b.site;
    }
}
