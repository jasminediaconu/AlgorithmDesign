package main.Exams.Endterms.Endterm2020.Quest;

import java.util.Objects;

public class Node {

        protected int id;

        /**
         *  Create a new node
         *  @param id: Id for the node.
         */
    public Node(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public boolean equals(Object other) {
            if (other instanceof Node) {
                Node that = (Node) other;
                return this.id == that.id;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
}

