package main.MidtermPreparation.GreedyAlgorithms.Policemen;

import java.util.ArrayList;

public class Policemen {
    public static int findThieves(char[] arr, int n, int k) {
        int res = 0;
        ArrayList<Integer> th = new ArrayList<>();
        ArrayList<Integer> pol = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            if(arr[i] == 'P') pol.add(i);
            else th.add(i);
        }

        int l = 0, r = 0;

        while(l < pol.size() && r < th.size()) {
            if(Math.abs(th.get(r) - pol.get(l)) <= k) {
                res++;
                l++;
                r++;
            }

            else if(th.get(r) < pol.get(l)) r++;
            else l++;
        }

        return res;
    }
}
