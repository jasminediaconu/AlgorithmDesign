package Exams.Midterms.Midterm2019;

import main.Exams.Midterms.Midterm2019.HowManyOnes;
import main.Exams.Midterms.Midterm2019.Skill;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HowManyOnesTest {
    @Test
    public void example() {
        int solution = 2;
        int n = 3;
        Skill[] skills = new Skill[4];
        skills[1] = new Skill("Binary counting", false);
        skills[2] = new Skill("Huffman encoding", true);
        skills[3] = new Skill("Exchange arguments", true);
        assertEquals(solution, HowManyOnes.numberOfCompletedSkills(n, skills));
    }
}
