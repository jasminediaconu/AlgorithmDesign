package Exams.Midterms.Midterm2018;

import main.Exams.Midterms.Midterm2018.DominatedSolutions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DominatedSolutionsTest {
    @Test
    public void example() {
        List<DominatedSolutions.Solution> list = new ArrayList<>();
        list.add(new DominatedSolutions.Solution(10, 1));
        list.add(new DominatedSolutions.Solution(9, 7));
        list.add(new DominatedSolutions.Solution(1, 8));
        list.add(new DominatedSolutions.Solution(5, 5));
        DominatedSolutions.Solution sol = new DominatedSolutions.Solution(4, 4);
        List<DominatedSolutions.Solution> result = DominatedSolutions.undominatedBy(sol, list);
        assertEquals(3, result.size());
        assertTrue(result.contains(new DominatedSolutions.Solution(1, 8)));
        assertTrue(result.contains(new DominatedSolutions.Solution(9, 7)));
        assertTrue(result.contains(new DominatedSolutions.Solution(5, 5)));
    }

    @Test
    public void example2() {
        List<DominatedSolutions.Solution> list = new ArrayList<>();
        list.add(new DominatedSolutions.Solution(4, 3));
        list.add(new DominatedSolutions.Solution(5, 2));
        DominatedSolutions.Solution sol = new DominatedSolutions.Solution(2, 3);
        List<DominatedSolutions.Solution> result = DominatedSolutions.undominatedBy(sol, list);
        assertEquals(0, result.size());
    }

    @Test
    public void example3() {
        List<DominatedSolutions.Solution> list = new ArrayList<>();
        list.add(new DominatedSolutions.Solution(3, 3));
        list.add(new DominatedSolutions.Solution(6, 4));
        list.add(new DominatedSolutions.Solution(4, 7));
        DominatedSolutions.Solution sol = new DominatedSolutions.Solution(1, 5);
        List<DominatedSolutions.Solution> result = DominatedSolutions.undominatedBy(sol, list);
        assertEquals(1, result.size());
    }

    @Test
    public void example4() {
        List<DominatedSolutions.Solution> list = new ArrayList<>();
        list.add(new DominatedSolutions.Solution(1, 5));
        list.add(new DominatedSolutions.Solution(6, 4));
        list.add(new DominatedSolutions.Solution(4, 7));
        DominatedSolutions.Solution sol = new DominatedSolutions.Solution(3, 3);
        List<DominatedSolutions.Solution> result = DominatedSolutions.undominatedBy(sol, list);
        assertEquals(3, result.size());
    }

    @Test
    public void itself() {
        List<DominatedSolutions.Solution> list = new ArrayList<>();
        list.add(new DominatedSolutions.Solution(1, 5));
        DominatedSolutions.Solution sol = new DominatedSolutions.Solution(1, 5);
        List<DominatedSolutions.Solution> result = DominatedSolutions.undominatedBy(sol, list);
        assertEquals(1, result.size());
    }
}
