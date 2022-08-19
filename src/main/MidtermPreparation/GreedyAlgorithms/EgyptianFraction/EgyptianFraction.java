package main.MidtermPreparation.GreedyAlgorithms.EgyptianFraction;

import java.util.ArrayList;

public class EgyptianFraction {
    public static ArrayList<Double> egyptianFraction(double numerator, double denominator) {
        ArrayList<Double> result = new ArrayList<>();

        return computeFraction(numerator, denominator, result);

    }

    public static ArrayList<Double> computeFraction(double numerator, double denominator, ArrayList<Double> result) {
        double fraction = numerator/denominator;
        double unitFraction = Math.ceil(denominator/numerator);

        if(fraction > 0) {
            result.add(1/unitFraction);
            computeFraction(numerator * unitFraction - denominator, denominator * unitFraction, result);
        }

        return result;
    }

}
