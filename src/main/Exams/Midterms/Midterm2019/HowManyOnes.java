package main.Exams.Midterms.Midterm2019;

public class HowManyOnes {
    /**
     *  You should implement this method.
     *  @param n the number of elements in skills.
     *  @param skills the sorted array of `Skill`s (see Library for their implementation) to look through. Note that you should use entries skills[1] to skills[n]!
     *  @return the number of completed skills in the sorted array.
     */
    public static int numberOfCompletedSkills(int n, Skill[] skills) {
        return divideAndConquer(skills, 1, n);
    }

    public static int divideAndConquer(Skill[] skills, int low, int high) {
        if(low == high) {
            if(skills[low].isCompleted()) return 1;
            return 0;
        }

        int middle = (low + high)/2;
        int left = divideAndConquer(skills, low, middle);
        int right = divideAndConquer(skills, middle + 1, high);

        return left + right;
    }
}
