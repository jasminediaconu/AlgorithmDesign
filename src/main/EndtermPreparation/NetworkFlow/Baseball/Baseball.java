package main.EndtermPreparation.NetworkFlow.Baseball;

import java.io.InputStream;
import java.util.*;

public class Baseball {
    /**
     * Returns true if team x can still win the Cup.
     */
    public static boolean solve(InputStream in) {
        Scanner sc = new Scanner(in);

        Node source = new Node(-1);
        Node sink = new Node(-2);

        int m = sc.nextInt();

        int team = sc.nextInt();
        int maxWins = sc.nextInt();
        int gamesLeft = sc.nextInt();
        maxWins += gamesLeft;

        for(int i = 0; i < gamesLeft; i++) sc.nextInt();

        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(source);

        for(int i = 1; i <= m; i++) nodes.add(new Node(i));

        int matchId = m +1;

        for(int i = 1; i < m; i++) {
            int player = sc.nextInt();
            int wins = sc.nextInt();
            int left = sc.nextInt();

            Node current = nodes.get(player);

            // We already lost
            if (maxWins < wins) return false;

            current.addEdge(sink, maxWins - wins);

            HashMap<Integer, Integer> matches = new HashMap<>();

            for (int j = 0; j < left; j++) {
                int vs = sc.nextInt();
                if (vs < player || vs == team) continue;
                else {
                    int n = matches.getOrDefault(vs, 0);
                    matches.put(vs, n + 1);
                }
            }

            for (Integer vs : matches.keySet()) {
                Node match = new Node(nodes.size());
                source.addEdge(match, matches.get(vs));
                match.addEdge(current, Integer.MAX_VALUE);
                match.addEdge(nodes.get(vs), Integer.MAX_VALUE);
            }

         }

        nodes.add(sink);
        Graph g = new Graph(nodes, source, sink);

        MaxFlow.maximizeFlow(g);

        for(Edge e : g.getSink().getEdges()) {
            if(e.backwards.getFlow() < e.getCapacity()) return false;
        }

        return true;
    }


    public static boolean solveWithParameters() {
        return false;
    }
}




//    int  m = sc.nextInt();
//
//    Node source = new Node(0);
//
//    Node first = new Node(sc.nextInt());
//    int teamWins = sc.nextInt();
//    int gamesLeft = sc.nextInt();
//    int maxWins = teamWins + gamesLeft;
//
//    Node sink = new Node(first.getId());
//
//    ArrayList<Node> nodes = new ArrayList<>();
//
//        for(int i = 0; i <= m; i++) nodes.add(new Node(i));
//
//                HashMap<Integer, ArrayList<Integer>> mappedMatches = new HashMap<>();
//
//        for(Node n : nodes) mappedMatches.put(n.getId(), new ArrayList<Integer>());
//
//        for(int i = 0; i < gamesLeft; i++) sc.nextInt();
//
//        int matchId = m+2;
//
//        while (sc.hasNextInt()) {
//        // Parse info about the team
//        int id = sc.nextInt();
//        int wins = sc.nextInt();
//        int matchesLeft = sc.nextInt();
//
//        if(wins > maxWins) return false;
//
//        // Get the node
//        Node n = nodes.get(id);
//
//        ArrayList<Integer> teamAgainst = new ArrayList<>();
//
//        // Parse matches left to play
//        for(int i = 0; i < matchesLeft; i++) {
//        int team = sc.nextInt();
//        // Skip the matches played with the target team and the ones already mapped
//        if(team != first.id && !mappedMatches.get(team).contains(n.getId())) {
//        // Create a match node
//        Node currMatch = new Node(matchId);
//        nodes.add(currMatch);
//        matchId++;
//
//        // Source to match
//        source.addEdge(currMatch, 1);
//
//        // Match to teams
//        currMatch.addEdge(nodes.get(team), Integer.MAX_VALUE);
//        currMatch.addEdge(nodes.get(n.getId()), Integer.MAX_VALUE);
//
//        teamAgainst.add(team);
//        mappedMatches.put(n.getId(), teamAgainst);
//        }
//
//        else if(team == first.id) matchesLeft--;
//        }
//
//        int maxFlow = wins + matchesLeft - maxWins;
//
//        if(maxFlow <= 0) return true;
//
//        // Create edge to the sink
//        n.addEdge(sink, maxFlow);
//
//        }
//
//        sc.close();
//
//        Graph g = new Graph(nodes, source, sink);
//
//        // Maximize the flow
//        MaxFlow.maximizeFlow(g);
//
//        for(Edge e : g.getSink().getEdges()) {
//        if(e.flow != e.capacity) return false;
//        }
//
//        return true;