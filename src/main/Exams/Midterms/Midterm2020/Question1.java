package main.Exams.Midterms.Midterm2020;

import java.util.*;

/**
 * You are creating an exam for Algorithm Design and in this corona year,
 * you have to come up with a number of variants for each question to counter fraud.
 * Coming up with questions and good formulations is hard, though.
 * After a question has been designed, it therefore needs to be proofread by TAs,
 * but with all these variants that is a lot of work to be done before the exam starts.
 *
 * For some exercises it is more important that there is enough time for the TAs to proofread than for others.
 * This is expressed by a penalty per hour that the proofreading time for TAs is reduced,
 * compared to the (hypothetical) situation that the exercise is available right from the start.
 * In which order should you deliver the exercises to minimize this penalty?
 *
 * Given a set of n exercises to create, with ti>0 for all 1≤i≤n representing the time required to create
 * the exercise (in hours) and pi≥0 for all 1≤i≤n the penalty obtained per hour until the exercise is created.
 * E.g. if a job takes 8 hours to finish, and has a penalty of 2 per hour, the penalty is 16.
 * If it is started later, e.g. at time 5 then it finishes at time 13 and thus incurs a penalty of 26.
 *
 * Return the minimum penalty to create all of the n exercises.
 * See the test tab for an example input and answer.
 *
 * Note: Remember that you should add a few lines (between 3 and 10)
 * of comments explaining what your code does as the basis for the interview and an anti-fraud measure.
 */
public class Question1 {
    public static int prioritisingExercises(int n, int[] t, int[] p) {
        if(n == 0) return 0;

        ArrayList<Question> questions = new ArrayList<>();

        // Add questions in a data structure
        for(int i = 1; i <= n; i++) questions.add(new Question(t[i], p[i]));

        // Sort them by highest penalty first
        Collections.sort(questions);

        int startTime = 0;
        int minPenalty = 0;

        while(!questions.isEmpty()) {
            Question firstQuestion = questions.remove(0);
            startTime += firstQuestion.time;
            int currentPenalty = startTime * firstQuestion.penalty;

            if(currentPenalty < minPenalty) minPenalty += currentPenalty;

        }
        return minPenalty;
    }
}


class Question implements Comparable<Question> {

    int time;
    int penalty;

    public Question(int time, int penalty) {
        this.time = time;
        this.penalty = penalty;
    }


    @Override
    public int compareTo(Question q) {
        int result = Integer.compare(q.penalty, this.penalty);

        return result;
    }
}