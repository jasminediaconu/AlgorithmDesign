package MidtermPreparation.DivindeAndConquer;

import main.MidtermPreparation.DivideAndConquer.CountInversions.CountInversions;
import main.MidtermPreparation.DivideAndConquer.ExploringAMaze.BinaryTree;
import main.MidtermPreparation.DivideAndConquer.ExploringAMaze.ExploringAMaze;
import main.MidtermPreparation.DivideAndConquer.SmallIsBetter.Equation;
import main.MidtermPreparation.DivideAndConquer.SmallIsBetter.QuadraticEquation;
import main.MidtermPreparation.DivideAndConquer.SmallIsBetter.SmallIsBetter;
import main.MidtermPreparation.DivideAndConquer.SnuggleInTight.Point;
import main.MidtermPreparation.DivideAndConquer.SnuggleInTight.SnuggleInTight;
import main.MidtermPreparation.DivideAndConquer.SortingThroughMerging.SortingThroughMerging;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {

    @Test
    public void exploringAMaze() {
        ExploringAMaze s = new ExploringAMaze();
        BinaryTree tree = new BinaryTree(42, new BinaryTree(1337), new BinaryTree(39));
        assertTrue(s.search(tree, 42));
        assertFalse(s.search(tree, 100));
    }

    @Test
    public void exploringAMaze2() {
        ExploringAMaze s = new ExploringAMaze();
        BinaryTree tree = new BinaryTree(42, new BinaryTree(1337), new BinaryTree(39));
        assertTrue(s.search(tree, 39));
        assertFalse(s.search(tree, 100));
    }

    @Test
    public void sortingThroughMerging() {
        int[] input = { 4, 2, 5, 1, 3 };
        new SortingThroughMerging().sort(input);
        assertArrayEquals(new int[] { 1, 2, 3, 4, 5 }, input);
    }

    @Test
    public void smallIsBetter() {
        Equation eq1 = new QuadraticEquation(0, 8,
                -240);
        Equation eq2 = new QuadraticEquation(0, 6,
                -156);
        assertEquals(42, SmallIsBetter.findMin(eq1, eq2, 0, 100));
    }

    @Test
    public void smallIsBetter2() {
        Equation eq1 = new QuadraticEquation(0, 28,
                -3240);
        Equation eq2 = new QuadraticEquation(0, 6,
                -106);
        assertEquals(100, SmallIsBetter.findMin(eq1, eq2, 0, 100));
    }

    @Test
    public void smallIsBetter3() {
        Equation eq1 = new QuadraticEquation(0, 28,
                -3240);
        Equation eq2 = new QuadraticEquation(0, 6,
                -106);
        assertEquals(100, SmallIsBetter.findMin(eq2, eq1, 0, 100));
    }

    @Test
    public void smallIsBetter4() {
        Equation eq1 = new QuadraticEquation(0, 8,
                -240);
        Equation eq2 = new QuadraticEquation(0, 6,
                -156);
        assertEquals(100, SmallIsBetter.findMin(eq2, eq1, 0, 100));
    }

    @Test
    public void testTwoPoints() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(1, 2));
        points.add(new Point(4, 6));
        assertEquals(5, SnuggleInTight.closestPair(points), 5e-6);
    }

    @Test
    public void testSmall() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(2, 3));
        points.add(new Point(12, 30));
        points.add(new Point(40, 50));
        points.add(new Point(5, 1));
        points.add(new Point(12, 10));
        points.add(new Point(3, 4));
        assertEquals(1.4142135623730951, SnuggleInTight.closestPair(points), 1e-6);
    }

    @Test
    public void countInversions() {
        int[] input = { 2, 1, 0, 8 };
        assertEquals(3, CountInversions.countInversions(input));
    }

    @Test
    public void countInversions3() {
        int[] input = { 8, 4, 2, 1 };
        assertEquals(6, CountInversions.countInversions(input));
    }

    @Test
    public void countInversionsOneElement() {
        int[] input = { 6 };
        assertEquals(0, CountInversions.countInversions(input));
    }

    @Test
    public void countInversionsTwoElements() {
        int[] input = { 6, 3 };
        assertEquals(1, CountInversions.countInversions(input));
    }

    @Test
    public void countInversions2() {
        int[] input = { 1, 20, 6, 4, 5 };
        assertEquals(5, CountInversions.countInversions(input));
    }

    @Test
    public void countInversions4() {
        int[] input = { 3, 1, 2 };
        assertEquals(2, CountInversions.countInversions(input));
    }


    @Test
    public void countInversions5() {
        int[] input = { 5, 3, 2, 4, 1 };
        assertEquals(8, CountInversions.countInversions(input));
    }


}
