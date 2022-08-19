package main.MidtermPreparation.GreedyAlgorithms.JobSequencing;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Given an array of jobs where every job has a deadline and associated profit if the job
 * is finished before the deadline. It is also given that every job takes the single unit of time,
 * so the minimum possible deadline for any job is 1. How to maximize total profit if only one
 * job can be scheduled at a time.
 */
public class JobSequencing {
    public static int jobSequencing(int[] deadlines, int[] profits) {

        LinkedList<Job> jobs = new LinkedList<>();

        for(int i = 0; i < deadlines.length; i++) jobs.add(new Job(deadlines[i], profits[i]));

        Collections.sort(jobs);

        int count = 0;
        int maxProfit = 0;

        while(!jobs.isEmpty()) {
            Job first = jobs.removeFirst();

            if(first.deadline - 1 >= count) {
                maxProfit += first.profit;
                count++;
            }
        }

        return maxProfit;
    }

}

class Job implements Comparable<Job> {

    int deadline;
    int profit;

    public Job(int deadline, int profit) {
        this.deadline = deadline;
        this.profit = profit;
    }


    @Override
    public int compareTo(Job j) {
        int result = j.profit - this.profit;

        if(result == 0) return this.deadline - j.deadline;

        return result;
    }
}
