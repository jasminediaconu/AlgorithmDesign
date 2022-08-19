package main.Exams.Midterms.Midterm2018;

import java.util.*;

public class ParetoOptima {
    /**
     *  You should implement this method.
     *  @param S the list of solutions to get the pareto optima from.
     *  @return the list of pareto optimal solutions.
     */
    public static List<Solution> getParetoOptima(List<Solution> S) {
        // Sort by increasing number of hours
        Collections.sort(S, new Comparator<Solution>() {
            @Override
            public int compare(Solution sol1, Solution sol2) {
                return sol1.numberOfHours - sol2.numberOfHours;
            }});

        ArrayList<Solution> result = new ArrayList<>();

        result.addAll(divideAndConquer(S, 0 , S.size()));

        return result;
    }


    public static List<Solution> divideAndConquer(List<Solution> solutions, int low, int high) {
        List<Solution> undominated = new ArrayList<>();

        if(low + 1 == high) {
            undominated.add(solutions.get(low));
            return undominated;
        }

        int middle = (low + high) / 2;

        List<Solution> leftUndominated = divideAndConquer(solutions, low, middle);
        List<Solution> rightUndominated = divideAndConquer(solutions, middle, high);

        Solution lastLeft = leftUndominated.get(leftUndominated.size() - 1);
        leftUndominated.addAll(undominatedBy(lastLeft, rightUndominated));
        undominated = leftUndominated;

        return undominated;
    }

    public static ArrayList<Solution> undominatedBy(Solution sol, List<Solution> S) {
        ArrayList<Solution> undominatedSolutions = new ArrayList<>();

        for(int i = 0; i < S.size(); i++) {
            Solution current = S.get(i);
            if(sol.numberOfHours >= current.numberOfHours ||
                    sol.quality < current.quality)
                undominatedSolutions.add(current);
        }

        return undominatedSolutions;
    }

    public static class Solution {

        int numberOfHours;

        int quality;

        public Solution(int numberOfHours, int quality) {
            this.numberOfHours = numberOfHours;
            this.quality = quality;
        }

        /*
                You should not need the equals and hashcode method below, we just use them in the test.
             */
        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Solution that = (Solution) o;
            return numberOfHours == that.numberOfHours && quality == that.quality;
        }

        @Override
        public int hashCode() {
            return Objects.hash(numberOfHours, quality);
        }
    }
}
