package backend.model.solver;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Graph {

    private final List<Edge> edges;
    private final Set<Vertex> vertices;

    public Graph(List<Edge> edges) {
        this.edges = edges;
        this.vertices = edges.stream()
                .flatMap(e -> Stream.of(e.getFrom(), e.getTo()))
                .collect(Collectors.toSet());
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public Set<Vertex> getVertices() {
        return vertices;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "edges=" + edges +
                ", vertices=" + vertices +
                '}';
    }
}
