package main.MidtermPreparation.GreedyAlgorithms.Mice;

import java.util.Collections;
import java.util.List;

public class Mice {

    public static int findMinTime(List<Integer> mice, List<Integer> holes) {
        Collections.sort(mice);
        Collections.sort(holes);

        int minTime = 0;
        for(int i = 0; i < mice.size(); i++) {
            int elapsedTime = Math.abs(mice.get(i) - holes.get(i));
            if (elapsedTime > minTime) minTime = elapsedTime;
        }

        return minTime;
    }

}
