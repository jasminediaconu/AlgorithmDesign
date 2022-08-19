package main.Exams.Midterms.Midterm2019;

import java.util.*;

public class PresentationTime {

    /**
     *  You should implement this method.
     *  @param n the number of speakers
     *  @param presenterNames the names of the presenters p_1 through p_n. Note you should only use entries presenterNames[1] up to and including presenterNames[n].
     *  @param startTimes the start times of the presentations s_1 through s_n. Note you should only use entries startTimes[1] up to and including startTimes[n].
     *  @param endTimes the end times of the presentations e_1 through e_n. Note you should only use entries endTimes[1] up to and including endTimes[n].
     *  @return a largest possible set of presenters whose presentation we can attend.
     */
    public static Set<String> whatPresentations(int n, String[] presenterNames, int[] startTimes, int[] endTimes) {
        LinkedList<Presenter> presenters = new LinkedList<>();
        for(int i = 1; i <= n; i++) presenters.add(new Presenter(presenterNames[i], startTimes[i], endTimes[i]));
        Collections.sort(presenters);

        Set<String> result = new HashSet<>();

        int lastEnd = 0;
        for(Presenter p : presenters) {
            if(p.startTime >= lastEnd) {
                lastEnd = p.endTime;
                result.add(p.name);
            }
        }

        return result;
    }

}

class Presenter implements Comparable<Presenter> {

    String name;
    int startTime;
    int endTime;

    public Presenter(String name, int startTime, int endTime) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public int compareTo(Presenter o) {
        return this.endTime - o.endTime;
    }
}
