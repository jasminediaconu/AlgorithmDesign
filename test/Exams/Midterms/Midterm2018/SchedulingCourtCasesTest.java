package Exams.Midterms.Midterm2018;

import main.Exams.Midterms.Midterm2018.SchedulingCourtCases;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class SchedulingCourtCasesTest {
    @Test
    public void schedulingCourtCases() throws IOException {
        InputStream in = new FileInputStream("src/assets/exams/courtCases.txt");
        assertEquals("33", SchedulingCourtCases.run(in));
    }

    @Test
    public void schedulingCourtCases2() throws IOException {
        InputStream in = new FileInputStream("src/assets/exams/n_1000_m_10.txt");
        assertEquals("10420", SchedulingCourtCases.run(in));
    }
}
