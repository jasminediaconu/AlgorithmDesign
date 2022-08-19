package main.FinalExam.Question3;

import javax.lang.model.type.ArrayType;
import java.util.ArrayList;
import java.util.Set;

/**
 * Foodtrucks used to be a booming business, at least before lockdown. But with lockdown going on, some lecturers have decided that perhaps preparing food every day is at least more fun than preparing all of these exam exercises day in day out. So after lockdown is over, perhaps you can visit “Stefan’s Salad Shop”, “Mathijs’s Macaroni Mall” or “Emir’s Enchilladas Emporium”. Some lecturers may even want to make more than one type of food, or work together on a concept. For example “Stefan and Mathijs’s Sandwiches and Muffins”.
 * Despite leaning their names to their brands, the lecturers have too little money to buy one food truck each. As a result they all have to share one foodtruck where they can change the brand.
 *
 * You are given n lecturers and t days that the foodtruck needs to be staffed. Furthermore we are making plans for a total of m different brands. Allocate lectures to the days under the following conditions:
 */
public class NoMoreExams {
    /**
     * You should implement this method
     *
     * @param n the number of lecturers
     * @param t the number of days
     * @param m the number of brands
     * @param f the number of days a lecturer is willing to work from index _1_ to _n_. You should ignore p[0].
     * @param D the set of brands a lecturer is able to handle from index _1_ to _n_. You should ignore D[0].
     * @param M the set of days on which a brand can sell from index _1_ to _m_. You should ignore M[0].
     * @return true iff there is a way to staff the foodtruck with a lecturer on each of the t days meeting the conditions.
     */
    public static boolean servingFoodAndChangingLives(int n, int t, int m, int[] f,
                                                      Set<Integer>[] D, Set<Integer>[] M) {

        int daysNeeded = 0;
        for(int i = 1; i <= m; i++) {
            daysNeeded += M[i].size();
        }

        Node source = new Node(-1, -daysNeeded);
        Node sink = new Node(-2, daysNeeded);

        ArrayList<Node> nodes = new ArrayList<>();

        nodes.add(source);

        // Adding lecturers and connecting them to the source
        Node[] lecturers = new Node[n+1];

        for(int i = 1; i <= n; i++) {
            lecturers[i] = new Node(i, 0);
            nodes.add(lecturers[i]);
            source.addEdge(lecturers[i], 0, f[i]);
        }

        // Adding brands and connecting them to the sink
        Node[] brands = new Node[m+1];

        for(int i = 1; i <= m; i++) {
            brands[i] = new Node(i+n, 0);
            nodes.add(brands[i]);
            brands[i].addEdge(sink, 0, M[i].size());
        }

        // Connecting lecturers to the brands they want to work on
        for(int i = 1; i <= n; i++) {
            for(Integer brand : D[i]) {
                lecturers[i].addEdge(brands[brand], 0, 4);
            }
        }

        nodes.add(sink);

        Graph g = new Graph(nodes, source, sink);

        return g.hasCirculation();
    }
}
