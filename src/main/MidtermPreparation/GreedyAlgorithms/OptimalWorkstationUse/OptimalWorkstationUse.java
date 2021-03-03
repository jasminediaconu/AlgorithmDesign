package main.MidtermPreparation.GreedyAlgorithms.OptimalWorkstationUse;

import java.util.PriorityQueue;

/**
 * A cloud service offers the opportunity for n researchers to submit jobs from workstations in a nearby office
 * building. Access to these workstations is controlled by an operator. This operator needs to unlock the
 * workstations for the researchers who come to run their computations at the cloud service. However, this
 * operator is very lazy. She can unlock the machines remotely from her desk, but does not feel that this menial
 * task matches her qualifications. She therefore decides to ignore the security guidelines and to simply ask the
 * researchers not to lock their workstations when they leave, and then assign new researchers to workstations
 * that are not used any more but that are still unlocked. That way, she only needs to unlock each workstation
 * for the first researcher using it. Unfortunately, unused workstations lock themselves automatically if they are
 * unused for more than m minutes. After a workstation has locked itself, the operator has to unlock it again
 * for the next researcher using it. Given the exact schedule of arriving and leaving researchers, can you tell the
 * operator how many unlockings she may save by asking the researchers not to lock their workstations when
 * they leave and assigning arriving researchers to workstations in an optimal way? You may assume that there
 * are always enough workstations available.
 *
 * You are given start times si and duration di for all 1≤i≤n.
 * Given the following research times and a lock time of 10 minutes:
 */
public class OptimalWorkstationUse {
    public static /**
     * @param n number of researchers
     * @param m number of processes
     * @param start start times of jobs 1 through n. NB: you should ignore start[0]
     * @param duration duration of jobs 1 through n. NB: you should ignore duration[0]
     * @return the number of unlocks that can be saved.
     */
    int solve(int n, int m, int[] start, int[] duration) {
        int unlocksSaved = 0;

        PriorityQueue<Researcher> researchers = new PriorityQueue<>();

        for (int i = 1; i <= n; i++) researchers.add(new Researcher(start[i], start[i] + duration[i]));

        PriorityQueue<Integer> workstations = new PriorityQueue<>();
        workstations.add(researchers.remove().endTime);

        while (!researchers.isEmpty()) {
            Researcher first = researchers.remove();

            int workstation = workstations.remove();

            boolean isAvailable = (first.startTime - workstation) >= 0 ? true : false;
            boolean isUnlocked = (first.startTime - workstation) <= m ? true : false;

            if (isAvailable) {
                workstations.add(first.endTime);
                if (isUnlocked) unlocksSaved++;
            } else {
                workstations.add(workstation);
                workstations.add(first.endTime);
            }
        }

        return unlocksSaved;
}

static class Researcher implements Comparable<Researcher> {
        int startTime;
        int endTime;

        public Researcher(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Researcher r) {
            return this.startTime - r.startTime;
        }
    }
}


