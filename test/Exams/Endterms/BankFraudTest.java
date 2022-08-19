package Exams.Endterms;

import main.Exams.Endterms.Resit2020.BankFraud;
import main.Exams.Endterms.Resit2020.Card;
import main.MidtermPreparation.DivideAndConquer.MergeSort;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class BankFraudTest {
    class PublicTestCard implements Card {

        int id;

        public PublicTestCard(int id) {
            this.id = id;
        }

        @Override
        public boolean isEquivalent(Card other) {
            if (other instanceof PublicTestCard) {
                return this.id == ((PublicTestCard) other).id;
            }
            return false;
        }
    }

    @Test
    public void onlyTwoCards() {
        int n = 2;
        Card[] cards = new Card[n + 1];
        cards[1] = new PublicTestCard(0);
        cards[2] = new PublicTestCard(1);
        Assert.assertFalse(BankFraud.bankScams(n, cards));
        cards[2] = new PublicTestCard(0);
        Assert.assertTrue(BankFraud.bankScams(n, cards));
    }

    @Test
    public void example() {
        int n = 20;
        Card[] cards = new Card[n + 1];
        for (int i = 1; i <= n; i++) {
            cards[i] = new PublicTestCard(i);
        }
        // They are all different
        Assert.assertFalse(BankFraud.bankScams(n, cards));
        for (int i = 1; i <= n / 2; i++) {
            cards[i] = new PublicTestCard(1);
        }
        // Exactly half are equivalent now.
        Assert.assertFalse(BankFraud.bankScams(n, cards));
        cards[n] = new PublicTestCard(1);
        // Exactly half + 1 are equivalent now.
        Assert.assertTrue(BankFraud.bankScams(n, cards));
    }

    @Test
    public void example2() {
        int[] input = { 4, 2, 5, 1, 3 };
        MergeSort.mergeSort(input);
        assertArrayEquals(new int[] { 1, 2, 3, 4, 5 }, input);
    }
}