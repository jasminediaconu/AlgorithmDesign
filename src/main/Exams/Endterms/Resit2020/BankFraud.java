package main.Exams.Endterms.Resit2020;

public class BankFraud {
    /**
     * You should implement this method.
     * @param n the number of bank cards
     * @param cards the bank cards at indices 1 through n, note that you should ignore index 0!
     * @return true iff more than n/2 of the bankcards are equivalent.
     */
    public static boolean bankScams(int n, Card[] cards) {
        // 1. Base case
        if(n < 2) return false;

        return bankFraud(n, cards, 1, n) != null ? true : false;
    }

    public static Card bankFraud(int n, Card[] cards, int low, int high) {
        if(low == high) return cards[low];

        int m = (low + high) / 2;
        // 1. Split into left & right array
        Card left = bankFraud(n, cards, low, m);
        Card right = bankFraud(n, cards, m+1, high);

        // 2. findMajority in left
        if(left != null) {
            int leftCount = findEquivalent(cards, left, low, high);
            if(leftCount > (high - low + 1)/2) return left;
        }

        // 3. findMajority in right
        else if(right != null) {
            int rightCount = findEquivalent(cards, right, low, high);
            if(rightCount > (high - low + 1)/2) return right;
        }

        return null;
    }

    public static int findEquivalent(Card[] cards, Card current, int low, int high) {
        int count = 0;

        for(int i = low; i <= high; i++) {
            if(current.isEquivalent(cards[i])) count += 1;
        }

        return count;
    }



    /*
       A class you may find useful to keep track of three pieces of data together.
       Although our reference solution uses it, there is no obligation to use it!
       */
    static class Triple {

        int cnt;

        int index;

        Card card;

        public Triple(int cnt, int index, Card card) {
            this.cnt = cnt;
            this.index = index;
            this.card = card;
        }
    }
}
