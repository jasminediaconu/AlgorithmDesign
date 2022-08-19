package Exams.Midterms.Midterm2019;

import main.Exams.Midterms.Midterm2019.PresentationTime;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PresentationTimeTest {

    @Test
    public void example() {
        int n = 4;
        String[] presenters = { "", "Mia", "Phoenix", "Maya", "Miles" };
        int[] startTimes = { 0, 12, 1, 17, 13 };
        int[] endTimes = { 0, 18, 11, 20, 15 };
        Set<String> result = new HashSet<>();
        result.add("Phoenix");
        result.add("Maya");
        result.add("Miles");
        assertEquals(result, PresentationTime.whatPresentations(n, presenters, startTimes, endTimes));
    }
}
