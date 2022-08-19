package main.MidtermPreparation.GreedyAlgorithms.GreedyTriathlon;

import java.util.ArrayList;
import java.util.Collections;

public class GreedyTriathlon {
    public static int greedyTriathlon(int[] swimming, int[] biking, int[] running) {
        int numberOfContestants = swimming.length;
        int completionTime = 0;

        ArrayList<Contestant> contestants = new ArrayList<>();

        for(int i = 0; i < numberOfContestants; i++)
            contestants.add(new Contestant(swimming[i], biking[i], running[i]));

        Collections.sort(contestants);

        for(int i = 0; i < numberOfContestants; i++) completionTime += contestants.get(i).swimming;

        completionTime += contestants.get(numberOfContestants-1).biking
                + contestants.get(numberOfContestants-1).running;

        return completionTime;
    }
}

class Contestant implements Comparable<Contestant> {
    int swimming;
    int biking;
    int running;

    public Contestant (int swimming, int biking, int running) {
        this.swimming = swimming;
        this.biking = biking;
        this.running = running;
    }


    @Override
    public int compareTo(Contestant c) {
        return (c.biking + c.running) - (this.biking + this.running);
    }
}
