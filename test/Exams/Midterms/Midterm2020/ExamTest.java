package Exams.Midterms.Midterm2020;
import main.Exams.Midterms.Midterm2020.Connection;
import main.Exams.Midterms.Midterm2020.Question1;

import main.Exams.Midterms.Midterm2020.Question2;
import main.Exams.Midterms.Midterm2020.Question3;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExamTest {

  /* Doing job 1 first: 5*1 + 9*3 = 32
       Doing job 2 first: 4*3 + 9*1 = 21 */
    @Test(timeout = 100)
    public void example() {
        int n = 2;
        int[] t = { 0, 5, 4 };
        int[] p = { 0, 1, 3 };
        assertEquals(21, Question1.prioritisingExercises(n, t, p));
    }


    @Test
    public void exampleOneStudent() {
        int n = 2;
        Set<Connection> connections = new HashSet<>();
        connections.add(new Connection(1, 2, 0.8));
        assertEquals(0.2, Question2.minimalLargestFailureChance(n, connections), 1E-6);
    }

    @Test
    public void exampleTwoStudents() {
        int n = 3;
        Set<Connection> connections = new HashSet<>();
        connections.add(new Connection(1, 2, 0.8));
        connections.add(new Connection(1, 3, 0.7));
        assertEquals(0.3, Question2.minimalLargestFailureChance(n, connections), 1E-6);
    }

    @Test(timeout = 100)
    public void exampleTwoStudentsIndirect() {
        int n = 3;
        Set<Connection> connections = new HashSet<>();
        connections.add(new Connection(1, 2, 0.8));
        connections.add(new Connection(1, 3, 0.7));
        connections.add(new Connection(2, 3, 0.99));
        assertEquals(0.208, Question2.minimalLargestFailureChance(n, connections), 1E-6);
    }

    // see assignment text
    @Test(timeout = 100)
    public void text_example() {
        int n = 4;
        int[] h = { 0, 5, 10, 12, 10 };
        assertEquals(7, Question3.findBestTrainingBruteForce(n, h));
    }

    @Test(timeout = 100)
    public void text_example_dc() {
        int n = 4;
        int[] h = { 0, 5, 10, 12, 10 };
        assertEquals(7, Question3.findBestTrainingDivideAndConquer(n, h));
    }

    // So 3 is the best
    @Test(timeout = 100)
    public void small_example() {
        int n = 3;
        int[] h = { 0, 1, 4, 3 };
        assertEquals(3, Question3.findBestTrainingBruteForce(n, h));
    }

    @Test(timeout = 100)
    public void small_example_dc() {
        int n = 3;
        int[] h = { 0, 1, 4, 3 };
        assertEquals(3, Question3.findBestTrainingDivideAndConquer(n, h));
    }

    // The best one is [5,10,12,11,13]
    @Test(timeout = 100)
    public void large_example() {
        int n = 8;
        int[] h = { 0, 6, 5, 10, 12, 11, 13, 10, 2 };
        assertEquals(8, Question3.findBestTrainingBruteForce(n, h));
    }

    @Test(timeout = 100)
    public void large_example_dc() {
        int n = 8;
        int[] h = { 0, 6, 5, 10, 12, 11, 13, 10, 2 };
        assertEquals(8, Question3.findBestTrainingDivideAndConquer(n, h));
    }
}
