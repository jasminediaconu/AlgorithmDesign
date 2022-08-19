package Exams.Midterms.Midterm2018;

import main.Exams.Midterms.Midterm2018.ParetoOptima;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParetoOptimaTest {
    @Test
    public void example() {
        List<ParetoOptima.Solution> list = new ArrayList<>();
        list.add(new ParetoOptima.Solution(10, 1));
        list.add(new ParetoOptima.Solution(9, 7));
        list.add(new ParetoOptima.Solution(1, 6));
        list.add(new ParetoOptima.Solution(5, 3));
        List<ParetoOptima.Solution> result = ParetoOptima.getParetoOptima(list);
        assertEquals(2, result.size());
        assertTrue(result.contains(new ParetoOptima.Solution(1, 6)));
        assertTrue(result.contains(new ParetoOptima.Solution(9, 7)));
    }

    @Test
    public void example2() {
        List<ParetoOptima.Solution> list = new ArrayList<>();
        list.add(new ParetoOptima.Solution(1, 5));
        list.add(new ParetoOptima.Solution(4, 7));
        list.add(new ParetoOptima.Solution(2, 6));
        List<ParetoOptima.Solution> result = ParetoOptima.getParetoOptima(list);
        assertEquals(3, result.size());
    }
}
