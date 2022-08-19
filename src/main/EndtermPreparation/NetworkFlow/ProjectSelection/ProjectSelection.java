package main.EndtermPreparation.NetworkFlow.ProjectSelection;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ProjectSelection {

    public static boolean solve(InputStream in) {
        Scanner sc = new Scanner(in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        ArrayList<Node> nodes = new ArrayList<>();

        Node source = new Node("Source");
        Node sink = new Node("Sink");

        HashMap<Node, ArrayList<String>> members = new HashMap<>();
        HashMap<Node, ArrayList<String>> jobs = new HashMap<>();

        HashMap<Node, Integer> hoursRequired = new HashMap<>();

        for(int i = 0; i < n; i++) {

            String name = sc.next();
            int t_i = sc.nextInt();
            int n_skills = sc.nextInt();

            Node member = new Node(name);

            nodes.add(member);

            source.addEdge(member, t_i);

            ArrayList<String> skills = new ArrayList<>();

            for(int j = 0; j < n_skills; j++) skills.add(sc.next());

            members.put(member, skills);
        }

        for(int i = 0; i < m; i++) {

            String name = sc.next();
            int p_i = sc.nextInt();
            int n_skills = sc.nextInt();

            Node job = new Node(name);

            nodes.add(job);

            job.addEdge(sink, p_i);

            ArrayList<String> skills = new ArrayList<>();

            for(int j = 0; j < n_skills; j++) skills.add(sc.next());

            hoursRequired.put(job, p_i);
            jobs.put(job, skills);
        }

        sc.close();

        for(Node job : jobs.keySet()) {
            ArrayList<String> requiredSkills = jobs.get(job);
            for(Node member : members.keySet()) {
                ArrayList<String> skills = members.get(member);
                if (skills.containsAll(requiredSkills)) {
                    member.addEdge(job, hoursRequired.get(job));
                }
            }
        }

        nodes.add(source);
        nodes.add(sink);

        Graph g = new Graph(nodes, source, sink);

        MaxFlow.maximizeFlow(g);

        int maxFlow = 0;
        for(Integer value : hoursRequired.values()) maxFlow += value;


        int currFlow = 0;
        for(Edge e : source.getEdges()) currFlow += e.flow;


        return currFlow == maxFlow ? true : false;
    }
}
