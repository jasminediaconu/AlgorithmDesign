package main.EndtermPreparation.DynamicProgramming.WeightedIntervalScheduling;

public class Interval implements Comparable<Interval> {
    public int s;
    public int f;
    public int weight;

    public Interval(int s, int f, int weight) {
        this.s = s;
        this.f = f;
        this.weight = weight;
    }

    @Override
    public int compareTo(Interval i) {
        return this.f - i.f;
    }
}
