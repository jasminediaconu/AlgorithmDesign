package main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GraphGenerator {
  public static void main(String[] args) {
    if (args.length != 5) {
      System.err.println("Usage: java GraphGenerator <n> <m> <c> <s> <t>\n" +
          "  n = number of vertices\n" +
          "  m = number of edges\n" +
          "  c = maximum edge weight\n" +
          "  s = starting vertex\n" +
          "  t = exit vertex");
      System.exit(2);
    }

    int n = Integer.parseInt(args[0]);
    int m = Integer.parseInt(args[1]);
    int c = Integer.parseInt(args[2]);
    int s = Integer.parseInt(args[3]);
    int t = Integer.parseInt(args[4]);

    if (m > n * (n - 1) / 2) {
      System.err.println("Too many edges (or too few nodes)!");
      System.err.println("With " + n + " nodes, at most " + (n * (n - 1) / 2) + " edges are possible.");
      System.exit(2);
    }
    if (s < 0 || s > n) {
      System.err.println("The starting vertex is invalid!");
      System.err.println("It must be between 1 and n (inclusive).");
      System.exit(2);
    }
    if (t < 0 || t > n) {
      System.err.println("The exit vertex is invalid!");
      System.err.println("It must be between 1 and n (inclusive).");
      System.exit(2);
    }

    int[] edgeIndices = new int[n * (n - 1) / 2];
    for (int i = 0; i < edgeIndices.length; i++) {
      edgeIndices[i] = i;
    }
    shuffleArray(edgeIndices);
    Random rnd = new Random();

    try {
      FileWriter myWriter = new FileWriter("src/assets/output.txt");
      String firstRow = n + " " + m + " " + s + " " + t + "\n";
      myWriter.write(firstRow);
      for (int i = 0; i < m; i++) {
        int row = (int) Math.floor((1 + Math.sqrt(1 + 8 * edgeIndices[i])) / 2.); // Inverse of i = n*(n-1)/2
        int col = edgeIndices[i] - row * (row - 1) / 2;
        int node1 = n - row;
        int node2 = n - col;
        //System.out.printf("%d %d %d\n", n - row - 1 , n - col - 1, rnd.nextInt(c));
        myWriter.write(node1 + " " + node2 + " " + rnd.nextInt(c) + "\n");
      }
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }


  }

  // Implementing Fisher–Yates shuffle
  static void shuffleArray(int[] arr) {
    Random rnd = new Random();
    for (int i = arr.length - 1; i > 0; i--) {
      int index = rnd.nextInt(i + 1);
      // Simple swap
      int a = arr[index];
      arr[index] = arr[i];
      arr[i] = a;
    }
  }
}
