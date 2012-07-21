import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
  Thread-safe undirected graph.
*/
public class graph {
    private String name;
    private final Set<node> nodes;

    public graph(final String name) {
        if (name == null) {
            throw new IllegalArgumentException("graph name is null");
        }
        this.name = name;
        this.nodes = new HashSet<>();
    }

    public node node(final node n) {
        if (n == null) {
            throw new IllegalArgumentException("n cannot be null");
        }

        if (nodes.contains(n)) {
            throw new IllegalStateException("n already existed, not adding");
        }

        nodes.add(n);
        return n;
    }

    public Iterator<node> allnodes() {
        return Collections.unmodifiableSet(nodes).iterator();
    }

    public class node {
        private int id;
        private String label;
        private final Map<node, Integer> adjmap = new HashMap<>();

        public node(final int id, final String label) {
            this.id = id;
            this.label = label;
        }

        public int getId() {
            return id;
        }

        public String getLabel() {
            return label;
        }

        public node to(final node n) {
            return to(n, null);
        }

        public node to(final node n, final Integer weight) {
            adjmap.put(n, weight);
            return n;
        }

        public Iterator<node> adjacents() {
            return Collections.unmodifiableSet(adjmap.keySet()).iterator();
        }

        @Override
        public int hashCode() {
            return id;
        }

        @Override
        public boolean equals(final Object o) {
            if (o == null || ! (o instanceof node)) {
                return false;
            }

            return id == ((node) o).id;
        }
    }

    public static void main(final String[] args) {
        final graph g = new graph("cities");
        node portland = g.new node(1, "Portland, OR");
        node austin = g.new node(2, "Austin, TX");
        node chicago = g.new node(3, "Chicago, IL");
        node pittsburgh = g.new node(4, "Pittsburgh, PA");
        node boston = g.new node(5, "Boston, MA");

        g.node(portland).to(austin).to(chicago).to(pittsburgh).to(boston);
    }
}
