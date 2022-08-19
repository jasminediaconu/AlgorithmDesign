package main.Exams.Endterms.Resit2020.NegativeCycles;

import java.util.Objects;

public class Town {
    protected int id;

    public Town(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Town))
            return false;
        Town that = (Town) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
