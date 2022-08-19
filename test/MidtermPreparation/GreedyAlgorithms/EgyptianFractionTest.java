package MidtermPreparation.GreedyAlgorithms;

import main.MidtermPreparation.GreedyAlgorithms.EgyptianFraction.EgyptianFraction;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EgyptianFractionTest {
    @Test
    public void egyptianFraction() {

        ArrayList<Double> result = new ArrayList<>();

        result.add(1/2.0);
        result.add(1/6.0);

        assertEquals(result, EgyptianFraction.egyptianFraction(2, 3));
    }

    @Test
    public void egyptianFraction2() {

        ArrayList<Double> result = new ArrayList<>();

        result.add(1/3.0);
        result.add(1/11.0);
        result.add(1/231.0);

        assertEquals(result, EgyptianFraction.egyptianFraction(6, 14));
    }

    @Test
    public void egyptianFraction3() {

        ArrayList<Double> result = new ArrayList<>();

        result.add(1/2.0);
        result.add(1/3.0);
        result.add(1/12.0);
        result.add(1/156.0);

        assertEquals(result, EgyptianFraction.egyptianFraction(12, 13));
    }

}