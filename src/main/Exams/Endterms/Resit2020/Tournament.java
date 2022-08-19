package main.Exams.Endterms.Resit2020;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * You are organising a virtual boardgame event to interact with your peers
 * in these interesting times. To that end all of your n participants need to create
 * an account at BoardgameArena, the platform you will be using. Since not all of
 * participants are CSE students, some take a little longer than others to create this
 * account; let ai be the time required for participant 1≤i≤n to create their account.
 * After they have created this account, you also need to talk to them to hear what
 * board games they have experience with. It is important that happens after they created
 * an account, as they need to look at the list of games available on the platform.
 * Furthermore this time bi spent talking differs per person as some are more talkative
 * than others.
 *
 * Since everyone can create an account in parallel, but they all need to talk to you
 * individually, you realise you can use a greedy algorithm to determine an optimal
 * order that minimises the total time required to get everyone ready for the tournament.
 *
 * Implement the function boardgameTime which given the times described above, returns
 * the time at which the last person will be ready to start the tournament using an
 * optimal order of participants.
 *
 */
public class Tournament {
    /**
     *  You should implement this method.
     *  @param n the number of participants.
     *  @param a an array of size n+1, containing the account creation a_1 through a_n. You should ignore a[0].
     *  @param b an array of size n+1, containing the interview times b_1 through b_n. You should ignore b[0].
     *  @return The minimum latest end time.
     */
    public static int boardgameTime(int n, int[] a, int[] b) {
        ArrayList<Player> players = new ArrayList<>();

        for(int i = 1; i <= n; i++) players.add(new Player(a[i], b[i]));

        Collections.sort(players);

        int minLatency = players.get(0).setupTime + players.get(0).talkTime;

        for(int i = 1; i < n; i++) {
            Player currentPlayer = players.get(i);
            if(currentPlayer.setupTime > minLatency)
                minLatency = currentPlayer.setupTime + currentPlayer.talkTime;
            else minLatency += currentPlayer.talkTime;

        }

        return minLatency;
    }
}

class Player implements Comparable<Player> {
    int setupTime;
    int talkTime;

    public Player(int setupTime, int talkTime) {
        this.setupTime = setupTime;
        this.talkTime = talkTime;
    }

    @Override
    public int compareTo(Player p) {
        return this.setupTime - p.setupTime;
    }
}
