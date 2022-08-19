package DynamicProgramming.ExtraCourse;

import main.EndtermPreparation.DynamicProgramming.ShorthestPaths.GridTraveler;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DynamicProgramming {
    @Test
    public void gridTravel() {
        assertEquals(1, GridTraveler.findMoves(1, 1, new int[1][1]));
        assertEquals(3, GridTraveler.findMoves(2, 3, new int[2][3]));
        assertEquals(3, GridTraveler.findMoves(3, 2, new int[3][2]));
        assertEquals(6, GridTraveler.findMoves(3, 3, new int[3][3]));
        assertEquals(40116600, GridTraveler.findMoves(15, 15, new int[15][15]));
    }

    @Test
    public void gridTravel2() {
        int[][] dp = new int[][] { {0,0,0},{0,1,0},{0,0,0} };
        assertEquals(2, GridTraveler.uniquePathsWithObstacles(dp));
    }

    @Test
    public void dungeon() {
        int[][] dp = new int[][]
                {       { -2, -3, 3},
                        { -5, -10, 1},
                        { 10, 30, -5} };
        assertEquals(7, GridTraveler.calculateMinimumHP(dp));
    }

    @Test
    public void dungeon2() {
        int[][] dp = new int[][]
                { { -2 } };
        assertEquals(3, GridTraveler.calculateMinimumHP(dp));
    }
}
