package main.EndtermPreparation.DynamicProgramming.WeightedIntervalScheduling;

public class WeightedInterval implements Comparable<WeightedInterval> {
    public int s;
    public int f;
    public int weight;

    public WeightedInterval(int s, int f, int weight) {
        this.s = s;
        this.f = f;
        this.weight = weight;
    }

    @Override
    public int compareTo(WeightedInterval i) {
        return this.s - i.s;
    }
}