package main.Exams.Endterms.Resit2020;

import java.util.Set;

public class SpecTestCard implements Card {

    public static int counter = 0;

    int id;

    Set<Integer> equivalent;

    public SpecTestCard(int id, Set<Integer> equivalent) {
        this.id = id;
        this.equivalent = equivalent;
    }

    @Override
    public boolean isEquivalent(Card other) {
        counter++;
        if (other == this) {
            return true;
        }
        if (other instanceof SpecTestCard) {
            return this.equivalent.contains(((SpecTestCard) other).id);
        }
        return false;
    }
}