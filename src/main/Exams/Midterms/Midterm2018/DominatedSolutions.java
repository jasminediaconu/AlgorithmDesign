package main.Exams.Midterms.Midterm2018;

import java.util.*;

public class DominatedSolutions {
    /**
     *  You should implement this method.
     *  @param sol a solution to compare the others to.
     *  @param S a set of solutions
     *  @return all solutions in S that are _not_ dominated by sol.
     */
    public static List<Solution> undominatedBy(Solution sol, List<Solution> S) {
        List<Solution> undominatedSolutions = new ArrayList<>();

        for(int i = 0; i < S.size(); i++) {
            Solution current = S.get(i);
            if(sol.numberOfHours >= current.numberOfHours || sol.quality < current.quality)
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
         * You should not need to change the equals and hashCode methods below. Our tests just use them.
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
